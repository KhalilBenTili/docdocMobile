/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Gui.Rihab.ListArticle;
import com.mycompany.myapp.Gui.alaa.AddPaiement;
import com.mycompany.myapp.Gui.alaa.ListPaiement;
import com.mycompany.myapp.Gui.khalil.Services;
import com.mycompany.myapp.Gui.wael.AddReclamation;
import com.mycompany.myapp.Gui.wael.ListMedecin;
import com.mycompany.myapp.Gui.wael.ListPharamcien;
import com.mycompany.myapp.Gui.wael.SessionManager;
import com.mycompany.myapp.Gui.zohra.AfficherConsultationPatient;
import com.mycompany.myapp.Gui.zohra.AfficherToutesLesQuestions;
import com.mycompany.myapp.Gui.zohra.ListeCategorieMedicale;
import com.mycompany.myapp.Gui.zohra.ListeConsultation;
import com.mycompany.myapp.gui.anas.HomeForm;
import java.io.IOException;

/**
 * GUI builder created Form
 *
 * @author khali
 */
public class Home extends Form {

    private Form current;
    private Resources theme;
    public Home() {
      
               setTitle("DOCDOC");
               Container c = new Container(BoxLayout.yCenter());
               Label l =new Label("DocDoc a votre service.");
               Label l1 =new Label("Des professionnels de santé.");
               Label l2 =new Label("Disponible 7/7:24/24h.");
               Label l3 =new Label("Votre guide médical.");
             try {
            Image img2 = Image.createImage("/slide1.jpg");
            Image img1 = Image.createImage("/slide2.jpg");
            Image img3 = Image.createImage("/slide3.jpg");
            img2.scaled(40, 40);
             c.add(img1);
            c.add(l);
            c.add(img2);
            c.add(l1);
            c.add(l2);
            c.add(img3);
            c.add(l3);
        } catch (IOException ex) {
        }
         add(c);   
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
   

//-- DON'T EDIT ABOVE THIS LINE!!!
}
