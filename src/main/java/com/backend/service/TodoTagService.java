package com.backend.service;

import com.backend.bean.PageBean;
import com.backend.domain.entity.TodoTag;
import com.backend.domain.query.TodoTagQuery;

import java.util.List;

public interface TodoTagService {

    /**
     * 分页获取待办标签关联列表
     * @param todoTagQuery 查询参数
     * @return 待办标签关联分页数据
     */
    PageBean<TodoTag> getPage(TodoTagQuery todoTagQuery);

    /**
     * 根据ID获取待办标签关联信息
     * @param id 待办标签关联ID
     * @return 待办标签关联信息
     */
    TodoTag getById(Long id);

    /**
     * 更新待办标签关联信息
     * @param todoTag 待办标签关联信息对象
     * @return 更新成功的记录数
     */
    int insertOrUpdate(TodoTag todoTag);

    /**
     * 根据ID删除待办标签关联
     * @param id 待办标签关联ID
     * @return 删除成功的记录数
     */
    int deleteById(Long id);

    /**
     * 批量删除待办标签关联
     * @param ids 待办标签关联ID列表
     * @return 删除成功的记录数
     */
    int deleteByIds(String ids);

    /**
     * 批量插入待办标签关联
     * @param todoTagList 待办标签关联列表
     * @return 插入成功的记录数
     */
    int batchInsert(List<TodoTag> todoTagList);

}
