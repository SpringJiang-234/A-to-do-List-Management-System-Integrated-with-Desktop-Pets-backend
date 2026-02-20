package com.backend.service;

import com.backend.bean.PageBean;
import com.backend.domain.entity.Category;
import com.backend.domain.query.CategoryQuery;

import java.util.List;

public interface CategoryService {

    /**
     * 分页获取类别列表
     * @param categoryQuery 查询参数
     * @return 类别分页数据
     */
    PageBean<Category> getPage(CategoryQuery categoryQuery);

    /**
     * 根据ID获取类别信息
     * @param id 类别ID
     * @return 类别信息
     */
    Category getById(Long id);

    /**
     * 更新类别信息
     * @param category 类别信息对象
     * @return 更新成功的记录数
     */
    int insertOrUpdate(Category category);

    /**
     * 根据ID删除类别
     * @param id 类别ID
     * @return 删除成功的记录数
     */
    int deleteById(Long id);

    /**
     * 批量删除类别
     * @param ids 类别ID列表
     * @return 删除成功的记录数
     */
    int deleteByIds(String ids);

    /**
     * 批量插入类别
     * @param categoryList 类别列表
     * @return 插入成功的记录数
     */
    int batchInsert(List<Category> categoryList);

}
