/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.wael;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextComponentPassword;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.Paiement;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Gui.alaa.ListPaiement;
import com.mycompany.myapp.Home;
import com.mycompany.myapp.Services.PaiementService;
import com.mycompany.myapp.Services.UserService;
import java.util.Vector;

/**
 *
 * @author zagho
 */
public class AddUser extends Form{
    Form current;
    public AddUser(Form previous) {
         setTitle("Inscription");
        setLayout(BoxLayout.y());
        ComboBox<String> comboAide = new ComboBox<>();
        
        TextComponent tfnom=new TextComponent().label("Nom :");
        TextComponent tfprenom=new TextComponent().label("Prenom :");
        TextComponent tfemail=new TextComponent().label("Adresse e-mail :");
         TextComponent tfpassword=new TextComponentPassword().label("Mot de passe :");
         TextComponent tfadresse=new TextComponent().label("Votre adresse:");
         TextComponent tfnumero=new TextComponent().label("Numero de tel :");
         
         //TextComponent tftype=new TextComponent().label("Type :");
        Label l2 = new Label("Type :");
        ComboBox<String> combo = new ComboBox<>();
        combo.addItem("patient");
        combo.addItem("medecin");
        combo.addItem("pharmacien");
        combo.addItem("delegue");
        
        TextComponent tfbesnat= new TextComponent().label("");
        
          
        

        
        Button btnValider = new Button("Inscription");
        btnValider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                if((tfnom.getText().length() == 0)  || (tfemail.getText().length() == 0) || (tfpassword.getText().length() == 0) || (tfprenom.getText().length() == 0) || (tfadresse.getText().length() == 0) || (tfnumero.getText().length() == 0) || (tfemail.getText().length() == 0) ){
                    Dialog.show("Alert","Veuillez remplir tout les champs!", new Command("OK"));
                }
                else{
                    try{
                        User p = new User( tfemail.getText(), tfpassword.getText(),tfnom.getText(), tfprenom.getText(), combo.getSelectedItem(), tfnumero.getText(), tfadresse.getText());
                        if(UserService.getInstance().ajoutUser(p)){
                            Dialog.show("Success","Connection accepted", new Command("OK")); 
                               
                            
                        }
                        else 
                            Dialog.show("ERROR","Server error", new Command("OK"));                
                    }
                    catch(NumberFormatException e){
                        Dialog.show("ERROR","Veuillez vérifier les champs!", new Command("OK"));                      
                    }
                }
            }    
        });
        btnValider.addActionListener(e-> new Login().show());


        
        addAll(tfnom, tfprenom, tfemail,tfpassword,tfadresse, tfnumero, combo, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e-> new Login().show());
        
        
    }
    
}
