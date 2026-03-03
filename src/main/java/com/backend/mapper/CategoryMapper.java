package com.backend.mapper;

import com.backend.domain.entity.Category;
import java.util.List;

import com.backend.domain.entity.Category;
import com.backend.domain.query.CategoryQuery;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByPrimaryKeyIn(String[] array);

    int insert(Category record);

    int insertOrUpdate(Category record);

    int insertOrUpdateSelective(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    int updateBatch(@Param("list") List<Category> list);

    int updateBatchUseMultiQuery(@Param("list") List<Category> list);

    int updateBatchSelective(@Param("list") List<Category> list);

    int batchInsert(@Param("list") List<Category> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<Category> list);

    int batchInsertOrUpdate(@Param("list") List<Category> list);

    List<Category> selectWithCondition(CategoryQuery CategoryQuery);

    int countByCondition(CategoryQuery CategoryQuery);

    /**
     * 获取分类总数
     * @return 分类总数
     */
    int countTotalCategories();

    /**
     * 获取某天的新增分类数
     * @param date 日期（格式：YYYY-MM-DD）
     * @return 新增分类数
     */
    int countNewCategoriesByDate(@Param("date") String date);
}