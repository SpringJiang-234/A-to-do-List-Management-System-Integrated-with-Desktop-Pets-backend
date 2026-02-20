package com.backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backend.bean.PageBean;
import com.backend.domain.entity.Announcement;
import com.backend.domain.query.AnnouncementQuery;
import com.backend.mapper.AnnouncementMapper;
import com.backend.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public PageBean<Announcement> getPage(AnnouncementQuery announcementQuery) {
        final Integer pageNum = announcementQuery.getPageNum();
        final Integer pageSize = announcementQuery.getPageSize();
        final Page<Announcement> page = PageHelper.startPage(pageNum, pageSize);
        announcementMapper.selectWithCondition(announcementQuery);
        return PageBean.page2pageBean(page);
    }


    @Override
    public Announcement getById(Long id) {
        return announcementMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(Announcement announcement) {
        return announcementMapper.insertOrUpdateSelective(announcement);
    }

    @Override
    public int deleteById(Long id) {
        return announcementMapper.deleteByPrimaryKey(id);
    }

    @Override
     @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(String ids) {
        final String[] split = ids.split(",");
        return announcementMapper.deleteByPrimaryKeyIn(split);
    }

    @Override
    public int batchInsert(List<Announcement> announcementList) {
        return announcementMapper.batchInsert(announcementList);
    }
}
