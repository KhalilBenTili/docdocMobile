/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.anas;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Home;

/**
 *
 * @author anasm
 */
public class HomeForm extends Form {
Form current;
    public HomeForm() {
        current=this;
        setTitle("Produit");
        setLayout(BoxLayout.y());
        
        Button btnAddCategorie = new Button("Add categorie");
        Button btnListCategorie = new Button("List categorie");
        btnAddCategorie.addActionListener(e-> new AddCategorieForm(current).show());
        btnListCategorie.addActionListener(e-> new ListCategorieForm(current).show());
        
        Button btnAddProduit = new Button("Add produit");
        Button btnListProduit = new Button("List produit");
        btnAddProduit.addActionListener(e-> new AddProduitForm(current).show());
        btnListProduit.addActionListener(e-> new ListProduitForm(current).show());
        
        addAll(btnAddCategorie,btnListCategorie,btnAddProduit,btnListProduit);
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new Home().show());

    }
    
    
    
}
