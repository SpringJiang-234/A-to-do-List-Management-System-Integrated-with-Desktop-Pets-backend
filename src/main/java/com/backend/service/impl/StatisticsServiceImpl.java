package com.backend.service.impl;

import com.backend.mapper.*;
import com.backend.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TodoMapper todoMapper;

    @Autowired
    private DesktopPetMapper desktopPetMapper;

    @Autowired
    private AuditLogMapper auditLogMapper;

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private AnnouncementMapper announcementMapper;

    // 用户分析相关方法
    public int getTotalUsers() {
        return userMapper.countTotalUsers();
    }

    public int getNewUsersByDate(String date) {
        return userMapper.countNewUsersByDate(date);
    }

    public List<Map<String, Object>> getUserTypeDistribution() {
        return userMapper.getUserTypeDistribution();
    }

    public List<Map<String, Object>> getUserStatusDistribution() {
        return userMapper.getUserStatusDistribution();
    }

    public List<Map<String, Object>> getUserGenderDistribution() {
        return userMapper.getUserGenderDistribution();
    }

    public List<Map<String, Object>> getUserBirthMonthDistribution() {
        return userMapper.getUserBirthMonthDistribution();
    }

    public List<Map<String, Object>> getUserRegistrationHeatmap(String startDate, String endDate) {
        return userMapper.getUserRegistrationHeatmap(startDate, endDate);
    }

    public List<Map<String, Object>> getDailyNewUsers(String startDate, String endDate) {
        return userMapper.getDailyNewUsers(startDate, endDate);
    }

    // 待办事项分析相关方法
    public int getTotalTodos() {
        return todoMapper.countTotalTodos();
    }

    public int getNewTodosByDate(String date) {
        return todoMapper.countNewTodosByDate(date);
    }

    public List<Map<String, Object>> getTodoStatusDistribution() {
        return todoMapper.getTodoStatusDistribution();
    }

    public List<Map<String, Object>> getTodoPriorityDistribution() {
        return todoMapper.getTodoPriorityDistribution();
    }

    public List<Map<String, Object>> getDailyNewTodos(String startDate, String endDate) {
        return todoMapper.getDailyNewTodos(startDate, endDate);
    }

    public List<Map<String, Object>> getTodoDeadlineWarning(int days) {
        return todoMapper.getTodoDeadlineWarning(days);
    }

    public List<Map<String, Object>> getTodoCategoryDistribution() {
        return todoMapper.getTodoCategoryDistribution();
    }

    public List<Map<String, Object>> getTodoTagFrequency() {
        return todoMapper.getTodoTagFrequency();
    }

    public List<Map<String, Object>> getTodoCompletionRateTrend(String startDate, String endDate) {
        return todoMapper.getTodoCompletionRateTrend(startDate, endDate);
    }

    // 桌面宠物分析相关方法
    public List<Map<String, Object>> getPetLevelDistribution() {
        return desktopPetMapper.getPetLevelDistribution();
    }

    public List<Map<String, Object>> getPetEnergyDistribution() {
        return desktopPetMapper.getPetEnergyDistribution();
    }

    public List<Map<String, Object>> getPetMoodDistribution() {
        return desktopPetMapper.getPetMoodDistribution();
    }

    public List<Map<String, Object>> getPetIntimacyDistribution() {
        return desktopPetMapper.getPetIntimacyDistribution();
    }

    public List<Map<String, Object>> getTopLevelPets(int limit) {
        return desktopPetMapper.getTopLevelPets(limit);
    }

    // 审计日志分析相关方法
    public List<Map<String, Object>> getOperationFrequencyTrend(String startDate, String endDate, String interval) {
        return auditLogMapper.getOperationFrequencyTrend(startDate, endDate, interval);
    }

    public List<Map<String, Object>> getOperationTypeDistribution(String startDate, String endDate) {
        return auditLogMapper.getOperationTypeDistribution(startDate, endDate);
    }

    public List<Map<String, Object>> getPopularModules(String startDate, String endDate, int limit) {
        return auditLogMapper.getPopularModules(startDate, endDate, limit);
    }

    public List<Map<String, Object>> getUserActivityRank(String startDate, String endDate, int limit) {
        return auditLogMapper.getUserActivityRank(startDate, endDate, limit);
    }

    // 反馈分析相关方法
    public List<Map<String, Object>> getFeedbackStatusDistribution() {
        return feedbackMapper.getFeedbackStatusDistribution();
    }

    public List<Map<String, Object>> getDailyFeedbackSubmission(String startDate, String endDate) {
        return feedbackMapper.getDailyFeedbackSubmission(startDate, endDate);
    }

    // 公告分析相关方法
    public List<Map<String, Object>> getAnnouncementTopRatio() {
        return announcementMapper.getAnnouncementTopRatio();
    }

    // 系统健康度仪表盘数据
    public Map<String, Object> getSystemHealthDashboard(String startDate, String endDate) {
        Map<String, Object> dashboard = new java.util.HashMap<>();
        
        // 今日活跃用户数（近24小时操作数）
        List<Map<String, Object>> activityData = auditLogMapper.getOperationFrequencyTrend(
                startDate, endDate, "day");
        int activeUsers = activityData.size() > 0 ? 
                ((Number) activityData.get(0).get("count")).intValue() : 0;
        
        // 待办完成率
        List<Map<String, Object>> completionRateData = todoMapper.getTodoCompletionRateTrend(
                startDate, endDate);
        double completionRate = completionRateData.size() > 0 ? 
                ((Number) completionRateData.get(0).get("completionRate")).doubleValue() : 0;
        
        // 反馈未处理数
        List<Map<String, Object>> feedbackStatusData = feedbackMapper.getFeedbackStatusDistribution();
        int pendingFeedback = 0;
        for (Map<String, Object> item : feedbackStatusData) {
            if (item.get("status").equals(1)) { // 1-未受理
                pendingFeedback = ((Number) item.get("count")).intValue();
                break;
            }
        }
        
        dashboard.put("activeUsers", activeUsers);
        dashboard.put("completionRate", completionRate);
        dashboard.put("pendingFeedback", pendingFeedback);
        dashboard.put("totalUsers", getTotalUsers());
        dashboard.put("totalTodos", getTotalTodos());
        
        return dashboard;
    }
}