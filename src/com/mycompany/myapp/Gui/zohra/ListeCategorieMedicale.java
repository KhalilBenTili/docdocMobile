/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.zohra;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.CategorieMedicale;
import com.mycompany.myapp.Services.categorieMedicaleService;
import java.util.ArrayList;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
/**
 *
 * @author user
 */
public class ListeCategorieMedicale extends Form {
    Form current;
    TextField tfTitre= new TextField("","Titre");
    Button btnAdd= new Button("ajouter");

        public ListeCategorieMedicale(CategorieMedicale u) {
        setTitle("Catégorie Médicale");
        add(tfTitre);
        
        //TextField isAnsw= new TextField("","status: 0 - 1");
        
        add(btnAdd);
        
        
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                u.setNom(tfTitre.getText());
                categorieMedicaleService cs=new categorieMedicaleService();
                
                if(cs.add(u))
                {
                    Dialog.show("Success","Ajoutée!",new Command("OK"));
                    new ListeCategorieMedicale(u).show();
                }
               else
                {
                    Dialog.show("Erreur","Erreur!",new Command("OK"));
                }
            }
            
        });
        
        
        
       

          Container co=new Container(BoxLayout.xCenter());;
                    ArrayList <CategorieMedicale> questions = new ArrayList();
                        categorieMedicaleService sa =new categorieMedicaleService();
                            questions=sa.getAll();
                             for (CategorieMedicale fi : questions) {
                            Container ct = new Container(BoxLayout.x());
                            
                            
                            Label lTitre = new Label(fi.getNom());
                            //lTitre.getAllStyles().setFgColor(0xf15f5f);
                               
                            ct.add(lTitre); 
                           
                            Button Supp = new Button("Supprimer");
                            Button modfier = new Button("Modfier");
                            
                             modfier.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {              
                if (Dialog.show("Confirmation", "Voulez vous Modifier cette categorie ?", "Modifier ", "Annuler")) {
                             //new EditQuestion(current, fi).show();
                             new  ModifierCategorieMedicale (fi).show();
            }    
            }
        });
                            Supp.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {              
                if (Dialog.show("Confirmation", "Voulez vous Supprimer cette categorie ?", "Supprimer", "Annuler")) {
                CategorieMedicale t = new CategorieMedicale();
                t.setId(fi.getId());
                        if( categorieMedicaleService.getInstance().delete(t)){
                            {
                                Dialog.show("Success","supprimée",new Command("OK"));
               
                                new ListeCategorieMedicale(u).show();
                            }
                   
                }
            }    
            }
        });
                       
                       Label separator = new Label("","Separator");
                       
                       add(ct);
                       
                       ct.add(modfier);
                       ct.add(Supp);
                             }
        //Tool Bar
       // getToolbar().addCommandToSideMenu("Home", null, e -> new MenuCategoryChambre().show());
        //getToolbar().addCommandToSideMenu("Consultations", null, e -> new MenuChambre(u).show());
        //getToolbar().addCommandToSideMenu("Reponses", null, e -> new MenuCategory(u).show());*/
    
}
}
