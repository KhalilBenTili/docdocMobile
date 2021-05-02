/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Gui.alaa.AddPaiement;
import com.mycompany.myapp.Gui.alaa.ListPaiement;

/**
 * GUI builder created Form
 *
 * @author khali
 */
public class Home extends Form {

    public Home() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public Home(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

    protected com.codename1.ui.Button gui_Button = new com.codename1.ui.Button();
    Form current;

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
       
        Button btnAddPayement = new Button("Ajouter Paiement");
        Button btnListPayement = new Button("Afficher Paiement");
        btnAddPayement.addActionListener(e-> new AddPaiement(current).show());
        btnListPayement.addActionListener(e-> new ListPaiement(current).show());
        addAll(btnAddPayement,btnListPayement);
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
