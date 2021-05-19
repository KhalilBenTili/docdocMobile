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
public class PoserQuestion extends Form{
Form current;

    public PoserQuestion(User u,Question question) {
        QuestionService sq=new QuestionService();
        setTitle("Poser Question");
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


        add(rb1);
        add(rb2);
        
        Button btnAdd= new Button("ajouter");
        add(btnAdd);
        CategorieMedicale catMed=new CategorieMedicale(3,"Allergie");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                Question q=new Question(catMed, u, tfTitre.getText(), isAnsw.getText(), 150, 50,rb1.isSelected() , rb2.isSelected(), false, false);
            if(sq.add(q))
            {
                Dialog.show("Success","Question Ajoutée!",new Command("OK"));
                new AfficherToutesLesQuestions(question,u).show();
            }
            }
            
        });
    }
    
    
}
