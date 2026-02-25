package com.backend.mapper;

import com.backend.domain.entity.UserTag;
import java.util.List;

import com.backend.domain.query.UserTagQuery;
import org.apache.ibatis.annotations.Param;

public interface UserTagMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByPrimaryKeyIn(String[] array);

    int insert(UserTag record);

    int insertOrUpdate(UserTag record);

    int insertOrUpdateSelective(UserTag record);

    int insertSelective(UserTag record);

    UserTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserTag record);

    int updateByPrimaryKey(UserTag record);

    int updateBatch(@Param("list") List<UserTag> list);

    int updateBatchUseMultiQuery(@Param("list") List<UserTag> list);

    int updateBatchSelective(@Param("list") List<UserTag> list);

    int batchInsert(@Param("list") List<UserTag> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<UserTag> list);

    int batchInsertOrUpdate(@Param("list") List<UserTag> list);

    List<UserTag> selectWithCondition(UserTagQuery userTagQuery);

    int countByCondition(UserTagQuery userTagQuery);
}