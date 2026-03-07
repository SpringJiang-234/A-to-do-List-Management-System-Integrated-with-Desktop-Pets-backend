package com.backend.controller.client;

import com.backend.bean.PageBean;
import com.backend.bean.ResultBean;
import com.backend.converter.TodoConverter;
import com.backend.domain.dto.TodoDTO;
import com.backend.domain.entity.Todo;
import com.backend.domain.query.TodoQuery;
import com.backend.domain.vo.TodoVO;
import com.backend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 获取待办事项列表
     *
     * @param todoQuery 查询参数
     * @return 待办事项列表
     */
    @PostMapping("/list")
    public ResultBean<PageBean<TodoVO>> list(@RequestBody TodoQuery todoQuery) {
        return null;
    }

    /**
     * 获取待办事项详情
     *
     * @param id 待办事项id
     * @return 待办事项详情
     */
    @GetMapping("/details/{id}")
    public ResultBean<Todo> getDetails(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 添加待办事项
     *
     * @param todoDTO 待办事项信息
     * @return 添加结果
     */
    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody TodoDTO todoDTO) {
        return null;
    }

    /**
     * 修改待办事项
     *
     * @param todoDTO 待办事项信息
     * @return 修改结果
     */
    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody TodoDTO todoDTO) {
        return null;
    }

    /**
     * 删除待办事项
     *
     * @param id 待办事项id
     * @return 删除结果
     */
    @GetMapping("/delete/{id}")
    public ResultBean<Void> delete(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 完成待办事项
     *
     * @param id 待办事项id
     * @return 完成结果
     */
    @GetMapping("/complete/{id}")
    public ResultBean<Void> complete(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 放弃待办事项
     *
     * @param id 待办事项id
     * @return 放弃结果
     */
    @GetMapping("/abandon/{id}")
    public ResultBean<Void> abandon(@PathVariable("id") Long id) {
        return null;
    }
}
