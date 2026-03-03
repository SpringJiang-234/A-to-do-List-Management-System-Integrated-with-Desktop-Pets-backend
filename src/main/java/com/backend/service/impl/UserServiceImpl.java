package com.backend.service.impl;

import com.backend.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backend.bean.PageBean;
import com.backend.domain.dto.LoginDTO;
import com.backend.domain.dto.RegisterDTO;
import com.backend.domain.entity.User;
import com.backend.domain.query.UserQuery;
import com.backend.service.UserService;
import com.backend.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageBean<User> getPage(UserQuery userQuery) {
        final Integer pageNum = userQuery.getPageNum();
        final Integer pageSize = userQuery.getPageSize();
        final Page<User> page = PageHelper.startPage(pageNum, pageSize);
        userMapper.selectWithCondition(userQuery);
        final int total = userMapper.countByCondition(userQuery);
        return PageBean.page2pageBean(page, (long) total);
    }


    @Override
    public User getById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(User user) {
        // 如果密码不为空，对密码进行加密处理
        if (user.getPasswordHash() != null && !user.getPasswordHash().isEmpty()) {
            user.setPasswordHash(PasswordUtil.hashPassword(user.getPasswordHash()));
        }
        return userMapper.insertOrUpdateSelective(user);
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(String ids) {
        final String[] split = ids.split(",");
        return userMapper.deleteByPrimaryKeyIn(split);
    }

    @Override
    public int batchInsert(List<User> userList) {
        return userMapper.batchInsertSelectiveUseDefaultForNull(userList);
    }

    @Override
    public List<User> getAll(UserQuery userQuery) {
        return userMapper.selectWithCondition(userQuery);
    }

    @Override
    public int isAccountExist(String account) {
        return userMapper.countByAccount(account);
    }

    @Override
    public int register(RegisterDTO registerDTO) {
        if (registerDTO == null || registerDTO.getAccount() == null || registerDTO.getPassword() == null) {
            return 0;
        }
        
        if (isAccountExist(registerDTO.getAccount()) > 0) {
            return 0;
        }
        
        try {
            User user = new User();
            user.setAccount(registerDTO.getAccount());
            user.setPasswordHash(PasswordUtil.hashPassword(registerDTO.getPassword()));
            user.setNickname(registerDTO.getUsername());
            user.setType(2); // 默认类型 用户类型：1-管理员 2-普通用户
            user.setStatus(1); // 默认状态 状态：1-正常 2-已注销
            user.setGender(3); // 默认性别 性别：1-男 2-女 3-未知
            return userMapper.insertSelective(user);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public User login(LoginDTO loginDTO) {
        UserQuery query = new UserQuery();
        query.setAccount(loginDTO.getAccount());
        List<User> users = userMapper.selectWithCondition(query);
        if (users.isEmpty()) {
            return null;
        }
        User user = users.get(0);
        
        // 检查密码哈希值格式是否正确
        try {
            if (PasswordUtil.checkPassword(loginDTO.getPassword(), user.getPasswordHash())) {
                return user;
            }
        } catch (NumberFormatException e) {
            // 如果密码哈希值格式不正确，重新生成哈希值并更新到数据库
            String newHashedPassword = PasswordUtil.hashPassword(loginDTO.getPassword());
            user.setPasswordHash(newHashedPassword);
            userMapper.updateByPrimaryKeySelective(user);
            return user;
        }
        return null;
    }

    @Override
    public User adminLogin(LoginDTO loginDTO) {
        UserQuery query = new UserQuery();
        query.setAccount(loginDTO.getAccount());
        List<User> users = userMapper.selectWithCondition(query);
        if (users.isEmpty()) {
            return null;
        }
        User user = users.get(0);
        
        // 检查用户类型是否为管理员（type=1）
        if (user.getType() != 1) {
            return null;
        }
        
        // 检查密码哈希值格式是否正确
        try {
            if (PasswordUtil.checkPassword(loginDTO.getPassword(), user.getPasswordHash())) {
                return user;
            }
        } catch (NumberFormatException e) {
            // 如果密码哈希值格式不正确，重新生成哈希值并更新到数据库
            String newHashedPassword = PasswordUtil.hashPassword(loginDTO.getPassword());
            user.setPasswordHash(newHashedPassword);
            userMapper.updateByPrimaryKeySelective(user);
            return user;
        }
        return null;
    }

    @Override
    public int cancelById(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return 0;
        }
        user.setStatus(2);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cancelByIds(String ids) {
        final String[] split = ids.split(",");
        int count = 0;
        for (String idStr : split) {
            Long id = Long.parseLong(idStr.trim());
            count += cancelById(id);
        }
        return count;
    }

    @Override
    public int countTotalUsers() {
        return userMapper.countTotalUsers();
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getNewUsersTrend() {
        // 生成近七天的日期列表
        java.util.List<java.util.Map<String, Object>> result = new java.util.ArrayList<>();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        
        // 循环获取近七天的日期，每个日期查询新用户总数
        for (int i = 6; i >= 0; i--) {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.add(java.util.Calendar.DAY_OF_YEAR, -i);
            String dateStr = sdf.format(calendar.getTime());
            
            // 调用mapper方法获取当天的新增用户数
            int count = userMapper.countNewUsersByDate(dateStr);
            
            java.util.Map<String, Object> dayData = new java.util.HashMap<>();
            dayData.put("date", dateStr);
            dayData.put("count", count);
            result.add(dayData);
        }
        
        return result;
    }
}
