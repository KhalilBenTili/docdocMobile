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
public class Question {
    private int id;
    private int catMed;
    private int user;
    private String titre;
    private String Symptomes;
    private int taille;
    private int poids;
    private boolean isTreated;
    private boolean isAntMed;
    private boolean isNameShown;
    private boolean isAnswered;

    public Question() {
    }

    public Question(int id, int catMed, int user, String titre, String Symptomes, int taille, int poids, boolean isTreated, boolean isAntMed, boolean isNameShown, boolean isAnswered) {
        this.id = id;
        this.catMed = catMed;
        this.user = user;
        this.titre = titre;
        this.Symptomes = Symptomes;
        this.taille = taille;
        this.poids = poids;
        this.isTreated = isTreated;
        this.isAntMed = isAntMed;
        this.isNameShown = isNameShown;
        this.isAnswered = isAnswered;
    }
        public Question(int id) {
        this.id = id;
    }
    public Question(int catMed, int user, String titre, String Symptomes, int taille, int poids, boolean isTreated, boolean isAntMed, boolean isNameShown, boolean isAnswered) {
        this.catMed = catMed;
        this.user = user;
        this.titre = titre;
        this.Symptomes = Symptomes;
        this.taille = taille;
        this.poids = poids;
        this.isTreated = isTreated;
        this.isAntMed = isAntMed;
        this.isNameShown = isNameShown;
        this.isAnswered = isAnswered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatMed() {
        return catMed;
    }

    public void setCatMed(int catMed) {
        this.catMed = catMed;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSymptomes() {
        return Symptomes;
    }

    public void setSymptomes(String Symptomes) {
        this.Symptomes = Symptomes;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public boolean isIsTreated() {
        return isTreated;
    }

    public void setIsTreated(boolean isTreated) {
        this.isTreated = isTreated;
    }

    public boolean isIsAntMed() {
        return isAntMed;
    }

    public void setIsAntMed(boolean isAntMed) {
        this.isAntMed = isAntMed;
    }

    public boolean isIsNameShown() {
        return isNameShown;
    }

    public void setIsNameShown(boolean isNameShown) {
        this.isNameShown = isNameShown;
    }

    public boolean isIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", catMed=" + catMed + ", user=" + user + ", titre=" + titre + ", Symptomes=" + Symptomes + ", taille=" + taille + ", poids=" + poids + ", isTreated=" + isTreated + ", isAntMed=" + isAntMed + ", isNameShown=" + isNameShown + ", isAnswered=" + isAnswered + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.catMed);
        hash = 79 * hash + Objects.hashCode(this.user);
        hash = 79 * hash + Objects.hashCode(this.titre);
        hash = 79 * hash + Objects.hashCode(this.Symptomes);
        hash = 79 * hash + this.taille;
        hash = 79 * hash + this.poids;
        hash = 79 * hash + (this.isTreated ? 1 : 0);
        hash = 79 * hash + (this.isAntMed ? 1 : 0);
        hash = 79 * hash + (this.isNameShown ? 1 : 0);
        hash = 79 * hash + (this.isAnswered ? 1 : 0);
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
        final Question other = (Question) obj;
        if (this.taille != other.taille) {
            return false;
        }
        if (this.poids != other.poids) {
            return false;
        }
        if (this.isTreated != other.isTreated) {
            return false;
        }
        if (this.isAntMed != other.isAntMed) {
            return false;
        }
        if (this.isNameShown != other.isNameShown) {
            return false;
        }
        if (this.isAnswered != other.isAnswered) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.Symptomes, other.Symptomes)) {
            return false;
        }
        if (!Objects.equals(this.catMed, other.catMed)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }
    
    
}
