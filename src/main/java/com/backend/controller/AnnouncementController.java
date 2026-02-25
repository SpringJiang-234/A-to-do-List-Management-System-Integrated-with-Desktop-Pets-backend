package com.backend.controller;

import com.backend.bean.PageBean;
import com.backend.bean.ResultBean;
import com.backend.converter.AnnouncementConverter;
import com.backend.domain.details.AnnouncementDetails;
import com.backend.domain.dto.AnnouncementDTO;
import com.backend.domain.entity.Announcement;
import com.backend.domain.excel.AnnouncementExcel;
import com.backend.domain.query.AnnouncementQuery;
import com.backend.domain.vo.AnnouncementVO;
import com.backend.ex.GlobalException;
import com.backend.service.AnnouncementService;
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
 * 公告控制层
 *
 */
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private AnnouncementConverter announcementConverter;


    @PostMapping("/page")
    public ResultBean<PageBean<AnnouncementVO>> page(@RequestBody AnnouncementQuery announcementQuery) {
        final PageBean<Announcement> announcementPageBean = announcementService.getPage(announcementQuery);
        final PageBean<AnnouncementVO> pageBean = announcementConverter.announcementPageBean2announcementVOPageBean(announcementPageBean);
        return ResultBean.success(pageBean);
    }

    @GetMapping("/details/{id}")
    public ResultBean<AnnouncementDetails> getDetails(@PathVariable("id") Long id) {
        final Announcement announcement = announcementService.getById(id);
        final AnnouncementDetails announcementDetails = announcementConverter.announcement2announcementDetails(announcement);
        return ResultBean.success(announcementDetails);
    }

    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody AnnouncementDTO announcementDTO) {
        final Announcement announcement = announcementConverter.announcementDTO2announcement(announcementDTO);
        announcementService.insertOrUpdate(announcement);
        return ResultBean.success("修改成功!", null);
    }

    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody AnnouncementDTO announcementDTO) {
        final Announcement announcement = announcementConverter.announcementDTO2announcement(announcementDTO);
        announcementService.insertOrUpdate(announcement);
        return ResultBean.success("添加成功!", null);
    }

    @GetMapping("/delete/{id}")
    public ResultBean<Void> delete(@PathVariable("id") Long id) {
        announcementService.deleteById(id);
        return ResultBean.success("删除成功!", null);
    }

    @GetMapping("/batchDelete/{ids}")
    public ResultBean<Void> batchDelete(@PathVariable("ids") String ids) {
        announcementService.deleteByIds(ids);
        return ResultBean.success("批量删除成功!", null);
    }

    @PostMapping("/export")
    public void exportData(HttpServletResponse response, @RequestBody AnnouncementQuery announcementQuery) {
        final List<Announcement> list = announcementService.getAll(announcementQuery);
        final List<AnnouncementExcel> excelList = announcementConverter.announcementList2announcementExcelList(list);
        try {
            EasyExcelUtil.writeWithSheetsWeb(response, "announcement列表")
                    .writeModel(AnnouncementExcel.class, excelList, "announcement")
                    .finish();
        } catch (IOException e) {
            throw new GlobalException("公告信息导出失败！");
        }
    }

    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        try {
            EasyExcelUtil.writeWithSheetsWeb(response, "公告导入模板")
                    .writeModel(AnnouncementExcel.class, List.of(), "公告数据")
                    .finish();
        } catch (IOException e) {
            throw new GlobalException("模板下载失败！");
        }
    }

    @PostMapping("/import")
    public ResultBean<Void> importData(@RequestPart(value = "file", required = true) MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            final EasyExcelListener<AnnouncementExcel> listener = new EasyExcelListener<AnnouncementExcel>() {
                @Override
                protected void exec(List<AnnouncementExcel> list) {
                    final List<Announcement> announcementList = announcementConverter.announcementExcelList2announcementList(list);
                    announcementService.batchInsert(announcementList);
                }
            };
            EasyExcelUtil.asyncReadModel(is, listener, AnnouncementExcel.class);
        } catch (IOException e) {
            throw new GlobalException("公告信息导入失败！");
        }
        return ResultBean.success("公告信息导入成功!", null);
    }
}
