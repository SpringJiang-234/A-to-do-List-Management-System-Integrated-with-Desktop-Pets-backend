package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.DesktopPetDetails;
import com.backend.domain.dto.DesktopPetDTO;
import com.backend.domain.entity.DesktopPet;
import com.backend.domain.excel.DesktopPetExcel;
import com.backend.domain.vo.DesktopPetVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DesktopPetConverter {

    DesktopPet desktopPetDTO2desktopPet(DesktopPetDTO desktopPetDTO);

    /**
     * 将DesktopPet对象转换为DesktopPetDetails对象
     * 用于转换桌宠基本信息
     *
     * @param desktopPet 源DesktopPet对象，包含桌宠基本信息
     * @return DesktopPetDetails对象，包含桌宠详细信息
     */
    DesktopPetDetails desktopPet2desktopPetDetails(DesktopPet desktopPet);

    /**
     * 将PageBean<DesktopPet>分页对象转换为PageBean<DesktopPetVO>分页对象
     * 用于分页数据的类型转换，保持分页结构不变
     *
     * @param desktopPetPageBean 源DesktopPet分页对象，包含桌宠信息的分页数据
     * @return DesktopPetVO分页对象，包含转换后的桌宠VO对象的分页数据
     */
    PageBean<DesktopPetVO> desktopPetPageBean2desktopPetVOPageBean(PageBean<DesktopPet> desktopPetPageBean);

    /**
     * 将DesktopPet对象转换为DesktopPetVO对象
     * 用于列表展示
     *
     * @param desktopPet 源DesktopPet对象，包含桌宠基本信息
     * @return DesktopPetVO对象，包含桌宠展示信息
     */
    DesktopPetVO desktopPet2desktopPetVO(DesktopPet desktopPet);

    /**
     * 将DesktopPet对象转换为DesktopPetExcel对象
     * 用于Excel导出桌宠信息
     *
     * @param desktopPet 源DesktopPet对象，包含桌宠基本信息
     * @return DesktopPetExcel对象，包含桌宠Excel信息
     */
    DesktopPetExcel desktopPet2desktopPetExcel(DesktopPet desktopPet);

    /**
     * 将DesktopPet列表转换为DesktopPetExcel列表
     * 批量转换桌宠信息用于Excel导出
     *
     * @param desktopPetList 源DesktopPet对象列表
     * @return DesktopPetExcel对象列表，用于Excel导出
     */
    List<DesktopPetExcel> desktopPetList2desktopPetExcelList(List<DesktopPet> desktopPetList);

    /**
     * 将DesktopPetExcel对象转换为DesktopPet对象
     * 用于从Excel导入桌宠信息
     *
     * @param desktopPetExcel 源DesktopPetExcel对象，包含从Excel导入的桌宠信息
     * @return DesktopPet对象，包含转换后的桌宠基本信息
     */
    DesktopPet desktopPetExcel2desktopPet(DesktopPetExcel desktopPetExcel);

    /**
     * 将DesktopPetExcel列表转换为DesktopPet列表
     * 批量转换从Excel导入的桌宠信息
     *
     * @param desktopPetExcelList 源DesktopPetExcel对象列表，包含从Excel导入的桌宠信息
     * @return DesktopPet对象列表，包含转换后的桌宠基本信息
     */
    List<DesktopPet> desktopPetExcelList2desktopPetList(List<DesktopPetExcel> desktopPetExcelList);

}

