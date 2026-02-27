package com.school.lostfound.controller;


import com.school.lostfound.model.Claim;
import com.school.lostfound.model.Item;
import com.school.lostfound.repository.ClaimRepository;
import com.school.lostfound.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimRepository claimRepo;

    private final ItemRepository itemRepo;

    public ClaimController(ClaimRepository claimRepo,
                           ItemRepository itemRepo){
        this.claimRepo = claimRepo;
        this.itemRepo = itemRepo;
    }

    @GetMapping
    public List<Claim> list(){
        return claimRepo.findAll();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Claim> updateStatus(@PathVariable Long id, @RequestBody Claim payload){
        Optional<Claim> opt = claimRepo.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        Claim claim = opt.get();
        claim.setStatus(payload.getStatus());
        claimRepo.save(claim);

        if (payload.getStatus() == Claim.ClaimStatus.APPROVED) {
            Optional<Item> oi = itemRepo.findById(claim.getItemId());
            if (oi.isPresent()) {
                Item item = oi.get();
                item.setStatus(Item.Status.CLAIMED);
                itemRepo.save(item);
            }
        }
        return ResponseEntity.ok(claim);
    }

    @PostMapping
    public ResponseEntity<Claim> create(@RequestBody Claim c) {
        c.setStatus(Claim.ClaimStatus.PENDING);
        c.setCreatedAt(Instant.now().toString());
        Claim saved = claimRepo.save(c);
        return ResponseEntity.ok(saved);
    }
}

