package com.backend.mapper;

import com.backend.domain.entity.AuditLog;
import java.util.List;

import com.backend.domain.query.AuditLogQuery;
import org.apache.ibatis.annotations.Param;

public interface AuditLogMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByPrimaryKeyIn(String[] array);

    int insert(AuditLog record);

    int insertOrUpdate(AuditLog record);

    int insertOrUpdateSelective(AuditLog record);

    int insertSelective(AuditLog record);

    AuditLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuditLog record);

    int updateByPrimaryKey(AuditLog record);

    int updateBatch(@Param("list") List<AuditLog> list);

    int updateBatchUseMultiQuery(@Param("list") List<AuditLog> list);

    int updateBatchSelective(@Param("list") List<AuditLog> list);

    int batchInsert(@Param("list") List<AuditLog> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<AuditLog> list);

    int batchInsertOrUpdate(@Param("list") List<AuditLog> list);

    List<AuditLog> selectWithCondition(AuditLogQuery auditLogQuery);
}