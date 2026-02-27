package com.school.lostfound.service;

import com.school.lostfound.model.Item;
import com.school.lostfound.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository repo;

    public ItemService(ItemRepository repo){ this.repo = repo; }

    public List<Item> listAll() {
        return repo.findAll();
    }

    public Optional<Item> get(Long id) {
        return repo.findById(id);
    }

    public Item create(Item item) {
        return repo.save(item);
    }

    public Item updateStatus(Long id, String status) {
        return repo.findById(id)
            .map(i -> { i.setStatus(Item.Status.valueOf(status));
                return repo.save(i); })
            .orElse(null);
    }



}
