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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Entities.CategorieService;
import com.mycompany.myapp.Entities.Paiement;
import com.mycompany.myapp.Entities.Service;
import com.mycompany.myapp.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author khali
 */
public class ServiceService {
    public boolean resultOK;
     public ArrayList<Service> services;
      ConnectionRequest req= new ConnectionRequest();
    
      
      public ArrayList<Service> Services(){
        String url = Statics.BASE_URL+"/afficheServiceMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                   services =parseService(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
        return services;
    }
      
      
    public ArrayList<Service> parseService(String jsonText){
        try {
           this.services=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Service s = new Service();
                s.setId((int) Float.parseFloat(obj.get("id").toString()));
                s.setLibelle(obj.get("libelle").toString());
                s.setDescription(obj.get("description").toString());  
                s.setPrix((float)Float.parseFloat(obj.get("prix").toString()));
                s.setAvgrating((float )Float.parseFloat(obj.get("avgrating").toString()));
               //CategorieService c=new CategorieService(obj.get("categorie.libelle").toString(),obj.get("categorie.description").toString());
              //  s.setCategorie(c);
                //Ajouter la tâche extraite de la réponse Json à la liste
                services.add(s);
            }
            
            
        } catch (IOException ex) {}
        return services;
    }
    
}
