package com.backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backend.bean.PageBean;
import com.backend.domain.entity.TodoTag;
import com.backend.domain.query.TodoTagQuery;
import com.backend.mapper.TodoTagMapper;
import com.backend.service.TodoTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TodoTagServiceImpl implements TodoTagService {
    @Autowired
    private TodoTagMapper todoTagMapper;

    @Override
    public PageBean<TodoTag> getPage(TodoTagQuery todoTagQuery) {
        final Integer pageNum = todoTagQuery.getPageNum();
        final Integer pageSize = todoTagQuery.getPageSize();
        final Page<TodoTag> page = PageHelper.startPage(pageNum, pageSize);
        todoTagMapper.selectWithCondition(todoTagQuery);
        final int total = todoTagMapper.countByCondition(todoTagQuery);
        return PageBean.page2pageBean(page, (long) total);
    }


    @Override
    public TodoTag getById(Long id) {
        return todoTagMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(TodoTag todoTag) {
        return todoTagMapper.insertOrUpdateSelective(todoTag);
    }

    @Override
    public int deleteById(Long id) {
        return todoTagMapper.deleteByPrimaryKey(id);
    }

    @Override
     @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(String ids) {
        final String[] split = ids.split(",");
        return todoTagMapper.deleteByPrimaryKeyIn(split);
    }

    @Override
    public int batchInsert(List<TodoTag> todoTagList) {
        return todoTagMapper.batchInsertSelectiveUseDefaultForNull(todoTagList);
    }

    @Override
    public int deleteByTagId(Long tagId) {
        return todoTagMapper.deleteByTagId(tagId);
    }
}
