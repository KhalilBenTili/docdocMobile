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
import com.mycompany.myapp.Entities.CategorieMedicale;
import com.mycompany.myapp.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class categorieMedicaleService {
    public ArrayList<CategorieMedicale> CategorieMedicales;
    public boolean resultOK;
    public static categorieMedicaleService instance=null;
    private ConnectionRequest con;
    public categorieMedicaleService() {
         con = new ConnectionRequest();
    }
    public static categorieMedicaleService getInstance() {
        if (instance == null) {
            instance = new categorieMedicaleService();
        }
        return instance;
    }
    public void add(CategorieMedicale ev) {
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion 
        con.setUrl(Statics.BASE_URL+"/add-catMed-json?nom="+ev.getNom());

       // Insertion de l'URL de notre demande de connexion
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    public boolean edit(CategorieMedicale t) {
        String url = Statics.BASE_URL +"/edit-catMed-json?id="+t.getId()+"&nom="+t.getNom();
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return resultOK;
    }  
    
    public ArrayList<CategorieMedicale> parseQuestions(String jsonText){
        try {
            CategorieMedicales=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                
                
                //Création des tâches et récupération de leurs données
                CategorieMedicale p = new CategorieMedicale();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
                p.setNom(obj.get("nom").toString());

                //Ajouter la tâche extraite de la réponse Json à la liste
                CategorieMedicales.add(p);
            }
            
                        //Parcourir la liste des tâches Json
            for(int i=0;i<CategorieMedicales.size();i++){
                System.out.println(CategorieMedicales.get(i));
            } 
        } catch (IOException ex) {
            
        }
        return CategorieMedicales;
    }
    
    
    
        public ArrayList<CategorieMedicale> getAll(){
        String url = Statics.BASE_URL+"/display-categorie-med";
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CategorieMedicales = parseQuestions(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return CategorieMedicales;
    }
    
    
}
