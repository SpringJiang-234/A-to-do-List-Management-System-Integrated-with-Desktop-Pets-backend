package com.backend.service;

import com.backend.bean.PageBean;
import com.backend.domain.entity.Todo;
import com.backend.domain.query.ClientTodoQuery;
import com.backend.domain.query.TodoQuery;

import java.util.List;

public interface TodoService {

    /**
     * 分页获取待办事项列表
     * @param todoQuery 查询参数
     * @return 待办事项分页数据
     */
    PageBean<Todo> getPage(TodoQuery todoQuery);

    /**
     * 获取待办事项列表（不分页）
     * @param todoQuery 查询参数
     * @return 待办事项列表
     */
    List<Todo> getList(TodoQuery todoQuery);

    /**
     * 获取客户端待办事项列表（不分页）
     * @param clientTodoQuery 客户端查询参数
     * @return 待办事项列表
     */
    List<Todo> getClientList(ClientTodoQuery clientTodoQuery);

    /**
     * 根据ID获取待办事项信息
     * @param id 待办事项ID
     * @return 待办事项信息
     */
    Todo getById(Long id);

    /**
     * 更新待办事项信息
     * @param todo 待办事项信息对象
     * @return 更新成功的记录数
     */
    int insertOrUpdate(Todo todo);
    
    /**
     * 更新待办事项信息（带桌宠养成数据开关）
     * @param todo 待办事项信息对象
     * @param enablePetGrowth 是否启用桌宠养成数据
     * @return 更新成功的记录数
     */
    int insertOrUpdate(Todo todo, boolean enablePetGrowth);

    /**
     * 根据ID删除待办事项
     * @param id 待办事项ID
     * @return 删除成功的记录数
     */
    int deleteById(Long id);

    /**
     * 软删除待办事项（将is_delete设置为2）
     * @param id 待办事项ID
     * @return 更新成功的记录数
     */
    int softDeleteById(Long id);

    /**
     * 批量删除待办事项
     * @param ids 待办事项ID列表
     * @return 删除成功的记录数
     */
    int deleteByIds(String ids);

    /**
     * 批量插入待办事项
     * @param todoList 待办事项列表
     * @return 插入成功的记录数
     */
    int batchInsert(List<Todo> todoList);

    /**
     * 获取待办总数
     * @return 待办总数
     */
    int countTotalTodos();

    /**
     * 获取近七天待办数新增趋势
     * @return 近七天每天的新待办数
     */
    java.util.List<java.util.Map<String, Object>> getNewTodosTrend();

    /**
     * 更新待办事项专注时间
     * @param id 待办事项ID
     * @param focusTime 专注时间（秒）
     * @return 更新成功的记录数
     */
    int updateFocusTime(Long id, Long focusTime);

    /**
     * 批量更新待办事项的分类ID
     * @param oldCategoryId 原分类ID
     * @param newCategoryId 新分类ID
     * @return 更新成功的记录数
     */
    int updateCategoryIdByOldCategoryId(Long oldCategoryId, Long newCategoryId);
}
