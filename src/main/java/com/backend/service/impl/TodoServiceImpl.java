package com.backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backend.bean.PageBean;
import com.backend.domain.entity.Todo;
import com.backend.domain.query.ClientTodoQuery;
import com.backend.domain.query.TodoQuery;
import com.backend.mapper.TodoMapper;
import com.backend.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoMapper todoMapper;

    @Override
    public PageBean<Todo> getPage(TodoQuery todoQuery) {
        final Integer pageNum = todoQuery.getPageNum();
        final Integer pageSize = todoQuery.getPageSize();
        final Page<Todo> page = PageHelper.startPage(pageNum, pageSize);
        todoMapper.selectWithCondition(todoQuery);
        final int total = todoMapper.countByCondition(todoQuery);
        return PageBean.page2pageBean(page, (long) total);
    }

    @Override
    public List<Todo> getList(TodoQuery todoQuery) {
        log.info("查询待办事项列表，查询条件：{}", todoQuery);
        return todoMapper.selectWithCondition(todoQuery);
    }

    @Override
    public List<Todo> getClientList(ClientTodoQuery clientTodoQuery) {
        log.info("查询客户端待办事项列表，查询条件：{}", clientTodoQuery);
        return todoMapper.selectWithConditionForClient(clientTodoQuery);
    }


    @Override
    public Todo getById(Long id) {
        return todoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(Todo todo) {
        return todoMapper.insertOrUpdateSelective(todo);
    }

    @Override
    public int deleteById(Long id) {
        return todoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int softDeleteById(Long id) {
        final Todo todo = new Todo();
        todo.setId(id);
        todo.setIsDelete(2);
        return todoMapper.updateByPrimaryKeySelective(todo);
    }

    @Override
     @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(String ids) {
        final String[] split = ids.split(",");
        return todoMapper.deleteByPrimaryKeyIn(split);
    }

    @Override
    public int batchInsert(List<Todo> todoList) {
        return todoMapper.batchInsertSelectiveUseDefaultForNull(todoList);
    }

    @Override
    public int countTotalTodos() {
        return todoMapper.countTotalTodos();
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getNewTodosTrend() {
        // 生成近七天的日期列表
        java.util.List<java.util.Map<String, Object>> result = new java.util.ArrayList<>();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        
        // 循环获取近七天的日期，每个日期查询新待办总数
        for (int i = 6; i >= 0; i--) {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.add(java.util.Calendar.DAY_OF_YEAR, -i);
            String dateStr = sdf.format(calendar.getTime());
            
            // 调用mapper方法获取当天的新增待办数
            int count = todoMapper.countNewTodosByDate(dateStr);
            
            java.util.Map<String, Object> dayData = new java.util.HashMap<>();
            dayData.put("date", dateStr);
            dayData.put("count", count);
            result.add(dayData);
        }
        
        return result;
    }
}
