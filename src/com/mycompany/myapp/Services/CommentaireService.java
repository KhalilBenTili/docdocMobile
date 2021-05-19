package com.mycompany.myapp.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Entities.Commentaire;
import com.mycompany.myapp.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dell
 */
public class CommentaireService {

    public ArrayList<Commentaire> commentaires;

    public boolean resultOK;

    public static CommentaireService instance = null;

    private ConnectionRequest req;

    private CommentaireService() {
        req = new ConnectionRequest();
    }

    public static CommentaireService getInstance() {
        if (instance == null) {
            instance = new CommentaireService();
        }
        return instance;
    }

    public boolean ajoutCommentaire(Commentaire commentaire ) {
        String url = Statics.BASE_URL + "/commentaire/ajouterMobile";
        req.addArgument("contenu", String.valueOf(commentaire.getContenu()));
        req.addArgument("articleId", String.valueOf(commentaire.getArticleId()));
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Commentaire> parseCommentaires(String jsonText) {
        try {
            commentaires = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            if (!list.isEmpty()) {
                //Parcourir la liste des tâches Json
                for (Map<String, Object> obj : list) {
                    //Création des tâches et récupération de leurs données
                    Commentaire p = new Commentaire(
                            (String) obj.get("contenu"),
                            (int) Float.parseFloat(obj.get("articleId").toString())
                    );
                    //Ajouter la tâche extraite de la réponse Json à la liste
                    commentaires.add(p);
                    
                }
                System.out.println(commentaires);
            } else {
                return null;
            }

        } catch (IOException ex) {

        }
        return commentaires;
    }

    public ArrayList<Commentaire> getAllCommentaires() {
        String url = Statics.BASE_URL + "/commentaire/afficheMobile/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentaires = parseCommentaires(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (NullPointerException e) {
            System.out.println("Aucun commentaire");
        }
        return commentaires;
    }

}
