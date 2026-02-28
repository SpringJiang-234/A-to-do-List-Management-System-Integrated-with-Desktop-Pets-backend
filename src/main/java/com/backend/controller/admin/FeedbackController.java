package com.backend.controller.admin;

import com.backend.bean.PageBean;
import com.backend.bean.ResultBean;
import com.backend.converter.FeedbackConverter;
import com.backend.domain.details.FeedbackDetails;
import com.backend.domain.dto.FeedbackDTO;
import com.backend.domain.entity.Feedback;
import com.backend.domain.excel.FeedbackExcel;
import com.backend.domain.query.FeedbackQuery;
import com.backend.domain.vo.FeedbackVO;
import com.backend.ex.GlobalException;
import com.backend.service.FeedbackService;
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
 * 反馈控制层
 *
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private FeedbackConverter feedbackConverter;

    /**
     * 获取反馈分页数据
     *
     * @param feedbackQuery
     * @return
     */
    @PostMapping("/page")
    public ResultBean<PageBean<FeedbackVO>> page(@RequestBody FeedbackQuery feedbackQuery) {
        final PageBean<Feedback> feedbackPageBean = feedbackService.getPage(feedbackQuery);
        final PageBean<FeedbackVO> pageBean = feedbackConverter.feedbackPageBean2feedbackVOPageBean(feedbackPageBean);
        return ResultBean.success(pageBean);
    }

    /**
     * 获取反馈详情：暂时没用
     *
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    public ResultBean<FeedbackDetails> getDetails(@PathVariable("id") Long id) {
        final Feedback feedback = feedbackService.getById(id);
        final FeedbackDetails feedbackDetails = feedbackConverter.feedback2feedbackDetails(feedback);
        return ResultBean.success(feedbackDetails);
    }

    /**
     * 修改反馈信息
     *
     * @param feedbackDTO
     * @return
     */
    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody FeedbackDTO feedbackDTO) {
        final Feedback feedback = feedbackConverter.feedbackDTO2feedback(feedbackDTO);
        feedbackService.insertOrUpdate(feedback);
        return ResultBean.success("修改成功!", null);
    }

    /**
     * 添加反馈信息
     *
     * @param feedbackDTO
     * @return
     */
    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody FeedbackDTO feedbackDTO) {
        final Feedback feedback = feedbackConverter.feedbackDTO2feedback(feedbackDTO);
        feedbackService.insertOrUpdate(feedback);
        return ResultBean.success("添加成功!", null);
    }

    /**
     * 删除反馈信息
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public ResultBean<Void> delete(@PathVariable("id") Long id) {
        feedbackService.deleteById(id);
        return ResultBean.success("删除成功!", null);
    }

    /**
     * 批量删除反馈信息
     *
     * @param ids
     * @return
     */
    @GetMapping("/batchDelete/{ids}")
    public ResultBean<Void> batchDelete(@PathVariable("ids") String ids) {
        feedbackService.deleteByIds(ids);
        return ResultBean.success("批量删除成功!", null);
    }

    /**
     * 导出反馈信息
     *
     * @param feedbackQuery
     * @param response
     */
    @PostMapping("/export")
    public void exportData(HttpServletResponse response, @RequestBody FeedbackQuery feedbackQuery) {
        final List<Feedback> feedbackList = feedbackService.getAll(feedbackQuery);

        final List<FeedbackExcel> list = feedbackConverter.feedbackList2feedbackExcelList(feedbackList);
        try {
            EasyExcelUtil.writeWithSheetsWeb(response, "feedback列表")
                    .writeModel(FeedbackExcel.class, list, "feedback")
                    .finish();
        } catch (IOException e) {
            throw new GlobalException("反馈信息导出失败！");
        }
    }

    /**
     * 下载反馈导入模板
     *
     * @param response
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        try {
            EasyExcelUtil.writeWithSheetsWeb(response, "反馈导入模板")
                    .writeModel(FeedbackExcel.class, null, "feedback")
                    .finish();
        } catch (IOException e) {
            throw new GlobalException("反馈导入模板下载失败！");
        }
    }

    /**
     * 导入反馈信息
     *
     * @param file
     * @return
     */
    @PostMapping("/import")
    public ResultBean<Void> importData(@RequestPart(value = "file", required = true) MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            final EasyExcelListener<FeedbackExcel> listener = new EasyExcelListener<FeedbackExcel>() {
                @Override
                protected void exec(List<FeedbackExcel> list) {
                    final List<Feedback> feedbackList = feedbackConverter.feedbackExcelList2feedbackList(list);
                    feedbackService.batchInsert(feedbackList);
                }
            };
            EasyExcelUtil.asyncReadModel(is, listener, FeedbackExcel.class);
        } catch (IOException e) {
            throw new GlobalException("反馈信息导入失败！");
        }
        return ResultBean.success("反馈信息导入成功!", null);
    }
}
