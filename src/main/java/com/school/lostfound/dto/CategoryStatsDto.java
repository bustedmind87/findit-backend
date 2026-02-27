package com.school.lostfound.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryStatsDto {
    private String category;
    private int foundCount;
    private int lostCount;
}
