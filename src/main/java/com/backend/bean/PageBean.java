package com.backend.bean;

import com.github.pagehelper.Page;
import com.backend.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 每页显示的记录数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 当前页的数据记录列表
     */
    private List<T> records;

    /**
     * 将 Page 对象转换为 PageBean 对象
     * @param page
     * @return
     * @param <T>
     */
    public static <T> PageBean<T> page2pageBean(Page<T> page) {
        return page2pageBean(page, null);
    }

    /**
     * 将 Page 对象转换为 PageBean 对象，支持手动指定 total
     * @param page
     * @param total 手动指定的总数
     * @return
     * @param <T>
     */
    public static <T> PageBean<T> page2pageBean(Page<T> page, Long total) {
        if (page == null) {
            throw new IllegalArgumentException("Page 参数不能为 null");
        }
        final PageBean<T> pageBean = new PageBean<>();
        pageBean.setPageNum(page.getPageNum());
        pageBean.setPageSize(page.getPageSize());
        pageBean.setPages(page.getPages());
        pageBean.setTotal(total != null ? total : page.getTotal());
        pageBean.setRecords(page.getResult());
        return pageBean;
    }

    @Override
    public String toString() {
        return JsonUtil.obj2string(this);
    }
}
