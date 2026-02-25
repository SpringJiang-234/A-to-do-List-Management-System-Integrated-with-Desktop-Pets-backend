package com.backend.service;

import com.backend.bean.PageBean;
import com.backend.domain.entity.AuditLog;
import com.backend.domain.query.AuditLogQuery;

import java.util.List;

public interface AuditLogService {

    /**
     * 分页获取审计日志表列表
     * @param auditLogQuery 查询参数
     * @return 审计日志表分页数据
     */
    PageBean<AuditLog> getPage(AuditLogQuery auditLogQuery);

    /**
     * 根据ID获取审计日志表信息
     * @param id 审计日志表ID
     * @return 审计日志表信息
     */
    AuditLog getById(Long id);

    /**
     * 更新审计日志表信息
     * @param auditLog 审计日志表信息对象
     * @return 更新成功的记录数
     */
    int insertOrUpdate(AuditLog auditLog);

    /**
     * 根据ID删除审计日志表
     * @param id 审计日志表ID
     * @return 删除成功的记录数
     */
    int deleteById(Long id);

    /**
     * 批量删除审计日志表
     * @param ids 审计日志表ID列表
     * @return 删除成功的记录数
     */
    int deleteByIds(String ids);

    /**
     * 批量插入审计日志表
     * @param auditLogList 审计日志表列表
     * @return 插入成功的记录数
     */
    int batchInsert(List<AuditLog> auditLogList);

}
