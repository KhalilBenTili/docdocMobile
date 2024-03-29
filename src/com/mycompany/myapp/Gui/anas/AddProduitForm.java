/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.anas;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import com.mycompany.myapp.Entities.Categorie;
import com.mycompany.myapp.Entities.Produit;
import com.mycompany.myapp.Gui.wael.SessionManager;
import com.mycompany.myapp.Services.ServiceCategorie;
import com.mycompany.myapp.Services.ServiceProduit;
import java.util.ArrayList;

/**
 *
 * @author anasm
 */
public class AddProduitForm extends Form{
    Form current;
    public AddProduitForm(Form previous) {
        setTitle("Ajouter Produit");
        setLayout(BoxLayout.y());
        Label l = new Label("Categorie :");
        ComboBox<Categorie> comboCat = new ComboBox<>();
        ArrayList<Categorie> listC=ServiceCategorie.getInstance().getAllCategories();
        for(int i=0;i<listC.size();i++)
        {
            comboCat.addItem(listC.get(i));            
        }
        TextComponent tfReference= new TextComponent().label("Reference :");
        TextComponent tfNom= new TextComponent().label("Nom :");
        TextComponent tfQuantite= new TextComponent().label("Quantite :");
        TextComponent tfDescription= new TextComponent().label("Description :");
        TextComponent tfPrix= new TextComponent().label("Prix :");
        TextComponent tfemail= new TextComponent().label("Email :");
       
        Button btnValider = new Button("Ajouter Produit");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfReference.getText().length()==0)||(tfQuantite.getText().length()==0)||(tfDescription.getText().length()==0)||(tfPrix.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try{
                        Produit p = new Produit(comboCat.getSelectedItem().getId(),Integer.parseInt(tfReference.getText()),tfNom.getText(),Integer.parseInt(tfQuantite.getText()),tfDescription.getText(),Float.parseFloat(tfPrix.getText()), SessionManager.getId());
                        if(ServiceProduit.getInstance().addProduit(p,tfemail.getText())){
                            ToastBar.getInstance().setPosition(BOTTOM);
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setShowProgressIndicator(true);
                            status.setMessage("Produit ajouté avec succès");
                            status.setExpires(10000);   
                            status.show(); 
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
        btnValider.addActionListener(e-> new ListProduitForm(current).show());
        addAll(l,comboCat,tfReference,tfNom,tfQuantite,tfDescription,tfPrix,tfemail,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
        


    
    
}
