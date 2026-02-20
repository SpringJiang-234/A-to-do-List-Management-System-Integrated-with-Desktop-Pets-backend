package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.TagDetails;
import com.backend.domain.dto.TagDTO;
import com.backend.domain.entity.Tag;
import com.backend.domain.excel.TagExcel;
import com.backend.domain.vo.TagVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-20T15:21:25+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class TagConverterImpl implements TagConverter {

    @Override
    public Tag tagDTO2tag(TagDTO tagDTO) {
        if ( tagDTO == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setId( tagDTO.getId() );
        tag.setUserId( tagDTO.getUserId() );
        tag.setName( tagDTO.getName() );
        tag.setColor( tagDTO.getColor() );
        tag.setSortOrder( tagDTO.getSortOrder() );

        return tag;
    }

    @Override
    public TagDetails tag2tagDetails(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDetails tagDetails = new TagDetails();

        tagDetails.setId( tag.getId() );
        tagDetails.setCreateTime( tag.getCreateTime() );
        tagDetails.setUpdateTime( tag.getUpdateTime() );
        tagDetails.setUserId( tag.getUserId() );
        tagDetails.setName( tag.getName() );
        tagDetails.setColor( tag.getColor() );
        tagDetails.setSortOrder( tag.getSortOrder() );

        return tagDetails;
    }

    @Override
    public PageBean<TagVO> tagPageBean2tagVOPageBean(PageBean<Tag> tagPageBean) {
        if ( tagPageBean == null ) {
            return null;
        }

        PageBean<TagVO> pageBean = new PageBean<TagVO>();

        pageBean.setPageNum( tagPageBean.getPageNum() );
        pageBean.setPageSize( tagPageBean.getPageSize() );
        pageBean.setPages( tagPageBean.getPages() );
        pageBean.setRecords( tagListToTagVOList( tagPageBean.getRecords() ) );

        return pageBean;
    }

    @Override
    public TagExcel tag2tagExcel(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagExcel tagExcel = new TagExcel();

        tagExcel.setId( tag.getId() );
        tagExcel.setCreateTime( tag.getCreateTime() );
        tagExcel.setUpdateTime( tag.getUpdateTime() );
        tagExcel.setUserId( tag.getUserId() );
        tagExcel.setName( tag.getName() );
        tagExcel.setColor( tag.getColor() );
        tagExcel.setSortOrder( tag.getSortOrder() );

        return tagExcel;
    }

    @Override
    public List<TagExcel> tagList2tagExcelList(List<Tag> tagList) {
        if ( tagList == null ) {
            return null;
        }

        List<TagExcel> list = new ArrayList<TagExcel>( tagList.size() );
        for ( Tag tag : tagList ) {
            list.add( tag2tagExcel( tag ) );
        }

        return list;
    }

    @Override
    public Tag tagExcel2tag(TagExcel tagExcel) {
        if ( tagExcel == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setId( tagExcel.getId() );
        tag.setCreateTime( tagExcel.getCreateTime() );
        tag.setUpdateTime( tagExcel.getUpdateTime() );
        tag.setUserId( tagExcel.getUserId() );
        tag.setName( tagExcel.getName() );
        tag.setColor( tagExcel.getColor() );
        tag.setSortOrder( tagExcel.getSortOrder() );

        return tag;
    }

    @Override
    public List<Tag> tagExcelList2tagList(List<TagExcel> tagExcelList) {
        if ( tagExcelList == null ) {
            return null;
        }

        List<Tag> list = new ArrayList<Tag>( tagExcelList.size() );
        for ( TagExcel tagExcel : tagExcelList ) {
            list.add( tagExcel2tag( tagExcel ) );
        }

        return list;
    }

    protected TagVO tagToTagVO(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagVO tagVO = new TagVO();

        tagVO.setId( tag.getId() );
        tagVO.setUserId( tag.getUserId() );
        tagVO.setName( tag.getName() );
        tagVO.setColor( tag.getColor() );
        tagVO.setSortOrder( tag.getSortOrder() );

        return tagVO;
    }

    protected List<TagVO> tagListToTagVOList(List<Tag> list) {
        if ( list == null ) {
            return null;
        }

        List<TagVO> list1 = new ArrayList<TagVO>( list.size() );
        for ( Tag tag : list ) {
            list1.add( tagToTagVO( tag ) );
        }

        return list1;
    }
}
