/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Entities;

/**
 *
 * @author dell
 */
public class Commentaire {

    private int id;
    private String contenu;
    private int articleId;

    public Commentaire(int id, String contenu, int articleId) {
        this.id = id;
        this.contenu = contenu;
        this.articleId = articleId;
    }

    public Commentaire(String contenu, int articleId) {
        this.contenu = contenu;
        this.articleId = articleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

}
