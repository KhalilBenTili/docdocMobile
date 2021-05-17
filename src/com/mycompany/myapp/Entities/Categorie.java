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
public class Categorie {
    private int id;
    private String Titre;
    private String Description;

    public Categorie() {
    }

    public Categorie(String Titre, String Description) {
        this.Titre = Titre;
        this.Description = Description;
    }

    public Categorie(int id, String Titre, String Description) {
        this.id = id;
        this.Titre = Titre;
        this.Description = Description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", Titre=" + Titre + ", Description=" + Description + '}';
    }
    
    
}
