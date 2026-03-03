package com.backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backend.bean.PageBean;
import com.backend.domain.entity.Tag;
import com.backend.domain.query.TagQuery;
import com.backend.mapper.TagMapper;
import com.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public PageBean<Tag> getPage(TagQuery tagQuery) {
        final Integer pageNum = tagQuery.getPageNum();
        final Integer pageSize = tagQuery.getPageSize();
        final Page<Tag> page = PageHelper.startPage(pageNum, pageSize);
        tagMapper.selectWithCondition(tagQuery);
        final int total = tagMapper.countByCondition(tagQuery);
        return PageBean.page2pageBean(page, (long) total);
    }


    @Override
    public Tag getById(Long id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(Tag tag) {
        return tagMapper.insertOrUpdateSelective(tag);
    }

    @Override
    public int deleteById(Long id) {
        return tagMapper.deleteByPrimaryKey(id);
    }

    @Override
     @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(String ids) {
        final String[] split = ids.split(",");
        return tagMapper.deleteByPrimaryKeyIn(split);
    }

    @Override
    public int batchInsert(List<Tag> tagList) {
        return tagMapper.batchInsertSelectiveUseDefaultForNull(tagList);
    }

    @Override
    public int countTotalTags() {
        return tagMapper.countTotalTags();
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getNewTagsTrend() {
        // 生成近七天的日期列表
        java.util.List<java.util.Map<String, Object>> result = new java.util.ArrayList<>();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        
        // 循环获取近七天的日期，每个日期查询新标签总数
        for (int i = 6; i >= 0; i--) {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.add(java.util.Calendar.DAY_OF_YEAR, -i);
            String dateStr = sdf.format(calendar.getTime());
            
            // 调用mapper方法获取当天的新增标签数
            int count = tagMapper.countNewTagsByDate(dateStr);
            
            java.util.Map<String, Object> dayData = new java.util.HashMap<>();
            dayData.put("date", dateStr);
            dayData.put("count", count);
            result.add(dayData);
        }
        
        return result;
    }
}
