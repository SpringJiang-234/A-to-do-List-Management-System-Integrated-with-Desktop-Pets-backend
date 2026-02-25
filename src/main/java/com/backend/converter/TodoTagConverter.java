package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.TodoTagDetails;
import com.backend.domain.dto.TodoTagDTO;
import com.backend.domain.entity.TodoTag;
import com.backend.domain.excel.TodoTagExcel;
import com.backend.domain.vo.TodoTagVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoTagConverter {

    TodoTag todoTagDTO2todoTag(TodoTagDTO todoTagDTO);

    /**
     * 将TodoTag对象转换为TodoTagDetails对象
     * 用于转换待办标签关联基本信息
     *
     * @param todoTag 源TodoTag对象，包含待办标签关联基本信息
     * @return TodoTagDetails对象，包含待办标签关联详细信息
     */
    TodoTagDetails todoTag2todoTagDetails(TodoTag todoTag);

    /**
     * 将PageBean<TodoTag>分页对象转换为PageBean<TodoTagVO>分页对象
     * 用于分页数据的类型转换，保持分页结构不变
     *
     * @param todoTagPageBean 源TodoTag分页对象，包含待办标签关联信息的分页数据
     * @return TodoTagVO分页对象，包含转换后的待办标签关联VO对象的分页数据
     */
    PageBean<TodoTagVO> todoTagPageBean2todoTagVOPageBean(PageBean<TodoTag> todoTagPageBean);

    /**
     * 将TodoTag对象转换为TodoTagExcel对象
     * 用于Excel导出待办标签关联信息
     *
     * @param todoTag 源TodoTag对象，包含待办标签关联基本信息
     * @return TodoTagExcel对象，包含待办标签关联Excel信息
     */
    TodoTagExcel todoTag2todoTagExcel(TodoTag todoTag);

    /**
     * 将TodoTag列表转换为TodoTagExcel列表
     * 批量转换待办标签关联信息用于Excel导出
     *
     * @param todoTagList 源TodoTag对象列表
     * @return TodoTagExcel对象列表，用于Excel导出
     */
    List<TodoTagExcel> todoTagList2todoTagExcelList(List<TodoTag> todoTagList);

    /**
     * 将TodoTagExcel对象转换为TodoTag对象
     * 用于从Excel导入待办标签关联信息
     *
     * @param todoTagExcel 源TodoTagExcel对象，包含从Excel导入的待办标签关联信息
     * @return TodoTag对象，包含转换后的待办标签关联基本信息
     */
    TodoTag todoTagExcel2todoTag(TodoTagExcel todoTagExcel);

    /**
     * 将TodoTagExcel列表转换为TodoTag列表
     * 批量转换从Excel导入的待办标签关联信息
     *
     * @param todoTagExcelList 源TodoTagExcel对象列表，包含从Excel导入的待办标签关联信息
     * @return TodoTag对象列表，包含转换后的待办标签关联基本信息
     */
    List<TodoTag> todoTagExcelList2todoTagList(List<TodoTagExcel> todoTagExcelList);

}

