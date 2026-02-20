package com.backend.mapper;

import com.backend.domain.entity.Feedback;
import java.util.List;

import com.backend.domain.entity.Feedback;
import com.backend.domain.query.FeedbackQuery;
import org.apache.ibatis.annotations.Param;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByPrimaryKeyIn(String[] array);

    int insert(Feedback record);

    int insertOrUpdate(Feedback record);

    int insertOrUpdateSelective(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);

    int updateBatch(@Param("list") List<Feedback> list);

    int updateBatchUseMultiQuery(@Param("list") List<Feedback> list);

    int updateBatchSelective(@Param("list") List<Feedback> list);

    int batchInsert(@Param("list") List<Feedback> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<Feedback> list);

    int batchInsertOrUpdate(@Param("list") List<Feedback> list);

    List<Feedback> selectWithCondition(FeedbackQuery feedbackQuery);
}