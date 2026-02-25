package com.backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backend.bean.PageBean;
import com.backend.domain.entity.DesktopPet;
import com.backend.domain.query.DesktopPetQuery;
import com.backend.mapper.DesktopPetMapper;
import com.backend.service.DesktopPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DesktopPetServiceImpl implements DesktopPetService {
    @Autowired
    private DesktopPetMapper desktopPetMapper;

    @Override
    public PageBean<DesktopPet> getPage(DesktopPetQuery desktopPetQuery) {
        final Integer pageNum = desktopPetQuery.getPageNum();
        final Integer pageSize = desktopPetQuery.getPageSize();
        final Page<DesktopPet> page = PageHelper.startPage(pageNum, pageSize);
        desktopPetMapper.selectWithCondition(desktopPetQuery);
        final int total = desktopPetMapper.countByCondition(desktopPetQuery);
        return PageBean.page2pageBean(page, (long) total);
    }


    @Override
    public DesktopPet getById(Long id) {
        return desktopPetMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(DesktopPet desktopPet) {
        return desktopPetMapper.insertOrUpdateSelective(desktopPet);
    }

    @Override
    public int deleteById(Long id) {
        return desktopPetMapper.deleteByPrimaryKey(id);
    }

    @Override
     @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(String ids) {
        final String[] split = ids.split(",");
        return desktopPetMapper.deleteByPrimaryKeyIn(split);
    }

    @Override
    public int batchInsert(List<DesktopPet> desktopPetList) {
        return desktopPetMapper.batchInsertSelectiveUseDefaultForNull(desktopPetList);
    }
}
