package com.example.vessel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ShipRepository shipRepository;

    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @PostMapping
    public Owner addOwner(@RequestBody OwnerRequest ownerRequest) {
        Owner owner = new Owner();
        owner.setOwnerName(ownerRequest.getOwnerName());
        return ownerRepository.save(owner);
    }

    @PutMapping("/{id}")
    public Owner updateOwner(@PathVariable int id, @RequestBody OwnerRequest ownerRequest) {
        Owner owner = ownerRepository.findById(id).orElse(new Owner());
        owner.setOwnerId(id);
        owner.setOwnerName(ownerRequest.getOwnerName());
        return ownerRepository.save(owner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable int id) {
        Owner owner = ownerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Owner not found"));

        for (Ship ship : owner.getShips()) {
            ship.getOwners().remove(owner);
            shipRepository.save(ship);
        }

        ownerRepository.delete(owner);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Owner getOwnerDetails(@PathVariable int id) {
        return ownerRepository.findById(id).orElse(null);
    }

    public static class OwnerRequest {
        private String ownerName;

        public String getOwnerName() { return ownerName; }
        public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    }
} 