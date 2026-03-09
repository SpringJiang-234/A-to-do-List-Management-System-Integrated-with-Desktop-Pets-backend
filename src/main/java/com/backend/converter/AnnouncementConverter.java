package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.AnnouncementDetails;
import com.backend.domain.dto.AnnouncementDTO;
import com.backend.domain.entity.Announcement;
import com.backend.domain.enums.AnnouncementIsTop;
import com.backend.domain.excel.AnnouncementExcel;
import com.backend.domain.vo.AnnouncementVO;
import com.backend.domain.vo.ClientAnnouncementVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnnouncementConverter {

    Announcement announcementDTO2announcement(AnnouncementDTO announcementDTO);

    /**
     * 将Announcement对象转换为AnnouncementDetails对象
     * 用于转换公告基本信息
     *
     * @param announcement 源Announcement对象，包含公告基本信息
     * @return AnnouncementDetails对象，包含公告详细信息
     */
    @Mapping(source = "isTop", target = "isTop", qualifiedByName = "isTopToText")
    AnnouncementDetails announcement2announcementDetails(Announcement announcement);

    /**
     * 将PageBean<Announcement>分页对象转换为PageBean<AnnouncementVO>分页对象
     * 用于分页数据的类型转换，保持分页结构不变
     *
     * @param announcementPageBean 源Announcement分页对象，包含公告信息的分页数据
     * @return AnnouncementVO分页对象，包含转换后的公告VO对象的分页数据
     */
    PageBean<AnnouncementVO> announcementPageBean2announcementVOPageBean(PageBean<Announcement> announcementPageBean);

    /**
     * 将Announcement对象转换为AnnouncementVO对象
     * 用于列表展示
     *
     * @param announcement 源Announcement对象，包含公告基本信息
     * @return AnnouncementVO对象，包含公告展示信息
     */
    @Mapping(source = "isTop", target = "isTop", qualifiedByName = "isTopToText")
    AnnouncementVO announcement2announcementVO(Announcement announcement);

    /**
     * 将Announcement对象转换为ClientAnnouncementVO对象
     * 用于客户端公告列表展示
     *
     * @param announcement 源Announcement对象，包含公告基本信息
     * @return ClientAnnouncementVO对象，包含客户端公告展示信息
     */
    @Mapping(source = "isTop", target = "isTop", qualifiedByName = "isTopToText")
    ClientAnnouncementVO announcement2clientAnnouncementVO(Announcement announcement);

    /**
     * 将Announcement列表转换为ClientAnnouncementVO列表
     * 用于客户端公告列表展示
     *
     * @param announcementList 源Announcement对象列表
     * @return ClientAnnouncementVO对象列表
     */
    List<ClientAnnouncementVO> announcementList2clientAnnouncementVOList(List<Announcement> announcementList);

    /**
     * 将Announcement对象转换为AnnouncementExcel对象
     * 用于Excel导出公告信息
     *
     * @param announcement 源Announcement对象，包含公告基本信息
     * @return AnnouncementExcel对象，包含公告Excel信息
     */
    @Mapping(source = "isTop", target = "isTop", qualifiedByName = "isTopToText")
    AnnouncementExcel announcement2announcementExcel(Announcement announcement);

    /**
     * 将Announcement列表转换为AnnouncementExcel列表
     * 批量转换公告信息用于Excel导出
     *
     * @param announcementList 源Announcement对象列表
     * @return AnnouncementExcel对象列表，用于Excel导出
     */
    List<AnnouncementExcel> announcementList2announcementExcelList(List<Announcement> announcementList);

    /**
     * 将AnnouncementExcel对象转换为Announcement对象
     * 用于从Excel导入公告信息
     *
     * @param announcementExcel 源AnnouncementExcel对象，包含从Excel导入的公告信息
     * @return Announcement对象，包含转换后的公告基本信息
     */
    @Mapping(source = "isTop", target = "isTop", qualifiedByName = "textToIsTop")
    Announcement announcementExcel2announcement(AnnouncementExcel announcementExcel);

    /**
     * 将AnnouncementExcel列表转换为Announcement列表
     * 批量转换从Excel导入的公告信息
     *
     * @param announcementExcelList 源AnnouncementExcel对象列表，包含从Excel导入的公告信息
     * @return Announcement对象列表，包含转换后的公告基本信息
     */
    List<Announcement> announcementExcelList2announcementList(List<AnnouncementExcel> announcementExcelList);

    /**
     * 将Integer类型的isTop转换为String类型的文本
     * 用于导出Excel时显示中文
     *
     * @param isTop 是否置顶的数字编码
     * @return 是否置顶的中文文本
     */
    @Named("isTopToText")
    default String isTopToText(Integer isTop) {
        return AnnouncementIsTop.getTextByCode(isTop);
    }

    /**
     * 将String类型的文本转换为Integer类型的isTop
     * 用于导入Excel时解析中文
     *
     * @param text 是否置顶的中文文本
     * @return 是否置顶的数字编码
     */
    @Named("textToIsTop")
    default Integer textToIsTop(String text) {
        return AnnouncementIsTop.getCodeByText(text);
    }

}

