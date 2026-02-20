package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.UserTagDetails;
import com.backend.domain.dto.UserTagDTO;
import com.backend.domain.entity.UserTag;
import com.backend.domain.excel.UserTagExcel;
import com.backend.domain.vo.UserTagVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserTagConverter {

    UserTag userTagDTO2userTag(UserTagDTO userTagDTO);

    /**
     * 将UserTag对象转换为UserTagDetails对象
     * 用于转换用户标签关联基本信息
     *
     * @param userTag 源UserTag对象，包含用户标签关联基本信息
     * @return UserTagDetails对象，包含用户标签关联详细信息
     */
    UserTagDetails userTag2userTagDetails(UserTag userTag);

    /**
     * 将PageBean<UserTag>分页对象转换为PageBean<UserTagVO>分页对象
     * 用于分页数据的类型转换，保持分页结构不变
     *
     * @param userTagPageBean 源UserTag分页对象，包含用户标签关联信息的分页数据
     * @return UserTagVO分页对象，包含转换后的用户标签关联VO对象的分页数据
     */
    PageBean<UserTagVO> userTagPageBean2userTagVOPageBean(PageBean<UserTag> userTagPageBean);

    /**
     * 将UserTag对象转换为UserTagExcel对象
     * 用于Excel导出用户标签关联信息
     *
     * @param userTag 源UserTag对象，包含用户标签关联基本信息
     * @return UserTagExcel对象，包含用户标签关联Excel信息
     */
    UserTagExcel userTag2userTagExcel(UserTag userTag);

    /**
     * 将UserTag列表转换为UserTagExcel列表
     * 批量转换用户标签关联信息用于Excel导出
     *
     * @param userTagList 源UserTag对象列表
     * @return UserTagExcel对象列表，用于Excel导出
     */
    List<UserTagExcel> userTagList2userTagExcelList(List<UserTag> userTagList);

    /**
     * 将UserTagExcel对象转换为UserTag对象
     * 用于从Excel导入用户标签关联信息
     *
     * @param userTagExcel 源UserTagExcel对象，包含从Excel导入的用户标签关联信息
     * @return UserTag对象，包含转换后的用户标签关联基本信息
     */
    UserTag userTagExcel2userTag(UserTagExcel userTagExcel);

    /**
     * 将UserTagExcel列表转换为UserTag列表
     * 批量转换从Excel导入的用户标签关联信息
     *
     * @param userTagExcelList 源UserTagExcel对象列表，包含从Excel导入的用户标签关联信息
     * @return UserTag对象列表，包含转换后的用户标签关联基本信息
     */
    List<UserTag> userTagExcelList2userTagList(List<UserTagExcel> userTagExcelList);

}

