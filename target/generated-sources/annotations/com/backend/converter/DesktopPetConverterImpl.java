package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.DesktopPetDetails;
import com.backend.domain.dto.DesktopPetDTO;
import com.backend.domain.entity.DesktopPet;
import com.backend.domain.excel.DesktopPetExcel;
import com.backend.domain.vo.DesktopPetVO;
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
public class DesktopPetConverterImpl implements DesktopPetConverter {

    @Override
    public DesktopPet desktopPetDTO2desktopPet(DesktopPetDTO desktopPetDTO) {
        if ( desktopPetDTO == null ) {
            return null;
        }

        DesktopPet desktopPet = new DesktopPet();

        desktopPet.setId( desktopPetDTO.getId() );
        desktopPet.setUserId( desktopPetDTO.getUserId() );
        desktopPet.setNickname( desktopPetDTO.getNickname() );
        desktopPet.setEnergy( desktopPetDTO.getEnergy() );
        desktopPet.setMood( desktopPetDTO.getMood() );
        desktopPet.setIntimacy( desktopPetDTO.getIntimacy() );
        desktopPet.setExp( desktopPetDTO.getExp() );
        desktopPet.setLevel( desktopPetDTO.getLevel() );

        return desktopPet;
    }

    @Override
    public DesktopPetDetails desktopPet2desktopPetDetails(DesktopPet desktopPet) {
        if ( desktopPet == null ) {
            return null;
        }

        DesktopPetDetails desktopPetDetails = new DesktopPetDetails();

        desktopPetDetails.setId( desktopPet.getId() );
        desktopPetDetails.setCreateTime( desktopPet.getCreateTime() );
        desktopPetDetails.setUpdateTime( desktopPet.getUpdateTime() );
        desktopPetDetails.setUserId( desktopPet.getUserId() );
        desktopPetDetails.setNickname( desktopPet.getNickname() );
        desktopPetDetails.setEnergy( desktopPet.getEnergy() );
        desktopPetDetails.setMood( desktopPet.getMood() );
        desktopPetDetails.setIntimacy( desktopPet.getIntimacy() );
        desktopPetDetails.setExp( desktopPet.getExp() );
        desktopPetDetails.setLevel( desktopPet.getLevel() );

        return desktopPetDetails;
    }

    @Override
    public PageBean<DesktopPetVO> desktopPetPageBean2desktopPetVOPageBean(PageBean<DesktopPet> desktopPetPageBean) {
        if ( desktopPetPageBean == null ) {
            return null;
        }

        PageBean<DesktopPetVO> pageBean = new PageBean<DesktopPetVO>();

        pageBean.setPageNum( desktopPetPageBean.getPageNum() );
        pageBean.setPageSize( desktopPetPageBean.getPageSize() );
        pageBean.setPages( desktopPetPageBean.getPages() );
        pageBean.setRecords( desktopPetListToDesktopPetVOList( desktopPetPageBean.getRecords() ) );

        return pageBean;
    }

    @Override
    public DesktopPetExcel desktopPet2desktopPetExcel(DesktopPet desktopPet) {
        if ( desktopPet == null ) {
            return null;
        }

        DesktopPetExcel desktopPetExcel = new DesktopPetExcel();

        desktopPetExcel.setId( desktopPet.getId() );
        desktopPetExcel.setCreateTime( desktopPet.getCreateTime() );
        desktopPetExcel.setUpdateTime( desktopPet.getUpdateTime() );
        desktopPetExcel.setUserId( desktopPet.getUserId() );
        desktopPetExcel.setNickname( desktopPet.getNickname() );
        desktopPetExcel.setEnergy( desktopPet.getEnergy() );
        desktopPetExcel.setMood( desktopPet.getMood() );
        desktopPetExcel.setIntimacy( desktopPet.getIntimacy() );
        desktopPetExcel.setExp( desktopPet.getExp() );
        desktopPetExcel.setLevel( desktopPet.getLevel() );

        return desktopPetExcel;
    }

    @Override
    public List<DesktopPetExcel> desktopPetList2desktopPetExcelList(List<DesktopPet> desktopPetList) {
        if ( desktopPetList == null ) {
            return null;
        }

        List<DesktopPetExcel> list = new ArrayList<DesktopPetExcel>( desktopPetList.size() );
        for ( DesktopPet desktopPet : desktopPetList ) {
            list.add( desktopPet2desktopPetExcel( desktopPet ) );
        }

        return list;
    }

    @Override
    public DesktopPet desktopPetExcel2desktopPet(DesktopPetExcel desktopPetExcel) {
        if ( desktopPetExcel == null ) {
            return null;
        }

        DesktopPet desktopPet = new DesktopPet();

        desktopPet.setId( desktopPetExcel.getId() );
        desktopPet.setCreateTime( desktopPetExcel.getCreateTime() );
        desktopPet.setUpdateTime( desktopPetExcel.getUpdateTime() );
        desktopPet.setUserId( desktopPetExcel.getUserId() );
        desktopPet.setNickname( desktopPetExcel.getNickname() );
        desktopPet.setEnergy( desktopPetExcel.getEnergy() );
        desktopPet.setMood( desktopPetExcel.getMood() );
        desktopPet.setIntimacy( desktopPetExcel.getIntimacy() );
        desktopPet.setExp( desktopPetExcel.getExp() );
        desktopPet.setLevel( desktopPetExcel.getLevel() );

        return desktopPet;
    }

    @Override
    public List<DesktopPet> desktopPetExcelList2desktopPetList(List<DesktopPetExcel> desktopPetExcelList) {
        if ( desktopPetExcelList == null ) {
            return null;
        }

        List<DesktopPet> list = new ArrayList<DesktopPet>( desktopPetExcelList.size() );
        for ( DesktopPetExcel desktopPetExcel : desktopPetExcelList ) {
            list.add( desktopPetExcel2desktopPet( desktopPetExcel ) );
        }

        return list;
    }

    protected DesktopPetVO desktopPetToDesktopPetVO(DesktopPet desktopPet) {
        if ( desktopPet == null ) {
            return null;
        }

        DesktopPetVO desktopPetVO = new DesktopPetVO();

        desktopPetVO.setId( desktopPet.getId() );
        desktopPetVO.setUserId( desktopPet.getUserId() );
        desktopPetVO.setNickname( desktopPet.getNickname() );
        desktopPetVO.setLevel( desktopPet.getLevel() );

        return desktopPetVO;
    }

    protected List<DesktopPetVO> desktopPetListToDesktopPetVOList(List<DesktopPet> list) {
        if ( list == null ) {
            return null;
        }

        List<DesktopPetVO> list1 = new ArrayList<DesktopPetVO>( list.size() );
        for ( DesktopPet desktopPet : list ) {
            list1.add( desktopPetToDesktopPetVO( desktopPet ) );
        }

        return list1;
    }
}
