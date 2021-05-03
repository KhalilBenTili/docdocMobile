/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Entities;


import java.util.Date;
import java.util.Objects;

/**
 *
 * @author khali
 */
public class Consultation {
        private int id;
    private boolean isAccepted;
    private Date datehr;
    private User medecinUser;
    private User PatientUser;

    public Consultation() {
    }

    public Consultation(int id, boolean isAccepted, Date datehr, User medecinUser, User PatientUser) {
        this.id = id;
        this.isAccepted = isAccepted;
        this.datehr = datehr;
        this.medecinUser = medecinUser;
        this.PatientUser = PatientUser;
    }
    public Consultation( boolean isAccepted, Date datehr, User medecinUser, User PatientUser) {

        this.isAccepted = isAccepted;
        this.datehr = datehr;
        this.medecinUser = medecinUser;
        this.PatientUser = PatientUser;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public Date getDatehr() {
        return datehr;
    }

    public void setDatehr(Date datehr) {
        this.datehr = datehr;
    }

    public User getMedecinUser() {
        return medecinUser;
    }

    public void setMedecinUser(User medecinUser) {
        this.medecinUser = medecinUser;
    }

    public User getPatientUser() {
        return PatientUser;
    }

    public void setPatientUser(User PatientUser) {
        this.PatientUser = PatientUser;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", isAccepted=" + isAccepted + ", datehr=" + datehr + ", medecinUser=" + medecinUser + ", PatientUser=" + PatientUser + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.isAccepted ? 1 : 0);
        hash = 83 * hash + Objects.hashCode(this.datehr);
        hash = 83 * hash + Objects.hashCode(this.medecinUser);
        hash = 83 * hash + Objects.hashCode(this.PatientUser);
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
        final Consultation other = (Consultation) obj;
        if (this.isAccepted != other.isAccepted) {
            return false;
        }
        if (!Objects.equals(this.datehr, other.datehr)) {
            return false;
        }
        if (!Objects.equals(this.medecinUser, other.medecinUser)) {
            return false;
        }
        if (!Objects.equals(this.PatientUser, other.PatientUser)) {
            return false;
        }
        return true;
    }
    
    
}
