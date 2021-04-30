/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Entities;

import java.util.Objects;

/**
 *
 * @author khali
 */
public class FournisseurService {
    private int id;
    private String fourniseur;
     private  String contact;
     private String numero; 
     private String gouvernorat;
     private String maplocation;
     private Service service;

    public int getId() {
        return id;
    }

    public FournisseurService() {
    }

    public FournisseurService(int id, String fourniseur, String contact, String numero, String gouvernorat, String maplocation) {
        this.id = id;
        this.fourniseur = fourniseur;
        this.contact = contact;
        this.numero = numero;
        this.gouvernorat = gouvernorat;
        this.maplocation = maplocation;
    }

    public FournisseurService(String fourniseur, String contact, String numero, String gouvernorat, String maplocation, Service service) {
        this.fourniseur = fourniseur;
        this.contact = contact;
        this.numero = numero;
        this.gouvernorat = gouvernorat;
        this.maplocation = maplocation;
        this.service = service;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFourniseur() {
        return fourniseur;
    }

    public FournisseurService(int id, String fourniseur, String contact, String numero, String gouvernorat, String maplocation, Service service) {
        this.id = id;
        this.fourniseur = fourniseur;
        this.contact = contact;
        this.numero = numero;
        this.gouvernorat = gouvernorat;
        this.maplocation = maplocation;
        this.service = service;
    }

    public void setFourniseur(String fourniseur) {
        this.fourniseur = fourniseur;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.fourniseur);
        hash = 37 * hash + Objects.hashCode(this.service);
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
        final FournisseurService other = (FournisseurService) obj;
        if (!Objects.equals(this.fourniseur, other.fourniseur)) {
            return false;
        }
        if (!Objects.equals(this.service, other.service)) {
            return false;
        }
        return true;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getMaplocation() {
        return maplocation;
    }

    public void setMaplocation(String maplocation) {
        this.maplocation = maplocation;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "FournniseurService{" + "id=" + id + ", fourniseur=" + fourniseur + ", contact=" + contact + ", numero=" + numero + ", gouvernorat=" + gouvernorat + ", maplocation=" + maplocation + ", service=" + service + '}';
    }
     
     
}
