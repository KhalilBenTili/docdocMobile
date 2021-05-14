/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Entities.Consultation;
import com.mycompany.myapp.Utils.Statics;
import java.util.ArrayList;

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
    public void add(Consultation ev) {
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion 
        con.setUrl(Statics.BASE_URL+"/add-consultation-json?date="+ev.getDate()+"&hr="+ev.getHr()+"&medid="+ev.getMedecinUser().getId()+"&pid="+ev.getPatientUser().getId());

       // Insertion de l'URL de notre demande de connexion
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
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
}
