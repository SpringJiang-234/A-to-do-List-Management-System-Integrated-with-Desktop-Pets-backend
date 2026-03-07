package com.backend.controller.client;

import com.backend.bean.ResultBean;
import com.backend.converter.TagConverter;
import com.backend.domain.dto.TagDTO;
import com.backend.domain.entity.Tag;
import com.backend.domain.vo.TagVO;
import com.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 标签控制层
 */
@RestController
@RequestMapping("/client/tag")
public class ClientTagController {
    @Autowired
    private TagService tagService;
    @Autowired
    private TagConverter tagConverter;

    /**
     * 获取标签列表
     *
     * @return 标签列表
     */
    @GetMapping("/list")
    public ResultBean<List<TagVO>> list() {
        return null;
    }

    /**
     * 获取标签详情
     *
     * @param id 标签id
     * @return 标签详情
     */
    @GetMapping("/details/{id}")
    public ResultBean<Tag> getDetails(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 添加标签
     *
     * @param tagDTO 标签信息
     * @return 添加结果
     */
    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody TagDTO tagDTO) {
        return null;
    }

    /**
     * 修改标签
     *
     * @param tagDTO 标签信息
     * @return 修改结果
     */
    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody TagDTO tagDTO) {
        return null;
    }

    /**
     * 删除标签
     *
     * @param id 标签id
     * @return 删除结果
     */
    @GetMapping("/delete/{id}")
    public ResultBean<Void> delete(@PathVariable("id") Long id) {
        return null;
    }
}
