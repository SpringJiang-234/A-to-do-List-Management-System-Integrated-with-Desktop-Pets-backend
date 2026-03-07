package com.backend.controller.client;

import com.backend.bean.ResultBean;
import com.backend.converter.FeedbackConverter;
import com.backend.domain.dto.FeedbackDTO;
import com.backend.domain.entity.Feedback;
import com.backend.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 提交反馈
     *
     * @param feedbackDTO 反馈信息
     * @return 提交结果
     */
    @PostMapping("/submit")
    public ResultBean<Void> submit(@RequestBody FeedbackDTO feedbackDTO) {
        return null;
    }

    /**
     * 获取我的反馈列表
     *
     * @return 反馈列表
     */
    @PostMapping("/myFeedbacks")
    public ResultBean<java.util.List<Feedback>> getMyFeedbacks() {
        return null;
    }
}
