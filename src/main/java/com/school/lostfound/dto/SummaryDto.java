package com.school.lostfound.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SummaryDto {
    private long totalItems;
    private long found30d;
    private long lost30d;
    private long pending;
}
