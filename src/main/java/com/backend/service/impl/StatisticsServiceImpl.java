package com.backend.service.impl;

import com.backend.mapper.*;
import com.backend.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public List<Map<String, Object>> getDailyTotalUsers(String startDate, String endDate) {
        return userMapper.getDailyTotalUsers(startDate, endDate);
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
    
    // 报表相关方法
    public List<Map<String, Object>> getTodoCountByCategory(String startDate, String endDate, List<Long> categoryIdList) {
        return todoMapper.getTodoCountByCategory(startDate, endDate, categoryIdList);
    }
    
    public List<Map<String, Object>> getTodoCountByCategoryAndDate(String startDate, String endDate, List<Long> categoryIdList) {
        List<Map<String, Object>> todoDetails = todoMapper.getTodoCountByCategoryAndDate(startDate, endDate, categoryIdList);
        Map<String, Map<String, Integer>> categoryDateCount = new HashMap<>();
        
        for (Map<String, Object> todo : todoDetails) {
            Long categoryId = (Long) todo.get("categoryId");
            String categoryName = (String) todo.get("categoryName");
            Date start = (Date) todo.get("startDate");
            Date end = (Date) todo.get("endDate");
            
            // 计算待办事项在查询范围内的所有日期
            List<Date> dates = getDatesBetween(start, end, startDate, endDate);
            
            for (Date date : dates) {
                String dateStr = formatDate(date);
                String key = categoryId + "_" + categoryName;
                
                if (!categoryDateCount.containsKey(key)) {
                    categoryDateCount.put(key, new HashMap<>());
                }
                
                Map<String, Integer> dateCount = categoryDateCount.get(key);
                dateCount.put(dateStr, dateCount.getOrDefault(dateStr, 0) + 1);
            }
        }
        
        // 转换为返回格式
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, Map<String, Integer>> entry : categoryDateCount.entrySet()) {
            String[] keyParts = entry.getKey().split("_");
            Long categoryId = Long.parseLong(keyParts[0]);
            String categoryName = entry.getKey().substring(keyParts[0].length() + 1);
            
            Map<String, Integer> dateCount = entry.getValue();
            for (Map.Entry<String, Integer> dateEntry : dateCount.entrySet()) {
                Map<String, Object> item = new HashMap<>();
                item.put("categoryId", categoryId);
                item.put("categoryName", categoryName);
                item.put("date", dateEntry.getKey());
                item.put("sum", dateEntry.getValue());
                result.add(item);
            }
        }
        
        // 按日期和类别排序
        result.sort((a, b) -> {
            int dateCompare = ((String) a.get("date")).compareTo((String) b.get("date"));
            if (dateCompare != 0) {
                return dateCompare;
            }
            return ((Long) a.get("categoryId")).compareTo((Long) b.get("categoryId"));
        });
        
        return result;
    }
    
    /**
     * 获取两个日期之间的所有日期，并且限制在查询范围内
     */
    private List<Date> getDatesBetween(Date start, Date end, String queryStart, String queryEnd) {
        List<Date> dates = new ArrayList<>();
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date queryStartDate = sdf.parse(queryStart);
            Date queryEndDate = sdf.parse(queryEnd);
            
            // 调整开始日期为查询开始日期和待办开始日期的最大值
            Date current = start.before(queryStartDate) ? queryStartDate : start;
            // 调整结束日期为查询结束日期和待办结束日期的最小值
            Date last = end.after(queryEndDate) ? queryEndDate : end;
            
            while (!current.after(last)) {
                dates.add(new Date(current.getTime()));
                current.setDate(current.getDate() + 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return dates;
    }
    
    /**
     * 格式化日期为 yyyy-MM-dd 格式
     */
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    
    @Override
    public List<Map<String, Object>> getTodoCountByDate(String startDate, String endDate, List<Long> categoryIdList) {
        // 调用mapper查询数据
        List<Map<String, Object>> todos = todoMapper.getTodoCountByDate(startDate, endDate, categoryIdList);
        
        // 处理数据，按日期分组
        Map<String, Integer> dateMap = new HashMap<>();
        
        for (Map<String, Object> todo : todos) {
            Object dateObj = todo.get("date");
            String dateStr = null;
            
            // 处理日期类型转换
            if (dateObj instanceof java.sql.Date) {
                dateStr = dateObj.toString();
            } else if (dateObj instanceof String) {
                dateStr = (String) dateObj;
            }
            
            Object sumObj = todo.get("sum");
            Integer sum = null;
            
            // 处理 sum 类型转换
            if (sumObj instanceof Integer) {
                sum = (Integer) sumObj;
            } else if (sumObj instanceof Long) {
                sum = ((Long) sumObj).intValue();
            }
            
            if (dateStr != null && sum != null) {
                dateMap.put(dateStr, sum);
            }
        }
        
        // 转换为结果格式
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : dateMap.entrySet()) {
            Map<String, Object> item = new HashMap<>();
            item.put("date", entry.getKey());
            item.put("sum", entry.getValue());
            result.add(item);
        }
        
        return result;
    }
}
