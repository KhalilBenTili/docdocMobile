/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.anas;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Categorie;
import com.mycompany.myapp.Services.ServiceCategorie;

/**
 *
 * @author anasm
 */
public class ListCategorieForm extends Form {
    private Resources theme;


    Form current;
    public ListCategorieForm(Form previous) {
        theme = UIManager.initFirstTheme("/theme");

        setTitle("Votre liste de categories");

        for (Categorie c : ServiceCategorie.getInstance().getAllCategories()){
            addItem(c);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new HomeForm().show());
    }
   
        public void addItem(Categorie c){
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label titre = new Label("Titre : "+c.getTitre());
        Label description = new Label("Description : "+c.getDescription());
        
        Label sep = new Label("------------------------------------------------------------------");
        
        Button btnSupprimer = new Button("Supprimer");
        
        btnSupprimer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(ServiceCategorie.getInstance().suppCategorie(c)){
                    Dialog.show("Success","Connection accepted", new Command("OK")); 
                }
                else 
                    Dialog.show("ERROR","Server error", new Command("OK"));                
                    
                
            }    
        });
        btnSupprimer.addActionListener(e-> new ListCategorieForm(current).show());
        
        
        C1.add(titre);
        C1.add(description);
        C1.add(btnSupprimer);
        C1.add(sep);
        
        C2.add(C1);
        add(C2);
        
    }
    
    
}