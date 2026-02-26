package com.backend.controller.admin;

import com.backend.bean.PageBean;
import com.backend.bean.ResultBean;
import com.backend.converter.CategoryConverter;
import com.backend.domain.details.CategoryDetails;
import com.backend.domain.dto.CategoryDTO;
import com.backend.domain.entity.Category;
import com.backend.domain.excel.CategoryExcel;
import com.backend.domain.query.CategoryQuery;
import com.backend.domain.vo.CategoryVO;
import com.backend.ex.GlobalException;
import com.backend.service.CategoryService;
import com.backend.utils.excel.EasyExcelListener;
import com.backend.utils.excel.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 类别控制层
 *
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryConverter categoryConverter;


    @PostMapping("/page")
    public ResultBean<PageBean<CategoryVO>> page(@RequestBody CategoryQuery categoryQuery) {
        final PageBean<Category> categoryPageBean = categoryService.getPage(categoryQuery);
        final PageBean<CategoryVO> pageBean = categoryConverter.categoryPageBean2categoryVOPageBean(categoryPageBean);
        return ResultBean.success(pageBean);
    }

    @GetMapping("/details/{id}")
    public ResultBean<CategoryDetails> getDetails(@PathVariable("id") Long id) {
        final Category category = categoryService.getById(id);
        final CategoryDetails categoryDetails = categoryConverter.category2categoryDetails(category);
        return ResultBean.success(categoryDetails);
    }

    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody CategoryDTO categoryDTO) {
        final Category category = categoryConverter.categoryDTO2category(categoryDTO);
        categoryService.insertOrUpdate(category);
        return ResultBean.success("修改成功!", null);
    }

    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody CategoryDTO categoryDTO) {
        final Category category = categoryConverter.categoryDTO2category(categoryDTO);
        categoryService.insertOrUpdate(category);
        return ResultBean.success("添加成功!", null);
    }

    @GetMapping("/delete/{id}")
    public ResultBean<Void> delete(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return ResultBean.success("删除成功!", null);
    }

    @GetMapping("/batchDelete/{ids}")
    public ResultBean<Void> batchDelete(@PathVariable("ids") String ids) {
        categoryService.deleteByIds(ids);
        return ResultBean.success("批量删除成功!", null);
    }

    @PostMapping("/export")
    public void exportData(HttpServletResponse response, @RequestBody CategoryQuery categoryQuery) {
        final PageBean<Category> pageBean = categoryService.getPage(categoryQuery);

        final List<CategoryExcel> list = categoryConverter.categoryList2categoryExcelList(pageBean.getRecords());
        try {
            EasyExcelUtil.writeWithSheetsWeb(response, "category列表")
                    .writeModel(CategoryExcel.class, list, "category")
                    .finish();
        } catch (IOException e) {
            throw new GlobalException("类别信息导出失败！");
        }
    }

    @PostMapping("/import")
    public ResultBean<Void> importData(@RequestPart(value = "file", required = true) MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            final EasyExcelListener<CategoryExcel> listener = new EasyExcelListener<CategoryExcel>() {
                @Override
                protected void exec(List<CategoryExcel> list) {
                    final List<Category> categoryList = categoryConverter.categoryExcelList2categoryList(list);
                    categoryService.batchInsert(categoryList);
                }
            };
            EasyExcelUtil.asyncReadModel(is, listener, CategoryExcel.class);
        } catch (IOException e) {
            throw new GlobalException("类别信息导入失败！");
        }
        return ResultBean.success("类别信息导入成功!", null);
    }
}
