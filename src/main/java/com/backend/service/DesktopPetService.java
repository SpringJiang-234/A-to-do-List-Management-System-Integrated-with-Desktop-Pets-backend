package com.backend.service;

import com.backend.bean.PageBean;
import com.backend.domain.entity.DesktopPet;
import com.backend.domain.query.DesktopPetQuery;

import java.util.List;

public interface DesktopPetService {

    /**
     * 分页获取桌宠列表
     * @param desktopPetQuery 查询参数
     * @return 桌宠分页数据
     */
    PageBean<DesktopPet> getPage(DesktopPetQuery desktopPetQuery);

    /**
     * 根据ID获取桌宠信息
     * @param id 桌宠ID
     * @return 桌宠信息
     */
    DesktopPet getById(Long id);

    /**
     * 更新桌宠信息
     * @param desktopPet 桌宠信息对象
     * @return 更新成功的记录数
     */
    int insertOrUpdate(DesktopPet desktopPet);

    /**
     * 根据ID删除桌宠
     * @param id 桌宠ID
     * @return 删除成功的记录数
     */
    int deleteById(Long id);

    /**
     * 批量删除桌宠
     * @param ids 桌宠ID列表
     * @return 删除成功的记录数
     */
    int deleteByIds(String ids);

    /**
     * 批量插入桌宠
     * @param desktopPetList 桌宠列表
     * @return 插入成功的记录数
     */
    int batchInsert(List<DesktopPet> desktopPetList);

}
