package com.backend.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.backend.utils.JsonUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class EasyExcelListener<T> extends AnalysisEventListener<T> {
    /**
     * 批处理阈值
     */
    private static final int BATCH_COUNT = 20;

    /**
     * 用来存放待处理的数据
     */
    @Getter
    private final List<T> list = new ArrayList<>(BATCH_COUNT);

    /**
     * 具体业务
     */
    protected abstract void exec(List<T> list);

    /**
     * 读取excel数据前操作 <br>
     * 只有不读取表头数据时才会触发此方法)
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("解析第一行数据:{}", JsonUtil.obj2string(headMap));
    }

    /**
     * 读取excel数据操作
     * 每读取到一行数据，invoke 方法就会执行一次，数据会被添加到 list 集合中
     * 只有当 list 中的数据量达到 BATCH_COUNT（20条）时，才会执行 exec 方法，这是一种批处理机制，用于提高处理效率
     * @param obj
     * @param context
     */
    @Override
    public void invoke(T obj, AnalysisContext context) {
        list.add(obj);

        if (list.size() >= BATCH_COUNT) {
            // 将数据保存到数据库中
            exec(list);
            list.clear();
        }
    }

    /**
     * 读取完excel数据后的操作
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (!list.isEmpty()) {
            exec(list);
            // 清空list集合
            list.clear();
        }
    }

    /**
     * 在读取excel异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
        if (exception instanceof ExcelDataConvertException ex) {
            log.error(
                    "第{}行，第{}列解析异常，数据为:{}",
                    ex.getRowIndex(),
                    ex.getColumnIndex(),
                    ex.getCellData());
        }
    }
}
