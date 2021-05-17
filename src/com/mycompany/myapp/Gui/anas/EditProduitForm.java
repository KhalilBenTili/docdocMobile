/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.anas;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.Categorie;
import com.mycompany.myapp.Entities.Produit;
import com.mycompany.myapp.Services.ServiceCategorie;
import com.mycompany.myapp.Services.ServiceProduit;
import java.util.ArrayList;

/**
 *
 * @author anasm
 */
public class EditProduitForm extends Form{
    
    public EditProduitForm(Form previous,Produit pro) {
        setTitle("Modifier Produit");
        setLayout(BoxLayout.y());
        
        
        TextComponent tfReference= new TextComponent().label("Reference :");
        tfReference.text(String.valueOf(pro.getReference()));
        TextComponent tfNom= new TextComponent().label("Nom :");
        tfNom.text(pro.getNom());

        TextComponent tfQuantite= new TextComponent().label("Quantite :");
        tfQuantite.text(String.valueOf(pro.getQuantite()));

        
        TextComponent tfDescription= new TextComponent().label("Description :");
        tfDescription.text(pro.getDescription());
        TextComponent tfPrix= new TextComponent().label("Prix :");
        tfPrix.text(String.valueOf(pro.getPrix()));


        Button btnValider = new Button("Modifier");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfReference.getText().length()==0)||(tfNom.getText().length()==0)||(tfQuantite.getText().length()==0)||(tfDescription.getText().length()==0)||(tfPrix.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        
                        Produit p= new Produit(pro.getId(),Integer.parseInt(tfReference.getText()), tfNom.getText(),Integer.parseInt(tfQuantite.getText()), tfDescription.getText(),Float.parseFloat(tfPrix.getText()));                        

                        
                        if( ServiceProduit.getInstance().editProduit(p))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfReference,tfNom,tfQuantite,tfDescription,tfPrix,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new HomeForm().show());
                
    }

    
}
