package com.backend.controller.client;

import com.backend.bean.ResultBean;
import com.backend.converter.FeedbackConverter;
import com.backend.domain.dto.FeedbackDTO;
import com.backend.domain.entity.Feedback;
import com.backend.domain.query.FeedbackQuery;
import com.backend.domain.vo.FeedbackVO;
import com.backend.service.FeedbackService;
import com.backend.utils.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 反馈控制层
 */
@RestController
@RequestMapping("/client/feedback")
public class ClientFeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private FeedbackConverter feedbackConverter;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 提交反馈
     *
     * @param feedbackDTO 反馈信息
     * @return 提交结果
     */
    @PostMapping("/submit")
    public ResultBean<Void> submit(@RequestBody FeedbackDTO feedbackDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return ResultBean.error("未登录", null);
        }

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        Object userIdObj = redisUtil.get("token:" + token);
        if (userIdObj == null) {
            return ResultBean.error("登录已过期，请重新登录", null);
        }

        Long userId = Long.valueOf(userIdObj.toString());

        Feedback feedback = feedbackConverter.feedbackDTO2feedback(feedbackDTO);
        feedback.setUserId(userId);
        feedback.setStatus(1);
        int result = feedbackService.insertOrUpdate(feedback);

        if (result > 0) {
            return ResultBean.success("提交成功!", null);
        } else {
            return ResultBean.error("提交失败", null);
        }
    }

    /**
     * 获取我的反馈列表
     *
     * @return 反馈列表
     */
    @PostMapping("/myFeedbacks")
    public ResultBean<List<FeedbackVO>> getMyFeedbacks(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return ResultBean.error("未登录", null);
        }

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        Object userIdObj = redisUtil.get("token:" + token);
        if (userIdObj == null) {
            return ResultBean.error("登录已过期，请重新登录", null);
        }

        Long userId = Long.valueOf(userIdObj.toString());

        FeedbackQuery feedbackQuery = new FeedbackQuery();
        feedbackQuery.setUserId(userId);
        List<Feedback> feedbackList = feedbackService.getAll(feedbackQuery);
        List<FeedbackVO> feedbackVOList = feedbackList.stream()
                .map(feedbackConverter::feedback2feedbackVO)
                .toList();
        return ResultBean.success(feedbackVOList);
    }

    /**
     * 获取反馈详情
     *
     * @param id 反馈id
     * @return 反馈详情
     */
    @GetMapping("/details/{id}")
    public ResultBean<Feedback> getDetails(@PathVariable("id") Long id) {
        Feedback feedback = feedbackService.getById(id);
        return ResultBean.success(feedback);
    }
}
