package com.backend.controller.client;

import com.backend.bean.ResultBean;
import com.backend.converter.AnnouncementConverter;
import com.backend.domain.entity.Announcement;
import com.backend.domain.vo.AnnouncementVO;
import com.backend.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 公告控制层
 */
@RestController
@RequestMapping("/client/announcement")
public class ClientAnnouncementController {
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private AnnouncementConverter announcementConverter;

    /**
     * 获取公告列表
     *
     * @return 公告列表
     */
    @GetMapping("/list")
    public ResultBean<List<AnnouncementVO>> list() {
        return null;
    }

    /**
     * 获取公告详情
     *
     * @param id 公告id
     * @return 公告详情
     */
    @GetMapping("/details/{id}")
    public ResultBean<Announcement> getDetails(@PathVariable("id") Long id) {
        return null;
    }
}
