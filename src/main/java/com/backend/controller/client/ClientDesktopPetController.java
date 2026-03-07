package com.backend.controller.client;

import com.backend.bean.ResultBean;
import com.backend.converter.DesktopPetConverter;
import com.backend.domain.dto.DesktopPetDTO;
import com.backend.domain.entity.DesktopPet;
import com.backend.service.DesktopPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 桌宠控制层
 */
@RestController
@RequestMapping("/client/desktopPet")
public class ClientDesktopPetController {
    @Autowired
    private DesktopPetService desktopPetService;
    @Autowired
    private DesktopPetConverter desktopPetConverter;

    /**
     * 获取桌宠信息
     *
     * @return 桌宠信息
     */
    @GetMapping("/info")
    public ResultBean<DesktopPet> getDesktopPetInfo() {
        return null;
    }

    /**
     * 更新桌宠信息
     *
     * @param desktopPetDTO 桌宠信息
     * @return 更新结果
     */
    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody DesktopPetDTO desktopPetDTO) {
        return null;
    }

    /**
     * 召唤桌宠
     *
     * @return 召唤结果
     */
    @PostMapping("/summon")
    public ResultBean<Void> summon() {
        return null;
    }

    /**
     * 关闭桌宠
     *
     * @return 关闭结果
     */
    @PostMapping("/dismiss")
    public ResultBean<Void> dismiss() {
        return null;
    }
}
