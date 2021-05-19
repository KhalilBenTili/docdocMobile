/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.zohra;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.CategorieMedicale;
import com.mycompany.myapp.Services.categorieMedicaleService;
import java.util.ArrayList;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;

/**
 *
 * @author user
 */
public class ModifierCategorieMedicale extends Form{
    Form current;
    
    TextField tfTitre= new TextField("","Titre");
    Button btnAdd= new Button("Modifier ");
    public ModifierCategorieMedicale (CategorieMedicale u) {
         setTitle("Modifier Categorie Medicale");
        
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->{ new ListeCategorieMedicale().show();});

        
        //TextField isAnsw= new TextField("","status: 0 - 1");
        add(tfTitre);
        add(btnAdd);
        tfTitre.setText(u.getNom());
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                
                
                categorieMedicaleService cs=new categorieMedicaleService();
                u.setNom(tfTitre.getText());
                if(cs.edit(u))
                {
                    
                    Dialog.show("Success","Modifiée!",new Command("OK"));
                    new ListeCategorieMedicale().show();
                }
               else
                {
                    Dialog.show("Erreur","Erreur!",new Command("OK"));
                }
                //Question q=new Question(catMed, user, titre, Symptomes, TOP, TOP, focusScrolling, focusScrolling, focusScrolling, focusScrolling)
            }
            
        });
    }
}
