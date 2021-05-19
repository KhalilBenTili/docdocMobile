/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.zohra;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.Entities.Consultation;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Services.ConsultationService;
import java.util.Date;

/**
 *
 * @author user
 */
public class AjouterConsultation extends Form{
    Form current;
    Button btnAdd= new Button("Ajouter");
    public AjouterConsultation(Consultation c, User u)
    {   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->{ new  ListeConsultation(u).show();});
       
        ConsultationService cs=new ConsultationService();
        Form hi = new Form("Picker", new BoxLayout(BoxLayout.Y_AXIS));
        Picker dateTimePicker = new Picker();
        dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        dateTimePicker.setDate(new Date());
        hi.add(dateTimePicker).add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {     
                        c.setDate(dateTimePicker.getText());
                        String newDate=dateTimePicker.getText().replace( '/','-');
                        

        String newD = newDate.substring(0, 6) +"20" +newDate.substring(6,8);
        String hr=newDate.substring(9, newDate.length())+":00";
        c.setDate(newD);
        c.setHhr(hr);
        c.setMedecinUser(u);

        if(cs.add(c))
        {
            Dialog.show("Success","Consultation Ajoutée avec succes",new Command("OK"));
        }

            }       
        });

        hi.show();
        
    
    
    }
            
    
}
