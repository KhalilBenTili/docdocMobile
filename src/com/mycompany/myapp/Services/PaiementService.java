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
import com.mycompany.myapp.Entities.Paiement;
import com.mycompany.myapp.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alaae
 */
public class PaiementService {
    
    public ArrayList<Paiement> paiements;
    
    public boolean resultOK;
    
    public static PaiementService instance=null;
    
    private ConnectionRequest req;
    
    private PaiementService() {
         req = new ConnectionRequest();
    }
    
    public static PaiementService getInstance() {
        if (instance == null) {
            instance = new PaiementService();
        }
        return instance;
    }
    
    public boolean ajoutPaiement(Paiement p){
        String url = Statics.BASE_URL+"/paiement/addJSON/"+p.getNom()+"/"+p.getPrenom()+"/"+p.getAdresse()+"/"+p.getEmail()+"/"+p.getPrix()+"/"+p.getNumero()+"/"+p.getType()+"/"+p.getStatus()+"/"+p.getUserid();
        req.setUrl(url);
        req.setPost(true);
        req.setContentType("application/json");
        req.setRequestBody("{\"nom\":\""+p.getNom()+"\",\"prenom\":\""+p.getPrenom()+"\",\"adresse\":\""+p.getAdresse()+"\",\"email\":\""+p.getEmail()+"\",\"numero\":\""+p.getNumero()+",\"type\":\""+p.getType()+"\",\"status\":\""+p.getStatus()+"\",\"prix\":\""+p.getPrix()+"\",\"userid\":1}");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode()==200;
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
     public ArrayList<Paiement> parsePaiements(String jsonText){
        try {
            paiements=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Paiement p = new Paiement();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
                p.setNom(obj.get("nom").toString());
                p.setPrenom(obj.get("prenom").toString());
                p.setAdresse(obj.get("adresse").toString());
                p.setEmail(obj.get("email").toString());
                p.setPrix(Double.parseDouble(obj.get("prix").toString()));
                p.setNumero(0);
                p.setStatus(obj.get("status").toString());
                p.setUserid(1);      
                //Ajouter la tâche extraite de la réponse Json à la liste
                paiements.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
        return paiements;
    }
    
    public ArrayList<Paiement> getAllPaiements(){
        String url = Statics.BASE_URL+"/paiement/afficheMobile/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                paiements = parsePaiements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return paiements;
    }
    
}
