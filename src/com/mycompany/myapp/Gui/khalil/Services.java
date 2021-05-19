/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.khalil;

import com.codename1.components.ImageViewer;
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
import com.mycompany.myapp.Entities.Service;
import com.mycompany.myapp.Home;
import com.mycompany.myapp.Services.ServiceService;
import java.io.IOException;

/**
 * GUI builder created Form
 *
 * @author khali
 */
public class Services extends com.codename1.ui.Form {
 private Resources theme;

        Form current;
public Services(Form previous) {
        theme = UIManager.initFirstTheme("/theme");

        setTitle("Services");
       

             TextField rech = new TextField("","Nom");
            Button recherche = new Button("Recherche");
//            recherche.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                ServiceService.getInstance().RechercheNom(rech.getText());
//                new RechercheNom(rech.getText()).show();
//               
//            }
//        });
        add(rech);
        add(recherche); 
        ServiceService serv= new ServiceService();
        for (Service s : serv.Services()){
            
            addItem(s);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new Home().show());
    }

     public void addItem(Service s){
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Label libelle = new Label("Libelle : "+s.getLibelle());
//        Label categorie = new Label("Categorie: "+s.getCategorie().getLibelle());
        Label description = new Label("Description : "+s.getDescription());
        Label prix = new Label("Prix : "+String.valueOf(s.getPrix()));
        Label rate = new Label("Rate : "+String.valueOf(s.getAvgrating()));
              
        Label sep = new Label("-------------------------------------------------------------------");
        
        ImageViewer m = new ImageViewer();      
        C3.add(libelle);
        C1.add(C3);
//               try {
//         //   Image img = Image.createImage("/icon.png");
//            C3.add("               ");
//          //  C3.add(img);
//        } catch (IOException ex) {
//        }
      // C1.add(categorie);
        C1.add(description);
        C1.add(prix);
       
        C1.add(rate);
        C1.add(sep);
        C2.add(C1);
        add(C2);
        
    }
}
//  public Services() {
//        setTitle("Liste des Chambre");
//        
//
//          Container co=new Container(BoxLayout.xCenter());;
//                    ArrayList <Service> services = new ArrayList();
//                        ServiceService serv =new ServiceService();
//                    services=serv.Services();
//                             for (Service ser : services) {
//                            Container ct = new Container(BoxLayout.y());
//                            Label l = new Label("Libelle : "+ser.getLibelle());
//                            Label l2 = new Label("Catégorie : "+ser.getCategorie().getLibelle(),"SmallLabel");
//                            Label l4 = new Label("PRIX : "+ser.getPrix()+" DT","RedLabel");
//                            l2.getAllStyles().setFgColor(0xf15f5f);
//                            ct.add(l);
//                            ct.add(l2);
//                            ct.add(l4);
//                            
//                            
//                       Label separator = new Label("","Separator");
//                       ct.add(separator);
//                       add(ct);
//                             }
//}
