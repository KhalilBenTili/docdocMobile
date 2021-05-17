/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.anas;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.Categorie;
import com.mycompany.myapp.Services.ServiceCategorie;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author anasm
 */
public class AddCategorieForm extends Form {
    Form current;
    public AddCategorieForm(Form previous) {
        setTitle("Ajouter une nouvelle categorie");
        setLayout(BoxLayout.y());
        
        TextField tfTitre = new TextField("","titre");
        TextField tfDescription = new TextField("","description");
        
        Button btnValider = new Button("Ajouter");
        
        btnValider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                if((tfTitre.getText().length() == 0) || (tfDescription.getText().length() == 0)){
                    Dialog.show("Alert","Veuillez remplir tout les champs!", new Command("OK"));
                }
                else{
                    try{
                        Categorie c = new Categorie(tfTitre.getText(), tfDescription.getText());
                        if(ServiceCategorie.getInstance().ajoutCategorie(c)){
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
        btnValider.addActionListener(e-> new ListCategorieForm(current).show());
//        Form hi = new Form("Browser", new BorderLayout());
//        BrowserComponent browser = new BrowserComponent();
//        browser.setURL("https://www.codenameone.com/");
//        hi.add(BorderLayout.CENTER, browser);

        
        addAll(tfTitre, tfDescription, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
    
}