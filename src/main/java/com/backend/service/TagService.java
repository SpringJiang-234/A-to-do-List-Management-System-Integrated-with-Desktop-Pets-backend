package com.backend.service;

import com.backend.bean.PageBean;
import com.backend.domain.entity.Tag;
import com.backend.domain.query.TagQuery;

import java.util.List;

public interface TagService {

    /**
     * 分页获取标签列表
     * @param tagQuery 查询参数
     * @return 标签分页数据
     */
    PageBean<Tag> getPage(TagQuery tagQuery);

    /**
     * 获取用户的所有标签列表
     * @param userId 用户ID
     * @return 标签列表
     */
    List<Tag> getListByUserId(Long userId);

    /**
     * 根据ID获取标签信息
     * @param id 标签ID
     * @return 标签信息
     */
    Tag getById(Long id);

    /**
     * 更新标签信息
     * @param tag 标签信息对象
     * @return 更新成功的记录数
     */
    int insertOrUpdate(Tag tag);

    /**
     * 根据ID删除标签
     * @param id 标签ID
     * @return 删除成功的记录数
     */
    int deleteById(Long id);

    /**
     * 批量删除标签
     * @param ids 标签ID列表
     * @return 删除成功的记录数
     */
    int deleteByIds(String ids);

    /**
     * 批量插入标签
     * @param tagList 标签列表
     * @return 插入成功的记录数
     */
    int batchInsert(List<Tag> tagList);

    /**
     * 获取标签总数
     * @return 标签总数
     */
    int countTotalTags();

    /**
     * 获取近七天标签数新增趋势
     * @return 近七天每天的新标签数
     */
    java.util.List<java.util.Map<String, Object>> getNewTagsTrend();

}
