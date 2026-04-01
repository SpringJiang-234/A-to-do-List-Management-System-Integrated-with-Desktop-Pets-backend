package com.backend.controller.client;

import com.backend.bean.ResultBean;
import com.backend.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 统计数据控制器
 * 提供用户个人统计数据的API接口
 */
@RestController
@RequestMapping("/client/statistics")
public class ClientStatisticsController {

    @Autowired
    private StatisticsService statisticsService;
    
    /**
     * 按类别分组查询待办数量
     * @param params 查询参数
     * @return 按类别分组的待办数量
     */
    @PostMapping("/todo-count-by-category")
    public ResultBean<List<Map<String, Object>>> getTodoCountByCategory(@RequestBody Map<String, Object> params) {
        String startDate = (String) params.get("startDate");
        String endDate = (String) params.get("endDate");
        List<Long> categoryIdList = (List<Long>) params.get("categoryIdList");
        return ResultBean.success(statisticsService.getTodoCountByCategory(startDate, endDate, categoryIdList));
    }
    
    /**
     * 按类别和日期分组查询待办数量
     * @param params 查询参数
     * @return 按类别和日期分组的待办数量
     */
    @PostMapping("/todo-count-by-category-and-date")
    public ResultBean<List<Map<String, Object>>> getTodoCountByCategoryAndDate(@RequestBody Map<String, Object> params) {
        String startDate = (String) params.get("startDate");
        String endDate = (String) params.get("endDate");
        List<Long> categoryIdList = (List<Long>) params.get("categoryIdList");
        return ResultBean.success(statisticsService.getTodoCountByCategoryAndDate(startDate, endDate, categoryIdList));
    }
    
    /**
     * 按日期分组查询待办数量（用于热力图）
     * @param params 查询参数
     * @return 按日期分组的待办数量
     */
    @PostMapping("/todo-count-by-date")
    public ResultBean<List<Map<String, Object>>> getTodoCountByDate(@RequestBody Map<String, Object> params) {
        String startDate = (String) params.get("startDate");
        String endDate = (String) params.get("endDate");
        List<Long> categoryIdList = (List<Long>) params.get("categoryIdList");
        return ResultBean.success(statisticsService.getTodoCountByDate(startDate, endDate, categoryIdList));
    }
}
