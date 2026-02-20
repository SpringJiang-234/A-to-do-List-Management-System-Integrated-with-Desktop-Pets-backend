package com.backend.mapper;

import com.backend.domain.entity.User;
import java.util.List;

import com.backend.domain.query.UserQuery;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByPrimaryKeyIn(String[] array);

    int insert(User record);

    int insertOrUpdate(User record);

    int insertOrUpdateSelective(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateBatch(@Param("list") List<User> list);

    int updateBatchUseMultiQuery(@Param("list") List<User> list);

    int updateBatchSelective(@Param("list") List<User> list);

    int batchInsert(@Param("list") List<User> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<User> list);

    int batchInsertOrUpdate(@Param("list") List<User> list);

    List<User> selectWithCondition(UserQuery userQuery);
}