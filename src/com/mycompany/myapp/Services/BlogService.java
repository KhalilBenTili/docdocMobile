/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Entities.CategorieArticle;
import com.mycompany.myapp.Entities.article;
import com.mycompany.myapp.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dell
 */
public class BlogService {
    
    public CategorieArticle categorie;
    
    public ArrayList<article> articles;
    
    public boolean resultOK;
    
    public static BlogService instance=null;
    
    private ConnectionRequest req;
    
    private BlogService() {
         req = new ConnectionRequest();
    }
    
    public static BlogService getInstance() {
        if (instance == null) {
            instance = new BlogService();
        }
        return instance;
    }
    
    
     public ArrayList<article> parseArticle(String jsonText){
        try {
            articles=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                date = obj.get("created_at").toString();
                article p = new article();
                
                p.setId((int)Float.parseFloat(obj.get("id").toString()));
                p.setContenu(obj.get("contenu").toString());
                p.setCreated_at(date);
                p.setImg(obj.get("imageName").toString());
                p.setCat("santé");
                //p.setNbdislike(((int)Float.parseFloat(obj.get("nbdislike").toString())));
                //p.setNbdislike(((int)Float.parseFloat(obj.get("nblike").toString())));
                p.setTitre(obj.get("titre").toString());
                
;      
                //Ajouter la tâche extraite de la réponse Json à la liste
                articles.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
        return articles;
    }
    
    public ArrayList<article> getAllArticles(){
        String url = Statics.BASE_URL+"/blog/afficheMobile/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticle(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
    
    
}
