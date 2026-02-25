package com.backend.service.impl;

import com.backend.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backend.bean.PageBean;
import com.backend.domain.entity.User;
import com.backend.domain.query.UserQuery;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageBean<User> getPage(UserQuery userQuery) {
        final Integer pageNum = userQuery.getPageNum();
        final Integer pageSize = userQuery.getPageSize();
        final Page<User> page = PageHelper.startPage(pageNum, pageSize);
        userMapper.selectWithCondition(userQuery);
        final int total = userMapper.countByCondition(userQuery);
        return PageBean.page2pageBean(page, (long) total);
    }


    @Override
    public User getById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(User user) {
        return userMapper.insertOrUpdateSelective(user);
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
     @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(String ids) {
        final String[] split = ids.split(",");
        return userMapper.deleteByPrimaryKeyIn(split);
    }

    @Override
    public int batchInsert(List<User> userList) {
        return userMapper.batchInsert(userList);
    }
}
