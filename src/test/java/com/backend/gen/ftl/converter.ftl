import com.backend.bean.PageBean;
import com.backend.domain.details.${Entity}Details;
import com.backend.domain.dto.${Entity}DTO;
import com.backend.domain.entity.${Entity};
import com.backend.domain.excel.${Entity}Excel;
import com.backend.domain.vo.${Entity}VO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ${Entity}Converter {

    ${Entity} ${obj}DTO2${obj}(${Entity}DTO ${obj}DTO);

    /**
     * 将${Entity}对象转换为${Entity}Details对象
     * 用于转换${desc}基本信息
     *
     * @param ${obj} 源${Entity}对象，包含${desc}基本信息
     * @return ${Entity}Details对象，包含${desc}详细信息
     */
    ${Entity}Details ${obj}2${obj}Details(${Entity} ${obj});

    /**
     * 将PageBean<${Entity}>分页对象转换为PageBean<${Entity}VO>分页对象
     * 用于分页数据的类型转换，保持分页结构不变
     *
     * @param ${obj}PageBean 源${Entity}分页对象，包含${desc}信息的分页数据
     * @return ${Entity}VO分页对象，包含转换后的${desc}VO对象的分页数据
     */
    PageBean<${Entity}VO> ${obj}PageBean2${obj}VOPageBean(PageBean<${Entity}> ${obj}PageBean);

    /**
     * 将${Entity}对象转换为${Entity}Excel对象
     * 用于Excel导出${desc}信息
     *
     * @param ${obj} 源${Entity}对象，包含${desc}基本信息
     * @return ${Entity}Excel对象，包含${desc}Excel信息
     */
    ${Entity}Excel ${obj}2${obj}Excel(${Entity} ${obj});

    /**
     * 将${Entity}列表转换为${Entity}Excel列表
     * 批量转换${desc}信息用于Excel导出
     *
     * @param ${obj}List 源${Entity}对象列表
     * @return ${Entity}Excel对象列表，用于Excel导出
     */
    List<${Entity}Excel> ${obj}List2${obj}ExcelList(List<${Entity}> ${obj}List);

    /**
     * 将${Entity}Excel对象转换为${Entity}对象
     * 用于从Excel导入${desc}信息
     *
     * @param ${obj}Excel 源${Entity}Excel对象，包含从Excel导入的${desc}信息
     * @return ${Entity}对象，包含转换后的${desc}基本信息
     */
    ${Entity} ${obj}Excel2${obj}(${Entity}Excel ${obj}Excel);

    /**
     * 将${Entity}Excel列表转换为${Entity}列表
     * 批量转换从Excel导入的${desc}信息
     *
     * @param ${obj}ExcelList 源${Entity}Excel对象列表，包含从Excel导入的${desc}信息
     * @return ${Entity}对象列表，包含转换后的${desc}基本信息
     */
    List<${Entity}> ${obj}ExcelList2${obj}List(List<${Entity}Excel> ${obj}ExcelList);

}

