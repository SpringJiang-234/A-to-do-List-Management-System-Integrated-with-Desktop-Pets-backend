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
    public List<Category> getListByUserId(Long userId) {
        CategoryQuery categoryQuery = new CategoryQuery();
        categoryQuery.setUserId(userId);
        return categoryMapper.selectWithCondition(categoryQuery);
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

    @Override
    public int countTotalCategories() {
        return categoryMapper.countTotalCategories();
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getNewCategoriesTrend() {
        // 生成近七天的日期列表
        java.util.List<java.util.Map<String, Object>> result = new java.util.ArrayList<>();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        
        // 循环获取近七天的日期，每个日期查询新分类总数
        for (int i = 6; i >= 0; i--) {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.add(java.util.Calendar.DAY_OF_YEAR, -i);
            String dateStr = sdf.format(calendar.getTime());
            
            // 调用mapper方法获取当天的新增分类数
            int count = categoryMapper.countNewCategoriesByDate(dateStr);
            
            java.util.Map<String, Object> dayData = new java.util.HashMap<>();
            dayData.put("date", dateStr);
            dayData.put("count", count);
            result.add(dayData);
        }
        
        return result;
    }
}
