/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.alaa;

import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.Paiement;
import com.mycompany.myapp.Home;
import com.mycompany.myapp.Services.PaiementService;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
/**
 *
 * @author alaae
 */
public class AddPaiement extends Form {
    Form current;
    public AddPaiement(Form previous, float prix) {
        setTitle("Ajouter un nouveau paiement.");
        setLayout(BoxLayout.y());
        
        TextField tfnom = new TextField("","Nom");
        TextField tfprenom = new TextField("","Prenom");
        TextField tfadresse = new TextField("","Adresse");
        TextField tfnumero = new TextField("","Num Tel");
        TextField tfemail = new TextField("","Email");
        Button btnValider = new Button("Ajouter");
        btnValider.animate();
        
        btnValider.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                if((tfnom.getText().length() == 0) || (tfprenom.getText().length() == 0) || (tfadresse.getText().length() == 0) || (tfnumero.getText().length() == 0) || (tfemail.getText().length() == 0) ){
                    Dialog.show("Alert","Veuillez remplir tout les champs!", new Command("OK"));
                }
                else{
                    try{
                        Paiement p = new Paiement(tfnom.getText(), tfprenom.getText(), tfadresse.getText(), tfemail.getText(),"Enligne", "Paid", Integer.parseInt(tfnumero.getText()),Double.parseDouble(String.valueOf(prix))  , 1);
                        if(PaiementService.getInstance().ajoutPaiement(p)){
                            Message m = new Message("Paiement validé.\n"+
"Salutation Madame/Monsieur,\n" +
"C'est avec plaisir qu'on vous écrit pour vous remercier sincérement d'avoir choisi de faire confiance à DOCDOC");
                            
                            //m.getAttachments().put("", "text/plain");
                            //m.getAttachments().put("", "image/png");
                            
                            Display.getInstance().sendMessage(new String[] {p.getEmail()}, "Subject of message", m);
                                                     
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
        btnValider.addActionListener(e-> new ListPaiement(current).show());


        
        addAll(tfnom, tfprenom, tfadresse, tfnumero, tfemail, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new Home().show());
    }
    
    
}
