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
public class Rate {
    private User user;
    private Service service;
    private int Nbstars;

    public Rate(User user, Service service, int Nbstars) {
        this.user = user;
        this.service = service;
        this.Nbstars = Nbstars;
    }

    public Rate() {
    }

    @Override
    public String toString() {
        return String.valueOf(Nbstars);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getNbstars() {
        return Nbstars;
    }

    public void setNbstars(int Nbstars) {
        this.Nbstars = Nbstars;
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
        final Rate other = (Rate) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.service, other.service)) {
            return false;
        }
        return true;
    }
    
    
}
