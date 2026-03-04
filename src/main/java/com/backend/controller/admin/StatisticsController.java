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

@RestController
@RequestMapping("/admin/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    // 用户分析相关接口
    @GetMapping("/user/total")
    public ResultBean<Integer> getTotalUsers() {
        return ResultBean.success(statisticsService.getTotalUsers());
    }

    @GetMapping("/user/new-by-date")
    public ResultBean<Integer> getNewUsersByDate(@RequestParam String date) {
        return ResultBean.success(statisticsService.getNewUsersByDate(date));
    }

    @GetMapping("/user/type-distribution")
    public ResultBean<List<Map<String, Object>>> getUserTypeDistribution() {
        return ResultBean.success(statisticsService.getUserTypeDistribution());
    }

    @GetMapping("/user/status-distribution")
    public ResultBean<List<Map<String, Object>>> getUserStatusDistribution() {
        return ResultBean.success(statisticsService.getUserStatusDistribution());
    }

    @GetMapping("/user/gender-distribution")
    public ResultBean<List<Map<String, Object>>> getUserGenderDistribution() {
        return ResultBean.success(statisticsService.getUserGenderDistribution());
    }

    @GetMapping("/user/birth-month-distribution")
    public ResultBean<List<Map<String, Object>>> getUserBirthMonthDistribution() {
        return ResultBean.success(statisticsService.getUserBirthMonthDistribution());
    }

    @GetMapping("/user/registration-heatmap")
    public ResultBean<List<Map<String, Object>>> getUserRegistrationHeatmap(
            @RequestParam String startDate, @RequestParam String endDate) {
        return ResultBean.success(statisticsService.getUserRegistrationHeatmap(startDate, endDate));
    }

    @GetMapping("/user/daily-new")
    public ResultBean<List<Map<String, Object>>> getDailyNewUsers(
            @RequestParam String startDate, @RequestParam String endDate) {
        return ResultBean.success(statisticsService.getDailyNewUsers(startDate, endDate));
    }

    // 待办事项分析相关接口
    @GetMapping("/todo/total")
    public ResultBean<Integer> getTotalTodos() {
        return ResultBean.success(statisticsService.getTotalTodos());
    }

    @GetMapping("/todo/new-by-date")
    public ResultBean<Integer> getNewTodosByDate(@RequestParam String date) {
        return ResultBean.success(statisticsService.getNewTodosByDate(date));
    }

    @GetMapping("/todo/status-distribution")
    public ResultBean<List<Map<String, Object>>> getTodoStatusDistribution() {
        return ResultBean.success(statisticsService.getTodoStatusDistribution());
    }

    @GetMapping("/todo/priority-distribution")
    public ResultBean<List<Map<String, Object>>> getTodoPriorityDistribution() {
        return ResultBean.success(statisticsService.getTodoPriorityDistribution());
    }

    @GetMapping("/todo/daily-new")
    public ResultBean<List<Map<String, Object>>> getDailyNewTodos(
            @RequestParam String startDate, @RequestParam String endDate) {
        return ResultBean.success(statisticsService.getDailyNewTodos(startDate, endDate));
    }

    @GetMapping("/todo/deadline-warning")
    public ResultBean<List<Map<String, Object>>> getTodoDeadlineWarning(@RequestParam int days) {
        return ResultBean.success(statisticsService.getTodoDeadlineWarning(days));
    }

    @GetMapping("/todo/category-distribution")
    public ResultBean<List<Map<String, Object>>> getTodoCategoryDistribution() {
        return ResultBean.success(statisticsService.getTodoCategoryDistribution());
    }

    @GetMapping("/todo/tag-frequency")
    public ResultBean<List<Map<String, Object>>> getTodoTagFrequency() {
        return ResultBean.success(statisticsService.getTodoTagFrequency());
    }

    @GetMapping("/todo/completion-rate-trend")
    public ResultBean<List<Map<String, Object>>> getTodoCompletionRateTrend(
            @RequestParam String startDate, @RequestParam String endDate) {
        return ResultBean.success(statisticsService.getTodoCompletionRateTrend(startDate, endDate));
    }

    // 桌面宠物分析相关接口
    @GetMapping("/pet/level-distribution")
    public ResultBean<List<Map<String, Object>>> getPetLevelDistribution() {
        return ResultBean.success(statisticsService.getPetLevelDistribution());
    }

    @GetMapping("/pet/energy-distribution")
    public ResultBean<List<Map<String, Object>>> getPetEnergyDistribution() {
        return ResultBean.success(statisticsService.getPetEnergyDistribution());
    }

    @GetMapping("/pet/mood-distribution")
    public ResultBean<List<Map<String, Object>>> getPetMoodDistribution() {
        return ResultBean.success(statisticsService.getPetMoodDistribution());
    }

    @GetMapping("/pet/intimacy-distribution")
    public ResultBean<List<Map<String, Object>>> getPetIntimacyDistribution() {
        return ResultBean.success(statisticsService.getPetIntimacyDistribution());
    }

    @GetMapping("/pet/top-level")
    public ResultBean<List<Map<String, Object>>> getTopLevelPets(@RequestParam(defaultValue = "10") int limit) {
        return ResultBean.success(statisticsService.getTopLevelPets(limit));
    }

    // 审计日志分析相关接口
    @GetMapping("/audit/operation-frequency")
    public ResultBean<List<Map<String, Object>>> getOperationFrequencyTrend(
            @RequestParam String startDate, @RequestParam String endDate, 
            @RequestParam(defaultValue = "day") String interval) {
        return ResultBean.success(statisticsService.getOperationFrequencyTrend(startDate, endDate, interval));
    }

    @GetMapping("/audit/operation-type-distribution")
    public ResultBean<List<Map<String, Object>>> getOperationTypeDistribution(
            @RequestParam String startDate, @RequestParam String endDate) {
        return ResultBean.success(statisticsService.getOperationTypeDistribution(startDate, endDate));
    }

    @GetMapping("/audit/popular-modules")
    public ResultBean<List<Map<String, Object>>> getPopularModules(
            @RequestParam String startDate, @RequestParam String endDate, 
            @RequestParam(defaultValue = "10") int limit) {
        return ResultBean.success(statisticsService.getPopularModules(startDate, endDate, limit));
    }

    @GetMapping("/audit/user-activity-rank")
    public ResultBean<List<Map<String, Object>>> getUserActivityRank(
            @RequestParam String startDate, @RequestParam String endDate, 
            @RequestParam(defaultValue = "10") int limit) {
        return ResultBean.success(statisticsService.getUserActivityRank(startDate, endDate, limit));
    }

    // 反馈分析相关接口
    @GetMapping("/feedback/status-distribution")
    public ResultBean<List<Map<String, Object>>> getFeedbackStatusDistribution() {
        return ResultBean.success(statisticsService.getFeedbackStatusDistribution());
    }

    @GetMapping("/feedback/daily-submission")
    public ResultBean<List<Map<String, Object>>> getDailyFeedbackSubmission(
            @RequestParam String startDate, @RequestParam String endDate) {
        return ResultBean.success(statisticsService.getDailyFeedbackSubmission(startDate, endDate));
    }

    // 公告分析相关接口
    @GetMapping("/announcement/top-ratio")
    public ResultBean<List<Map<String, Object>>> getAnnouncementTopRatio() {
        return ResultBean.success(statisticsService.getAnnouncementTopRatio());
    }

    // 系统健康度仪表盘接口
    @GetMapping("/dashboard/system-health")
    public ResultBean<Map<String, Object>> getSystemHealthDashboard(
            @RequestParam String startDate, @RequestParam String endDate) {
        return ResultBean.success(statisticsService.getSystemHealthDashboard(startDate, endDate));
    }
}