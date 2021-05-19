/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.wael;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.Reclamation;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Services.ReclamationService;
import com.mycompany.myapp.Services.UserService;

/**
 *
 * @author zagho
 */
public class AddReclamation extends Form {
    Form current;
    public AddReclamation(Form previous) {
         setTitle("Ajouter une réclamation");
        setLayout(BoxLayout.y());
        Label l = new Label("Aide :");
         
        TextComponent tfmotif=new TextComponent().label("Motif :");
        TextComponent tfdecription=new TextComponent().label("Decription :");
        
        
        Button btnValider = new Button("Ajouter");
        btnValider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                if((tfmotif.getText().length() == 0) || (tfdecription.getText().length() == 0)){
                    Dialog.show("Alert","Veuillez remplir tout les champs!", new Command("OK"));
                }
                else{
                    try{
                        Reclamation p = new Reclamation( tfmotif.getText(), tfdecription.getText());
                        if(ReclamationService.getInstance().ajoutReclamation(p)){
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
        btnValider.addActionListener(e-> new ListMedecin(current).show());        
        addAll(tfmotif,tfdecription,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        
        
    }
          
    
}
