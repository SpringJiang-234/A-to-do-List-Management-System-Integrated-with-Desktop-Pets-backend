package com.backend.mapper;

import com.backend.domain.entity.DesktopPet;
import java.util.List;

import com.backend.domain.entity.DesktopPet;
import com.backend.domain.query.DesktopPetQuery;
import org.apache.ibatis.annotations.Param;

public interface DesktopPetMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByPrimaryKeyIn(String[] array);

    int insert(DesktopPet record);

    int insertOrUpdate(DesktopPet record);

    int insertOrUpdateSelective(DesktopPet record);

    int insertSelective(DesktopPet record);

    DesktopPet selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DesktopPet record);

    int updateByPrimaryKey(DesktopPet record);

    int updateBatch(@Param("list") List<DesktopPet> list);

    int updateBatchUseMultiQuery(@Param("list") List<DesktopPet> list);

    int updateBatchSelective(@Param("list") List<DesktopPet> list);

    int batchInsert(@Param("list") List<DesktopPet> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<DesktopPet> list);

    int batchInsertOrUpdate(@Param("list") List<DesktopPet> list);

    List<DesktopPet> selectWithCondition(DesktopPetQuery desktopPetQuery);
}