package com.backend.mapper;

import com.backend.domain.entity.AuditLog;
import java.util.List;
import java.util.Map;

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

    int countByCondition(AuditLogQuery auditLogQuery);

    /**
     * 获取操作频率趋势
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param interval 时间间隔（hour/day/week）
     * @return 操作频率趋势列表
     */
    List<Map<String, Object>> getOperationFrequencyTrend(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("interval") String interval);

    /**
     * 获取操作类型分布
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 操作类型分布列表
     */
    List<Map<String, Object>> getOperationTypeDistribution(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 获取热门操作模块
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param limit 限制数量
     * @return 热门操作模块列表
     */
    List<Map<String, Object>> getPopularModules(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("limit") int limit);

    /**
     * 获取用户活跃度排行
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param limit 限制数量
     * @return 用户活跃度排行列表
     */
    List<Map<String, Object>> getUserActivityRank(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("limit") int limit);
}