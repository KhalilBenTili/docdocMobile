/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Entities;

import java.util.Date;

/**
 *
 * @author khali
 */
public class article {
     private String titre;
   private String contenu;
   private int	nblike;
   private int nbdislike;
   private String created_at;
   private int id;
   private String img;
   private String cat;

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public article(String titre, String contenu, int nblike, int nbdislike, String created_at, int id, String img, String cat) {
        this.titre = titre;
        this.contenu = contenu;
        this.nblike = nblike;
        this.nbdislike = nbdislike;
        this.created_at = created_at;
        this.id = id;
        this.img = img;
        this.cat = cat;
    }

    public article() {
    }

   
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getNblike() {
        return nblike;
    }

    public void setNblike(int nblike) {
        this.nblike = nblike;
    }

    public int getNbdislike() {
        return nbdislike;
    }

    public void setNbdislike(int nbdislike) {
        this.nbdislike = nbdislike;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
