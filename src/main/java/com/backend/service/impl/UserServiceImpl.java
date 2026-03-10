package com.backend.service.impl;

import com.backend.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backend.bean.PageBean;
import com.backend.domain.dto.LoginDTO;
import com.backend.domain.dto.RegisterDTO;
import com.backend.domain.entity.User;
import com.backend.domain.entity.DesktopPet;
import com.backend.domain.query.UserQuery;
import com.backend.domain.query.DesktopPetQuery;
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
    
    @Autowired
    private com.backend.mapper.DesktopPetMapper desktopPetMapper;

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
    @Transactional(rollbackFor = Exception.class)
    public int insertOrUpdate(User user) {
        // 如果密码不为空，对密码进行加密处理
        // 新增用户时，密码是明文，需要加密
        // 编辑用户时，如果密码字段不为空，说明用户修改了密码，也需要加密
        if (user.getPasswordHash() != null && !user.getPasswordHash().isEmpty()) {
            // 检查是否已经是BCrypt哈希格式（以$2a$或$2b$开头，且长度为60）
            boolean isBcryptHash = (user.getPasswordHash().startsWith("$2a$") || user.getPasswordHash().startsWith("$2b$")) 
                && user.getPasswordHash().length() == 60;
            
            if (!isBcryptHash) {
                user.setPasswordHash(PasswordUtil.hashPassword(user.getPasswordHash()));
            }
        }
        int result = userMapper.insertOrUpdateSelective(user);
        
        if (result > 0 && user.getId() != null) {
            DesktopPetQuery query = new DesktopPetQuery();
            query.setUserId(user.getId());
            List<DesktopPet> existingPets = desktopPetMapper.selectWithCondition(query);
            
            if (existingPets == null || existingPets.isEmpty()) {
                DesktopPet desktopPet = new DesktopPet();
                desktopPet.setUserId(user.getId());
                desktopPet.setNickname("桌宠");
                desktopPet.setEnergy(0);
                desktopPet.setMood(60);
                desktopPet.setIntimacy(0);
                desktopPet.setExp(0);
                desktopPet.setLevel(1);
                desktopPetMapper.insertSelective(desktopPet);
            }
        }
        return result;
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
    @Transactional(rollbackFor = Exception.class)
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
            int result = userMapper.insertSelective(user);
            
            // 如果注册成功，为用户创建桌宠
            if (result > 0 && user.getId() != null) {
                DesktopPet desktopPet = new DesktopPet();
                desktopPet.setUserId(user.getId());
                desktopPet.setNickname("桌宠");
                desktopPet.setEnergy(0);
                desktopPet.setMood(60);
                desktopPet.setIntimacy(0);
                desktopPet.setExp(0);
                desktopPet.setLevel(1);
                desktopPetMapper.insertSelective(desktopPet);
            }
            
            return result;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public User login(LoginDTO loginDTO) {
        System.out.println("========== login 开始 ==========");
        System.out.println("登录账号: " + loginDTO.getAccount());
        System.out.println("登录密码: " + loginDTO.getPassword());
        
        User user = userMapper.selectByAccount(loginDTO.getAccount());
        
        if (user == null) {
            System.out.println("用户不存在");
            return null;
        }
        
        System.out.println("找到用户: " + user.getAccount());
        System.out.println("用户ID: " + user.getId());
        System.out.println("用户类型: " + user.getType());
        System.out.println("用户状态: " + user.getStatus());
        System.out.println("密码哈希: " + user.getPasswordHash());
        
        // 检查密码哈希值格式是否正确
        try {
            boolean passwordMatch = PasswordUtil.checkPassword(loginDTO.getPassword(), user.getPasswordHash());
            System.out.println("密码验证结果: " + passwordMatch);
            if (passwordMatch) {
                System.out.println("登录成功");
                return user;
            }
        } catch (NumberFormatException e) {
            System.out.println("密码哈希格式异常: " + e.getMessage());
            // 如果密码哈希值格式不正确，重新生成哈希值并更新到数据库
            String newHashedPassword = PasswordUtil.hashPassword(loginDTO.getPassword());
            user.setPasswordHash(newHashedPassword);
            userMapper.updateByPrimaryKeySelective(user);
            System.out.println("已更新密码哈希");
            return user;
        }
        
        System.out.println("密码验证失败");
        return null;
    }

    @Override
    public User adminLogin(LoginDTO loginDTO) {
        System.out.println("========== adminLogin 开始 ==========");
        System.out.println("登录账号: " + loginDTO.getAccount());
        System.out.println("登录密码: " + loginDTO.getPassword());
        
        User user = userMapper.selectByAccount(loginDTO.getAccount());
        
        if (user == null) {
            System.out.println("用户不存在");
            return null;
        }
        
        System.out.println("找到用户: " + user.getAccount());
        System.out.println("用户ID: " + user.getId());
        System.out.println("用户类型: " + user.getType());
        System.out.println("用户状态: " + user.getStatus());
        System.out.println("密码哈希: " + user.getPasswordHash());
        
        // 检查用户类型是否为管理员（type=1）
        if (user.getType() != 1) {
            System.out.println("用户类型不是管理员，拒绝登录");
            return null;
        }
        System.out.println("用户类型验证通过");
        
        // 检查密码哈希值格式是否正确
        try {
            boolean passwordMatch = PasswordUtil.checkPassword(loginDTO.getPassword(), user.getPasswordHash());
            System.out.println("密码验证结果: " + passwordMatch);
            if (passwordMatch) {
                System.out.println("登录成功");
                return user;
            }
        } catch (NumberFormatException e) {
            System.out.println("密码哈希格式异常: " + e.getMessage());
            // 如果密码哈希值格式不正确，重新生成哈希值并更新到数据库
            String newHashedPassword = PasswordUtil.hashPassword(loginDTO.getPassword());
            user.setPasswordHash(newHashedPassword);
            userMapper.updateByPrimaryKeySelective(user);
            System.out.println("已更新密码哈希");
            return user;
        }
        
        System.out.println("密码验证失败");
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
