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
}
