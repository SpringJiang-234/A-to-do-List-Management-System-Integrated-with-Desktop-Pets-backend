package com.backend.service;

import java.util.List;
import java.util.Map;

public interface StatisticsService {
    // 用户分析相关方法
    int getTotalUsers();
    int getNewUsersByDate(String date);
    List<Map<String, Object>> getUserTypeDistribution();
    List<Map<String, Object>> getUserStatusDistribution();
    List<Map<String, Object>> getUserGenderDistribution();
    List<Map<String, Object>> getUserBirthMonthDistribution();
    List<Map<String, Object>> getUserRegistrationHeatmap(String startDate, String endDate);
    List<Map<String, Object>> getDailyNewUsers(String startDate, String endDate);
    List<Map<String, Object>> getDailyTotalUsers(String startDate, String endDate);

    // 待办事项分析相关方法
    int getTotalTodos();
    int getNewTodosByDate(String date);
    List<Map<String, Object>> getTodoStatusDistribution();
    List<Map<String, Object>> getTodoPriorityDistribution();
    List<Map<String, Object>> getDailyNewTodos(String startDate, String endDate);
    List<Map<String, Object>> getTodoDeadlineWarning(int days);
    List<Map<String, Object>> getTodoCategoryDistribution();
    List<Map<String, Object>> getTodoTagFrequency();
    List<Map<String, Object>> getTodoCompletionRateTrend(String startDate, String endDate);

    // 桌面宠物分析相关方法
    List<Map<String, Object>> getPetLevelDistribution();
    List<Map<String, Object>> getPetEnergyDistribution();
    List<Map<String, Object>> getPetMoodDistribution();
    List<Map<String, Object>> getPetIntimacyDistribution();
    List<Map<String, Object>> getTopLevelPets(int limit);

    // 审计日志分析相关方法
    List<Map<String, Object>> getOperationFrequencyTrend(String startDate, String endDate, String interval);
    List<Map<String, Object>> getOperationTypeDistribution(String startDate, String endDate);
    List<Map<String, Object>> getPopularModules(String startDate, String endDate, int limit);
    List<Map<String, Object>> getUserActivityRank(String startDate, String endDate, int limit);

    // 反馈分析相关方法
    List<Map<String, Object>> getFeedbackStatusDistribution();
    List<Map<String, Object>> getDailyFeedbackSubmission(String startDate, String endDate);

    // 公告分析相关方法
    List<Map<String, Object>> getAnnouncementTopRatio();

    // 系统健康度仪表盘数据
    Map<String, Object> getSystemHealthDashboard(String startDate, String endDate);
    
    // 报表相关方法
    List<Map<String, Object>> getTodoCountByCategory(String startDate, String endDate, List<Integer> categoryIdList);
    List<Map<String, Object>> getTodoCountByCategoryAndDate(String startDate, String endDate, List<Integer> categoryIdList);
}
