package com.backend.controller;

import com.backend.bean.PageBean;
import com.backend.bean.ResultBean;
import com.backend.converter.DesktopPetConverter;
import com.backend.domain.details.DesktopPetDetails;
import com.backend.domain.dto.DesktopPetDTO;
import com.backend.domain.entity.DesktopPet;
import com.backend.domain.excel.DesktopPetExcel;
import com.backend.domain.query.DesktopPetQuery;
import com.backend.domain.vo.DesktopPetVO;
import com.backend.ex.GlobalException;
import com.backend.service.DesktopPetService;
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
 * 桌宠控制层
 *
 */
@RestController
@RequestMapping("/desktopPet")
public class DesktopPetController {
    @Autowired
    private DesktopPetService desktopPetService;
    @Autowired
    private DesktopPetConverter desktopPetConverter;


    @PostMapping("/page")
    public ResultBean<PageBean<DesktopPetVO>> page(@RequestBody DesktopPetQuery desktopPetQuery) {
        final PageBean<DesktopPet> desktopPetPageBean = desktopPetService.getPage(desktopPetQuery);
        final PageBean<DesktopPetVO> pageBean = desktopPetConverter.desktopPetPageBean2desktopPetVOPageBean(desktopPetPageBean);
        return ResultBean.success(pageBean);
    }

    @GetMapping("/details/{id}")
    public ResultBean<DesktopPetDetails> getDetails(@PathVariable("id") Long id) {
        final DesktopPet desktopPet = desktopPetService.getById(id);
        final DesktopPetDetails desktopPetDetails = desktopPetConverter.desktopPet2desktopPetDetails(desktopPet);
        return ResultBean.success(desktopPetDetails);
    }

    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody DesktopPetDTO desktopPetDTO) {
        final DesktopPet desktopPet = desktopPetConverter.desktopPetDTO2desktopPet(desktopPetDTO);
        desktopPetService.insertOrUpdate(desktopPet);
        return ResultBean.success("修改成功!", null);
    }

    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody DesktopPetDTO desktopPetDTO) {
        final DesktopPet desktopPet = desktopPetConverter.desktopPetDTO2desktopPet(desktopPetDTO);
        desktopPetService.insertOrUpdate(desktopPet);
        return ResultBean.success("添加成功!", null);
    }

    @GetMapping("/delete/{id}")
    public ResultBean<Void> delete(@PathVariable("id") Long id) {
        desktopPetService.deleteById(id);
        return ResultBean.success("删除成功!", null);
    }

    @GetMapping("/batchDelete/{ids}")
    public ResultBean<Void> batchDelete(@PathVariable("ids") String ids) {
        desktopPetService.deleteByIds(ids);
        return ResultBean.success("批量删除成功!", null);
    }

    @PostMapping("/export")
    public void exportData(HttpServletResponse response, @RequestBody DesktopPetQuery desktopPetQuery) {
        final PageBean<DesktopPet> pageBean = desktopPetService.getPage(desktopPetQuery);

        final List<DesktopPetExcel> list = desktopPetConverter.desktopPetList2desktopPetExcelList(pageBean.getRecords());
        try {
            EasyExcelUtil.writeWithSheetsWeb(response, "desktopPet列表")
                    .writeModel(DesktopPetExcel.class, list, "desktopPet")
                    .finish();
        } catch (IOException e) {
            throw new GlobalException("桌宠信息导出失败！");
        }
    }

    @PostMapping("/import")
    public ResultBean<Void> importData(@RequestPart(value = "file", required = true) MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            final EasyExcelListener<DesktopPetExcel> listener = new EasyExcelListener<DesktopPetExcel>() {
                @Override
                protected void exec(List<DesktopPetExcel> list) {
                    final List<DesktopPet> desktopPetList = desktopPetConverter.desktopPetExcelList2desktopPetList(list);
                    desktopPetService.batchInsert(desktopPetList);
                }
            };
            EasyExcelUtil.asyncReadModel(is, listener, DesktopPetExcel.class);
        } catch (IOException e) {
            throw new GlobalException("桌宠信息导入失败！");
        }
        return ResultBean.success("桌宠信息导入成功!", null);
    }
}
