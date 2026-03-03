package com.backend.mapper;

import com.backend.domain.entity.Tag;
import java.util.List;

import com.backend.domain.entity.Tag;
import com.backend.domain.query.TagQuery;
import org.apache.ibatis.annotations.Param;

public interface TagMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByPrimaryKeyIn(String[] array);

    int insert(Tag record);

    int insertOrUpdate(Tag record);

    int insertOrUpdateSelective(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    int updateBatch(@Param("list") List<Tag> list);

    int updateBatchUseMultiQuery(@Param("list") List<Tag> list);

    int updateBatchSelective(@Param("list") List<Tag> list);

    int batchInsert(@Param("list") List<Tag> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<Tag> list);

    int batchInsertOrUpdate(@Param("list") List<Tag> list);

    List<Tag> selectWithCondition(TagQuery tagQuery);

    int countByCondition(TagQuery tagQuery);

    /**
     * 获取标签总数
     * @return 标签总数
     */
    int countTotalTags();

    /**
     * 获取某天的新增标签数
     * @param date 日期（格式：YYYY-MM-DD）
     * @return 新增标签数
     */
    int countNewTagsByDate(@Param("date") String date);
}