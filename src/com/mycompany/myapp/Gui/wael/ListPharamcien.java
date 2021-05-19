/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.wael;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Home;
import com.mycompany.myapp.Services.UserService;

/**
 *
 * @author zagho
 */
public class ListPharamcien extends Form {
    private Resources theme;
    public ListPharamcien(Form previous) {
        theme = UIManager.initFirstTheme("/theme");        
        setTitle("Votre liste des pharamcies.");
          for (User p : UserService.getInstance().getAllUsersPharmacie()){
            addItem(p);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new Home().show());
    }
    
      
        

    private void addItem(User p) {
            Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
             Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
              Label pharmacie = new Label("Pharamcie : "+p.getPharamcie());
             Label nom = new Label("Nom : "+p.getNom());
             Label prenom = new Label("Prenom : "+p.getPrenom());
             Label email = new Label("Email : "+p.getEmail());
                   
             Label adresse = new Label("Adresse : "+p.getAdresse());
             Label numtel = new Label("Numero tel : "+p.getNumtel());
              Label sep = new Label("------------------------------------------------------------------");
         C1.add(pharmacie);
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
