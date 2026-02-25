package com.backend.bean.base;

import com.backend.constant.Constant;
import lombok.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseQuery {
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 页面大小
     */
    private Integer pageSize;

    public Integer getPageNum() {
        return this.pageNum == null ? 1 : this.pageNum;
    }

    public Integer getPageSize() {
        return this.pageSize == null ? Constant.PAGE_SIZE : this.pageSize;
    }
}
