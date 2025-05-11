package com.example.vessel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.ManyToMany;

@Entity(name = "Ships")
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Ship_name")
    private String shipName;

    @Column(name = "Imo_number")
    private Integer imoNumber;

    @ManyToOne
    @JoinColumn(name = "Owner_Id")
    private Owner owner;

    @OneToOne(mappedBy = "ship")
    private Category category;

    @ManyToMany
    @JoinColumn(name = "Ship_Id", referencedColumnName = "Id")
    private Set<Owner> owners = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public Integer getImoNumber() {
        return imoNumber;
    }

    public void setImoNumber(Integer imoNumber) {
        this.imoNumber = imoNumber;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }
}
