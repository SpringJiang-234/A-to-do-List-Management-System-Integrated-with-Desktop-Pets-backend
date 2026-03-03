package com.backend.service;

import com.backend.bean.PageBean;
import com.backend.domain.dto.LoginDTO;
import com.backend.domain.dto.RegisterDTO;
import com.backend.domain.entity.User;
import com.backend.domain.query.UserQuery;

import java.util.List;

public interface UserService {

    /**
     * 分页获取用户列表
     * @param userQuery 查询参数
     * @return 用户分页数据
     */
    PageBean<User> getPage(UserQuery userQuery);

    /**
     * 根据ID获取用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    User getById(Long id);

    /**
     * 更新用户信息
     * @param user 用户信息对象
     * @return 更新成功的记录数
     */
    int insertOrUpdate(User user);

    /**
     * 根据ID删除用户
     * @param id 用户ID
     * @return 删除成功的记录数
     */
    int deleteById(Long id);

    /**
     * 批量删除用户
     * @param ids 用户ID列表
     * @return 删除成功的记录数
     */
    int deleteByIds(String ids);

    /**
     * 批量插入用户
     * @param userList 用户列表
     * @return 插入成功的记录数
     */
    int batchInsert(List<User> userList);

    /**
     * 获取所有用户列表
     * @param userQuery 查询参数
     * @return 用户列表
     */
    List<User> getAll(UserQuery userQuery);

    /**
     * 检查账号是否存在
     * @param account 账号
     * @return 存在的账号数量
     */
    int isAccountExist(String account);

    /**
     * 注册用户
     * @param registerDTO 注册信息
     * @return 注册成功的记录数
     */
    int register(RegisterDTO registerDTO);

    /**
     * 用户登录
     * @param loginDTO 登录信息
     * @return 用户信息
     */
    User login(LoginDTO loginDTO);

    /**
     * 管理员登录
     * @param loginDTO 登录信息
     * @return 用户信息
     */
    User adminLogin(LoginDTO loginDTO);

    /**
     * 注销用户
     * @param id 用户ID
     * @return 注销成功的记录数
     */
    int cancelById(Long id);

    /**
     * 批量注销用户
     * @param ids 用户ID列表
     * @return 注销成功的记录数
     */
    int cancelByIds(String ids);

    /**
     * 获取用户总数
     * @return 用户总数
     */
    int countTotalUsers();

    /**
     * 获取近七天用户数新增趋势
     * @return 近七天每天的新用户数
     */
    java.util.List<java.util.Map<String, Object>> getNewUsersTrend();
}
