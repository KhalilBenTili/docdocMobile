/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Entities;


import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author khali
 */
public class Service implements Comparable<Service> {
    private int id;
    private String libelle;
    private CategorieService categorie;
    private float prix;
    private String description;
    private boolean disponibilite;
    private ArrayList<FournisseurService> fournisseurs;
    private float avgrating;

   
    public Service() {
        this.avgrating=0;
        this.fournisseurs=new ArrayList<>();
        
    }

    public Service(int id, String libelle, CategorieService categorie, float prix, String description, boolean disponibilite, float avgrating) {
        this.id = id;
        this.libelle = libelle;
        this.categorie = categorie;
        this.prix = prix;
        this.description = description;
        this.disponibilite = disponibilite;
        this.avgrating = avgrating;
    }
    public Service(String libelle, CategorieService categorie, float prix, String description, boolean disponibilite) {
        this.libelle = libelle;
        this.categorie = categorie;
        this.prix = prix;
        this.description = description;
        this.disponibilite = disponibilite;
        this.avgrating = 0;
    }

    public Service(int id, String libelle, CategorieService categorie, float prix, String description, boolean disponibilite, ArrayList fournisseurs) {
        this.id = id;
        this.libelle = libelle;
        this.categorie = categorie;
        this.prix = prix;
        this.description = description;
        this.disponibilite = disponibilite;
        this.fournisseurs = fournisseurs;
        this.avgrating = 0;
    }

    public Service(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public CategorieService getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieService categorie) {
        this.categorie = categorie;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public ArrayList<FournisseurService> getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(ArrayList<FournisseurService> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    

    public float getAvgrating() {
        return avgrating;
    }

    public void setAvgrating(float avgrating) {
        this.avgrating = avgrating;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Service other = (Service) obj;
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (Float.floatToIntBits(this.avgrating) != Float.floatToIntBits(other.avgrating)) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getLibelle();
    }

  

    @Override
    public int compareTo(Service o) {
        return  (int) ((int) prix-o.getPrix());
    }

 
    
   

}