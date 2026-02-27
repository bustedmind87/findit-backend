package com.school.lostfound.controller;

import com.school.lostfound.dto.CategoryStatsDto;
import com.school.lostfound.dto.SummaryDto;
import com.school.lostfound.service.StatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/stats")
public class AdminStatsController {

    private final StatsService stats;

    public AdminStatsController(StatsService stats){
        this.stats = stats;
    }

    @GetMapping("/summary")
    public SummaryDto summary(@RequestParam(defaultValue="30") int days){
        return stats.getSummary(days);
    }

    @GetMapping("/category")
    public List<CategoryStatsDto> categoryStats(@RequestParam(defaultValue="30") int days){
        return stats.getCategoryStats(days);
    }
}
