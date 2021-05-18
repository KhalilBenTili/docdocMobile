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
import com.mycompany.myapp.Entities.Question;
import com.mycompany.myapp.Entities.Reponse;
import com.mycompany.myapp.Entities.User;
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
                
                
                //user affectation
                String UserL =obj.get("user").toString();
                System.out.println(UserL);
                int finUId =UserL.indexOf(".");
                String newUserLId = UserL.substring(4, finUId); 
                System.out.println(newUserLId);
                int DebutNom=UserL.indexOf("nom=")+4;
                int FinNom=UserL.indexOf(", prenom");
                String nom = UserL.substring(DebutNom, FinNom); 
                System.out.println("nom:"+nom);
                int DebutPrenom=UserL.indexOf("prenom=")+7;
                int FinPrenom=UserL.indexOf(", type");
                String prenom = UserL.substring(DebutPrenom, FinPrenom); 
                System.out.println("prenom:"+prenom);
                
                int DebutType=UserL.indexOf("type=")+5;
                int FinType=UserL.indexOf(", numtel");
                String type = UserL.substring(DebutType, FinType); 
                System.out.println("type:"+type);
                
                int DebutNum=UserL.indexOf("numtel=")+7;
                int FinNum=UserL.indexOf(", cin");
                String numTel = UserL.substring(DebutNum, DebutNum+8); 
                System.out.println(numTel);
                
                /*int DebutCin=UserL.indexOf("cin=")+4;
                int FinCin=UserL.indexOf("}, isBad");
                String cin = UserL.substring(DebutCin, FinCin); 
                System.out.println(cin); */
                String cin="0";
                User u= new User(finUId, nom, prenom, null, type, numTel, cin);
                u.setId((int)Float.parseFloat(newUserLId));
                //p.setUser(u);
                

                float id = Float.parseFloat(obj.get("id").toString());
               /* p.setId((int)id);
                p.setDescription(obj.get("description").toString());
                p.setIsBad(Boolean.parseBoolean(obj.get("isBad").toString()));*/
                
                
                Question q= new Question();
                String QL =obj.get("question").toString();
                System.out.println(QL);
                int finQ =QL.indexOf(".");
                String newQId = QL.substring(4, finQ); 
                float idQ = Float.parseFloat(newQId);
                q.setId((int) idQ);
                System.out.println("mta3 l question"+idQ);
               // p.setQuestion(q);
                Reponse p = new Reponse((int)id, u, obj.get("description").toString(), Boolean.parseBoolean(obj.get("isBad").toString()), q); 
                //Ajouter la tâche extraite de la réponse Json à la liste
                reponses.add(p);
            }
            
             //Parcourir la liste des tâches Json
            for(int i=0;i<reponses.size();i++){
                System.out.println(reponses.get(i));
            } 
        } catch (IOException ex) {
            
        }
        return reponses;
    }
        public ArrayList<Reponse> getAll(Question t){
        String url = Statics.BASE_URL+"/afficher-reponses-par-question-json?id="+t.getId();
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reponses = parseQuestions(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return reponses;
    }
                    public boolean delete(Reponse t) {
        String url = Statics.BASE_URL + "/delete-reponse-json?id=" + t.getId();
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
        
        
}
