package com.backend.controller.client;

import com.backend.bean.PageBean;
import com.backend.bean.ResultBean;
import com.backend.converter.TodoConverter;
import com.backend.domain.dto.ClientTodoDTO;
import com.backend.domain.dto.ClientTodoFocusTimeUpdateDTO;
import com.backend.domain.details.ClientTodoDetails;
import com.backend.domain.details.TagDetails;
import com.backend.domain.entity.Category;
import com.backend.domain.entity.Tag;
import com.backend.domain.entity.Todo;
import com.backend.domain.entity.TodoTag;
import com.backend.domain.query.ClientTodoQuery;
import com.backend.domain.query.TodoQuery;
import com.backend.domain.query.TodoTagQuery;
import com.backend.domain.vo.ClientTodoVO;
import com.backend.service.CategoryService;
import com.backend.service.TagService;
import com.backend.service.TodoService;
import com.backend.service.TodoTagService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/client/todo")
public class ClientTodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoConverter todoConverter;
    @Autowired
    private TodoTagService todoTagService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;

    /**
     * 获取待办事项列表
     *
     * @param clientTodoQuery 查询参数
     * @return 待办事项列表
     */
    @PostMapping("/list")
    public ResultBean<List<ClientTodoVO>> list(@RequestBody ClientTodoQuery clientTodoQuery) {
        final List<Todo> todoList = todoService.getClientList(clientTodoQuery);
        
        final List<Long> tagIdList = clientTodoQuery.getTagIdList();
        if (tagIdList != null && !tagIdList.isEmpty()) {
            final List<Todo> filteredTodos = new ArrayList<>();
            for (Todo todo : todoList) {
                final TodoTagQuery todoTagQuery = new TodoTagQuery();
                todoTagQuery.setTodoId(todo.getId());
                final com.backend.bean.PageBean<TodoTag> todoTagPageBean = todoTagService.getPage(todoTagQuery);
                final List<TodoTag> todoTags = todoTagPageBean.getRecords();
                
                final List<Long> todoTagIds = todoTags.stream()
                        .map(TodoTag::getTagId)
                        .collect(Collectors.toList());
                
                for (Long tagId : tagIdList) {
                    if (todoTagIds.contains(tagId)) {
                        filteredTodos.add(todo);
                        break;
                    }
                }
            }
            final List<ClientTodoVO> clientTodoVOList = todoConverter.todoList2clientTodoVOList(filteredTodos);
            return ResultBean.success(clientTodoVOList);
        }
        
        final List<ClientTodoVO> clientTodoVOList = todoConverter.todoList2clientTodoVOList(todoList);
        return ResultBean.success(clientTodoVOList);
    }

    /**
     * 获取分类/标签页面待办事项列表
     *
     * @param todoQuery 查询参数
     * @return 待办事项列表
     */
    @PostMapping("/list-by-category-or-tag")
    public ResultBean<List<ClientTodoVO>> listByCategoryOrTag(@RequestBody TodoQuery todoQuery) {
        final List<Todo> todoList = todoService.getList(todoQuery);
        final List<ClientTodoVO> clientTodoVOList = todoConverter.todoList2clientTodoVOList(todoList);
        return ResultBean.success(clientTodoVOList);
    }

    /**
     * 获取待办事项详情
     *
     * @param id 待办事项id
     * @return 待办事项详情
     */
    @GetMapping("/details/{id}")
    public ResultBean<ClientTodoDetails> getDetails(@PathVariable("id") Long id) {
        log.info("getDetails方法被调用，id: {}", id);
        final Todo todo = todoService.getById(id);
        final ClientTodoDetails clientTodoDetails = new ClientTodoDetails();
        
        clientTodoDetails.setId(todo.getId());
        clientTodoDetails.setUserId(todo.getUserId());
        clientTodoDetails.setTitle(todo.getTitle());
        clientTodoDetails.setContent(todo.getContent());
        clientTodoDetails.setStartTime(todo.getStartTime() != null ? todo.getStartTime().toString() : null);
        clientTodoDetails.setEndTime(todo.getEndTime() != null ? todo.getEndTime().toString() : null);
        clientTodoDetails.setStatus(String.valueOf(todo.getStatus()));
        clientTodoDetails.setIsTop(String.valueOf(todo.getIsTop()));
        clientTodoDetails.setPriority(todo.getPriority());
        
        if (todo.getCategoryId() != null) {
            final Category category = categoryService.getById(todo.getCategoryId());
            if (category != null) {
                clientTodoDetails.setCategoryName(category.getName());
            }
        }
        
        final TodoTagQuery todoTagQuery = new TodoTagQuery();
        todoTagQuery.setTodoId(id);
        final com.backend.bean.PageBean<TodoTag> todoTagPageBean = todoTagService.getPage(todoTagQuery);
        final List<TodoTag> todoTags = todoTagPageBean.getRecords();
        
        if (!todoTags.isEmpty()) {
            final List<Long> tagIds = todoTags.stream()
                    .map(TodoTag::getTagId)
                    .collect(Collectors.toList());
            
            final List<TagDetails> tagDetailsList = new ArrayList<>();
            for (Long tagId : tagIds) {
                final Tag tag = tagService.getById(tagId);
                if (tag != null) {
                    final TagDetails tagDetails = new TagDetails();
                    tagDetails.setId(tag.getId());
                    tagDetails.setUserId(tag.getUserId());
                    tagDetails.setName(tag.getName());
                    tagDetails.setColor(tag.getColor());
                    tagDetails.setSortOrder(tag.getSortOrder());
                    tagDetailsList.add(tagDetails);
                }
            }
            clientTodoDetails.setTags(tagDetailsList);
        }
        
        log.info("getDetails方法执行完成，返回结果: {}", clientTodoDetails);
        return ResultBean.success(clientTodoDetails);
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
        
        todoService.softDeleteById(id);
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
     * 取消完成待办事项
     *
     * @param id 待办事项id
     * @return 取消完成结果
     */
    @GetMapping("/cancel-complete/{id}")
    public ResultBean<Void> cancelComplete(@PathVariable("id") Long id) {
        final Todo todo = todoService.getById(id);
        todo.setStatus(1);
        todo.setFinishTime(null);
        todoService.insertOrUpdate(todo);
        return ResultBean.success("取消完成成功!", null);
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

    /**
     * 待办事项计时累加
     *
     * @param dto 包含id和focusTime的DTO
     * @return 更新结果
     */
    @PostMapping("/updateFocusTime")
    public ResultBean<Void> updateFocusTime(@RequestBody com.backend.domain.dto.ClientTodoFocusTimeUpdateDTO dto) {
        if (dto.getId() == null || dto.getFocusTime() == null) {
            return ResultBean.error("参数错误", null);
        }
        
        todoService.updateFocusTime(dto.getId(), dto.getFocusTime());
        return ResultBean.success("更新成功", null);
    }
}
