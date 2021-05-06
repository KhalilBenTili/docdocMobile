/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.zohra;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.Question;

/**
 *
 * @author user
 */
public class PoserQuestion extends Form{

    public PoserQuestion(Form previous) {
        setTitle("Poser Question");
        TextField tfTitre= new TextField("","Titre");
        TextField isAnsw= new TextField("","status: 0 - 1");
        Button btnAdd= new Button("ajouter");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                //Question q=new Question(catMed, user, titre, Symptomes, TOP, TOP, focusScrolling, focusScrolling, focusScrolling, focusScrolling)
            }
            
        });
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());
    }
    
    
}
