package com.backend.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LocalDateRange {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
}
