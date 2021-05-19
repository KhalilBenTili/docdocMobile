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
import com.mycompany.myapp.Gui.wael.AddReclamation;
import com.mycompany.myapp.Gui.wael.AddUser;
import com.mycompany.myapp.Gui.wael.ListMedecin;
import com.mycompany.myapp.Gui.wael.ListPharamcien;
import com.mycompany.myapp.Gui.wael.Login;
import com.mycompany.myapp.gui.anas.HomeForm;

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
       
         Button btnLoginUser = new Button("Login");
        Button btnAddUser = new Button("Inscription");
        Button btnListPayement = new Button("Paiement");
        Button btnProduit = new Button("Produit");
        Button btnListMedecin = new Button("Afficher Medecins");
        Button btnListPharamcie = new Button("Afficher Pharamcie");
        Button btnAddReclamation = new Button("Ajouter Reclamation");
        btnLoginUser.addActionListener(e-> new Login(current).show());
        btnAddUser.addActionListener(e-> new AddUser(current).show());
        btnListPayement.addActionListener(e-> new ListPaiement(current).show());
        btnProduit.addActionListener(e-> new HomeForm().show());
        btnListMedecin.addActionListener(e-> new ListMedecin(current).show());
        btnListPharamcie.addActionListener(e-> new ListPharamcien(current).show());
        btnAddReclamation.addActionListener(e-> new AddReclamation(current).show());
        addAll(btnLoginUser,btnAddUser,btnListPayement,btnProduit,btnListMedecin,btnListPharamcie,btnAddReclamation);
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
