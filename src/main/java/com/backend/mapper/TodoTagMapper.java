package com.backend.mapper;

import com.backend.domain.entity.TodoTag;

import java.util.List;

import com.backend.domain.query.TodoTagQuery;
import org.apache.ibatis.annotations.Param;

public interface TodoTagMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByPrimaryKeyIn(String[] array);

    int insert(TodoTag record);

    int insertOrUpdate(TodoTag record);

    int insertOrUpdateSelective(TodoTag record);

    int insertSelective(TodoTag record);

    TodoTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TodoTag record);

    int updateByPrimaryKey(TodoTag record);

    int updateBatch(@Param("list") List<TodoTag> list);

    int updateBatchUseMultiQuery(@Param("list") List<TodoTag> list);

    int updateBatchSelective(@Param("list") List<TodoTag> list);

    int batchInsert(@Param("list") List<TodoTag> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<TodoTag> list);

    int batchInsertOrUpdate(@Param("list") List<TodoTag> list);

    List<TodoTag> selectWithCondition(TodoTagQuery todoTagQuery);

    int countByCondition(TodoTagQuery todoTagQuery);

    /**
     * 根据标签ID删除所有待办标签关联
     * @param tagId 标签ID
     * @return 删除的记录数
     */
    int deleteByTagId(@Param("tagId") Long tagId);
}