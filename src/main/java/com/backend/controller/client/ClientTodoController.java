package com.backend.controller.client;

import com.backend.bean.PageBean;
import com.backend.bean.ResultBean;
import com.backend.converter.TodoConverter;
import com.backend.domain.dto.ClientTodoDTO;
import com.backend.domain.entity.Todo;
import com.backend.domain.entity.TodoTag;
import com.backend.domain.query.ClientTodoQuery;
import com.backend.domain.query.TodoTagQuery;
import com.backend.domain.vo.TodoVO;
import com.backend.service.TodoService;
import com.backend.service.TodoTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 待办事项控制层
 */
@RestController
@RequestMapping("/client/todo")
public class ClientTodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoConverter todoConverter;
    @Autowired
    private TodoTagService todoTagService;

    /**
     * 获取待办事项列表
     *
     * @param clientTodoQuery 查询参数
     * @return 待办事项列表
     */
    @PostMapping("/list")
    public ResultBean<PageBean<TodoVO>> list(@RequestBody ClientTodoQuery clientTodoQuery) {
        final PageBean<Todo> todoPageBean = todoService.getPage(clientTodoQuery);
        
        final List<Long> tagIdList = clientTodoQuery.getTagIdList();
        if (tagIdList != null && !tagIdList.isEmpty()) {
            final List<Todo> filteredTodos = new ArrayList<>();
            for (Todo todo : todoPageBean.getRecords()) {
                final TodoTagQuery todoTagQuery = new TodoTagQuery();
                todoTagQuery.setTodoId(todo.getId());
                final com.backend.bean.PageBean<TodoTag> todoTagPageBean = todoTagService.getPage(todoTagQuery);
                final List<TodoTag> todoTags = todoTagPageBean.getRecords();
                
                final List<Long> todoTagIds = todoTags.stream()
                        .map(TodoTag::getTagId)
                        .collect(Collectors.toList());
                
                if (todoTagIds.containsAll(tagIdList)) {
                    filteredTodos.add(todo);
                }
            }
            todoPageBean.setRecords(filteredTodos);
        }
        
        final PageBean<TodoVO> pageBean = todoConverter.todoPageBean2todoVOPageBean(todoPageBean);
        return ResultBean.success(pageBean);
    }

    /**
     * 获取待办事项详情
     *
     * @param id 待办事项id
     * @return 待办事项详情
     */
    @GetMapping("/details/{id}")
    public ResultBean<Todo> getDetails(@PathVariable("id") Long id) {
        final Todo todo = todoService.getById(id);
        return ResultBean.success(todo);
    }

    /**
     * 添加待办事项
     *
     * @param clientTodoDTO 待办事项信息
     * @return 添加结果
     */
    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody ClientTodoDTO clientTodoDTO) {
        final Todo todo = todoConverter.clientTodoDTO2todo(clientTodoDTO);
        todoService.insertOrUpdate(todo);
        
        final List<Long> tagIdList = clientTodoDTO.getTagIdList();
        if (tagIdList != null && !tagIdList.isEmpty()) {
            final List<TodoTag> todoTagList = new ArrayList<>();
            for (Long tagId : tagIdList) {
                final TodoTag todoTag = new TodoTag();
                todoTag.setTodoId(todo.getId());
                todoTag.setTagId(tagId);
                todoTagList.add(todoTag);
            }
            todoTagService.batchInsert(todoTagList);
        }
        
        return ResultBean.success("添加成功!", null);
    }

    /**
     * 修改待办事项
     *
     * @param clientTodoDTO 待办事项信息
     * @return 修改结果
     */
    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody ClientTodoDTO clientTodoDTO) {
        final Todo todo = todoConverter.clientTodoDTO2todo(clientTodoDTO);
        todoService.insertOrUpdate(todo);
        
        final TodoTagQuery todoTagQuery = new TodoTagQuery();
        todoTagQuery.setTodoId(todo.getId());
        final com.backend.bean.PageBean<TodoTag> todoTagPageBean = todoTagService.getPage(todoTagQuery);
        final List<TodoTag> existingTodoTags = todoTagPageBean.getRecords();
        
        if (!existingTodoTags.isEmpty()) {
            final String ids = existingTodoTags.stream()
                    .map(TodoTag::getId)
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            todoTagService.deleteByIds(ids);
        }
        
        final List<Long> tagIdList = clientTodoDTO.getTagIdList();
        if (tagIdList != null && !tagIdList.isEmpty()) {
            final List<TodoTag> todoTagList = new ArrayList<>();
            for (Long tagId : tagIdList) {
                final TodoTag todoTag = new TodoTag();
                todoTag.setTodoId(todo.getId());
                todoTag.setTagId(tagId);
                todoTagList.add(todoTag);
            }
            todoTagService.batchInsert(todoTagList);
        }
        
        return ResultBean.success("修改成功!", null);
    }

    /**
     * 删除待办事项
     *
     * @param id 待办事项id
     * @return 删除结果
     */
    @GetMapping("/delete/{id}")
    public ResultBean<Void> delete(@PathVariable("id") Long id) {
        final TodoTagQuery todoTagQuery = new TodoTagQuery();
        todoTagQuery.setTodoId(id);
        final com.backend.bean.PageBean<TodoTag> todoTagPageBean = todoTagService.getPage(todoTagQuery);
        final List<TodoTag> existingTodoTags = todoTagPageBean.getRecords();
        
        if (!existingTodoTags.isEmpty()) {
            final String ids = existingTodoTags.stream()
                    .map(TodoTag::getId)
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            todoTagService.deleteByIds(ids);
        }
        
        todoService.deleteById(id);
        return ResultBean.success("删除成功!", null);
    }

    /**
     * 完成待办事项
     *
     * @param id 待办事项id
     * @return 完成结果
     */
    @GetMapping("/complete/{id}")
    public ResultBean<Void> complete(@PathVariable("id") Long id) {
        final Todo todo = todoService.getById(id);
        todo.setStatus(2);
        todo.setFinishTime(LocalDateTime.now());
        todoService.insertOrUpdate(todo);
        return ResultBean.success("完成成功!", null);
    }

    /**
     * 放弃待办事项
     *
     * @param id 待办事项id
     * @return 放弃结果
     */
    @GetMapping("/abandon/{id}")
    public ResultBean<Void> abandon(@PathVariable("id") Long id) {
        final Todo todo = todoService.getById(id);
        todo.setStatus(3);
        todo.setFinishTime(LocalDateTime.now());
        todoService.insertOrUpdate(todo);
        return ResultBean.success("放弃成功!", null);
    }
}
