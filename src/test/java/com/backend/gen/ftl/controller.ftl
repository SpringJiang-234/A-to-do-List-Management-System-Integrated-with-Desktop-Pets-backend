import com.backend.bean.PageBean;
import com.backend.bean.ResultBean;
import com.backend.converter.${Entity}Converter;
import com.backend.domain.details.${Entity}Details;
import com.backend.domain.dto.${Entity}DTO;
import com.backend.domain.entity.${Entity};
import com.backend.domain.excel.${Entity}Excel;
import com.backend.domain.query.${Entity}Query;
import com.backend.domain.vo.${Entity}VO;
import com.backend.ex.GlobalException;
import com.backend.service.${Entity}Service;
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
 * ${desc}控制层
 *
 */
@RestController
@RequestMapping("/${obj}")
public class ${Entity}Controller {
    @Autowired
    private ${Entity}Service ${obj}Service;
    @Autowired
    private ${Entity}Converter ${obj}Converter;


    @PostMapping("/page")
    public ResultBean<PageBean<${Entity}VO>> page(@RequestBody ${Entity}Query ${obj}Query) {
        final PageBean<${Entity}> ${obj}PageBean = ${obj}Service.getPage(${obj}Query);
        final PageBean<${Entity}VO> pageBean = ${obj}Converter.${obj}PageBean2${obj}VOPageBean(${obj}PageBean);
        return ResultBean.success(pageBean);
    }

    @GetMapping("/details/{id}")
    public ResultBean<${Entity}Details> getDetails(@PathVariable("id") Long id) {
        final ${Entity} ${obj} = ${obj}Service.getById(id);
        final ${Entity}Details ${obj}Details = ${obj}Converter.${obj}2${obj}Details(${obj});
        return ResultBean.success(${obj}Details);
    }

    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody ${Entity}DTO ${obj}DTO) {
        final ${Entity} ${obj} = ${obj}Converter.${obj}DTO2${obj}(${obj}DTO);
        ${obj}Service.insertOrUpdate(${obj});
        return ResultBean.success("修改成功!", null);
    }

    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody ${Entity}DTO ${obj}DTO) {
        final ${Entity} ${obj} = ${obj}Converter.${obj}DTO2${obj}(${obj}DTO);
        ${obj}Service.insertOrUpdate(${obj});
        return ResultBean.success("添加成功!", null);
    }

    @GetMapping("/delete/{id}")
    public ResultBean<Void> delete(@PathVariable("id") Long id) {
        ${obj}Service.deleteById(id);
        return ResultBean.success("删除成功!", null);
    }

    @GetMapping("/batchDelete/{ids}")
    public ResultBean<Void> batchDelete(@PathVariable("ids") String ids) {
        ${obj}Service.deleteByIds(ids);
        return ResultBean.success("批量删除成功!", null);
    }

    @PostMapping("/export")
    public void exportData(HttpServletResponse response, @RequestBody ${Entity}Query ${obj}Query) {
        final PageBean<${Entity}> pageBean = ${obj}Service.getPage(${obj}Query);

        final List<${Entity}Excel> list = ${obj}Converter.${obj}List2${obj}ExcelList(pageBean.getRecords());
        try {
            EasyExcelUtil.writeWithSheetsWeb(response, "${obj}列表")
                    .writeModel(${Entity}Excel.class, list, "${obj}")
                    .finish();
        } catch (IOException e) {
            throw new GlobalException("${desc}信息导出失败！");
        }
    }

    @PostMapping("/import")
    public ResultBean<Void> importData(@RequestPart(value = "file", required = true) MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            final EasyExcelListener<${Entity}Excel> listener = new EasyExcelListener<${Entity}Excel>() {
                @Override
                protected void exec(List<${Entity}Excel> list) {
                    final List<${Entity}> ${obj}List = ${obj}Converter.${obj}ExcelList2${obj}List(list);
                    ${obj}Service.batchInsert(${obj}List);
                }
            };
            EasyExcelUtil.asyncReadModel(is, listener, ${Entity}Excel.class);
        } catch (IOException e) {
            throw new GlobalException("${desc}信息导入失败！");
        }
        return ResultBean.success("${desc}信息导入成功!", null);
    }
}
