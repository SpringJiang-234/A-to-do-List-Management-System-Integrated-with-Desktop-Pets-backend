package com.backend.controller.admin;

import com.backend.bean.PageBean;
import com.backend.bean.ResultBean;
import com.backend.converter.AuditLogConverter;
import com.backend.domain.details.AuditLogDetails;
import com.backend.domain.dto.AuditLogDTO;
import com.backend.domain.entity.AuditLog;
import com.backend.domain.excel.AuditLogExcel;
import com.backend.domain.query.AuditLogQuery;
import com.backend.domain.vo.AuditLogVO;
import com.backend.ex.GlobalException;
import com.backend.service.AuditLogService;
import com.backend.utils.excel.EasyExcelListener;
import com.backend.utils.excel.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 审计日志表控制层
 *
 */
@RestController
@RequestMapping("/auditLog")
public class AuditLogController {
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private AuditLogConverter auditLogConverter;


    @PostMapping("/page")
    public ResultBean<PageBean<AuditLogVO>> page(@RequestBody AuditLogQuery auditLogQuery) {
        final PageBean<AuditLog> auditLogPageBean = auditLogService.getPage(auditLogQuery);
        final PageBean<AuditLogVO> pageBean = auditLogConverter.auditLogPageBean2auditLogVOPageBean(auditLogPageBean);
        return ResultBean.success(pageBean);
    }

    @GetMapping("/details/{id}")
    public ResultBean<AuditLogDetails> getDetails(@PathVariable("id") Long id) {
        final AuditLog auditLog = auditLogService.getById(id);
        final AuditLogDetails auditLogDetails = auditLogConverter.auditLog2auditLogDetails(auditLog);
        return ResultBean.success(auditLogDetails);
    }

    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody AuditLogDTO auditLogDTO) {
        final AuditLog auditLog = auditLogConverter.auditLogDTO2auditLog(auditLogDTO);
        auditLogService.insertOrUpdate(auditLog);
        return ResultBean.success("修改成功!", null);
    }

    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody AuditLogDTO auditLogDTO) {
        final AuditLog auditLog = auditLogConverter.auditLogDTO2auditLog(auditLogDTO);
        auditLogService.insertOrUpdate(auditLog);
        return ResultBean.success("添加成功!", null);
    }

    @GetMapping("/delete/{id}")
    public ResultBean<Void> delete(@PathVariable("id") Long id) {
        auditLogService.deleteById(id);
        return ResultBean.success("删除成功!", null);
    }

    @GetMapping("/batchDelete/{ids}")
    public ResultBean<Void> batchDelete(@PathVariable("ids") String ids) {
        auditLogService.deleteByIds(ids);
        return ResultBean.success("批量删除成功!", null);
    }

    @PostMapping("/export")
    public void exportData(HttpServletResponse response, @RequestBody AuditLogQuery auditLogQuery) {
        final List<AuditLog> list = auditLogService.getAll(auditLogQuery);

        final List<AuditLogExcel> excelList = auditLogConverter.auditLogList2auditLogExcelList(list);
        try {
            EasyExcelUtil.writeWithSheetsWeb(response, "auditLog列表")
                    .writeModel(AuditLogExcel.class, excelList, "auditLog")
                    .finish();
        } catch (IOException e) {
            throw new GlobalException("审计日志表信息导出失败！");
        }
    }

    @PostMapping("/import")
    public ResultBean<Void> importData(@RequestPart(value = "file", required = true) MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            final EasyExcelListener<AuditLogExcel> listener = new EasyExcelListener<AuditLogExcel>() {
                @Override
                protected void exec(List<AuditLogExcel> list) {
                    final List<AuditLog> auditLogList = auditLogConverter.auditLogExcelList2auditLogList(list);
                    auditLogService.batchInsert(auditLogList);
                }
            };
            EasyExcelUtil.asyncReadModel(is, listener, AuditLogExcel.class);
        } catch (IOException e) {
            throw new GlobalException("审计日志表信息导入失败！");
        }
        return ResultBean.success("审计日志表信息导入成功!", null);
    }
}
