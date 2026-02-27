package com.school.lostfound.repository;

import com.school.lostfound.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT COUNT(*) FROM items i WHERE i.type = :type AND i.date_found >= CURDATE() - INTERVAL :days DAY AND i.status <> 'REJECTED'", nativeQuery = true)
    int countByTypeInLastDays(@Param("type") String type, @Param("days") int days);

    @Query(value = "SELECT c.name AS category, " + "SUM(CASE WHEN i.type='FOUND' THEN 1 ELSE 0 END) AS foundCount, "
            + "SUM(CASE WHEN i.type='LOST' THEN 1 ELSE 0 END) AS lostCount "
            + "FROM categories c LEFT JOIN items i ON i.category_id = c.id "
            + "AND i.date_found >= CURDATE() - INTERVAL :days DAY "
            + "AND i.status <> 'REJECTED' "
            + "WHERE c.active = 1 GROUP BY c.id, c.name", nativeQuery = true)
    List<Object[]> categoryStats(@Param("days") int days);
}
