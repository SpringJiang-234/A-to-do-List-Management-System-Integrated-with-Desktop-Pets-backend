package com.backend.controller;

import com.backend.bean.PageBean;
import com.backend.bean.ResultBean;
import com.backend.converter.TodoTagConverter;
import com.backend.domain.details.TodoTagDetails;
import com.backend.domain.dto.TodoTagDTO;
import com.backend.domain.entity.TodoTag;
import com.backend.domain.excel.TodoTagExcel;
import com.backend.domain.query.TodoTagQuery;
import com.backend.domain.vo.TodoTagVO;
import com.backend.ex.GlobalException;
import com.backend.service.TodoTagService;
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
 * 待办标签关联控制层
 *
 */
@RestController
@RequestMapping("/todoTag")
public class TodoTagController {
    @Autowired
    private TodoTagService todoTagService;
    @Autowired
    private TodoTagConverter todoTagConverter;


    @PostMapping("/page")
    public ResultBean<PageBean<TodoTagVO>> page(@RequestBody TodoTagQuery todoTagQuery) {
        final PageBean<TodoTag> todoTagPageBean = todoTagService.getPage(todoTagQuery);
        final PageBean<TodoTagVO> pageBean = todoTagConverter.todoTagPageBean2todoTagVOPageBean(todoTagPageBean);
        return ResultBean.success(pageBean);
    }

    @GetMapping("/details/{id}")
    public ResultBean<TodoTagDetails> getDetails(@PathVariable("id") Long id) {
        final TodoTag todoTag = todoTagService.getById(id);
        final TodoTagDetails todoTagDetails = todoTagConverter.todoTag2todoTagDetails(todoTag);
        return ResultBean.success(todoTagDetails);
    }

    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody TodoTagDTO todoTagDTO) {
        final TodoTag todoTag = todoTagConverter.todoTagDTO2todoTag(todoTagDTO);
        todoTagService.insertOrUpdate(todoTag);
        return ResultBean.success("修改成功!", null);
    }

    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody TodoTagDTO todoTagDTO) {
        final TodoTag todoTag = todoTagConverter.todoTagDTO2todoTag(todoTagDTO);
        todoTagService.insertOrUpdate(todoTag);
        return ResultBean.success("添加成功!", null);
    }

    @GetMapping("/delete/{id}")
    public ResultBean<Void> delete(@PathVariable("id") Long id) {
        todoTagService.deleteById(id);
        return ResultBean.success("删除成功!", null);
    }

    @GetMapping("/batchDelete/{ids}")
    public ResultBean<Void> batchDelete(@PathVariable("ids") String ids) {
        todoTagService.deleteByIds(ids);
        return ResultBean.success("批量删除成功!", null);
    }

    @PostMapping("/export")
    public void exportData(HttpServletResponse response, @RequestBody TodoTagQuery todoTagQuery) {
        final PageBean<TodoTag> pageBean = todoTagService.getPage(todoTagQuery);

        final List<TodoTagExcel> list = todoTagConverter.todoTagList2todoTagExcelList(pageBean.getRecords());
        try {
            EasyExcelUtil.writeWithSheetsWeb(response, "todoTag列表")
                    .writeModel(TodoTagExcel.class, list, "todoTag")
                    .finish();
        } catch (IOException e) {
            throw new GlobalException("待办标签关联信息导出失败！");
        }
    }

    @PostMapping("/import")
    public ResultBean<Void> importData(@RequestPart(value = "file", required = true) MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            final EasyExcelListener<TodoTagExcel> listener = new EasyExcelListener<TodoTagExcel>() {
                @Override
                protected void exec(List<TodoTagExcel> list) {
                    final List<TodoTag> todoTagList = todoTagConverter.todoTagExcelList2todoTagList(list);
                    todoTagService.batchInsert(todoTagList);
                }
            };
            EasyExcelUtil.asyncReadModel(is, listener, TodoTagExcel.class);
        } catch (IOException e) {
            throw new GlobalException("待办标签关联信息导入失败！");
        }
        return ResultBean.success("待办标签关联信息导入成功!", null);
    }
}
