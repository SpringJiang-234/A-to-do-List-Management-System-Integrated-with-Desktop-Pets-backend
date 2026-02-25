package com.backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backend.bean.PageBean;
import com.backend.domain.entity.UserTag;
import com.backend.domain.query.UserTagQuery;
import com.backend.mapper.UserTagMapper;
import com.backend.service.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserTagServiceImpl implements UserTagService {
    @Autowired
    private UserTagMapper userTagMapper;

    @Override
    public PageBean<UserTag> getPage(UserTagQuery userTagQuery) {
        final Integer pageNum = userTagQuery.getPageNum();
        final Integer pageSize = userTagQuery.getPageSize();
        final Page<UserTag> page = PageHelper.startPage(pageNum, pageSize);
        userTagMapper.selectWithCondition(userTagQuery);
        final int total = userTagMapper.countByCondition(userTagQuery);
        return PageBean.page2pageBean(page, (long) total);
    }


    @Override
    public UserTag getById(Long id) {
        return userTagMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(UserTag userTag) {
        return userTagMapper.insertOrUpdateSelective(userTag);
    }

    @Override
    public int deleteById(Long id) {
        return userTagMapper.deleteByPrimaryKey(id);
    }

    @Override
     @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(String ids) {
        final String[] split = ids.split(",");
        return userTagMapper.deleteByPrimaryKeyIn(split);
    }

    @Override
    public int batchInsert(List<UserTag> userTagList) {
        return userTagMapper.batchInsert(userTagList);
    }
}
