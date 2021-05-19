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
public class Reponse {
    private int id;
    private User user;
    private String description;
    private boolean isBad;
    private Question question;

    public Reponse() {
    }

    public Reponse(int id, User user, String description, boolean isBad, Question question) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.isBad = isBad;
        this.question = question;
    }

    public Reponse(User user, String description, boolean isBad) {

        this.user = user;
        this.description = description;
        this.isBad = isBad;

    }
    
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsBad() {
        return isBad;
    }

    public void setIsBad(boolean isBad) {
        this.isBad = isBad;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", user=" + user + ", description=" + description + ", isBad=" + isBad + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.user);
        hash = 13 * hash + Objects.hashCode(this.description);
        hash = 13 * hash + (this.isBad ? 1 : 0);
        hash = 13 * hash + Objects.hashCode(this.question);
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
        final Reponse other = (Reponse) obj;
        if (this.isBad != other.isBad) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        return true;
    }
    
    
}
