import com.backend.bean.PageBean;
import com.backend.domain.entity.${Entity};
import com.backend.domain.query.${Entity}Query;

import java.util.List;

public interface ${Entity}Service {

    /**
     * 分页获取${desc}列表
     * @param ${obj}Query 查询参数
     * @return ${desc}分页数据
     */
    PageBean<${Entity}> getPage(${Entity}Query ${obj}Query);

    /**
     * 根据ID获取${desc}信息
     * @param id ${desc}ID
     * @return ${desc}信息
     */
    ${Entity} getById(Long id);

    /**
     * 更新${desc}信息
     * @param ${obj} ${desc}信息对象
     * @return 更新成功的记录数
     */
    int insertOrUpdate(${Entity} ${obj});

    /**
     * 根据ID删除${desc}
     * @param id ${desc}ID
     * @return 删除成功的记录数
     */
    int deleteById(Long id);

    /**
     * 批量删除${desc}
     * @param ids ${desc}ID列表
     * @return 删除成功的记录数
     */
    int deleteByIds(String ids);

    /**
     * 批量插入${desc}
     * @param ${obj}List ${desc}列表
     * @return 插入成功的记录数
     */
    int batchInsert(List<${Entity}> ${obj}List);

}
