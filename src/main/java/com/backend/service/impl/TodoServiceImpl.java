package com.backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backend.bean.PageBean;
import com.backend.domain.entity.Todo;
import com.backend.domain.query.TodoQuery;
import com.backend.mapper.TodoMapper;
import com.backend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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
     @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(String ids) {
        final String[] split = ids.split(",");
        return todoMapper.deleteByPrimaryKeyIn(split);
    }

    @Override
    public int batchInsert(List<Todo> todoList) {
        return todoMapper.batchInsert(todoList);
    }
}
