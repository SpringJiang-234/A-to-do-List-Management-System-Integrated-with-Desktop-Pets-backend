package com.backend.mapper;

import com.backend.domain.entity.DesktopPet;
import java.util.List;
import java.util.Map;

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

    int countByCondition(DesktopPetQuery desktopPetQuery);

    /**
     * 获取宠物等级分布
     * @return 等级分布列表
     */
    List<Map<String, Object>> getPetLevelDistribution();

    /**
     * 获取宠物活力值分布
     * @return 活力值分布列表
     */
    List<Map<String, Object>> getPetEnergyDistribution();

    /**
     * 获取宠物心情值分布
     * @return 心情值分布列表
     */
    List<Map<String, Object>> getPetMoodDistribution();

    /**
     * 获取宠物亲密度分布
     * @return 亲密度分布列表
     */
    List<Map<String, Object>> getPetIntimacyDistribution();

    /**
     * 获取最高等级宠物排行
     * @param limit 限制数量
     * @return 最高等级宠物列表
     */
    List<Map<String, Object>> getTopLevelPets(@Param("limit") int limit);
}