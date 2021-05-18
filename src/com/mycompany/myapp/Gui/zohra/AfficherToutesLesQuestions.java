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
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.Question;
import com.mycompany.myapp.Entities.Reponse;
import com.mycompany.myapp.Entities.User;
/*import com.mycompany.myapp.entities.gui.MenuCategoryChambre;
import com.mycompany.myapp.entities.services.ServiceAide;
import com.mycompany.myapp.entities.services.ServiceBeneficiaire;
import com.mycompany.myapp.entities.gui.Chambre.MenuChambre;*/
import com.mycompany.myapp.Services.QuestionService;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author amina
 */
public class AfficherToutesLesQuestions extends Form {
        Form current;

        public AfficherToutesLesQuestions(Question u, User us) {
        setTitle("Liste des questions");
       

          Container co=new Container(BoxLayout.xCenter());;
                    ArrayList <Question> questions = new ArrayList();
                        QuestionService sa =new QuestionService();
                            questions=sa.getAll();
                             for (Question fi : questions) {
                            Container ct = new Container(BoxLayout.y());
                            Label lTitre = new Label("Titre : "+fi.getTitre(),"RedLabel");
                            Label ldusercat = new Label("categorie : "+fi.getCatMed().getNom()+" nom user: "+fi.getUser().getNom(),"RedLabel");
                           // Label lUser = new Label("cette question est ajoutée par: "+fi.getUser().getNom()+" "+fi.getUser().getPrenom(),"SmallLabel");
                            Label lSymptomes = new Label("Symptomes : "+fi.getSymptomes(),"RedLabel");
                            Label ldetails = new Label("Details poids: "+fi.getPoids()+" Taille: "+fi.getTaille(),"SmallLabel");
                            lTitre.getAllStyles().setFgColor(0xf15f5f);
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
                                  new AfficherUneQuestion(r,fi,us).show();
                            }
                        });
                                ct.add(afficher);
                            
                            Button Supp = new Button("Supprimer");
                            Button modfier = new Button("Modfier");
                             /*ct.add(modfier);
                       ct.add(Supp);*/
                       
                            Supp.setEnabled(false);
                            modfier.setEnabled(false);
                                 System.out.println("ahawa el id mta3 user question"+fi.getUser().getId());
                           if(fi.getUser().getId()==us.getId())
                           {
                            
                            Supp.setEnabled(true);
                            modfier.setEnabled(true);
                           
                            
                             modfier.addActionListener(new ActionListener() {
                                            @Override
                            public void actionPerformed(ActionEvent evt) {              
                                if (Dialog.show("Confirmation", "Voulez vous Modifier cette question ?", "Modifier ", "Annuler")) {
                                             new EditQuestion(current, fi).show();
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

                                                new AfficherToutesLesQuestions(u,us).show();
                                            }

                                }
                            }    
                            }
                        });
                           }
                           
                       ct.add(modfier);
                       ct.add(Supp);
                       Label separator = new Label("","Separator");
                       ct.add(separator);

                            add(ct);
                             }
        //Tool Bar
       // getToolbar().addCommandToSideMenu("Home", null, e -> new MenuCategoryChambre().show());
        //getToolbar().addCommandToSideMenu("Consultations", null, e -> new MenuChambre(u).show());
        //getToolbar().addCommandToSideMenu("Reponses", null, e -> new MenuCategory(u).show());*/
    }

   
}

