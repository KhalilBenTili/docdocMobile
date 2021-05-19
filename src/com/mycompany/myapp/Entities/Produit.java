/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Entities;

/**
 *
 * @author khali
 */
public class Produit {
    private int id;
    private int categorie_id;
    private int reference;
    private String nom;
    private int quantite;
    private String description;
    private float prix;
    private int userid;
    private String path_image;
    
    private Categorie categorie;

    public Produit(int id,int reference, String nom, int quantite, String description, float prix) {
        this.id = id;
        this.reference = reference;
        this.nom = nom;
        this.quantite = quantite;
        this.description = description;
        this.prix = prix;
    }

    public Produit() {
    }

    public Produit(int categorie_id, int reference, String nom, int quantite, String description, float prix, int userid, String path_image, Categorie categorie) {
        this.categorie_id = categorie_id;
        this.categorie_id = this.categorie.getId();
        this.reference = reference;
        this.nom = nom;
        this.quantite = quantite;
        this.description = description;
        this.prix = prix;
        this.userid = userid;
        this.path_image = path_image;
        this.categorie = categorie;
    }
    public Produit(int categorie_id, int reference, String nom, int quantite, String description, float prix, int userid) {
        //this.categorie_id = categorie_id;
        this.categorie_id = categorie_id;
        this.reference = reference;
        this.nom = nom;
        this.quantite = quantite;
        this.description = description;
        this.prix = prix;
        this.userid = userid;
        //this.categorie = categorie;
    }

    public Produit(int id, int categorie_id, int reference, String nom, int quantite, String description, float prix, int userid, String path_image, Categorie categorie) {
        this.id = id;
        //this.categorie_id = categorie_id;
        this.categorie_id = this.categorie.getId();
        this.reference = reference;
        this.nom = nom;
        this.quantite = quantite;
        this.description = description;
        this.prix = prix;
        this.userid = userid;
        this.path_image = path_image;
        this.categorie = categorie;
    }

    public Produit(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPath_image() {
        return path_image;
    }

    public void setPath_image(String path_image) {
        this.path_image = path_image;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", categorie_id=" + categorie_id + ", reference=" + reference + ", nom=" + nom + ", quantite=" + quantite + ", description=" + description + ", prix=" + prix + ", userid=" + userid + ", path_image=" + path_image + ", categorie=" + categorie + '}';
    }
    
    
}
