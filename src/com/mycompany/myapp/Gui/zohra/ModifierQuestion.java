 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.zohra;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.CategorieMedicale;
import com.mycompany.myapp.Entities.Question;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Services.QuestionService;

/**
 *
 * @author user
 */
public class ModifierQuestion extends Form{
    
    Form current;
public ModifierQuestion(User u,Question question) {
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->{ new AfficherToutesLesQuestions(u).show();});
    QuestionService sq=new QuestionService();
        setTitle("Modifier Question");
        Label titreL=new Label("Titre");
        TextField tfTitre= new TextField("","Ajouter le titre ici");
        Label SymptomesL=new Label("Symptomes");
        TextField isAnsw= new TextField("","Fournir plus de details ici...");
        Label PoidsL=new Label("Poids");
        TextField poids=new TextField("","Saisir votre poids");
        Label TailleL=new Label("Taille");
        TextField taille=new TextField("","Saisir votre taille");
        CheckBox rb1 = new CheckBox("Traitement en cours");
        CheckBox rb2 = new CheckBox(" Antécédents médicaux");
        add(titreL).add(tfTitre);
        add(SymptomesL).add(isAnsw);
        add(PoidsL).add(poids);
        add(TailleL).add(taille);
        tfTitre.setText(question.getTitre());
        isAnsw.setText(question.getSymptomes());
        Integer p=question.getPoids();
        Integer t=question.getTaille();
        poids.setText(p.toString());
        taille.setText(t.toString());


        add(rb1);
        add(rb2);
        
        Button btnAdd= new Button("ajouter");
        add(btnAdd);
        CategorieMedicale catMed=new CategorieMedicale(3,"Allergie");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                question.setTitre(tfTitre.getText());
                question.setSymptomes(isAnsw.getText());
    
                
            if(sq.edit(question))
            {
                Dialog.show("Success","Question Modifiée!",new Command("OK"));
                new AfficherToutesLesQuestions(u).show();
            }
            }
            
        });
}
    
}
