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
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.Consultation;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Services.ConsultationService;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class AfficherConsultationPatient extends Form{
    Form current;
    public AfficherConsultationPatient(Consultation u,User user) {
        
        
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
               
                                new AfficherConsultationPatient(u,user).show();
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
        //Tool Bar
       // getToolbar().addCommandToSideMenu("Home", null, e -> new MenuCategoryChambre().show());
        //getToolbar().addCommandToSideMenu("Consultations", null, e -> new MenuChambre(u).show());
        //getToolbar().addCommandToSideMenu("Reponses", null, e -> new MenuCategory(u).show());*/

        
            }
    
}
