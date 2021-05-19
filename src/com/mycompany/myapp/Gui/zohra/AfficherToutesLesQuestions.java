/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.zohra;


import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
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
import com.mycompany.myapp.Entities.Question;
import com.mycompany.myapp.Entities.Reponse;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Gui.Rihab.ListArticle;
import com.mycompany.myapp.Gui.alaa.ListPaiement;
import com.mycompany.myapp.Gui.khalil.Services;
import com.mycompany.myapp.Gui.wael.AddReclamation;
import com.mycompany.myapp.Gui.wael.ListMedecin;
import com.mycompany.myapp.Gui.wael.ListPharamcien;
import com.mycompany.myapp.Gui.wael.SessionManager;
import com.mycompany.myapp.Home;
/*import com.mycompany.myapp.entities.gui.MenuCategoryChambre;
import com.mycompany.myapp.entities.services.ServiceAide;
import com.mycompany.myapp.entities.services.ServiceBeneficiaire;
import com.mycompany.myapp.entities.gui.Chambre.MenuChambre;*/
import com.mycompany.myapp.Services.QuestionService;
import com.mycompany.myapp.gui.anas.HomeForm;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author amina
 */
public class AfficherToutesLesQuestions extends Form {
        Form current;
    private Resources theme;
        public AfficherToutesLesQuestions( User user) {
        setTitle("Liste des questions");
       
              Question question= new Question();
          Container co=new Container(BoxLayout.xCenter());;
                    ArrayList <Question> questions = new ArrayList();
                        QuestionService sa =new QuestionService();
                        Button poser= new Button("Poser une Question");
                            add(poser);
                             poser.addActionListener(new ActionListener() {
                                            @Override
                            public void actionPerformed(ActionEvent evt) { 
                                Reponse r= new Reponse();
                                  new PoserQuestion(user).show();
                            }
                        });
                            
                            questions=sa.getAll();
                             for (Question fi : questions) {
                            Container ct = new Container(BoxLayout.y());
                            Label lTitre = new Label("Titre : "+fi.getTitre(),"RedLabel");
                            Label ldusercat = new Label("categorie : "+fi.getCatMed().getNom()+" nom user: "+fi.getUser().getNom(),"RedLabel");
                           // Label lUser = new Label("cette question est ajoutée par: "+fi.getUser().getNom()+" "+fi.getUser().getPrenom(),"SmallLabel");
                            Label lSymptomes = new Label("Symptomes : "+fi.getSymptomes(),"RedLabel");
                            Label ldetails = new Label("Details poids: "+fi.getPoids()+" Taille: "+fi.getTaille(),"SmallLabel");
                            ct.add(lTitre);
                            //ct.add(lUser);
                            ct.add(ldetails);
                            ct.add(lSymptomes);
                            ct.add(ldusercat);
                                Button afficher = new Button("Afficher");
                                 afficher.addActionListener(new ActionListener() {
                                            @Override
                            public void actionPerformed(ActionEvent evt) { 
                                Reponse r= new Reponse();
                                  new AfficherUneQuestion(r,fi,user).show();
                            }
                        });  
                                 Container cb = new Container(BoxLayout.x());
                                cb.add(afficher);
                            
                          

                             /*ct.add(modfier);
                       ct.add(Supp);*/
                       
                            
                                 System.out.println("ahawa el id mta3 user question"+fi.getUser().getId());
                           if(fi.getUser().getId()==user.getId())
                           {
                              Button Supp = new Button("Supprimer");
                            Button modfier = new Button("Modfier");
                            
                           
                            
                             modfier.addActionListener(new ActionListener() {
                                            @Override
                            public void actionPerformed(ActionEvent evt) {              
                                if (Dialog.show("Confirmation", "Voulez vous Modifier cette question ?", "Modifier ", "Annuler")) {
                                             new ModifierQuestion( user,fi).show();
                            }    
                            }
                        });
                            Supp.addActionListener(new ActionListener() {
                                                            @Override
                            public void actionPerformed(ActionEvent evt) {              
                                if (Dialog.show("Confirmation", "Voulez vous Supprimer cette question ?", "Supprimer", "Annuler")) {
                                Question t = new Question(fi.getId());
                                        if( QuestionService.getInstance().delete(t)){
                                            {
                                                Dialog.show("Success","supprimer",new Command("OK"));

                                                new AfficherToutesLesQuestions(user).show();
                                            }

                                }
                            }    
                            }
                        });
                            cb.add(modfier);
                       cb.add(Supp);
                           }
                           
                       ct.add(cb);
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

