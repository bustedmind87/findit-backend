package com.school.lostfound.service;

import com.school.lostfound.dto.CategoryStatsDto;
import com.school.lostfound.dto.SummaryDto;
import com.school.lostfound.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {

    private final ItemRepository itemRepo;

    public StatsService(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    public SummaryDto getSummary(int days) {
        long found = itemRepo.countByTypeInLastDays("FOUND", days);
        long lost = itemRepo.countByTypeInLastDays("LOST", days);
        long total = itemRepo.count();
        long pending = itemRepo.findAll().stream()
                .filter(i -> i.getStatus() == null ? false : i.getStatus().name().equals("PENDING"))
                .count();
        return new SummaryDto(total, found, lost, pending);
    }

    public List<CategoryStatsDto> getCategoryStats(int days) {
        List<Object[]> rows = itemRepo.categoryStats(days);
        List<CategoryStatsDto> out = new ArrayList<>();
        for (Object[] r : rows) {
            String category = (String) r[0];
            int found = ((Number) r[1]).intValue();
            int lost = ((Number) r[2]).intValue();
            out.add(new CategoryStatsDto(category, found, lost));
        }
        return out;
    }

}
