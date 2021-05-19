/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Entities.Reclamation;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author zagho
 */
public class ReclamationService {
    public ArrayList<Reclamation> reclamations;
    
    public boolean resultOK;
    
    public static ReclamationService instance=null;
    
    private ConnectionRequest req;
    
    private ReclamationService() {
         req = new ConnectionRequest();
    }
    
    public static ReclamationService getInstance() {
        if (instance == null) {
            instance = new ReclamationService();
        }
        return instance;
    }
    
    public boolean ajoutReclamation(Reclamation p){
        String url = Statics.BASE_URL+"/addReclamationJson/new?motif="+p.getMotif()+"&description="+p.getDescription()+"&user_id="+p.getUser_id()+"&date=" ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode()==200;
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
    }
    public void sendMail() {

        Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
        m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
        boolean success = m.sendMessageViaCloudSync("Codename One", "br.rassil@gmail.com", "Name Of User", "Message Subject",
                "Check out Codename One at https://www.codenameone.com/");
        System.out.println("success: " + success);
    }
    
}
