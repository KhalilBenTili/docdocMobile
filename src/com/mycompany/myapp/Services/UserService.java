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
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Paiement;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Gui.wael.ListMedecin;
import com.mycompany.myapp.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author zagho
 */
public class UserService {
    public ArrayList<User> users;
    
    public boolean resultOK;
    
    public static UserService instance=null;
    
    private ConnectionRequest req;
    
    private UserService() {
         req = new ConnectionRequest();
    }
    
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
        public boolean ajoutUser(User p){
        String url = Statics.BASE_URL+"/addUserJson/new?nom="+p.getNom()+"&prenom="+p.getPrenom()+"&email="+p.getEmail()+"&password="+p.getPassword()+"&adresse="+p.getAdresse()+"&numtel="+p.getNumtel()+"&type="+p.getType();
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
        
              public void SignUp(TextField nom,TextField prenom,TextField email,TextField password,TextField adresse,TextField numtel,ComboBox types,Resources res){
              
               
        String url = Statics.BASE_URL+"/addUserJson/new?nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&email="+email.getText().toString()+"&password="+password.getText().toString()+"&adresse="+adresse.getText().toString()+"&numtel="+numtel.getText().toString()+"&type="+types.getSelectedItem().toString();
        req.setUrl(url);
        req.addResponseListener((e)->{
            byte[]data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            System.out.println("data ===>"+responseData);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
            
              
        }
        public boolean LoginUser(User p){
        String url = Statics.BASE_URL+"/loginmobile/" + p.getEmail()+ "/" + p.getPassword();
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
        

        
    public ArrayList<User> parseUsers(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                User p = new User();
                int id = (int) Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
                p.setNom(obj.get("nom").toString());
                p.setPrenom(obj.get("prenom").toString());
                p.setEmail(obj.get("email").toString());
                p.setPassword(obj.get("password").toString());
                p.setAdresse(obj.get("adresse").toString());
                p.setNumtel(obj.get("numtel").toString());
                p.setType(obj.get("type").toString());
              
                users.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
        return users;
    }
    
      public ArrayList<User> RechercheNom(String nom){
        String url = Statics.BASE_URL+"/user/rechercheNomPaiementMobile/"+nom;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
    
    public ArrayList<User> getAllUsers(){
        String url = Statics.BASE_URL+"/afficheusermob/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
     public ArrayList<User> getAllUsersPharmacie(){
        String url = Statics.BASE_URL+"/affpharmobile/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
     
     public void signin(TextField email,TextField password, Resources rs){
         String url = Statics.BASE_URL+"/user/signin?email="+email.getText().toString()+"&password="+password.getText().toString();
          req.setUrl(url);
        req.addResponseListener((e) ->{
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData())+ "";
            try{
            if(json.equals("failed")){
                Dialog.show("Echec d'authentification","email ou mdp erreue","OK",null);
                
            }
            else{
                System.out.println("data = "+json);
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                if(user.size() >0)
                    System.out.println("Okkkkk");
                    
            }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
         
         
         NetworkManager.getInstance().addToQueueAndWait(req);
         
     }
     
     
}
