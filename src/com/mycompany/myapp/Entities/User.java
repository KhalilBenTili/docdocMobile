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

/**
 *
 * @author zagho
 */
public class User {
    private int id;
    private String email;
    private String roles;
    private String password;
    private String nom;
    private String prenom;
    private Date dnaissance;
    private String type;
    private String numtel;
    private String adresse;
    private String image;
    private String cin;
    private String matricule;
    private String cnam;
    private String cnss;
    private String specialite;
    private Date disponabilite;
    private String societe;
    private String pharamcie; 
    private boolean is_blocked; 
    
    public User(int id, String email, String password, String nom,String prenom,String  type,String numtel,boolean is_blocked) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.numtel = numtel;
        this.is_blocked = is_blocked;
    }
    public User(int id,String nom,String prenom,String email,String type,String numTel,String cin)
{
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.cin = cin;
   
}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

     public User(String email, String nom, String prenom, String type) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
    }
    public User(int id, String email, String password, String nom, String prenom, Date dnaissance, String type, String numtel, String adresse) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.dnaissance = dnaissance;
        this.type = type;
        this.numtel = numtel;
        this.adresse = adresse;
    }

    public User(int id, String email, String roles, String password, String nom, String prenom, Date dnaissance, String type, String numtel, String adresse) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.dnaissance = dnaissance;
        this.type = type;
        this.numtel = numtel;
        this.adresse = adresse;
    }

    public User(int id, String email, String roles, String password, String nom, String prenom, String type, String numtel, String adresse, String cin, String matricule, String cnam, String cnss, String specialite, String societe, String pharamcie) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.numtel = numtel;
        this.adresse = adresse;
        this.cin = cin;
        this.matricule = matricule;
        this.cnam = cnam;
        this.cnss = cnss;
        this.specialite = specialite;
        this.societe = societe;
        this.pharamcie = pharamcie;
    }



    public User(String email, String password, String nom, String prenom, Date dnaissance, String type, String numtel, String adresse) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.dnaissance = dnaissance;
        this.type = type;
        this.numtel = numtel;
        this.adresse = adresse;
    }

    public User() {
        
    }

    public User(int id, String email, String password, String nom, String prenom, Date dnaissance, String numtel, String adresse) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.dnaissance = dnaissance;
        this.numtel = numtel;
        this.adresse = adresse;
    }

    public User(int id, String email, String password, String nom, String prenom, Date dnaissance, String type, String numtel, String adresse, String image) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.dnaissance = dnaissance;
        this.type = type;
        this.numtel = numtel;
        this.adresse = adresse;
        this.image = image;
     
    }

   

    public User(String email, String password, String nom, String prenom, String type, String numtel, String adresse) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.numtel = numtel;
        this.adresse = adresse;
    }

  
        public void cleanUserSession() {
        email = "";// or null
    }

    public User(int id, String email, String roles, String password, String nom, String prenom, Date dnaissance, String type, String numtel, String adresse, String image) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.dnaissance = dnaissance;
        this.type = type;
        this.numtel = numtel;
        this.adresse = adresse;
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", roles=" + roles + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", dnaissance=" + dnaissance + ", type=" + type + ", numtel=" + numtel + ", adresse=" + adresse + ", image=" + image + ", cin=" + cin + ", matricule=" + matricule + ", cnam=" + cnam + ", cnss=" + cnss + ", specialite=" + specialite + ", disponabilite=" + disponabilite + ", societe=" + societe + ", pharamcie=" + pharamcie + '}';
    }
    
    

   
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDnaissance() {
        return dnaissance;
    }

    public void setDnaissance(Date dnaissance) {
        this.dnaissance = dnaissance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
//
//    public boolean isIs_blocked() {
//        return is_blocked;
//    }
//
//    public void setIs_blocked(boolean is_blocked) {
//        this.is_blocked = is_blocked;
//    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }



    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCnam() {
        return cnam;
    }

    public void setCnam(String cnam) {
        this.cnam = cnam;
    }

    public String getCnss() {
        return cnss;
    }

    public void setCnss(String cnss) {
        this.cnss = cnss;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Date getDisponabilite() {
        return disponabilite;
    }

    public void setDisponabilite(Date disponabilite) {
        this.disponabilite = disponabilite;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getPharamcie() {
        return pharamcie;
    }

    public void setPharamcie(String pharamcie) {
        this.pharamcie = pharamcie;
    }

    public boolean isIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked = is_blocked;
    }

 
    
    
    
}

