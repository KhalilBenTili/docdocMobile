/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.Rihab;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Commentaire;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Entities.article;
import com.mycompany.myapp.Gui.alaa.ListPaiement;
import com.mycompany.myapp.Gui.khalil.Services;
import com.mycompany.myapp.Gui.wael.AddReclamation;
import com.mycompany.myapp.Gui.wael.ListMedecin;
import com.mycompany.myapp.Gui.wael.ListPharamcien;
import com.mycompany.myapp.Gui.wael.SessionManager;
import com.mycompany.myapp.Gui.zohra.AfficherConsultationPatient;
import com.mycompany.myapp.Gui.zohra.AfficherToutesLesQuestions;
import com.mycompany.myapp.Gui.zohra.ListeCategorieMedicale;
import com.mycompany.myapp.Gui.zohra.ListeConsultation;
import com.mycompany.myapp.Home;
import com.mycompany.myapp.Services.BlogService;
import com.mycompany.myapp.Services.CommentaireService;
import com.mycompany.myapp.gui.anas.HomeForm;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class ListArticle extends Form {
    private Resources theme;
    ImageViewer img = null;

    public ListArticle(Form current) {
        super("La liste des articles.");
   
       

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

    private void addGUIs() {
        for (article p : BlogService.getInstance().getAllArticles()) {
            this.add(addItem(p));
        }
    }

    public Container addItem(article P) {
        Container articleContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        articleContainer.setUIID("articleContainer");

        Label titre = new Label("Titre : " + P.getTitre());
        titre.setUIID("labelTitre");
        Label contenu = new Label("Contenu : " + P.getContenu());
        contenu.setUIID("labelContenu");
        Label created_at = new Label("Crée le : " + P.getCreated_at());
        created_at.setUIID("labelCreatedAt");
        Label cat = new Label("La catégorie : " + P.getCat());
        cat.setUIID("labelCat");

        try {
            img = new ImageViewer(Image.createImage(P.getImg()));

            int dw = Display.getInstance().getDisplayWidth();
            img.setPreferredH(dw / 2);
            img.setPreferredW(dw / 2);

        } catch (IOException ex) {

        }

        Label sep = new Label("------------------------------------------------------------------");

        articleContainer.addAll(titre, contenu, created_at, cat, img, creerCommentaireContainer(P));

        return articleContainer;
    }

    private Container creerCommentaireContainer(article a) {
        Container containerCommentaire = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        containerCommentaire.setUIID("containerCommentaire");
        
        containerCommentaire.add(new Label("Commentaires : "));

        ArrayList<Commentaire> arrayListCommentaire = CommentaireService.getInstance().getAllCommentaires();
        for (Commentaire commentaire : arrayListCommentaire) {
            if(commentaire.getArticleId()==a.getId()){ 
            Label labelCommentaire = new Label(commentaire.getContenu());
            labelCommentaire.setUIID("labelCommentaire");
            containerCommentaire.add(labelCommentaire);}
        }

        Container containerAjoutCommentaire = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        TextField tfCommentaire = new TextField();
        tfCommentaire.setTooltip("Ajouter un commentaire");
        Button buttonAjout = new Button("Envoyer");
        buttonAjout.setUIID("btnAjout");

        buttonAjout.addActionListener(action -> {
            CommentaireService.getInstance().ajoutCommentaire(new Commentaire(
                    tfCommentaire.getText(),
                    a.getId()
            ));

            Label labelCommentaire = new Label(tfCommentaire.getText());
            labelCommentaire.setUIID("labelCommentaire");
            containerCommentaire.addComponent(1, labelCommentaire);

            tfCommentaire.setText("");

            this.repaint();
            this.revalidate();
            
            
            
            
            
            
            
            
            
                    
                      ToastBar.getInstance().setPosition(BOTTOM);
                      ToastBar.Status status = ToastBar.getInstance().createStatus();
 status.setShowProgressIndicator(true);
  // status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                            status.setMessage("commentaire ajoutée avec succès");
                                                  status.setExpires(10000);   
                      status.show();
                  
            
            
            
            
            
        });

        containerAjoutCommentaire.addAll(tfCommentaire, buttonAjout);

        containerCommentaire.add(containerAjoutCommentaire);

        return containerCommentaire;
    }
}
