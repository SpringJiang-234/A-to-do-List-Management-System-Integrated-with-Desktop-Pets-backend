import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backend.bean.PageBean;
import com.backend.domain.entity.${Entity};
import com.backend.domain.query.${Entity}Query;
import com.backend.mapper.${Entity}Mapper;
import com.backend.service.${Entity}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ${Entity}ServiceImpl implements ${Entity}Service {
    @Autowired
    private ${Entity}Mapper ${obj}Mapper;

    @Override
    public PageBean<${Entity}> getPage(${Entity}Query ${obj}Query) {
        final Integer pageNum = ${obj}Query.getPageNum();
        final Integer pageSize = ${obj}Query.getPageSize();
        final Page<${Entity}> page = PageHelper.startPage(pageNum, pageSize);
        ${obj}Mapper.selectWithCondition(${obj}Query);
        return PageBean.page2pageBean(page);
    }


    @Override
    public ${Entity} getById(Long id) {
        return ${obj}Mapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(${Entity} ${obj}) {
        return ${obj}Mapper.insertOrUpdateSelective(${obj});
    }

    @Override
    public int deleteById(Long id) {
        return ${obj}Mapper.deleteByPrimaryKey(id);
    }

    @Override
     @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(String ids) {
        final String[] split = ids.split(",");
        return ${obj}Mapper.deleteByPrimaryKeyIn(split);
    }

    @Override
    public int batchInsert(List<${Entity}> ${obj}List) {
        return ${obj}Mapper.batchInsert(${obj}List);
    }
}
