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
import com.mycompany.myapp.Entities.Consultation;
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
public class ConsultationService {
    public ArrayList<Consultation> consultations;
    
    public boolean resultOK;
    
    public static ConsultationService instance=null;
    
    private ConnectionRequest con;
    
    public ConsultationService() {
         con = new ConnectionRequest();
    }
    
    public static ConsultationService getInstance() {
        if (instance == null) {
            instance = new ConsultationService();
        }
        return instance;
    }
    public boolean add(Consultation ev) {
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion 
        con.setUrl(Statics.BASE_URL+"/add-consultation-json?date="+ev.getDate()+"&hr="+ev.getHr()+"&medid="+ev.getMedecinUser().getId()+"&pid="+ev.getPatientUser().getId());

       // Insertion de l'URL de notre demande de connexion
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            resultOK = con.getResponseCode() == 200;
            System.out.println(str);//Affichage de la réponse serveur sur la console
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    return resultOK;
    }
        public boolean edit(Consultation t) {
        String url = Statics.BASE_URL +"/edit-consultation-json?id="+t.getId()+"&date="+t.getDate()+"&hr="+t.getHr(); //création de l'URL
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
        
                public boolean Accepter(Consultation t) {
        String url = Statics.BASE_URL +"/accepter-consultation-json?id="+t.getId(); //création de l'URL
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
        
        
         public ArrayList<Consultation> parseQuestions(String jsonText){
        try {
            consultations=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            

            for(Map<String,Object> obj : list){
                
                System.out.println(obj);
                //Création des tâches et récupération de leurs données
                
                
                //user medecin affectation
                String UserM =obj.get("userM").toString();
                System.out.println(UserM);
                int finUId =UserM.indexOf(".");
                String newUserMId = UserM.substring(4, finUId); 
                System.out.println(newUserMId);
                int DebutNom=UserM.indexOf("nom=")+4;
                int FinNom=UserM.indexOf(", prenom");
                String nom = UserM.substring(DebutNom, FinNom); 
                System.out.println("nom:"+nom);
                int DebutPrenom=UserM.indexOf("prenom=")+7;
                int FinPrenom=UserM.indexOf(", type");
                String prenom = UserM.substring(DebutPrenom, FinPrenom); 
                System.out.println("prenom:"+prenom);
                
                int DebutType=UserM.indexOf("type=")+5;
                int FinType=UserM.indexOf(", numtel");
                String type = UserM.substring(DebutType, FinType); 
                System.out.println("type:"+type);
                
                int DebutNum=UserM.indexOf("numtel=")+7;
                int FinNum=UserM.indexOf(", cin");
                String numTel = UserM.substring(DebutNum, DebutNum+8); 
                System.out.println(numTel);
                User medecin= new User(finUId, nom, prenom, null, type, numTel, "0");
               
                
                //user patient affectation
                String UserL =obj.get("user").toString();
                System.out.println("haw el patient"+UserL);
                int finUIdp =UserL.indexOf(".");
                String newUserLIdp = UserL.substring(4, finUIdp); 
                System.out.println(newUserLIdp);
                int DebutNomp=UserL.indexOf("nom=")+4;
                int FinNomp=UserL.indexOf(", prenom");
                String nomp = UserL.substring(DebutNomp, FinNomp); 
                System.out.println("nom:"+nomp);
                int DebutPrenomp=UserL.indexOf("prenom=")+7;
                int FinPrenomp=UserL.indexOf(", type");
                String prenomp = UserL.substring(DebutPrenomp, FinPrenomp); 
                System.out.println("prenom:"+prenomp);
                
                int DebutTypep=UserL.indexOf("type=")+5;
                int FinTypep=UserL.indexOf(", numtel");
                String typep = UserL.substring(DebutTypep, FinTypep); 
                System.out.println("type:"+typep);
                
                int DebutNump=UserL.indexOf("numtel=")+7;
                int FinNump=UserL.indexOf(", cin");
                String numTelp = UserL.substring(DebutNump, DebutNump+8); 
                System.out.println(numTelp);
                
                int DebutEmail=UserL.indexOf("email=")+6;
                int FinEmail=UserL.indexOf(", nom");
                String email = UserL.substring(DebutEmail,FinEmail); 
                System.out.println(email);
                 
                User patient= new User(newUserLIdp, nomp, prenomp, null, "patient", numTelp, "0");                

                float idFloat = Float.parseFloat(obj.get("id").toString());
                int id=(int) idFloat;
                boolean isAccepted=Boolean.parseBoolean(obj.get("isAccepted").toString());
                String datehr=obj.get("datehr").toString();
                int finDate=datehr.indexOf("T");
                String date=datehr.substring(0,finDate);
                System.out.println(date);
                String hr=datehr.substring(finDate+1,datehr.indexOf("+"));
                System.out.println(hr);
                patient.setPrenom(prenomp);
                patient.setNom(nomp);
                patient.setType("patient");
                patient.setEmail(email); 
                Consultation consultation= new Consultation(id, isAccepted ,date,hr, medecin, patient);
                

                consultations.add(consultation);
            }
            
             //Parcourir la liste des tâches Json
           for(int i=0;i<consultations.size();i++){
               // System.out.println(consultations.get(i));
            }
        } catch (IOException ex) {
            
        }
        return consultations;
    }
        public ArrayList<Consultation> getAllPatient(User u){
        String url = Statics.BASE_URL+"/afficher-consultation-patient?idUser="+u.getId();
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                consultations = parseQuestions(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return consultations;
    }
        public ArrayList<Consultation> getAllMedecin(User u){
        String url = Statics.BASE_URL+"/afficher-consultation-medecin?idUser="+u.getId();
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                consultations = parseQuestions(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return consultations;
    }
                    public boolean delete(Consultation t) {
        String url = Statics.BASE_URL + "/supp-consultation-json?id=" + t.getId();
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
