package fr.ensim.interop.introrest;

import fr.ensim.interop.introrest.controller.MessageRestController;
import fr.ensim.interop.introrest.model.telegram.utilitaire.getMeteoTask;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ListenerUpdateTelegram implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		Logger.getLogger("ListenerUpdateTelegram").log(Level.INFO, "Démarage du listener d'updates Telegram...");

		//1) Envoie d'un message dans une conversation Telegram
		//new MessageRestController().sendMessage("Hello Amine");
		new Timer().scheduleAtFixedRate(new getMeteoTask(), 0, 5000);

		for (int i = 0; i < 3; i++) {
			Thread.sleep(1000);
		}

	}

}
