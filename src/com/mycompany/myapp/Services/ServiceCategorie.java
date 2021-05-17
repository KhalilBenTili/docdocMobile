package com.mycompany.myapp.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Categorie;
import com.mycompany.myapp.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ServiceCategorie {
    
    public ArrayList<Categorie> categories;
    
    public boolean resultOK;
    
    public static ServiceCategorie instance=null;
    
    private ConnectionRequest req;
    
    
    private ServiceCategorie() {
         req = new ConnectionRequest();
    }
    
    public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }
    
    public boolean ajoutCategorie(Categorie c){
        String url = Statics.BASE_URL+"/categorie/ajouterCMobile?titre="+c.getTitre()+"&description="+c.getDescription()+"";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode()==200;
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
    }
    
    public boolean modifCategorie(Categorie c) {
        String url = Statics.BASE_URL + "/categorie/modifierCMobile?titre="+c.getTitre()+"&description="+c.getDescription()+""; //création de l'URL
               req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean suppCategorie(Categorie c) {
        String url = Statics.BASE_URL + "/categorie/supprimerCMobile/" + c.getId();
               req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
     public ArrayList<Categorie> parseCategories(String jsonText){
        try {
            categories=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Categorie c = new Categorie();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int)id);
                c.setTitre(obj.get("titre").toString());
                c.setDescription(obj.get("description").toString());
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                categories.add(c);
            }
            
            
        } catch (IOException ex) {
            
        }
        return categories;
    }
    
    public ArrayList<Categorie> getAllCategories(){
        String url = Statics.BASE_URL+"/categorie/afficherCMobile/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categories = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }
 
    
    
}