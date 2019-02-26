package com.dataprotect.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_produit;

    @Column(unique = true)
    private String id_cpe;

    private String nom;
    private String version;
    private String Type;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AffectedProduct> affectedProductList = new ArrayList<>();

    public Product(String id_cpe, String nom, String version, String type) {
        this.id_cpe = id_cpe;
        this.nom = nom;
        this.version = version;
        Type = type;
    }

    public Product() { }

    public Long getId_produit() {
        return id_produit;
    }

    public void setId_produit(Long id_produit) {
        this.id_produit = id_produit;
    }

    public String getId_cpe() {
        return id_cpe;
    }

    public void setId_cpe(String id_cpe) {
        this.id_cpe = id_cpe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
