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
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Paiement;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Gui.Rihab.ListArticle;
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
import com.mycompany.myapp.Services.PaiementService;
import com.mycompany.myapp.gui.anas.HomeForm;
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
