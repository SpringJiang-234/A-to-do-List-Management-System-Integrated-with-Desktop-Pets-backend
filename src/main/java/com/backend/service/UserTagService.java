package com.backend.service;

import com.backend.bean.PageBean;
import com.backend.domain.entity.UserTag;
import com.backend.domain.query.UserTagQuery;

import java.util.List;

public interface UserTagService {

    /**
     * 分页获取用户标签关联列表
     * @param userTagQuery 查询参数
     * @return 用户标签关联分页数据
     */
    PageBean<UserTag> getPage(UserTagQuery userTagQuery);

    /**
     * 根据ID获取用户标签关联信息
     * @param id 用户标签关联ID
     * @return 用户标签关联信息
     */
    UserTag getById(Long id);

    /**
     * 更新用户标签关联信息
     * @param userTag 用户标签关联信息对象
     * @return 更新成功的记录数
     */
    int insertOrUpdate(UserTag userTag);

    /**
     * 根据ID删除用户标签关联
     * @param id 用户标签关联ID
     * @return 删除成功的记录数
     */
    int deleteById(Long id);

    /**
     * 批量删除用户标签关联
     * @param ids 用户标签关联ID列表
     * @return 删除成功的记录数
     */
    int deleteByIds(String ids);

    /**
     * 批量插入用户标签关联
     * @param userTagList 用户标签关联列表
     * @return 插入成功的记录数
     */
    int batchInsert(List<UserTag> userTagList);

}
