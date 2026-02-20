package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.AnnouncementDetails;
import com.backend.domain.dto.AnnouncementDTO;
import com.backend.domain.entity.Announcement;
import com.backend.domain.excel.AnnouncementExcel;
import com.backend.domain.vo.AnnouncementVO;
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
public class AnnouncementConverterImpl implements AnnouncementConverter {

    @Override
    public Announcement announcementDTO2announcement(AnnouncementDTO announcementDTO) {
        if ( announcementDTO == null ) {
            return null;
        }

        Announcement announcement = new Announcement();

        announcement.setId( announcementDTO.getId() );
        announcement.setTitle( announcementDTO.getTitle() );
        announcement.setContent( announcementDTO.getContent() );
        announcement.setIsTop( announcementDTO.getIsTop() );

        return announcement;
    }

    @Override
    public AnnouncementDetails announcement2announcementDetails(Announcement announcement) {
        if ( announcement == null ) {
            return null;
        }

        AnnouncementDetails announcementDetails = new AnnouncementDetails();

        announcementDetails.setId( announcement.getId() );
        announcementDetails.setCreateTime( announcement.getCreateTime() );
        announcementDetails.setUpdateTime( announcement.getUpdateTime() );
        announcementDetails.setTitle( announcement.getTitle() );
        announcementDetails.setContent( announcement.getContent() );
        if ( announcement.getIsTop() != null ) {
            announcementDetails.setIsTop( String.valueOf( announcement.getIsTop() ) );
        }

        return announcementDetails;
    }

    @Override
    public PageBean<AnnouncementVO> announcementPageBean2announcementVOPageBean(PageBean<Announcement> announcementPageBean) {
        if ( announcementPageBean == null ) {
            return null;
        }

        PageBean<AnnouncementVO> pageBean = new PageBean<AnnouncementVO>();

        pageBean.setPageNum( announcementPageBean.getPageNum() );
        pageBean.setPageSize( announcementPageBean.getPageSize() );
        pageBean.setPages( announcementPageBean.getPages() );
        pageBean.setRecords( announcementListToAnnouncementVOList( announcementPageBean.getRecords() ) );

        return pageBean;
    }

    @Override
    public AnnouncementExcel announcement2announcementExcel(Announcement announcement) {
        if ( announcement == null ) {
            return null;
        }

        AnnouncementExcel announcementExcel = new AnnouncementExcel();

        announcementExcel.setId( announcement.getId() );
        announcementExcel.setCreateTime( announcement.getCreateTime() );
        announcementExcel.setUpdateTime( announcement.getUpdateTime() );
        announcementExcel.setTitle( announcement.getTitle() );
        announcementExcel.setContent( announcement.getContent() );
        if ( announcement.getIsTop() != null ) {
            announcementExcel.setIsTop( String.valueOf( announcement.getIsTop() ) );
        }

        return announcementExcel;
    }

    @Override
    public List<AnnouncementExcel> announcementList2announcementExcelList(List<Announcement> announcementList) {
        if ( announcementList == null ) {
            return null;
        }

        List<AnnouncementExcel> list = new ArrayList<AnnouncementExcel>( announcementList.size() );
        for ( Announcement announcement : announcementList ) {
            list.add( announcement2announcementExcel( announcement ) );
        }

        return list;
    }

    @Override
    public Announcement announcementExcel2announcement(AnnouncementExcel announcementExcel) {
        if ( announcementExcel == null ) {
            return null;
        }

        Announcement announcement = new Announcement();

        announcement.setId( announcementExcel.getId() );
        announcement.setCreateTime( announcementExcel.getCreateTime() );
        announcement.setUpdateTime( announcementExcel.getUpdateTime() );
        announcement.setTitle( announcementExcel.getTitle() );
        announcement.setContent( announcementExcel.getContent() );
        if ( announcementExcel.getIsTop() != null ) {
            announcement.setIsTop( Integer.parseInt( announcementExcel.getIsTop() ) );
        }

        return announcement;
    }

    @Override
    public List<Announcement> announcementExcelList2announcementList(List<AnnouncementExcel> announcementExcelList) {
        if ( announcementExcelList == null ) {
            return null;
        }

        List<Announcement> list = new ArrayList<Announcement>( announcementExcelList.size() );
        for ( AnnouncementExcel announcementExcel : announcementExcelList ) {
            list.add( announcementExcel2announcement( announcementExcel ) );
        }

        return list;
    }

    protected AnnouncementVO announcementToAnnouncementVO(Announcement announcement) {
        if ( announcement == null ) {
            return null;
        }

        AnnouncementVO announcementVO = new AnnouncementVO();

        announcementVO.setId( announcement.getId() );
        announcementVO.setTitle( announcement.getTitle() );
        announcementVO.setContent( announcement.getContent() );
        if ( announcement.getIsTop() != null ) {
            announcementVO.setIsTop( String.valueOf( announcement.getIsTop() ) );
        }

        return announcementVO;
    }

    protected List<AnnouncementVO> announcementListToAnnouncementVOList(List<Announcement> list) {
        if ( list == null ) {
            return null;
        }

        List<AnnouncementVO> list1 = new ArrayList<AnnouncementVO>( list.size() );
        for ( Announcement announcement : list ) {
            list1.add( announcementToAnnouncementVO( announcement ) );
        }

        return list1;
    }
}
