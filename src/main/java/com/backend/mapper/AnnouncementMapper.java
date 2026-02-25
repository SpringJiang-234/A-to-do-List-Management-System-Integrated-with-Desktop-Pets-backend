package com.backend.mapper;

import com.backend.domain.entity.Announcement;

import java.util.List;

import com.backend.domain.entity.Announcement;
import com.backend.domain.query.AnnouncementQuery;
import org.apache.ibatis.annotations.Param;

public interface AnnouncementMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByPrimaryKeyIn(String[] array);

    int insert(Announcement record);

    int insertOrUpdate(Announcement record);

    int insertOrUpdateSelective(Announcement record);

    int insertSelective(Announcement record);

    Announcement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKey(Announcement record);

    int updateBatch(@Param("list") List<Announcement> list);

    int updateBatchUseMultiQuery(@Param("list") List<Announcement> list);

    int updateBatchSelective(@Param("list") List<Announcement> list);

    int batchInsert(@Param("list") List<Announcement> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<Announcement> list);

    int batchInsertOrUpdate(@Param("list") List<Announcement> list);

    List<Announcement> selectWithCondition(AnnouncementQuery announcementQuery);

    int countByCondition(AnnouncementQuery announcementQuery);
}