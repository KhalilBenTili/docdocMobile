/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.khalil;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.Paiement;

/**
 * GUI builder created Form
 *
 * @author khali
 */
public class Services extends com.codename1.ui.Form {

    public Services() {
       
    }
    
    public Services(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }
    public void addItem(Paiement P){
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label nom = new Label("Nom : "+P.getNom());
        Label prenom = new Label("Prenom : "+P.getPrenom());
        Label adresse = new Label("Adresse : "+P.getAdresse());
        Label numero = new Label("Numero : "+String.valueOf(P.getNumero()));
        Label email = new Label("Email : "+P.getEmail());
        Label prix = new Label("Prix : "+String.valueOf(P.getPrix()));
        Label type = new Label("Type : "+P.getType());
        Label status = new Label("Status : "+P.getStatus());
        Label sep = new Label("------------------------------------------------------------------");
        
        C1.add(nom);
        C1.add(prenom);
        C1.add(adresse);
        C1.add(numero);
        C1.add(email);
        C1.add(prix);
        C1.add(type);
        C1.add(status);
        C1.add(sep);
        C2.add(C1);
        add(C2);
        
    }

////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(true);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("Services");
        setName("Services");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
