package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.TodoDetails;
import com.backend.domain.dto.ClientTodoDTO;
import com.backend.domain.dto.TodoDTO;
import com.backend.domain.entity.Todo;
import com.backend.domain.excel.TodoExcel;
import com.backend.domain.vo.TodoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoConverter {

    Todo todoDTO2todo(TodoDTO todoDTO);

    Todo clientTodoDTO2todo(ClientTodoDTO clientTodoDTO);

    /**
     * 将Todo对象转换为TodoDetails对象
     * 用于转换待办事项基本信息
     *
     * @param todo 源Todo对象，包含待办事项基本信息
     * @return TodoDetails对象，包含待办事项详细信息
     */
    TodoDetails todo2todoDetails(Todo todo);

    /**
     * 将PageBean<Todo>分页对象转换为PageBean<TodoVO>分页对象
     * 用于分页数据的类型转换，保持分页结构不变
     *
     * @param todoPageBean 源Todo分页对象，包含待办事项信息的分页数据
     * @return TodoVO分页对象，包含转换后的待办事项VO对象的分页数据
     */
    PageBean<TodoVO> todoPageBean2todoVOPageBean(PageBean<Todo> todoPageBean);

    /**
     * 将Todo对象转换为TodoExcel对象
     * 用于Excel导出待办事项信息
     *
     * @param todo 源Todo对象，包含待办事项基本信息
     * @return TodoExcel对象，包含待办事项Excel信息
     */
    TodoExcel todo2todoExcel(Todo todo);

    /**
     * 将Todo列表转换为TodoExcel列表
     * 批量转换待办事项信息用于Excel导出
     *
     * @param todoList 源Todo对象列表
     * @return TodoExcel对象列表，用于Excel导出
     */
    List<TodoExcel> todoList2todoExcelList(List<Todo> todoList);

    /**
     * 将TodoExcel对象转换为Todo对象
     * 用于从Excel导入待办事项信息
     *
     * @param todoExcel 源TodoExcel对象，包含从Excel导入的待办事项信息
     * @return Todo对象，包含转换后的待办事项基本信息
     */
    Todo todoExcel2todo(TodoExcel todoExcel);

    /**
     * 将TodoExcel列表转换为Todo列表
     * 批量转换从Excel导入的待办事项信息
     *
     * @param todoExcelList 源TodoExcel对象列表，包含从Excel导入的待办事项信息
     * @return Todo对象列表，包含转换后的待办事项基本信息
     */
    List<Todo> todoExcelList2todoList(List<TodoExcel> todoExcelList);

}

