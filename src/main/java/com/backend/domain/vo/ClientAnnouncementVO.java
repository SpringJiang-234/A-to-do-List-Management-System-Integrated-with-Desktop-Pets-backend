package com.backend.domain.vo;

import com.backend.bean.base.BaseVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClientAnnouncementVO extends BaseVO {
    private String title;
    private LocalDateTime updateTime;
    private String isTop;
}
