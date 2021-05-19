/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.wael;

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
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Gui.Rihab.ListArticle;
import com.mycompany.myapp.Gui.alaa.ListPaiement;
import com.mycompany.myapp.Gui.khalil.Services;
import com.mycompany.myapp.Gui.zohra.AfficherConsultationPatient;
import com.mycompany.myapp.Gui.zohra.AfficherToutesLesQuestions;
import com.mycompany.myapp.Gui.zohra.ListeCategorieMedicale;
import com.mycompany.myapp.Gui.zohra.ListeConsultation;
import com.mycompany.myapp.Home;
import com.mycompany.myapp.Services.UserService;
import com.mycompany.myapp.gui.anas.HomeForm;

/**
 *
 * @author zagho
 */
public class ListMedecin extends Form {
    private Resources theme;
    Form current;
    public ListMedecin() {
        theme = UIManager.initFirstTheme("/theme");        
        setTitle("Votre liste de medecins.");
        TextField rech = new TextField("","Nom");
            Button recherche = new Button("Recherche");
            recherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                UserService.getInstance().RechercheNom(rech.getText());
                new RechercheNom(rech.getText()).show();
               
            }
        });
        add(rech);
        add(recherche); 
        
        
          for (User p : UserService.getInstance().getAllUsers()){
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
    
      
        

    private void addItem(User p) {
            Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
             Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
             Label nom = new Label("Nom : "+p.getNom());
             Label prenom = new Label("Prenom : "+p.getPrenom());
             Label email = new Label("Email : "+p.getEmail());
             Label adresse = new Label("Adresse : "+p.getAdresse());
             Label numtel = new Label("Numero tel : "+p.getNumtel());
              Label sep = new Label("------------------------------------------------------------------");
        
        C1.add(nom);
        C1.add(prenom);
        C1.add(email);   
        C1.add(adresse);
        C1.add(numtel);
        C1.add(sep);
        C2.add(C1);        
        add(C2);
             
       
    }
    
    }
 
