 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.zohra;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Entities.Question;
import com.mycompany.myapp.Entities.Reponse;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Services.ReponseService;


/**
 *
 * @author user
 */
public class ModifierReponse extends Form{
    Form current;
    TextField tfTitre= new TextField("","Titre");
    Button btnAdd= new Button("Modifier ");
    public ModifierReponse (Reponse u,Question q, User user) {
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->{ new AfficherUneQuestion(u,q,user).show();});

        add(tfTitre);
        add(btnAdd);
        tfTitre.setText(u.getDescription());
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                
                
                ReponseService cs=new ReponseService();
                u.setDescription(tfTitre.getText());
                if(cs.edit(u))
                {
                    
                    Dialog.show("Success","Modifiée!",new Command("OK"));
                    new AfficherUneQuestion(u,q,user).show();
                }
               else
                {
                    Dialog.show("Erreur","Erreur!",new Command("OK"));
                }
            }
            
        });
    
    }
    
}
