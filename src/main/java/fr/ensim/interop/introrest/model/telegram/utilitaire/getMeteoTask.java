package fr.ensim.interop.introrest.model.telegram.utilitaire;

import fr.ensim.interop.introrest.controller.MessageRestController;

import java.util.TimerTask;

public class getMeteoTask extends TimerTask {
    MessageRestController msgCtrl =new MessageRestController();
    @Override
    public void run() {
        msgCtrl.getMeteo();
    }
}
