/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.wael;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Services.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextComponentPassword;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Home;
import com.mycompany.myapp.Services.PaiementService;
import com.mycompany.myapp.Services.UserService;
import java.io.IOException;

/**
 *
 * @author zagho
 */
public class Login extends Form {
     Form current;
    public Login() {
         setTitle("DocDoc");
        setLayout(BoxLayout.y());
        
        TextField tfemail= new TextField("","Email",20,TextField.ANY);
        TextField tfpassword= new TextField("","Password",20,TextField.PASSWORD);
        
         //TextComponent tfemail=new TextComponent().label("Adresse e-mail :");
       //  TextComponent tfpassword=new TextComponentPassword().label("Mot de passe :");
        
        Button btnValider = new Button("Connexion");
        Button btnValider1 = new Button("Inscription");
        btnValider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                if((tfemail.getText().length() == 0) || (tfpassword.getText().length() == 0)){
                    Dialog.show("Alert","Veuillez remplir tout les champs!", new Command("OK"));
                }
                else{
                    try{
                        
                        UserService.getInstance().signin(tfemail,tfpassword);
                        System.out.println("user : "+SessionManager.getId()+SessionManager.getNom()+SessionManager.getPrenom()+SessionManager.getType());
                            
                    }
                    catch(NumberFormatException e){
                        Dialog.show("ERROR","Veuillez vérifier les champs!", new Command("OK"));
                    }
                }
            }    
        });
        btnValider1.addActionListener(e-> new AddUser(current).show());
         Container ct = new Container(BoxLayout.xCenter());

         try {
            Image img = Image.createImage("/banner.png");
            ct.add(img);
            add(ct);
        } catch (IOException ex) {
        }
        addAll(tfemail,tfpassword, btnValider,btnValider1);
        
        
    }
    
}