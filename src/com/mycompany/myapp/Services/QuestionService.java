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
import com.mycompany.myapp.Entities.Question;
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
public class QuestionService {
        public ArrayList<Question> questions;
    
    public boolean resultOK;
    
    public static QuestionService instance=null;
    
    private ConnectionRequest con;
    
    public QuestionService() {
         con = new ConnectionRequest();
    }
    
    public static QuestionService getInstance() {
        if (instance == null) {
            instance = new QuestionService();
        }
        return instance;
    }
    
    public void add(Question ev) {
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion 
        con.setUrl(Statics.BASE_URL+"/add-question-json?titre="+ev.getTitre()+"&symptomes="+ev.getSymptomes()+"&userid="+ev.getUser()+"&catMedId="+ev.getCatMed()+"&ant="+ev.isIsAntMed()+"&name="+ev.isIsNameShown()+"&isTreated="+ev.isIsTreated()+"&poids="+ev.getPoids()+"&taille="+ev.getTaille());
        //con.setUrl(Statics.BASE_URL+"/add-question-json?titre="+"mobile"+"&symptomes="+"mobile"+"&userid=1&catMedId=1&ant=0&name=0&isTreated=0&poids=20&taille=90");

       // Insertion de l'URL de notre demande de connexion
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
    public boolean edit(Question t) {
        String url = Statics.BASE_URL + "/edit-question-json?id="+t.getId()+"&titre="+t.getTitre()+"&symptomes="+t.getSymptomes()+"&name="+t.isIsNameShown()+"&isTreated="+t.isIsTreated()+"&ant="+t.isIsAnswered()+"&poids="+t.getPoids()+"&taille="+t.getTaille(); //création de l'URL
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
    
    
    
    
    public boolean delete(Question t) {
        String url = Statics.BASE_URL + "/delete-question-json?id=" + t.getId();
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

    
    
    
    public ArrayList<Question> parseQuestions(String jsonText){
        try {
            questions=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            

            for(Map<String,Object> obj : list){
                
                
                //Création des tâches et récupération de leurs données
                Question p = new Question();
                
                
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
                
                int DebutEmail=UserL.indexOf("email=")+6;
                int FinEmail=UserL.indexOf(", nom");
                String email = UserL.substring(DebutEmail, FinEmail);
                System.out.println("email:"+email);
                
                int DebutType=UserL.indexOf("type=")+5;
                int FinType=UserL.indexOf(", numtel");
                String type = UserL.substring(DebutType, FinType); 
                System.out.println("type:"+type);
                
                int DebutNum=UserL.indexOf("numtel=")+7;
                int FinNum=UserL.indexOf(", cin");
                String numTel = UserL.substring(DebutNum, DebutNum+8); 
                System.out.println(numTel);
                
                int DebutCin=UserL.indexOf("cin=")+4;
                int FinCin=UserL.indexOf("}");
                String cin = UserL.substring(DebutCin, FinCin); 
                System.out.println(cin);                
                User u= new User(finUId, nom, prenom, email, type, numTel, cin);
                p.setUser(u);
                
                
                //categorieMedicale affectation
                String catL =obj.get("categorieMedicale").toString();
                int finId =catL.indexOf(".");
                String newCatId = catL.substring(4, finId);
                float catid = Float.parseFloat(newCatId);  
                int debutNom =catL.indexOf("nom=");
                int finNom=catL.indexOf("}");
                String newCatNom = catL.substring(debutNom+4, finNom);
                System.out.println(newCatId);
                System.out.println(newCatNom);
                CategorieMedicale catMed= new CategorieMedicale((int)catid, newCatNom);
                p.setCatMed(catMed);
                //fin categorie medicale

                float id = Float.parseFloat(obj.get("id").toString());
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
                questions.add(p);
            }
            
                        //Parcourir la liste des tâches Json
            for(int i=0;i<questions.size();i++){
                System.out.println(questions.get(i));
            } 
        } catch (IOException ex) {
            
        }
        return questions;
    }
    
    
    
    
    
    public ArrayList<Question> getAll(){
        String url = Statics.BASE_URL+"/display-question";
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                questions = parseQuestions(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return questions;
    }
    
}
