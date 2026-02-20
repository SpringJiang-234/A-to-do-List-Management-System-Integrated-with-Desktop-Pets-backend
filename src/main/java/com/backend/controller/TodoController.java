package com.backend.controller;

import com.backend.bean.PageBean;
import com.backend.bean.ResultBean;
import com.backend.converter.TodoConverter;
import com.backend.domain.details.TodoDetails;
import com.backend.domain.dto.TodoDTO;
import com.backend.domain.entity.Todo;
import com.backend.domain.excel.TodoExcel;
import com.backend.domain.query.TodoQuery;
import com.backend.domain.vo.TodoVO;
import com.backend.ex.GlobalException;
import com.backend.service.TodoService;
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
 * 待办事项控制层
 *
 */
@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoConverter todoConverter;


    @PostMapping("/page")
    public ResultBean<PageBean<TodoVO>> page(@RequestBody TodoQuery todoQuery) {
        final PageBean<Todo> todoPageBean = todoService.getPage(todoQuery);
        final PageBean<TodoVO> pageBean = todoConverter.todoPageBean2todoVOPageBean(todoPageBean);
        return ResultBean.success(pageBean);
    }

    @GetMapping("/details/{id}")
    public ResultBean<TodoDetails> getDetails(@PathVariable("id") Long id) {
        final Todo todo = todoService.getById(id);
        final TodoDetails todoDetails = todoConverter.todo2todoDetails(todo);
        return ResultBean.success(todoDetails);
    }

    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody TodoDTO todoDTO) {
        final Todo todo = todoConverter.todoDTO2todo(todoDTO);
        todoService.insertOrUpdate(todo);
        return ResultBean.success("修改成功!", null);
    }

    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody TodoDTO todoDTO) {
        final Todo todo = todoConverter.todoDTO2todo(todoDTO);
        todoService.insertOrUpdate(todo);
        return ResultBean.success("添加成功!", null);
    }

    @GetMapping("/delete/{id}")
    public ResultBean<Void> delete(@PathVariable("id") Long id) {
        todoService.deleteById(id);
        return ResultBean.success("删除成功!", null);
    }

    @GetMapping("/batchDelete/{ids}")
    public ResultBean<Void> batchDelete(@PathVariable("ids") String ids) {
        todoService.deleteByIds(ids);
        return ResultBean.success("批量删除成功!", null);
    }

    @PostMapping("/export")
    public void exportData(HttpServletResponse response, @RequestBody TodoQuery todoQuery) {
        final PageBean<Todo> pageBean = todoService.getPage(todoQuery);

        final List<TodoExcel> list = todoConverter.todoList2todoExcelList(pageBean.getRecords());
        try {
            EasyExcelUtil.writeWithSheetsWeb(response, "todo列表")
                    .writeModel(TodoExcel.class, list, "todo")
                    .finish();
        } catch (IOException e) {
            throw new GlobalException("待办事项信息导出失败！");
        }
    }

    @PostMapping("/import")
    public ResultBean<Void> importData(@RequestPart(value = "file", required = true) MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            final EasyExcelListener<TodoExcel> listener = new EasyExcelListener<TodoExcel>() {
                @Override
                protected void exec(List<TodoExcel> list) {
                    final List<Todo> todoList = todoConverter.todoExcelList2todoList(list);
                    todoService.batchInsert(todoList);
                }
            };
            EasyExcelUtil.asyncReadModel(is, listener, TodoExcel.class);
        } catch (IOException e) {
            throw new GlobalException("待办事项信息导入失败！");
        }
        return ResultBean.success("待办事项信息导入成功!", null);
    }
}
