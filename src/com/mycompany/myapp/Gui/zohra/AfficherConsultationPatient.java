/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.zohra;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Consultation;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Gui.Rihab.ListArticle;
import com.mycompany.myapp.Gui.alaa.ListPaiement;
import com.mycompany.myapp.Gui.khalil.Services;
import com.mycompany.myapp.Gui.wael.AddReclamation;
import com.mycompany.myapp.Gui.wael.ListMedecin;
import com.mycompany.myapp.Gui.wael.ListPharamcien;
import com.mycompany.myapp.Gui.wael.SessionManager;
import com.mycompany.myapp.Home;
import com.mycompany.myapp.Services.ConsultationService;
import com.mycompany.myapp.gui.anas.HomeForm;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class AfficherConsultationPatient extends Form{
    Form current;
        private Resources theme;
    public AfficherConsultationPatient(User user) {
        
        
        setTitle(" Consultation");
       

          Container co=new Container(BoxLayout.xCenter());;
                    ArrayList <Consultation> questions = new ArrayList();
                        ConsultationService sa =new ConsultationService();
                            questions=sa.getAllPatient(user);
                             for (Consultation fi : questions) {
                            Container ct = new Container(BoxLayout.y());
                            Label lTitre = new Label("Vous avez demandé une consultation avec Dr. "+fi.getMedecinUser().getNom()+" "+fi.getMedecinUser().getPrenom(),"RedLabel");
                            Label ldate = new Label("pour le "+fi.getDate()+"a "+fi.getHr(),"RedLabel");
                            ct.add(lTitre);   
                            ct.add(ldate);
                           
                            Button Supp = new Button("Supprimer");
                            Button modfier = new Button("Modfier");
                            
                             modfier.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {              
                if (Dialog.show("Confirmation", "Voulez vous Modifier cette demande de consultation ?", "Modifier ", "Annuler")) {
                             new ModifierConsultation(fi,user);
            }    
            }
        });
                            Supp.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {              
                if (Dialog.show("Confirmation", "Voulez vous Supprimer cette demande de consultation ?", "Supprimer", "Annuler")) {
                Consultation t = new Consultation();
                t.setId(fi.getId());
                        if( ConsultationService.getInstance().delete(t)){
                            {
                                Dialog.show("Success","supprimer",new Command("OK"));
               
                                new AfficherConsultationPatient(user).show();
                            }
                   
                }
            }    
            }
        });
                       ct.add(modfier);
                       ct.add(Supp);
                       Label separator = new Label("","Separator");
                       ct.add(separator);
                       add(ct);
                             }
          //toolbar  
        
      Toolbar tb=getToolbar();
       theme = UIManager.initFirstTheme("/theme");
      Image icon=theme.getImage("logo.png");
      Container topBar=BorderLayout.east(new Label(icon));
      topBar.add(BorderLayout.SOUTH,new Label("DOCDOC","SidemenuTagline"));
      topBar.setUIID("SideCommand");      
      
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
    
}
