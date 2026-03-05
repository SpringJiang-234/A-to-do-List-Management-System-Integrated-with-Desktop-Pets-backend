package com.backend.controller.admin;

import com.backend.service.StatisticsService;
import com.backend.bean.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 统计数据控制器
 * 提供各种统计数据的API接口
 */
@RestController
@RequestMapping("/admin/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    // 用户分析相关接口
    
    /**
     * 获取用户总数
     * @return 用户总数
     */
    @GetMapping("/user/total")
    public ResultBean<Integer> getTotalUsers() {
        return ResultBean.success(statisticsService.getTotalUsers());
    }

    /**
     * 获取指定日期新增用户数
     * @param date 日期，格式为YYYY-MM-DD
     * @return 新增用户数
     */
    @GetMapping("/user/new-by-date")
    public ResultBean<Integer> getNewUsersByDate(@RequestParam String date) {
        return ResultBean.success(statisticsService.getNewUsersByDate(date));
    }

    /**
     * 获取用户类型分布
     * @return 用户类型分布数据
     */
    @GetMapping("/user/type-distribution")
    public ResultBean<List<Map<String, Object>>> getUserTypeDistribution() {
        return ResultBean.success(statisticsService.getUserTypeDistribution());
    }

    /**
     * 获取用户状态分布
     * @return 用户状态分布数据
     */
    @GetMapping("/user/status-distribution")
    public ResultBean<List<Map<String, Object>>> getUserStatusDistribution() {
        return ResultBean.success(statisticsService.getUserStatusDistribution());
    }

    /**
     * 获取用户性别分布
     * @return 用户性别分布数据
     */
    @GetMapping("/user/gender-distribution")
    public ResultBean<List<Map<String, Object>>> getUserGenderDistribution() {
        return ResultBean.success(statisticsService.getUserGenderDistribution());
    }

    /**
     * 获取用户生日月份分布
     * @return 用户生日月份分布数据
     */
    @GetMapping("/user/birth-month-distribution")
    public ResultBean<List<Map<String, Object>>> getUserBirthMonthDistribution() {
        return ResultBean.success(statisticsService.getUserBirthMonthDistribution());
    }

    /**
     * 获取用户注册热力图数据
     * @param startDate 开始日期，格式为YYYY-MM-DD
     * @param endDate 结束日期，格式为YYYY-MM-DD
     * @return 用户注册热力图数据
     */
    @GetMapping("/user/registration-heatmap")
    public ResultBean<List<Map<String, Object>>> getUserRegistrationHeatmap(
            @RequestParam String startDate, @RequestParam String endDate) {
        return ResultBean.success(statisticsService.getUserRegistrationHeatmap(startDate, endDate));
    }

    /**
     * 获取每日新增用户趋势
     * @param startDate 开始日期，格式为YYYY-MM-DD
     * @param endDate 结束日期，格式为YYYY-MM-DD
     * @return 每日新增用户趋势数据
     */
    @GetMapping("/user/daily-new")
    public ResultBean<List<Map<String, Object>>> getDailyNewUsers(
            @RequestParam String startDate, @RequestParam String endDate) {
        return ResultBean.success(statisticsService.getDailyNewUsers(startDate, endDate));
    }

    // 待办事项分析相关接口
    
    /**
     * 获取待办总数
     * @return 待办总数
     */
    @GetMapping("/todo/total")
    public ResultBean<Integer> getTotalTodos() {
        return ResultBean.success(statisticsService.getTotalTodos());
    }

    /**
     * 获取指定日期新增待办数
     * @param date 日期，格式为YYYY-MM-DD
     * @return 新增待办数
     */
    @GetMapping("/todo/new-by-date")
    public ResultBean<Integer> getNewTodosByDate(@RequestParam String date) {
        return ResultBean.success(statisticsService.getNewTodosByDate(date));
    }

    /**
     * 获取待办状态分布
     * @return 待办状态分布数据
     */
    @GetMapping("/todo/status-distribution")
    public ResultBean<List<Map<String, Object>>> getTodoStatusDistribution() {
        return ResultBean.success(statisticsService.getTodoStatusDistribution());
    }

    /**
     * 获取待办优先级分布
     * @return 待办优先级分布数据
     */
    @GetMapping("/todo/priority-distribution")
    public ResultBean<List<Map<String, Object>>> getTodoPriorityDistribution() {
        return ResultBean.success(statisticsService.getTodoPriorityDistribution());
    }

    /**
     * 获取每日新增待办趋势
     * @param startDate 开始日期，格式为YYYY-MM-DD
     * @param endDate 结束日期，格式为YYYY-MM-DD
     * @return 每日新增待办趋势数据
     */
    @GetMapping("/todo/daily-new")
    public ResultBean<List<Map<String, Object>>> getDailyNewTodos(
            @RequestParam String startDate, @RequestParam String endDate) {
        return ResultBean.success(statisticsService.getDailyNewTodos(startDate, endDate));
    }

    /**
     * 获取待办截止时间预警
     * @param days 预警天数
     * @return 待办截止时间预警数据
     */
    @GetMapping("/todo/deadline-warning")
    public ResultBean<List<Map<String, Object>>> getTodoDeadlineWarning(@RequestParam int days) {
        return ResultBean.success(statisticsService.getTodoDeadlineWarning(days));
    }

    /**
     * 获取待办类别分布
     * @return 待办类别分布数据
     */
    @GetMapping("/todo/category-distribution")
    public ResultBean<List<Map<String, Object>>> getTodoCategoryDistribution() {
        return ResultBean.success(statisticsService.getTodoCategoryDistribution());
    }

    /**
     * 获取待办标签频率
     * @return 待办标签频率数据
     */
    @GetMapping("/todo/tag-frequency")
    public ResultBean<List<Map<String, Object>>> getTodoTagFrequency() {
        return ResultBean.success(statisticsService.getTodoTagFrequency());
    }

    /**
     * 获取待办完成率趋势
     * @param startDate 开始日期，格式为YYYY-MM-DD
     * @param endDate 结束日期，格式为YYYY-MM-DD
     * @return 待办完成率趋势数据
     */
    @GetMapping("/todo/completion-rate-trend")
    public ResultBean<List<Map<String, Object>>> getTodoCompletionRateTrend(
            @RequestParam String startDate, @RequestParam String endDate) {
        return ResultBean.success(statisticsService.getTodoCompletionRateTrend(startDate, endDate));
    }

    // 桌面宠物分析相关接口
    
    /**
     * 获取宠物等级分布
     * @return 宠物等级分布数据
     */
    @GetMapping("/pet/level-distribution")
    public ResultBean<List<Map<String, Object>>> getPetLevelDistribution() {
        return ResultBean.success(statisticsService.getPetLevelDistribution());
    }

    /**
     * 获取宠物活力值分布
     * @return 宠物活力值分布数据
     */
    @GetMapping("/pet/energy-distribution")
    public ResultBean<List<Map<String, Object>>> getPetEnergyDistribution() {
        return ResultBean.success(statisticsService.getPetEnergyDistribution());
    }

    /**
     * 获取宠物心情值分布
     * @return 宠物心情值分布数据
     */
    @GetMapping("/pet/mood-distribution")
    public ResultBean<List<Map<String, Object>>> getPetMoodDistribution() {
        return ResultBean.success(statisticsService.getPetMoodDistribution());
    }

    /**
     * 获取宠物亲密度分布
     * @return 宠物亲密度分布数据
     */
    @GetMapping("/pet/intimacy-distribution")
    public ResultBean<List<Map<String, Object>>> getPetIntimacyDistribution() {
        return ResultBean.success(statisticsService.getPetIntimacyDistribution());
    }

    /**
     * 获取最高等级宠物排行
     * @param limit 限制数量，默认10
     * @return 最高等级宠物排行数据
     */
    @GetMapping("/pet/top-level")
    public ResultBean<List<Map<String, Object>>> getTopLevelPets(@RequestParam(defaultValue = "10") int limit) {
        return ResultBean.success(statisticsService.getTopLevelPets(limit));
    }

    // 审计日志分析相关接口
    
    /**
     * 获取操作频率趋势
     * @param startDate 开始日期，格式为YYYY-MM-DD
     * @param endDate 结束日期，格式为YYYY-MM-DD
     * @param interval 时间间隔，默认day
     * @return 操作频率趋势数据
     */
    @GetMapping("/audit/operation-frequency")
    public ResultBean<List<Map<String, Object>>> getOperationFrequencyTrend(
            @RequestParam String startDate, @RequestParam String endDate, 
            @RequestParam(defaultValue = "day") String interval) {
        return ResultBean.success(statisticsService.getOperationFrequencyTrend(startDate, endDate, interval));
    }

    /**
     * 获取操作类型分布
     * @param startDate 开始日期，格式为YYYY-MM-DD
     * @param endDate 结束日期，格式为YYYY-MM-DD
     * @return 操作类型分布数据
     */
    @GetMapping("/audit/operation-type-distribution")
    public ResultBean<List<Map<String, Object>>> getOperationTypeDistribution(
            @RequestParam String startDate, @RequestParam String endDate) {
        return ResultBean.success(statisticsService.getOperationTypeDistribution(startDate, endDate));
    }

    /**
     * 获取热门操作模块
     * @param startDate 开始日期，格式为YYYY-MM-DD
     * @param endDate 结束日期，格式为YYYY-MM-DD
     * @param limit 限制数量，默认10
     * @return 热门操作模块数据
     */
    @GetMapping("/audit/popular-modules")
    public ResultBean<List<Map<String, Object>>> getPopularModules(
            @RequestParam String startDate, @RequestParam String endDate, 
            @RequestParam(defaultValue = "10") int limit) {
        return ResultBean.success(statisticsService.getPopularModules(startDate, endDate, limit));
    }

    /**
     * 获取用户活跃度排行
     * @param startDate 开始日期，格式为YYYY-MM-DD
     * @param endDate 结束日期，格式为YYYY-MM-DD
     * @param limit 限制数量，默认10
     * @return 用户活跃度排行数据
     */
    @GetMapping("/audit/user-activity-rank")
    public ResultBean<List<Map<String, Object>>> getUserActivityRank(
            @RequestParam String startDate, @RequestParam String endDate, 
            @RequestParam(defaultValue = "10") int limit) {
        return ResultBean.success(statisticsService.getUserActivityRank(startDate, endDate, limit));
    }

    // 反馈分析相关接口
    
    /**
     * 获取反馈状态分布
     * @return 反馈状态分布数据
     */
    @GetMapping("/feedback/status-distribution")
    public ResultBean<List<Map<String, Object>>> getFeedbackStatusDistribution() {
        return ResultBean.success(statisticsService.getFeedbackStatusDistribution());
    }

    /**
     * 获取每日反馈提交量
     * @param startDate 开始日期，格式为YYYY-MM-DD
     * @param endDate 结束日期，格式为YYYY-MM-DD
     * @return 每日反馈提交量数据
     */
    @GetMapping("/feedback/daily-submission")
    public ResultBean<List<Map<String, Object>>> getDailyFeedbackSubmission(
            @RequestParam String startDate, @RequestParam String endDate) {
        return ResultBean.success(statisticsService.getDailyFeedbackSubmission(startDate, endDate));
    }

    // 公告分析相关接口
    
    /**
     * 获取公告置顶比例
     * @return 公告置顶比例数据
     */
    @GetMapping("/announcement/top-ratio")
    public ResultBean<List<Map<String, Object>>> getAnnouncementTopRatio() {
        return ResultBean.success(statisticsService.getAnnouncementTopRatio());
    }

    // 系统健康度仪表盘接口
    
    /**
     * 获取系统健康度仪表盘数据
     * @param startDate 开始日期，格式为YYYY-MM-DD
     * @param endDate 结束日期，格式为YYYY-MM-DD
     * @return 系统健康度仪表盘数据
     */
    @GetMapping("/dashboard/system-health")
    public ResultBean<Map<String, Object>> getSystemHealthDashboard(
            @RequestParam String startDate, @RequestParam String endDate) {
        return ResultBean.success(statisticsService.getSystemHealthDashboard(startDate, endDate));
    }
}