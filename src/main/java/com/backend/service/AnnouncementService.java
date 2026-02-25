package com.backend.service;

import com.backend.bean.PageBean;
import com.backend.domain.entity.Announcement;
import com.backend.domain.query.AnnouncementQuery;

import java.util.List;

public interface AnnouncementService {

    /**
     * 分页获取公告列表
     * @param announcementQuery 查询参数
     * @return 公告分页数据
     */
    PageBean<Announcement> getPage(AnnouncementQuery announcementQuery);

    /**
     * 获取所有符合查询条件的公告列表（不分页）
     * @param announcementQuery 查询参数
     * @return 公告列表
     */
    List<Announcement> getAll(AnnouncementQuery announcementQuery);

    /**
     * 根据ID获取公告信息
     * @param id 公告ID
     * @return 公告信息
     */
    Announcement getById(Long id);

    /**
     * 更新公告信息
     * @param announcement 公告信息对象
     * @return 更新成功的记录数
     */
    int insertOrUpdate(Announcement announcement);

    /**
     * 根据ID删除公告
     * @param id 公告ID
     * @return 删除成功的记录数
     */
    int deleteById(Long id);

    /**
     * 批量删除公告
     * @param ids 公告ID列表
     * @return 删除成功的记录数
     */
    int deleteByIds(String ids);

    /**
     * 批量插入公告
     * @param announcementList 公告列表
     * @return 插入成功的记录数
     */
    int batchInsert(List<Announcement> announcementList);

}
