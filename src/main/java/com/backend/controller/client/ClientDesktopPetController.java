package com.backend.controller.client;

import com.backend.bean.ResultBean;
import com.backend.converter.DesktopPetConverter;
import com.backend.domain.dto.DesktopPetDTO;
import com.backend.domain.entity.DesktopPet;
import com.backend.domain.query.DesktopPetQuery;
import com.backend.service.DesktopPetService;
import com.backend.utils.AuthUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @Autowired
    private AuthUtil authUtil;

    /**
     * 获取桌宠信息
     *
     * @return 桌宠信息
     */
    @GetMapping("/info")
    public ResultBean<DesktopPet> getDesktopPetInfo(HttpServletRequest request) {
        Long userId = authUtil.getCurrentUserId(request);
        if (userId == null) {
            return ResultBean.error("未登录或登录已过期", null);
        }

        DesktopPetQuery query = new DesktopPetQuery();
        query.setUserId(userId);
        List<DesktopPet> desktopPetList = desktopPetService.getAll(query);

        if (desktopPetList == null || desktopPetList.isEmpty()) {
            return ResultBean.success("桌宠信息获取成功", null);
        }

        return ResultBean.success("桌宠信息获取成功", desktopPetList.get(0));
    }

    /**
     * 更新桌宠信息
     *
     * @param desktopPetDTO 桌宠信息
     * @return 更新结果
     */
    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody DesktopPetDTO desktopPetDTO, HttpServletRequest request) {
        Long userId = authUtil.getCurrentUserId(request);
        if (userId == null) {
            return ResultBean.error("未登录或登录已过期", null);
        }

        DesktopPet desktopPet = desktopPetConverter.desktopPetDTO2desktopPet(desktopPetDTO);
        desktopPet.setUserId(userId);
        int result = desktopPetService.insertOrUpdate(desktopPet);

        if (result > 0) {
            return ResultBean.success("更新成功!", null);
        } else {
            return ResultBean.error("更新失败", null);
        }
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

    /**
     * 新建待办时更新活力值（测试接口）
     *
     * @param request HTTP请求
     * @return 更新结果
     */
    @PostMapping("/onNewTodo")
    public ResultBean<Void> onNewTodo(HttpServletRequest request) {
        Long userId = authUtil.getCurrentUserId(request);
        if (userId == null) {
            return ResultBean.error("未登录或登录已过期", null);
        }

        desktopPetService.onNewTodo(userId);
        return ResultBean.success("更新成功", null);
    }

    /**
     * 完成待办时更新活力值、成长值和心情值（测试接口）
     *
     * @param request HTTP请求
     * @param isCompletedOnTime 是否按时完成
     * @return 更新结果
     */
    @PostMapping("/onTodoCompleted")
    public ResultBean<Void> onTodoCompleted(HttpServletRequest request, boolean isCompletedOnTime) {
        Long userId = authUtil.getCurrentUserId(request);
        if (userId == null) {
            return ResultBean.error("未登录或登录已过期", null);
        }

        desktopPetService.onTodoCompleted(userId, isCompletedOnTime);
        return ResultBean.success("更新成功", null);
    }

    /**
     * 更新亲密度（测试接口）
     *
     * @param request HTTP请求
     * @return 更新结果
     */
    @PostMapping("/updateIntimacyOnLogin")
    public ResultBean<Void> updateIntimacyOnLogin(HttpServletRequest request) {
        Long userId = authUtil.getCurrentUserId(request);
        if (userId == null) {
            return ResultBean.error("未登录或登录已过期", null);
        }

        desktopPetService.updateIntimacyOnLogin(userId);
        return ResultBean.success("更新成功", null);
    }

    /**
     * 每日重置（测试接口）
     *
     * @return 重置结果
     */
    @PostMapping("/dailyReset")
    public ResultBean<Void> dailyReset() {
        desktopPetService.dailyReset();
        return ResultBean.success("重置成功", null);
    }
}
