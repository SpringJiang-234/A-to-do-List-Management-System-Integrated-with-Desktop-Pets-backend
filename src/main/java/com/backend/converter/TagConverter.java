package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.TagDetails;
import com.backend.domain.dto.TagDTO;
import com.backend.domain.entity.Tag;
import com.backend.domain.excel.TagExcel;
import com.backend.domain.vo.TagVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagConverter {

    Tag tagDTO2tag(TagDTO tagDTO);

    /**
     * 将Tag对象转换为TagDetails对象
     * 用于转换标签基本信息
     *
     * @param tag 源Tag对象，包含标签基本信息
     * @return TagDetails对象，包含标签详细信息
     */
    TagDetails tag2tagDetails(Tag tag);

    /**
     * 将PageBean<Tag>分页对象转换为PageBean<TagVO>分页对象
     * 用于分页数据的类型转换，保持分页结构不变
     *
     * @param tagPageBean 源Tag分页对象，包含标签信息的分页数据
     * @return TagVO分页对象，包含转换后的标签VO对象的分页数据
     */
    PageBean<TagVO> tagPageBean2tagVOPageBean(PageBean<Tag> tagPageBean);

    /**
     * 将Tag对象转换为TagExcel对象
     * 用于Excel导出标签信息
     *
     * @param tag 源Tag对象，包含标签基本信息
     * @return TagExcel对象，包含标签Excel信息
     */
    TagExcel tag2tagExcel(Tag tag);

    /**
     * 将Tag列表转换为TagExcel列表
     * 批量转换标签信息用于Excel导出
     *
     * @param tagList 源Tag对象列表
     * @return TagExcel对象列表，用于Excel导出
     */
    List<TagExcel> tagList2tagExcelList(List<Tag> tagList);

    /**
     * 将TagExcel对象转换为Tag对象
     * 用于从Excel导入标签信息
     *
     * @param tagExcel 源TagExcel对象，包含从Excel导入的标签信息
     * @return Tag对象，包含转换后的标签基本信息
     */
    Tag tagExcel2tag(TagExcel tagExcel);

    /**
     * 将TagExcel列表转换为Tag列表
     * 批量转换从Excel导入的标签信息
     *
     * @param tagExcelList 源TagExcel对象列表，包含从Excel导入的标签信息
     * @return Tag对象列表，包含转换后的标签基本信息
     */
    List<Tag> tagExcelList2tagList(List<TagExcel> tagExcelList);

}

