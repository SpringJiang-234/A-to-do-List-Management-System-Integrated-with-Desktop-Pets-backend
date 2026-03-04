package com.backend.mapper;

import com.backend.domain.entity.Feedback;
import java.util.List;
import java.util.Map;

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

    int countByCondition(FeedbackQuery feedbackQuery);

    /**
     * 获取反馈状态分布
     * @return 反馈状态分布列表
     */
    List<Map<String, Object>> getFeedbackStatusDistribution();

    /**
     * 获取每日反馈提交量
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 每日反馈提交量列表
     */
    List<Map<String, Object>> getDailyFeedbackSubmission(@Param("startDate") String startDate, @Param("endDate") String endDate);
}