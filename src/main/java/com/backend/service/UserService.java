package com.backend.service;

import com.backend.bean.PageBean;
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

}
