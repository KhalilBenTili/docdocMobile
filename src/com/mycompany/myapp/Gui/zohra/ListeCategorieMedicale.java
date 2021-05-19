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
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Gui.Rihab.ListArticle;
import com.mycompany.myapp.Gui.alaa.ListPaiement;
import com.mycompany.myapp.Gui.khalil.Services;
import com.mycompany.myapp.Gui.wael.AddReclamation;
import com.mycompany.myapp.Gui.wael.ListMedecin;
import com.mycompany.myapp.Gui.wael.ListPharamcien;
import com.mycompany.myapp.Gui.wael.SessionManager;
import com.mycompany.myapp.Home;
import com.mycompany.myapp.gui.anas.HomeForm;
/**
 *
 * @author user
 */
public class ListeCategorieMedicale extends Form {
    Form current;
    private Resources theme;
    TextField tfTitre= new TextField("","Titre");
    Button btnAdd= new Button("ajouter");
    
        public ListeCategorieMedicale() {
            Container A = new Container(BoxLayout.y());
            setTitle("Catégorie Médicale");
            A.add(tfTitre);
            A.add(btnAdd);
                 btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt)
            {    CategorieMedicale u=new CategorieMedicale();
                u.setNom(tfTitre.getText());
                categorieMedicaleService cs=new categorieMedicaleService();
                
                if(cs.add(u))
                {
                    Dialog.show("Success","Ajoutée!",new Command("OK"));
                    new ListeCategorieMedicale().show();
                }
               else
                {
                    Dialog.show("Erreur","Erreur!",new Command("OK"));
                }
            }
            
        });
        
       
                    ArrayList <CategorieMedicale> questions = new ArrayList();
                        categorieMedicaleService sa =new categorieMedicaleService();
                            questions=sa.getAll();
                            Container ct = new Container(BoxLayout.y());
                             for (CategorieMedicale fi : questions) {

                            Label lTitre = new Label(fi.getNom());
                              ct.add(lTitre); 
                             Container bt = new Container(BoxLayout.x());
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
               
                                new ListeCategorieMedicale().show();
                            }
                   
                }
            }    
            }
        });
                       
                       Label separator = new Label("---------------------------","Separator");
                       
                       bt.add(modfier);
                       bt.add(Supp);
                       ct.add(bt);
                       ct.add(separator);
                             }
                             A.add(ct);
                             add(A);
      //toolbar     
      //toolbar  
        
      Toolbar tb=getToolbar();
       theme = UIManager.initFirstTheme("/theme");
      Image icon=theme.getImage("logo.png");
      Container topBar=BorderLayout.east(new Label(icon));
      topBar.add(BorderLayout.SOUTH,new Label("DOCDOC","SidemenuTagline"));
      topBar.setUIID("SideCommand");      
      User user=new User(SessionManager.getId(),SessionManager.getNom(),SessionManager.getPrenom(),SessionManager.getEmail(),SessionManager.getType());
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
