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
import com.mycompany.myapp.Entities.Produit;
import com.mycompany.myapp.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anasm
 */
public class ServiceProduit {
    
    public ArrayList<Produit> prods;
    
    public static ServiceProduit instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    //public ServiceUser su = new ServiceUser();
    public ServiceProduit() {
         req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }
    


    public boolean addProduit(Produit p, String email) {
        String url = Statics.BASE_URL + "/produit/ajouterPMobile/"+email+"?categorie_id="+p.getCategorie_id()+"&reference=" + p.getReference()+ "&nom="+ p.getNom()+ "&quantite=" + p.getQuantite()+ "&description="+p.getDescription()+ "&prix="+p.getPrix()+ "&userid="+p.getUserid()+ "&path_image="+p.getPath_image(); //création de l'URL
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
    public boolean editProduit(Produit p) {
        String url = Statics.BASE_URL + "/produit/modifierPMobile/"+p.getId()+"?&reference=" + p.getReference()+ "&nom="+ p.getNom()+ "&quantite=" + p.getQuantite()+ "&description="+p.getDescription()+ "&prix="+p.getPrix(); //création de l'URL
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
    public boolean deleteProduit(Produit p) {
        String url = Statics.BASE_URL + "/produit/supprimerPMobile/" + p.getId();
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

    public ArrayList<Produit> parseProduit(String jsonText){
                try {
            prods=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Produit p = new Produit();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
                //float categorie_id = Float.parseFloat(obj.get("categorie_id").toString());
                //p.setCategorie_id((int)categorie_id);
                float reference = Float.parseFloat(obj.get("reference").toString());
                p.setReference((int)reference);
                p.setNom(obj.get("nom").toString());
                float quantite = Float.parseFloat(obj.get("quantite").toString());
                p.setQuantite((int)quantite);
                p.setDescription(obj.get("description").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                p.setPrix(prix);
                float userid = Float.parseFloat(obj.get("userid").toString());
                p.setUserid((int)userid);
                //p.setPath_image(obj.get("path_image").toString());
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                prods.add(p);

            }
            
            
        } catch (IOException ex) {
            
        }
        return prods;
    }
              
                    

    
    public ArrayList<Produit> getAllProduit(){
        String url = Statics.BASE_URL+"/produit/afficherPMobile/p="+1;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                prods = parseProduit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return prods;
    }
    
    
          public ArrayList<Produit> RechercheProduitNom(String nom){
        String url = Statics.BASE_URL+"/produit/rechercheMobile/"+nom;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                prods = parseProduit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prods;
    }
      
    
    

//    public ArrayList<Produit> getProduit(int id){
//        String url = Statics.BASE_URL+"/produit/findProduit/?id="+id;
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                prods = parseProduit(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
//        return prods;
//    }    
//    public ArrayList<Produit> getMyProduit(int id){
//        String url = Statics.BASE_URL+"/produit/findmyProduit/?id="+id;
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                prods = parseProduit(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
//        return prods;
//    }
    
        
}
