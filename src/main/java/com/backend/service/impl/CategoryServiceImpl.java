package com.backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backend.bean.PageBean;
import com.backend.domain.entity.Category;
import com.backend.domain.query.CategoryQuery;
import com.backend.mapper.CategoryMapper;
import com.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageBean<Category> getPage(CategoryQuery categoryQuery) {
        final Integer pageNum = categoryQuery.getPageNum();
        final Integer pageSize = categoryQuery.getPageSize();
        final Page<Category> page = PageHelper.startPage(pageNum, pageSize);
        categoryMapper.selectWithCondition(categoryQuery);
        final int total = categoryMapper.countByCondition(categoryQuery);
        return PageBean.page2pageBean(page, (long) total);
    }


    @Override
    public Category getById(Long id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(Category category) {
        return categoryMapper.insertOrUpdateSelective(category);
    }

    @Override
    public int deleteById(Long id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
     @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(String ids) {
        final String[] split = ids.split(",");
        return categoryMapper.deleteByPrimaryKeyIn(split);
    }

    @Override
    public int batchInsert(List<Category> categoryList) {
        return categoryMapper.batchInsertSelectiveUseDefaultForNull(categoryList);
    }
}
