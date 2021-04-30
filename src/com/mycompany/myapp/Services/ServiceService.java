/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Entities.Service;
import com.mycompany.myapp.Utils.Statics;

/**
 *
 * @author khali
 */
public class ServiceService {
    public boolean resultOK;
    public boolean Services(Service s){
        String url = Statics.BASE_URL+"/services";
        ConnectionRequest req= new ConnectionRequest();
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                   resultOK =req.getResponseCode()==200;
            }
        });

        return resultOK;
    }
}
