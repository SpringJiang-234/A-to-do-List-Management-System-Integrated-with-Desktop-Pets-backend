package com.backend.mapper;

import com.backend.domain.entity.Todo;
import com.backend.domain.query.ClientTodoQuery;
import com.backend.domain.query.TodoQuery;
import java.util.List;
import java.util.Map;

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

    List<Todo> selectWithConditionForClient(ClientTodoQuery clientTodoQuery);

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

    /**
     * 获取待办状态分布
     * @return 待办状态分布列表
     */
    List<Map<String, Object>> getTodoStatusDistribution();

    /**
     * 获取待办优先级分布
     * @return 优先级分布列表
     */
    List<Map<String, Object>> getTodoPriorityDistribution();

    /**
     * 获取指定时间范围内的每日新增待办数
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 每日新增待办数列表
     */
    List<Map<String, Object>> getDailyNewTodos(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 获取待办截止时间预警
     * @param days 天数
     * @return 截止时间预警列表
     */
    List<Map<String, Object>> getTodoDeadlineWarning(@Param("days") int days);

    /**
     * 获取待办类别分布
     * @return 类别分布列表
     */
    List<Map<String, Object>> getTodoCategoryDistribution();

    /**
     * 获取待办标签使用频率
     * @return 标签使用频率列表
     */
    List<Map<String, Object>> getTodoTagFrequency();

    /**
     * 获取待办完成率随时间趋势
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 完成率趋势列表
     */
    List<Map<String, Object>> getTodoCompletionRateTrend(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 更新待办事项专注时间
     * @param params 包含id和focusTime的Map
     * @return 更新的行数
     */
    int updateFocusTime(@Param("id") Long id, @Param("focusTime") Long focusTime);

    /**
     * 批量更新待办事项的分类ID
     * @param oldCategoryId 原分类ID
     * @param newCategoryId 新分类ID
     * @return 更新的行数
     */
    int updateCategoryIdByOldCategoryId(@Param("oldCategoryId") Long oldCategoryId, @Param("newCategoryId") Long newCategoryId);
}