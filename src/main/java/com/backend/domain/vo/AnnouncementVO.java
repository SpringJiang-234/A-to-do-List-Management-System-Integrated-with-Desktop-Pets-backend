package com.backend.domain.vo;

import com.backend.bean.base.BaseVO;
import lombok.*;

/**
 * 公告表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementVO extends BaseVO {
    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容：截取一段预览
     */
    private String content;

    /**
     * 是否置顶：1-未置顶 2-置顶
     */
    private String isTop;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;
}