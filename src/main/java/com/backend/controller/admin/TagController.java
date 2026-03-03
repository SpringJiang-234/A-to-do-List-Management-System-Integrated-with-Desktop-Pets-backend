package com.backend.controller.admin;

import com.backend.bean.PageBean;
import com.backend.bean.ResultBean;
import com.backend.converter.TagConverter;
import com.backend.domain.details.TagDetails;
import com.backend.domain.dto.TagDTO;
import com.backend.domain.entity.Tag;
import com.backend.domain.excel.TagExcel;
import com.backend.domain.query.TagQuery;
import com.backend.domain.vo.TagVO;
import com.backend.ex.GlobalException;
import com.backend.service.TagService;
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
 * 标签控制层
 *
 */
@RestController
@RequestMapping("/admin/tag")
public class TagController {
    @Autowired
    private TagService tagService;
    @Autowired
    private TagConverter tagConverter;

    /**
     * 获取标签分页数据：暂时没用
     *
     * @param tagQuery
     * @return
     */
    @PostMapping("/page")
    public ResultBean<PageBean<TagVO>> page(@RequestBody TagQuery tagQuery) {
        final PageBean<Tag> tagPageBean = tagService.getPage(tagQuery);
        final PageBean<TagVO> pageBean = tagConverter.tagPageBean2tagVOPageBean(tagPageBean);
        return ResultBean.success(pageBean);
    }

    /**
     * 获取标签详情：暂时没用
     *
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    public ResultBean<TagDetails> getDetails(@PathVariable("id") Long id) {
        final Tag tag = tagService.getById(id);
        final TagDetails tagDetails = tagConverter.tag2tagDetails(tag);
        return ResultBean.success(tagDetails);
    }

    /**
     * 修改标签信息：暂时没用
     *
     * @param tagDTO
     * @return
     */
    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody TagDTO tagDTO) {
        final Tag tag = tagConverter.tagDTO2tag(tagDTO);
        tagService.insertOrUpdate(tag);
        return ResultBean.success("修改成功!", null);
    }

    /**
     * 添加标签信息：暂时没用
     *
     * @param tagDTO
     * @return
     */
    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody TagDTO tagDTO) {
        final Tag tag = tagConverter.tagDTO2tag(tagDTO);
        tagService.insertOrUpdate(tag);
        return ResultBean.success("添加成功!", null);
    }

    /**
     * 删除标签信息：暂时没用
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public ResultBean<Void> delete(@PathVariable("id") Long id) {
        tagService.deleteById(id);
        return ResultBean.success("删除成功!", null);
    }

    /**
     * 批量删除标签信息：暂时没用
     *
     * @param ids
     * @return
     */
    @GetMapping("/batchDelete/{ids}")
    public ResultBean<Void> batchDelete(@PathVariable("ids") String ids) {
        tagService.deleteByIds(ids);
        return ResultBean.success("批量删除成功!", null);
    }

    /**
     * 导出标签信息：暂时没用
     *
     * @param tagQuery
     * @param response
     */
    @PostMapping("/export")
    public void exportData(HttpServletResponse response, @RequestBody TagQuery tagQuery) {
        final PageBean<Tag> pageBean = tagService.getPage(tagQuery);

        final List<TagExcel> list = tagConverter.tagList2tagExcelList(pageBean.getRecords());
        try {
            EasyExcelUtil.writeWithSheetsWeb(response, "tag列表")
                    .writeModel(TagExcel.class, list, "tag")
                    .finish();
        } catch (IOException e) {
            throw new GlobalException("标签信息导出失败！");
        }
    }

    /**
     * 导入标签信息：暂时没用
     *
     * @param file
     * @return
     */
    @PostMapping("/import")
    public ResultBean<Void> importData(@RequestPart(value = "file", required = true) MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            final EasyExcelListener<TagExcel> listener = new EasyExcelListener<TagExcel>() {
                @Override
                protected void exec(List<TagExcel> list) {
                    final List<Tag> tagList = tagConverter.tagExcelList2tagList(list);
                    tagService.batchInsert(tagList);
                }
            };
            EasyExcelUtil.asyncReadModel(is, listener, TagExcel.class);
        } catch (IOException e) {
            throw new GlobalException("标签信息导入失败！");
        }
        return ResultBean.success("标签信息导入成功!", null);
    }

    /**
     * 获取标签总数
     *
     * @return 标签总数
     */
    @GetMapping("/total")
    public ResultBean<Integer> getTotalTags() {
        final int total = tagService.countTotalTags();
        return ResultBean.success(total);
    }

    /**
     * 获取近七天标签数新增趋势
     *
     * @return 近七天每天的新标签数
     */
    @GetMapping("/trend")
    public ResultBean<java.util.List<java.util.Map<String, Object>>> getNewTagsTrend() {
        final java.util.List<java.util.Map<String, Object>> trend = tagService.getNewTagsTrend();
        return ResultBean.success(trend);
    }
}
