/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.wael;

import com.codename1.components.ImageViewer;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Paiement;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Home;
import com.mycompany.myapp.Services.PaiementService;
import com.mycompany.myapp.Services.UserService;
import java.io.IOException;

/**
 *
 * @author zagho
 */
public class RechercheNom extends Form{
private Resources theme;

        Form current;
    public RechercheNom(String nom) {
        theme = UIManager.initFirstTheme("/theme");

        setTitle("Votre liste .");

        for (User p : UserService.getInstance().RechercheNom(nom)){
            addItem(p);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new Home().show());
    }
   
        public void addItem(User P){
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Label nom = new Label("Nom : "+P.getNom());
        Label prenom = new Label("Prenom : "+P.getPrenom());
        Label email = new Label("Email : "+P.getEmail());
        Label adresse = new Label("Adresse : "+P.getAdresse());
        Label numtel = new Label("Numero tel : "+P.getNumtel());
       
        TextField rech = new TextField();
        Button recherche = new Button("Recherche");
        
        Label sep = new Label("-------------------------------------------------------------------");
        
        ImageViewer m = new ImageViewer();
   
        C3.add(nom);
        C1.add(C3);
               try {
            Image img = Image.createImage("/icon.png");
            C3.add("                 ");
            C3.add(img);
        } catch (IOException ex) {
        }
        C1.add(prenom);
        C1.add(email);   
        C1.add(adresse);
        C1.add(numtel);
        C1.add(sep);
        C2.add(C1);
        add(C2);     
    }
    
}

