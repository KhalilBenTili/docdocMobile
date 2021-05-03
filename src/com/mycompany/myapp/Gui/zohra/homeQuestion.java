/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.zohra;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author user
 */
public class homeQuestion extends Form {
Form current;
    public homeQuestion() {
        current=this;
        setTitle("Question");
        setLayout(BoxLayout.y());
        
        add(new Label("choose an option"));
        Button btnPoserQuestion = new Button("Poser Question");
        Button btnListQuestion = new Button("Afficher Questions");
        
        btnPoserQuestion.addActionListener(e-> new PoserQuestion(current).show());
        btnListQuestion.addActionListener(e-> new AfficherToutesLesQuestions(current).show());
        addAll(btnPoserQuestion,btnListQuestion);
        
    }
    
    
}
