package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.AuditLogDetails;
import com.backend.domain.dto.AuditLogDTO;
import com.backend.domain.entity.AuditLog;
import com.backend.domain.excel.AuditLogExcel;
import com.backend.domain.vo.AuditLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuditLogConverter {

    AuditLog auditLogDTO2auditLog(AuditLogDTO auditLogDTO);

    /**
     * 将AuditLog对象转换为AuditLogVO对象
     * 用于转换审计日志表基本信息
     *
     * @param auditLog 源AuditLog对象，包含审计日志表基本信息
     * @return AuditLogVO对象，包含转换后的审计日志表VO信息
     */
    AuditLogVO auditLog2auditLogVO(AuditLog auditLog);

    /**
     * 将AuditLog对象转换为AuditLogDetails对象
     * 用于转换审计日志表基本信息
     *
     * @param auditLog 源AuditLog对象，包含审计日志表基本信息
     * @return AuditLogDetails对象，包含审计日志表详细信息
     */
    AuditLogDetails auditLog2auditLogDetails(AuditLog auditLog);

    /**
     * 将PageBean<AuditLog>分页对象转换为PageBean<AuditLogVO>分页对象
     * 用于分页数据的类型转换，保持分页结构不变
     *
     * @param auditLogPageBean 源AuditLog分页对象，包含审计日志表信息的分页数据
     * @return AuditLogVO分页对象，包含转换后的审计日志表VO对象的分页数据
     */
    PageBean<AuditLogVO> auditLogPageBean2auditLogVOPageBean(PageBean<AuditLog> auditLogPageBean);

    /**
     * 将AuditLog对象转换为AuditLogExcel对象
     * 用于Excel导出审计日志表信息
     *
     * @param auditLog 源AuditLog对象，包含审计日志表基本信息
     * @return AuditLogExcel对象，包含审计日志表Excel信息
     */
    AuditLogExcel auditLog2auditLogExcel(AuditLog auditLog);

    /**
     * 将AuditLog列表转换为AuditLogExcel列表
     * 批量转换审计日志表信息用于Excel导出
     *
     * @param auditLogList 源AuditLog对象列表
     * @return AuditLogExcel对象列表，用于Excel导出
     */
    List<AuditLogExcel> auditLogList2auditLogExcelList(List<AuditLog> auditLogList);

    /**
     * 将AuditLogExcel对象转换为AuditLog对象
     * 用于从Excel导入审计日志表信息
     *
     * @param auditLogExcel 源AuditLogExcel对象，包含从Excel导入的审计日志表信息
     * @return AuditLog对象，包含转换后的审计日志表基本信息
     */
    AuditLog auditLogExcel2auditLog(AuditLogExcel auditLogExcel);

    /**
     * 将AuditLogExcel列表转换为AuditLog列表
     * 批量转换从Excel导入的审计日志表信息
     *
     * @param auditLogExcelList 源AuditLogExcel对象列表，包含从Excel导入的审计日志表信息
     * @return AuditLog对象列表，包含转换后的审计日志表基本信息
     */
    List<AuditLog> auditLogExcelList2auditLogList(List<AuditLogExcel> auditLogExcelList);

}

