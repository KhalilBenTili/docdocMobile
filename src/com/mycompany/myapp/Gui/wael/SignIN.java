///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.Gui.wael;
//
//import com.codename1.ui.Button;
//import com.codename1.ui.Command;
//import com.codename1.ui.Dialog;
//import com.codename1.ui.FontImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.TextField;
//import com.codename1.ui.events.ActionEvent;
//import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.util.Resources;
//import com.mycompany.myapp.Entities.User;
//import com.mycompany.myapp.Services.UserService;
//
///**
// *
// * @author zagho
// */
//public class SignIN extends Form {
//     Form current;
//    public SignIN(Resources res) {
//         setTitle("Connexion");
//        setLayout(BoxLayout.y());
//        
//         TextField tfemail = new TextField("","Email");
//        TextField tfpassword = new TextField("","Mot de passe");
//        
//        Button btnValider = new Button("Connexion");
//        Button btnValider1 = new Button("Inscription");
//        btnValider.addActionListener(new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                if((tfemail.getText().length() == 0) || (tfpassword.getText().length() == 0)){
//                    Dialog.show("Alert","Veuillez remplir tout les champs!", new Command("OK"));
//                }
//                else{
//                    try{
//                        User p = new User( tfemail.getText(), tfpassword.getText());
//                        if(UserService.getInstance().signin(tfemail, tfpassword, rs)){
//                            Dialog.show("Success","Connection accepted", new Command("OK")); 
//                               
//                            
//                        }
//                        else 
//                            Dialog.show("ERROR","Server error", new Command("OK"));                
//                    }
//                    catch(NumberFormatException e){
//                        Dialog.show("ERROR","Veuillez vérifier les champs!", new Command("OK"));                      
//                    }
//                }
//            }    
//        });
//        btnValider.addActionListener(e-> new ListMedecin(current).show());
//        btnValider1.addActionListener(e-> new AddUser(current).show());
//
//
//        
//        addAll(tfemail,tfpassword, btnValider,btnValider1);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
//        
//        
//    }
//    
//}
//
