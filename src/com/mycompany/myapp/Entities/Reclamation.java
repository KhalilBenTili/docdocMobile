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
public class Reclamation {
    private int id;
    private int user_id;
    private String motif;
    private String description;
    private boolean  etat;
    private String email;

    public Reclamation(int id, int user_id, String motif, String description) {
        this.id = id;
        this.user_id = user_id;
        this.motif = motif;
        this.description = description;
    }
    

    public Reclamation(int user_id, String motif, String description) {
        this.user_id = user_id;
        this.motif = motif;
        this.description = description;
    }

    public Reclamation(String motif, String description) {
        this.motif = motif;
        this.description = description;
    }

    public Reclamation() {
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", user_id=" + user_id + ", motif=" + motif + ", description=" + description + ", email=" + email + '}';
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
