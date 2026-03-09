package com.backend.domain.dto;

import lombok.*;

/**
 * 客户端待办事项专注时间更新DTO
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientTodoFocusTimeUpdateDTO {
    /**
     * 待办事项ID
     */
    private Long id;

    /**
     * 专注时间（秒）
     */
    private Long focusTime;
}
