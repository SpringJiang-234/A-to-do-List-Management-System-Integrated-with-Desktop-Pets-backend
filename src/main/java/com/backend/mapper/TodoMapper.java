package com.backend.mapper;

import com.backend.domain.entity.Todo;
import java.util.List;

import com.backend.domain.entity.Todo;
import com.backend.domain.query.TodoQuery;
import org.apache.ibatis.annotations.Param;

public interface TodoMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByPrimaryKeyIn(String[] array);

    int insert(Todo record);

    int insertOrUpdate(Todo record);

    int insertOrUpdateSelective(Todo record);

    int insertSelective(Todo record);

    Todo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Todo record);

    int updateByPrimaryKey(Todo record);

    int updateBatch(@Param("list") List<Todo> list);

    int updateBatchUseMultiQuery(@Param("list") List<Todo> list);

    int updateBatchSelective(@Param("list") List<Todo> list);

    int batchInsert(@Param("list") List<Todo> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<Todo> list);

    int batchInsertOrUpdate(@Param("list") List<Todo> list);

    List<Todo> selectWithCondition(TodoQuery todoQuery);

    int countByCondition(TodoQuery todoQuery);

    /**
     * 获取待办总数
     * @return 待办总数
     */
    int countTotalTodos();

    /**
     * 获取某天的新增待办数
     * @param date 日期（格式：YYYY-MM-DD）
     * @return 新增待办数
     */
    int countNewTodosByDate(@Param("date") String date);
}