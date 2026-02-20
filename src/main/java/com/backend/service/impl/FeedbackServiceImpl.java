package com.backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backend.bean.PageBean;
import com.backend.domain.entity.Feedback;
import com.backend.domain.query.FeedbackQuery;
import com.backend.mapper.FeedbackMapper;
import com.backend.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public PageBean<Feedback> getPage(FeedbackQuery feedbackQuery) {
        final Integer pageNum = feedbackQuery.getPageNum();
        final Integer pageSize = feedbackQuery.getPageSize();
        final Page<Feedback> page = PageHelper.startPage(pageNum, pageSize);
        feedbackMapper.selectWithCondition(feedbackQuery);
        return PageBean.page2pageBean(page);
    }


    @Override
    public Feedback getById(Long id) {
        return feedbackMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(Feedback feedback) {
        return feedbackMapper.insertOrUpdateSelective(feedback);
    }

    @Override
    public int deleteById(Long id) {
        return feedbackMapper.deleteByPrimaryKey(id);
    }

    @Override
     @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(String ids) {
        final String[] split = ids.split(",");
        return feedbackMapper.deleteByPrimaryKeyIn(split);
    }

    @Override
    public int batchInsert(List<Feedback> feedbackList) {
        return feedbackMapper.batchInsert(feedbackList);
    }
}
