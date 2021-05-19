/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.zohra;

import com.codename1.ui.Form;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.Consultation;
import com.mycompany.myapp.Services.ConsultationService;
import java.util.ArrayList;
import com.codename1.ui.Dialog;
import com.mycompany.myapp.Entities.User;
/**
 *
 * @author user
 */
public class ListeConsultation extends Form{
    Form current;
    public ListeConsultation(Consultation u, User user) {
        setTitle(" Consultation");
       

          Container co=new Container(BoxLayout.xCenter());;
                    ArrayList <Consultation> questions = new ArrayList();
                        ConsultationService sa =new ConsultationService();
                            questions=sa.getAllMedecin(user);
                             for (Consultation fi : questions) {
                            Container ct = new Container(BoxLayout.y());
                            Label lTitre = new Label(fi.getPatientUser().getNom()+" "+fi.getPatientUser().getPrenom()+" a demandé une consultation avec vous ayant comme numero de telephone"+fi.getPatientUser().getNumtel(),"RedLabel");
                            Label ldate = new Label("pour le "+fi.getDate()+"a "+fi.getHr(),"RedLabel");
                            lTitre.getAllStyles().setFgColor(0x4ed9d9);
                            ct.add(lTitre);   
                            ct.add(ldate);
                           
                            Button Supp = new Button("Accepter");

                            
                            
                            Supp.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {              
                if (Dialog.show("Confirmation", "Voulez vous Accepter cette demande de consultation ?", "Oui", "Non")) {
                Consultation t = new Consultation();
                t.setId(fi.getId());
                        if(ConsultationService.getInstance().Accepter(t)){
                            {
                                
                                Dialog.show("Success","Acceptée",new Command("OK"));
                                new ListeConsultation(u,user).show();
                            }
                   
                }
            }    
            }
        });

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
