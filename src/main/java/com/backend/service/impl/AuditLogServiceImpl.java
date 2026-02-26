package com.backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backend.bean.PageBean;
import com.backend.domain.entity.AuditLog;
import com.backend.domain.query.AuditLogQuery;
import com.backend.mapper.AuditLogMapper;
import com.backend.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AuditLogServiceImpl implements AuditLogService {
    @Autowired
    private AuditLogMapper auditLogMapper;

    @Override
    public PageBean<AuditLog> getPage(AuditLogQuery auditLogQuery) {
        final Integer pageNum = auditLogQuery.getPageNum();
        final Integer pageSize = auditLogQuery.getPageSize();
        final Page<AuditLog> page = PageHelper.startPage(pageNum, pageSize);
        auditLogMapper.selectWithCondition(auditLogQuery);
        final int total = auditLogMapper.countByCondition(auditLogQuery);
        return PageBean.page2pageBean(page, (long) total);
    }


    @Override
    public AuditLog getById(Long id) {
        return auditLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(AuditLog auditLog) {
        return auditLogMapper.insertOrUpdateSelective(auditLog);
    }

    @Override
    public int deleteById(Long id) {
        return auditLogMapper.deleteByPrimaryKey(id);
    }

    @Override
     @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(String ids) {
        final String[] split = ids.split(",");
        return auditLogMapper.deleteByPrimaryKeyIn(split);
    }

    @Override
    public int batchInsert(List<AuditLog> auditLogList) {
        return auditLogMapper.batchInsertSelectiveUseDefaultForNull(auditLogList);
    }

    @Override
    public List<AuditLog> getAll(AuditLogQuery auditLogQuery) {
        return auditLogMapper.selectWithCondition(auditLogQuery);
    }
}
