package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.UserTagDetails;
import com.backend.domain.dto.UserTagDTO;
import com.backend.domain.entity.UserTag;
import com.backend.domain.excel.UserTagExcel;
import com.backend.domain.vo.UserTagVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-20T15:21:24+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class UserTagConverterImpl implements UserTagConverter {

    @Override
    public UserTag userTagDTO2userTag(UserTagDTO userTagDTO) {
        if ( userTagDTO == null ) {
            return null;
        }

        UserTag userTag = new UserTag();

        userTag.setId( userTagDTO.getId() );
        userTag.setUserId( userTagDTO.getUserId() );
        userTag.setTagId( userTagDTO.getTagId() );

        return userTag;
    }

    @Override
    public UserTagDetails userTag2userTagDetails(UserTag userTag) {
        if ( userTag == null ) {
            return null;
        }

        UserTagDetails userTagDetails = new UserTagDetails();

        userTagDetails.setId( userTag.getId() );
        userTagDetails.setCreateTime( userTag.getCreateTime() );
        userTagDetails.setUpdateTime( userTag.getUpdateTime() );
        userTagDetails.setUserId( userTag.getUserId() );
        userTagDetails.setTagId( userTag.getTagId() );

        return userTagDetails;
    }

    @Override
    public PageBean<UserTagVO> userTagPageBean2userTagVOPageBean(PageBean<UserTag> userTagPageBean) {
        if ( userTagPageBean == null ) {
            return null;
        }

        PageBean<UserTagVO> pageBean = new PageBean<UserTagVO>();

        pageBean.setPageNum( userTagPageBean.getPageNum() );
        pageBean.setPageSize( userTagPageBean.getPageSize() );
        pageBean.setPages( userTagPageBean.getPages() );
        pageBean.setRecords( userTagListToUserTagVOList( userTagPageBean.getRecords() ) );

        return pageBean;
    }

    @Override
    public UserTagExcel userTag2userTagExcel(UserTag userTag) {
        if ( userTag == null ) {
            return null;
        }

        UserTagExcel userTagExcel = new UserTagExcel();

        userTagExcel.setId( userTag.getId() );
        userTagExcel.setCreateTime( userTag.getCreateTime() );
        userTagExcel.setUpdateTime( userTag.getUpdateTime() );
        userTagExcel.setUserId( userTag.getUserId() );
        userTagExcel.setTagId( userTag.getTagId() );

        return userTagExcel;
    }

    @Override
    public List<UserTagExcel> userTagList2userTagExcelList(List<UserTag> userTagList) {
        if ( userTagList == null ) {
            return null;
        }

        List<UserTagExcel> list = new ArrayList<UserTagExcel>( userTagList.size() );
        for ( UserTag userTag : userTagList ) {
            list.add( userTag2userTagExcel( userTag ) );
        }

        return list;
    }

    @Override
    public UserTag userTagExcel2userTag(UserTagExcel userTagExcel) {
        if ( userTagExcel == null ) {
            return null;
        }

        UserTag userTag = new UserTag();

        userTag.setId( userTagExcel.getId() );
        userTag.setCreateTime( userTagExcel.getCreateTime() );
        userTag.setUpdateTime( userTagExcel.getUpdateTime() );
        userTag.setUserId( userTagExcel.getUserId() );
        userTag.setTagId( userTagExcel.getTagId() );

        return userTag;
    }

    @Override
    public List<UserTag> userTagExcelList2userTagList(List<UserTagExcel> userTagExcelList) {
        if ( userTagExcelList == null ) {
            return null;
        }

        List<UserTag> list = new ArrayList<UserTag>( userTagExcelList.size() );
        for ( UserTagExcel userTagExcel : userTagExcelList ) {
            list.add( userTagExcel2userTag( userTagExcel ) );
        }

        return list;
    }

    protected UserTagVO userTagToUserTagVO(UserTag userTag) {
        if ( userTag == null ) {
            return null;
        }

        UserTagVO userTagVO = new UserTagVO();

        userTagVO.setId( userTag.getId() );
        userTagVO.setUserId( userTag.getUserId() );
        userTagVO.setTagId( userTag.getTagId() );

        return userTagVO;
    }

    protected List<UserTagVO> userTagListToUserTagVOList(List<UserTag> list) {
        if ( list == null ) {
            return null;
        }

        List<UserTagVO> list1 = new ArrayList<UserTagVO>( list.size() );
        for ( UserTag userTag : list ) {
            list1.add( userTagToUserTagVO( userTag ) );
        }

        return list1;
    }
}
