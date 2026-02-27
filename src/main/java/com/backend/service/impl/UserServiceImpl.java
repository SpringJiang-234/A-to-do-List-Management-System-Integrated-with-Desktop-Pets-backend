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
}
