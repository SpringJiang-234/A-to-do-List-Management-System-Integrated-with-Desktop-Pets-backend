package com.backend.controller.admin;

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
@RequestMapping("/admin/todo")
public class AdminTodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoConverter todoConverter;

    /**
     * 获取待办事项列表：暂时没用
     *
     * @param todoQuery 查询参数
     * @return 待办事项列表
     */
    @PostMapping("/page")
    public ResultBean<PageBean<TodoVO>> page(@RequestBody TodoQuery todoQuery) {
        final PageBean<Todo> todoPageBean = todoService.getPage(todoQuery);
        final PageBean<TodoVO> pageBean = todoConverter.todoPageBean2todoVOPageBean(todoPageBean);
        return ResultBean.success(pageBean);
    }

    /**
     * 获取待办事项详情：暂时没用
     *
     * @param id 待办事项id
     * @return 待办事项详情
     */
    @GetMapping("/details/{id}")
    public ResultBean<TodoDetails> getDetails(@PathVariable("id") Long id) {
        final Todo todo = todoService.getById(id);
        final TodoDetails todoDetails = todoConverter.todo2todoDetails(todo);
        return ResultBean.success(todoDetails);
    }

    /**
     * 修改待办事项：暂时没用
     *
     * @param todoDTO 待办事项信息
     * @return 修改结果
     */
    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody TodoDTO todoDTO) {
        final Todo todo = todoConverter.todoDTO2todo(todoDTO);
        todoService.insertOrUpdate(todo);
        return ResultBean.success("修改成功!", null);
    }

    /**
     * 添加待办事项：暂时没用
     *
     * @param todoDTO 待办事项信息
     * @return 添加结果
     */
    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody TodoDTO todoDTO) {
        final Todo todo = todoConverter.todoDTO2todo(todoDTO);
        todoService.insertOrUpdate(todo);
        return ResultBean.success("添加成功!", null);
    }

    /**
     * 删除待办事项：暂时没用
     *
     * @param id 待办事项id
     * @return 删除结果
     */
    @GetMapping("/delete/{id}")
    public ResultBean<Void> delete(@PathVariable("id") Long id) {
        todoService.deleteById(id);
        return ResultBean.success("删除成功!", null);
    }

    /**
     * 批量删除待办事项：暂时没用
     *
     * @param ids 待办事项id列表
     * @return 批量删除结果
     */
    @GetMapping("/batchDelete/{ids}")
    public ResultBean<Void> batchDelete(@PathVariable("ids") String ids) {
        todoService.deleteByIds(ids);
        return ResultBean.success("批量删除成功!", null);
    }

    /**
     * 导出待办事项列表：暂时没用
     *
     * @param response 响应对象
     * @param todoQuery 查询参数
     */
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

    /**
     * 导入待办事项列表：暂时没用
     *
     * @param file 文件
     * @return 导入结果
     */
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

    /**
     * 获取待办总数
     *
     * @return 待办总数
     */
    @GetMapping("/total")
    public ResultBean<Integer> getTotalTodos() {
        final int total = todoService.countTotalTodos();
        return ResultBean.success(total);
    }

    /**
     * 获取近七天待办数新增趋势
     *
     * @return 近七天每天的新待办数
     */
    @GetMapping("/trend")
    public ResultBean<java.util.List<java.util.Map<String, Object>>> getNewTodosTrend() {
        final java.util.List<java.util.Map<String, Object>> trend = todoService.getNewTodosTrend();
        return ResultBean.success(trend);
    }
}
