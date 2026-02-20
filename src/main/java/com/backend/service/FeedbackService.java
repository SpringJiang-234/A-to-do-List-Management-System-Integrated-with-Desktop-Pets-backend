package com.backend.service;

import com.backend.bean.PageBean;
import com.backend.domain.entity.Feedback;
import com.backend.domain.query.FeedbackQuery;

import java.util.List;

public interface FeedbackService {

    /**
     * 分页获取反馈列表
     * @param feedbackQuery 查询参数
     * @return 反馈分页数据
     */
    PageBean<Feedback> getPage(FeedbackQuery feedbackQuery);

    /**
     * 根据ID获取反馈信息
     * @param id 反馈ID
     * @return 反馈信息
     */
    Feedback getById(Long id);

    /**
     * 更新反馈信息
     * @param feedback 反馈信息对象
     * @return 更新成功的记录数
     */
    int insertOrUpdate(Feedback feedback);

    /**
     * 根据ID删除反馈
     * @param id 反馈ID
     * @return 删除成功的记录数
     */
    int deleteById(Long id);

    /**
     * 批量删除反馈
     * @param ids 反馈ID列表
     * @return 删除成功的记录数
     */
    int deleteByIds(String ids);

    /**
     * 批量插入反馈
     * @param feedbackList 反馈列表
     * @return 插入成功的记录数
     */
    int batchInsert(List<Feedback> feedbackList);

}
