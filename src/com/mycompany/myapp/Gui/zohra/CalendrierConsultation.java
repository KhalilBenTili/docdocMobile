/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.zohra;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.util.Date;


public class CalendrierConsultation extends Form {
    
Form current;
    
    
    
  
    
    public CalendrierConsultation() {
        Form hi = new Form("Picker", new BoxLayout(BoxLayout.Y_AXIS));
        Picker dateTimePicker = new Picker();
        dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        dateTimePicker.setDate(new Date());
        hi.add(dateTimePicker);
        hi.show();
        dateTimePicker.getText();
        Dialog.show("Success",dateTimePicker.getText(),new Command("OK"));
    }
    
}


    
