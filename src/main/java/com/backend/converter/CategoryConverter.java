package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.CategoryDetails;
import com.backend.domain.dto.CategoryDTO;
import com.backend.domain.entity.Category;
import com.backend.domain.excel.CategoryExcel;
import com.backend.domain.vo.CategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryConverter {

    Category categoryDTO2category(CategoryDTO categoryDTO);

    /**
     * 将Category对象转换为CategoryDetails对象
     * 用于转换类别基本信息
     *
     * @param category 源Category对象，包含类别基本信息
     * @return CategoryDetails对象，包含类别详细信息
     */
    CategoryDetails category2categoryDetails(Category category);

    /**
     * 将Category列表转换为CategoryVO列表
     * 用于转换类别列表
     *
     * @param categoryList 源Category对象列表
     * @return CategoryVO对象列表
     */
    List<CategoryVO> categoryList2categoryVOList(List<Category> categoryList);

    /**
     * 将PageBean<Category>分页对象转换为PageBean<CategoryVO>分页对象
     * 用于分页数据的类型转换，保持分页结构不变
     *
     * @param categoryPageBean 源Category分页对象，包含类别信息的分页数据
     * @return CategoryVO分页对象，包含转换后的类别VO对象的分页数据
     */
    PageBean<CategoryVO> categoryPageBean2categoryVOPageBean(PageBean<Category> categoryPageBean);

    /**
     * 将Category对象转换为CategoryExcel对象
     * 用于Excel导出类别信息
     *
     * @param category 源Category对象，包含类别基本信息
     * @return CategoryExcel对象，包含类别Excel信息
     */
    CategoryExcel category2categoryExcel(Category category);

    /**
     * 将Category列表转换为CategoryExcel列表
     * 批量转换类别信息用于Excel导出
     *
     * @param categoryList 源Category对象列表
     * @return CategoryExcel对象列表，用于Excel导出
     */
    List<CategoryExcel> categoryList2categoryExcelList(List<Category> categoryList);

    /**
     * 将CategoryExcel对象转换为Category对象
     * 用于从Excel导入类别信息
     *
     * @param categoryExcel 源CategoryExcel对象，包含从Excel导入的类别信息
     * @return Category对象，包含转换后的类别基本信息
     */
    Category categoryExcel2category(CategoryExcel categoryExcel);

    /**
     * 将CategoryExcel列表转换为Category列表
     * 批量转换从Excel导入的类别信息
     *
     * @param categoryExcelList 源CategoryExcel对象列表，包含从Excel导入的类别信息
     * @return Category对象列表，包含转换后的类别基本信息
     */
    List<Category> categoryExcelList2categoryList(List<CategoryExcel> categoryExcelList);

}

