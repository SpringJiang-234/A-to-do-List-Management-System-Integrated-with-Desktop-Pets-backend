package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.CategoryDetails;
import com.backend.domain.dto.CategoryDTO;
import com.backend.domain.entity.Category;
import com.backend.domain.excel.CategoryExcel;
import com.backend.domain.vo.CategoryVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-19T21:47:09+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class CategoryConverterImpl implements CategoryConverter {

    @Override
    public Category categoryDTO2category(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setUserId( categoryDTO.getUserId() );
        category.setName( categoryDTO.getName() );
        category.setIsDefault( categoryDTO.getIsDefault() );
        category.setSortOrder( categoryDTO.getSortOrder() );

        return category;
    }

    @Override
    public CategoryDetails category2categoryDetails(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDetails categoryDetails = new CategoryDetails();

        categoryDetails.setId( category.getId() );
        categoryDetails.setCreateTime( category.getCreateTime() );
        categoryDetails.setUpdateTime( category.getUpdateTime() );
        categoryDetails.setUserId( category.getUserId() );
        categoryDetails.setName( category.getName() );
        if ( category.getIsDefault() != null ) {
            categoryDetails.setIsDefault( String.valueOf( category.getIsDefault() ) );
        }
        categoryDetails.setSortOrder( category.getSortOrder() );

        return categoryDetails;
    }

    @Override
    public PageBean<CategoryVO> categoryPageBean2categoryVOPageBean(PageBean<Category> categoryPageBean) {
        if ( categoryPageBean == null ) {
            return null;
        }

        PageBean<CategoryVO> pageBean = new PageBean<CategoryVO>();

        pageBean.setPageNum( categoryPageBean.getPageNum() );
        pageBean.setPageSize( categoryPageBean.getPageSize() );
        pageBean.setPages( categoryPageBean.getPages() );
        pageBean.setRecords( categoryListToCategoryVOList( categoryPageBean.getRecords() ) );

        return pageBean;
    }

    @Override
    public CategoryExcel category2categoryExcel(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryExcel categoryExcel = new CategoryExcel();

        categoryExcel.setId( category.getId() );
        categoryExcel.setCreateTime( category.getCreateTime() );
        categoryExcel.setUpdateTime( category.getUpdateTime() );
        categoryExcel.setUserId( category.getUserId() );
        categoryExcel.setName( category.getName() );
        if ( category.getIsDefault() != null ) {
            categoryExcel.setIsDefault( String.valueOf( category.getIsDefault() ) );
        }
        categoryExcel.setSortOrder( category.getSortOrder() );

        return categoryExcel;
    }

    @Override
    public List<CategoryExcel> categoryList2categoryExcelList(List<Category> categoryList) {
        if ( categoryList == null ) {
            return null;
        }

        List<CategoryExcel> list = new ArrayList<CategoryExcel>( categoryList.size() );
        for ( Category category : categoryList ) {
            list.add( category2categoryExcel( category ) );
        }

        return list;
    }

    @Override
    public Category categoryExcel2category(CategoryExcel categoryExcel) {
        if ( categoryExcel == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryExcel.getId() );
        category.setCreateTime( categoryExcel.getCreateTime() );
        category.setUpdateTime( categoryExcel.getUpdateTime() );
        category.setUserId( categoryExcel.getUserId() );
        category.setName( categoryExcel.getName() );
        if ( categoryExcel.getIsDefault() != null ) {
            category.setIsDefault( Integer.parseInt( categoryExcel.getIsDefault() ) );
        }
        category.setSortOrder( categoryExcel.getSortOrder() );

        return category;
    }

    @Override
    public List<Category> categoryExcelList2categoryList(List<CategoryExcel> categoryExcelList) {
        if ( categoryExcelList == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( categoryExcelList.size() );
        for ( CategoryExcel categoryExcel : categoryExcelList ) {
            list.add( categoryExcel2category( categoryExcel ) );
        }

        return list;
    }

    protected CategoryVO categoryToCategoryVO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryVO categoryVO = new CategoryVO();

        categoryVO.setId( category.getId() );
        categoryVO.setUserId( category.getUserId() );
        categoryVO.setName( category.getName() );
        if ( category.getIsDefault() != null ) {
            categoryVO.setIsDefault( String.valueOf( category.getIsDefault() ) );
        }
        categoryVO.setSortOrder( category.getSortOrder() );

        return categoryVO;
    }

    protected List<CategoryVO> categoryListToCategoryVOList(List<Category> list) {
        if ( list == null ) {
            return null;
        }

        List<CategoryVO> list1 = new ArrayList<CategoryVO>( list.size() );
        for ( Category category : list ) {
            list1.add( categoryToCategoryVO( category ) );
        }

        return list1;
    }
}
