/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.anas;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Produit;
import com.mycompany.myapp.Gui.alaa.AddPaiement;
import com.mycompany.myapp.Services.ServiceProduit;
import com.mycompany.myapp.Gui.anas.RechercheProduit;

/**
 *
 * @author anasm
 */
public class ListProduitForm extends Form{
    private Resources theme;
    Form current;

        public ListProduitForm(Form previous) {
        
        theme = UIManager.initFirstTheme("/theme");

        setTitle("Votre liste des produits");
        
            TextField rech = new TextField();
            Button recherche = new Button("Recherche");
            recherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceProduit.getInstance().RechercheProduitNom(rech.getText());
                new RechercheProduit(rech.getText()).show();

            }
        });
            
        add(rech);
        add(recherche);

        for (Produit p : ServiceProduit.getInstance().getAllProduit()){
            addItem(p);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new HomeForm().show());
    }
   
        public void addItem(Produit p){
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label titre = new Label("Titre : "+p.getNom());
        Label description = new Label("Description : "+p.getDescription());
        Label prix = new Label("Prix :"+p.getPrix());
        
        Label sep = new Label("------------------------------------------------------------------");
        
        Button btnSupprimer = new Button("Supprimer");
        
        btnSupprimer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(ServiceProduit.getInstance().deleteProduit(p)){
                    Dialog.show("Success","Connection accepted", new Command("OK")); 
                }
                else 
                    Dialog.show("ERROR","Server error", new Command("OK"));                
                    
                
            }    
        });
        btnSupprimer.addActionListener(e-> new ListProduitForm(current).show());
        
        
        Button AjouterPaiement = new Button("Acheter");
        
        AjouterPaiement.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {                              
                 AjouterPaiement.addActionListener(e-> new AddPaiement(current,p.getPrix()).show());
            }    
        });
        
        
        
        Button btnModifier = new Button("Modifier");
        
        btnModifier.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(ServiceProduit.getInstance().editProduit(p)){
                    Dialog.show("Success","Connection accepted", new Command("OK")); 
                }
                else 
                    Dialog.show("ERROR","Server error", new Command("OK"));                
                    
                
            }    
        });
        btnModifier.addActionListener(e-> new EditProduitForm(current,p).show());
        
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        C1.add(titre);
        C1.add(description);
        C1.add(prix);
        C1.add(btnModifier);
        C1.add(btnSupprimer);
        C1.add(AjouterPaiement);
        C1.add(sep);
        C2.add(C1);
        add(C2);

          
    }
}
