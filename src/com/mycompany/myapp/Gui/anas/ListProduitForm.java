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
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Produit;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Gui.Rihab.ListArticle;
import com.mycompany.myapp.Gui.alaa.AddPaiement;
import com.mycompany.myapp.Gui.alaa.ListPaiement;
import com.mycompany.myapp.Services.ServiceProduit;
import com.mycompany.myapp.Gui.anas.RechercheProduit;
import com.mycompany.myapp.Gui.khalil.Services;
import com.mycompany.myapp.Gui.wael.AddReclamation;
import com.mycompany.myapp.Gui.wael.ListMedecin;
import com.mycompany.myapp.Gui.wael.ListPharamcien;
import com.mycompany.myapp.Gui.wael.SessionManager;
import com.mycompany.myapp.Gui.zohra.AfficherConsultationPatient;
import com.mycompany.myapp.Gui.zohra.AfficherToutesLesQuestions;
import com.mycompany.myapp.Gui.zohra.ListeCategorieMedicale;
import com.mycompany.myapp.Gui.zohra.ListeConsultation;
import com.mycompany.myapp.Home;

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
      //toolbar     
      Toolbar tb=getToolbar();
       theme = UIManager.initFirstTheme("/theme");
      Image icon=theme.getImage("logo.png");
      Container topBar=BorderLayout.east(new Label(icon));
      topBar.add(BorderLayout.SOUTH,new Label("DOCDOC","SidemenuTagline"));
      topBar.setUIID("SideCommand");      
      User user=new User(SessionManager.getId(),SessionManager.getNom(),SessionManager.getPrenom(),SessionManager.getEmail(),SessionManager.getType());
      tb.addComponentToSideMenu(topBar);
      tb.addMaterialCommandToSideMenu("Accueil",FontImage.MATERIAL_HOME,e->{new Home().show();});
      tb.addMaterialCommandToSideMenu("Services",FontImage.MATERIAL_SCHOOL,e->{new Services().show();});
       tb.addMaterialCommandToSideMenu("Medecins",FontImage.MATERIAL_ACCOUNT_BOX,e-> new ListMedecin().show());
       tb.addMaterialCommandToSideMenu("Pharmacie",FontImage.MATERIAL_ACCOUNT_BOX,e-> new ListPharamcien(current).show());
      
       tb.addMaterialCommandToSideMenu("Consultation",FontImage.MATERIAL_ACCOUNT_BOX,e->{  if(user.getType()=="medecin")
     {
          new ListeConsultation(user).show();
     }
       else
     {
         new AfficherConsultationPatient(user).show();
     }});
       tb.addMaterialCommandToSideMenu("Produits",FontImage.MATERIAL_SCHOOL,e-> new HomeForm().show());
        tb.addMaterialCommandToSideMenu("Blog",FontImage.MATERIAL_SCHOOL,e-> new ListArticle(current).show());
         tb.addMaterialCommandToSideMenu("Forum",FontImage.MATERIAL_SCHOOL,e->{new AfficherToutesLesQuestions(user).show();});
          tb.addMaterialCommandToSideMenu("Catégorie Médicale",FontImage.MATERIAL_ACCOUNT_BOX,e->{new ListeCategorieMedicale().show();});
       tb.addMaterialCommandToSideMenu("Paiements",FontImage.MATERIAL_SETTINGS,e->new ListPaiement(current).show());
       tb.addMaterialCommandToSideMenu("Repport",FontImage.MATERIAL_SETTINGS,e-> new AddReclamation(current).show());
      //endtoolbar
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
