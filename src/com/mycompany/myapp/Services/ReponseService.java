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
import com.mycompany.myapp.Entities.Reponse;
import com.mycompany.myapp.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ReponseService {
    public ArrayList<Reponse> reponses;
    
    public boolean resultOK;
    
    public static ReponseService instance=null;
    
    private ConnectionRequest con;
    
    public ReponseService() {
         con = new ConnectionRequest();
    }
    
    public static ReponseService getInstance() {
        if (instance == null) {
            instance = new ReponseService();
        }
        return instance;
    }
    
        public void add(Reponse ev) {
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion 
        con.setUrl(Statics.BASE_URL+"add-reponse-json?description="+ev.getDescription()+"&userid="+ev.getUser().getId()+"&questionid="+ev.getQuestion().getId());

       // Insertion de l'URL de notre demande de connexion
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
        
    public boolean edit(Reponse t) {
        String url = Statics.BASE_URL +"/edit-reponse-json?id="+t.getId()+"&description="+t.getDescription(); //création de l'URL
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
    public ArrayList<Reponse> parseQuestions(String jsonText){
        try {
            reponses=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            

            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
              /*  Reponse p = new Reponse();
                float id = Float.parseFloat(obj.get("id").toString());
                String catL =obj.get("categorieMedicale").toString();
                int fin =catL.indexOf(".");
                String newCat = catL.substring(4, fin);
                float catid = Float.parseFloat(newCat);
                System.out.println(newCat);
                p.setCatMed((int) catid);
                String UserL =obj.get("user").toString();
                int finU =UserL.indexOf(".");
                String newUserL = catL.substring(4, finU);
                float userId = Float.parseFloat(newUserL);
                System.out.println(newCat);
                p.setUser((int)userId);

                p.setId((int)id);
                p.setTitre(obj.get("titre").toString());
                p.setSymptomes(obj.get("symptomes").toString());
                p.setPoids(10);
                p.setTaille(11);
                p.setIsAnswered(false);
                p.setIsTreated(false);
                p.setIsNameShown(false);
                p.setIsAntMed(false);
                //Ajouter la tâche extraite de la réponse Json à la liste
                reponses.add(p);*/
            }
            
                        //Parcourir la liste des tâches Json
            for(int i=0;i<reponses.size();i++){
                  System.out.println(reponses.get(i));
        } 
        } catch (IOException ex) {
            
        }
        return reponses;
    }
}
