package com.backend.domain.dto;

import lombok.*;

import java.util.List;

/**
 * 客户端待办事项表
 */
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClientTodoDTO extends TodoDTO {
    /**
    * 标签ID列表
    */
    private List<Long> tagIdList;
    
    /**
    * 是否启用桌宠养成数据
    */
    private boolean enablePetGrowth = true;
}
