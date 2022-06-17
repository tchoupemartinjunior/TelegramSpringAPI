package fr.ensim.interop.introrest.controller;

import fr.ensim.interop.introrest.model.telegram.utilitaire.ApiResponseUpdateTelegram;
import fr.ensim.interop.introrest.model.telegram.utilitaire.MeteoMsg;
import fr.ensim.interop.introrest.model.telegram.model.Joke;
import fr.ensim.interop.introrest.model.telegram.model.OpenWeather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MessageRestController {
	public ResponseEntity<ApiResponseUpdateTelegram> responseEntity;
	public String message_recu;
	public String weatherDescription;
	public Double temp;
	public static String messageMeteo;
	public String ville;
	public Long CHATID = ******;
	//public int offset;


	@Value("${telegram.api.url}")
	private String telegramApiUrl;

	/*@Value("${telegram.bot.id}")
	private String CHATID;*/



	public void listen(){

		/*resultSize =  responseEntity.getBody().getResult().size();
		if(resultSize<=0||resultSize==null){offset=-1;}
		else{
			offset = responseEntity.getBody().getResult().get(resultSize).getUpdateId();
		}*/
		//int offset=-1;
		RestTemplate restTemplate = new RestTemplate();
		responseEntity = restTemplate.getForEntity("https://api.telegram.org/bot5582335341:AAFVu90WyAdTIQUixHvtzgJzeyejBXT3n8w/getUpdates?offset=-1", ApiResponseUpdateTelegram .class);
		message_recu=responseEntity.getBody().getResult().get(0).getMessage().getText();
		System.out.println(responseEntity.getBody().getResult().get(0).getMessage().getText());



	}
public String getMeteo(){
	listen();
	if(message_recu.equalsIgnoreCase("Meteo")){

		OpenWeatherRestController.meteoVille("Le mans");
		OpenWeather res = OpenWeatherRestController.meteoVille("Le mans").getBody();
		weatherDescription = res.getWeather().get(0).getMain();
		temp = res.getMain().getTemp();
		System.out.println(weatherDescription+' '+temp);
		messageMeteo = "Weather in Le Mans"+'\n'+weatherDescription+'\n'+temp+ "°C";
		sendMeteoData();
		return messageMeteo;
	}

	if(message_recu.toLowerCase().startsWith("meteo")){
		ville = message_recu.substring(6);
		OpenWeatherRestController.meteoVille(ville);
		OpenWeather res = OpenWeatherRestController.meteoVille(ville).getBody();
		weatherDescription = res.getWeather().get(0).getMain();
		temp = res.getMain().getTemp();
		System.out.println(weatherDescription+' '+temp);
		messageMeteo = "Weather in "+ville+'\n'+weatherDescription+'\n'+temp+ "°C";
		sendMeteoData();
		//System.out.println(ville);
		return messageMeteo;
	}

	else if(message_recu.equalsIgnoreCase("joke")){
		Joke blague = JokeController.getRandomJoke().getBody();
		messageMeteo = blague.getTitre()+'\n'+blague.getTexte();
		sendMessage(messageMeteo);
	}
	else if(message_recu.equalsIgnoreCase("bad joke")){
		Joke blague = JokeController.getBadJoke().getBody();
		messageMeteo = "Bad Joke \n\n"+blague.getTitre()+'\n'+blague.getTexte();
		sendMessage(messageMeteo);
	}
	else if(message_recu.equalsIgnoreCase("good joke")){
		Joke blague = JokeController.getGoodJoke().getBody();
		messageMeteo =  "GoodJoke \n\n"+blague.getTitre()+'\n'+blague.getTexte();
		sendMessage(messageMeteo);
	}

	else if(message_recu.equalsIgnoreCase("meteo forecast")){
		ville = message_recu.substring(13);
		OpenWeatherRestController.meteoVille(ville);
		OpenWeather res = OpenWeatherRestController.meteoVille(ville).getBody();
		weatherDescription = res.getWeather().get(0).getMain();
		temp = res.getMain().getTemp();
		System.out.println(weatherDescription+' '+temp);
		messageMeteo = "Weather in "+ville+'\n'+weatherDescription+'\n'+temp+ "°C";
		sendMeteoData();
		//System.out.println(ville);
		return messageMeteo;
	}
	//sendMessage("Aucun traitement associé a ce message");
	return messageMeteo;

}


public MeteoMsg sendMeteoData(){
	RestTemplate restTemplate = new RestTemplate();
	MeteoMsg meteoMsg = new MeteoMsg();
	meteoMsg.setText(messageMeteo);
	meteoMsg.setChat_id((CHATID));

	MeteoMsg msg = restTemplate.postForObject("https://api.telegram.org/bot5582335341:AAFVu90WyAdTIQUixHvtzgJzeyejBXT3n8w/sendMessage",
			meteoMsg,MeteoMsg.class
			);
	System.out.println(msg);
	return msg;

}


	public MeteoMsg sendMessage(String msg){
		RestTemplate restTemplate2 = new RestTemplate();
		MeteoMsg meteoMsg = new MeteoMsg();
		meteoMsg.setText(msg);
		meteoMsg.setChat_id((CHATID));

		MeteoMsg msg_ = restTemplate2.postForObject("https://api.telegram.org/bot5582335341:AAFVu90WyAdTIQUixHvtzgJzeyejBXT3n8w/sendMessage",
				meteoMsg,MeteoMsg.class
		);
		System.out.println(msg_);
		return msg_;

	}

	//5405543053L
	//Opérations sur la ressource Message
}
