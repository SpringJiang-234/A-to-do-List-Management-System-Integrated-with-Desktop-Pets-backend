package com.backend.mapper;

import com.backend.domain.entity.User;
import java.util.List;
import java.util.Map;

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

    int countByCondition(UserQuery userQuery);

    int countByAccount(String account);

    /**
     * 获取用户总数
     * @return 用户总数
     */
    int countTotalUsers();

    /**
     * 获取某天的新增用户数
     * @param date 日期（格式：YYYY-MM-DD）
     * @return 新增用户数
     */
    int countNewUsersByDate(@Param("date") String date);

    /**
     * 获取用户类型分布
     * @return 用户类型分布列表
     */
    List<Map<String, Object>> getUserTypeDistribution();

    /**
     * 获取用户状态分布
     * @return 用户状态分布列表
     */
    List<Map<String, Object>> getUserStatusDistribution();

    /**
     * 获取用户性别分布
     * @return 用户性别分布列表
     */
    List<Map<String, Object>> getUserGenderDistribution();

    /**
     * 获取用户生日月份分布
     * @return 生日月份分布列表
     */
    List<Map<String, Object>> getUserBirthMonthDistribution();

    /**
     * 获取用户注册时间热力图数据
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 注册时间热力图数据
     */
    List<Map<String, Object>> getUserRegistrationHeatmap(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 获取指定时间范围内的每日新增用户数
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 每日新增用户数列表
     */
    List<Map<String, Object>> getDailyNewUsers(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Map<String, Object>> getDailyTotalUsers(@Param("startDate") String startDate, @Param("endDate") String endDate);
}