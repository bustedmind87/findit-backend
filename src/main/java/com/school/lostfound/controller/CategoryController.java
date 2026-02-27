package com.school.lostfound.controller;

import com.school.lostfound.model.Category;
import com.school.lostfound.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository repo;

    public CategoryController(CategoryRepository repo){
        this.repo = repo;
    }

    @GetMapping
    public List<Category> list(){
        return repo.findAll();
    }

    @PostMapping
    public Category create(@RequestBody Category c){
        return repo.save(c);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id,@RequestBody Category c) {
        c.setId(id);
        return repo.save(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        repo.deleteById(id);
    }

}
