package com.example.vessel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

@RestController
@RequestMapping("/api/ships")
public class ShipController {

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }

    @PostMapping
    public Ship addShip(@RequestBody ShipRequest shipRequest) {
        Ship ship = new Ship();
        ship.setShipName(shipRequest.getShipName());
        ship.setImoNumber(shipRequest.getImoNumber());
        
        if (shipRequest.getOwnerId() != null) {
            Owner owner = ownerRepository.findById(shipRequest.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));
            ship.setOwner(owner);
        }

        if (shipRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(shipRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
            ship.setCategory(category);
        }

        return shipRepository.save(ship);
    }

    @PutMapping("/{id}")
    public Ship updateShip(@PathVariable int id, @RequestBody ShipRequest shipRequest) {
        Ship ship = shipRepository.findById(id).orElse(new Ship());
        ship.setId(id);
        ship.setShipName(shipRequest.getShipName());
        ship.setImoNumber(shipRequest.getImoNumber());
        
        if (shipRequest.getOwnerId() != null) {
            Owner owner = ownerRepository.findById(shipRequest.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));
            ship.setOwner(owner);
        }

        if (shipRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(shipRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
            ship.setCategory(category);
        }

        return shipRepository.save(ship);
    }

    @DeleteMapping("/{id}")
    public void deleteShip(@PathVariable int id) {
        shipRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Ship getShipDetails(@PathVariable int id) {
        return shipRepository.findById(id).orElse(null);
    }

    public static class ShipRequest {
        private String shipName;
        private Integer imoNumber;
        private Integer ownerId;
        private Integer categoryId;

        public String getShipName() { return shipName; }
        public void setShipName(String shipName) { this.shipName = shipName; }
        public Integer getImoNumber() { return imoNumber; }
        public void setImoNumber(Integer imoNumber) { this.imoNumber = imoNumber; }
        public Integer getOwnerId() { return ownerId; }
        public void setOwnerId(Integer ownerId) { this.ownerId = ownerId; }
        public Integer getCategoryId() { return categoryId; }
        public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    }
}
