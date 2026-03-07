package com.backend.controller.client;

import com.backend.bean.ResultBean;
import com.backend.converter.CategoryConverter;
import com.backend.domain.dto.CategoryDTO;
import com.backend.domain.entity.Category;
import com.backend.domain.vo.CategoryVO;
import com.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分类控制层
 */
@RestController
@RequestMapping("/client/category")
public class ClientCategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryConverter categoryConverter;

    /**
     * 获取分类列表
     *
     * @return 分类列表
     */
    @GetMapping("/list")
    public ResultBean<List<CategoryVO>> list() {
        return null;
    }

    /**
     * 获取分类详情
     *
     * @param id 分类id
     * @return 分类详情
     */
    @GetMapping("/details/{id}")
    public ResultBean<Category> getDetails(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 添加分类
     *
     * @param categoryDTO 分类信息
     * @return 添加结果
     */
    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody CategoryDTO categoryDTO) {
        return null;
    }

    /**
     * 修改分类
     *
     * @param categoryDTO 分类信息
     * @return 修改结果
     */
    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody CategoryDTO categoryDTO) {
        return null;
    }

    /**
     * 删除分类
     *
     * @param id 分类id
     * @return 删除结果
     */
    @GetMapping("/delete/{id}")
    public ResultBean<Void> delete(@PathVariable("id") Long id) {
        return null;
    }
}
