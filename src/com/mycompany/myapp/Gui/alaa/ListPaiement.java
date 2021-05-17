/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.alaa;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Paiement;
import com.mycompany.myapp.Home;
import com.mycompany.myapp.Services.PaiementService;
import java.io.IOException;


/**
 *
 * @author alaae
 */
public class ListPaiement extends Form {
    private Resources theme;

        Form current;
    public ListPaiement(Form previous) {
        theme = UIManager.initFirstTheme("/theme");

        setTitle("Votre liste de paiements.");
       

             TextField rech = new TextField("","Nom");
            Button recherche = new Button("Recherche");
            recherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                PaiementService.getInstance().RechercheNom(rech.getText());
                new RechercheNom(rech.getText()).show();
               
            }
        });
        add(rech);
        add(recherche); 

        for (Paiement p : PaiementService.getInstance().getAllPaiements()){
            
            addItem(p);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new Home().show());
    }
   
        public void addItem(Paiement P){
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Label nom = new Label("Nom : "+P.getNom());
        Label prenom = new Label("Prenom : "+P.getPrenom());
        Label adresse = new Label("Adresse : "+P.getAdresse());
        Label numero = new Label("Numero : "+String.valueOf(P.getNumero()));
        Label email = new Label("Email : "+P.getEmail());
        Label prix = new Label("Prix : "+String.valueOf(P.getPrix()));
        Label type = new Label("Type : "+P.getType());
        Label status = new Label("Status : "+P.getStatus());
        Button supprimer = new Button("Supprimer");
        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                PaiementService.getInstance().SupprimerPaiement(P);
                new ListPaiement(current).show();
                
            }
        });
        Label sep = new Label("-------------------------------------------------------------------");
        
        ImageViewer m = new ImageViewer();
 
              
        C3.add(nom);
        C1.add(C3);
               try {
            Image img = Image.createImage("/icon.png");
            C3.add("               ");
            C3.add(img);
        } catch (IOException ex) {
        }
        C1.add(prenom);
        C1.add(adresse);
        C1.add(numero);
        C1.add(email);
        C1.add(prix);
        C1.add(type);
        C1.add(status);
        C1.add(supprimer);
        C1.add(sep);
        C2.add(C1);
        add(C2);
        
    }
}
