/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.zohra;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

/**
 *
 * @author user
 */
public class AfficherToutesLesQuestions extends Form {

    public AfficherToutesLesQuestions(Form previous) {
        setTitle("liste Questions");
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());
    
    }
    
}
