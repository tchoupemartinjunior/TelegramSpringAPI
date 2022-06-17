package fr.ensim.interop.introrest.controller;

import fr.ensim.interop.introrest.model.telegram.model.City;
import fr.ensim.interop.introrest.model.telegram.model.OpenWeather;
import fr.ensim.interop.introrest.model.telegram.model.OpenWeather;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OpenWeatherRestController {
	
	private final static String API_KEY="b2b3b6b151a17c4dd3116efaa51a52b9";

	@GetMapping(value = "/meteo", params = {"lat", "long"})
	public static ResponseEntity<OpenWeather> meteo(
			@RequestParam("lat") String lat,
			@RequestParam("long") String longitude) {

		RestTemplate restTemplate = new RestTemplate();
		OpenWeather openWeather = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?lat={lat}"
																	+ "&lon={longitude}&appid=" + API_KEY, 
															OpenWeather.class, lat, longitude);
		
		return ResponseEntity.ok().body(openWeather);
	}

	@GetMapping(value = "/meteo")
	public static ResponseEntity<OpenWeather> meteoVille(
			@RequestParam("ville") String nomVille)  {
		City infoVille = getCoord(nomVille).getBody();
		Double lat = infoVille.getLat();
		Double lng = infoVille.getLon();

		return meteo(lat.toString(),lng.toString());

	}
	@GetMapping(value = "/meteo/forecast")
	public static ResponseEntity<OpenWeather> meteoVilleForecast(
			@RequestParam("ville") String nomVille)  {
		City infoVille = getCoord(nomVille).getBody();
		Double lat = infoVille.getLat();
		Double lng = infoVille.getLon();

		return meteoForeCast(lat.toString(),lng.toString());

	}

	@GetMapping("/position")
	public static ResponseEntity<City> getCoord(
			@RequestParam("ville") String nomVille) {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<City[]> responseEntity = restTemplate.getForEntity("http://api.openweathermap.org/geo/1.0/direct?q={ville}&limit=3"
																	+ "&appid=" + API_KEY,
															City[].class, nomVille);
		City[] cities = responseEntity.getBody();
		City city = cities[0];

		return ResponseEntity.ok().body(city);
	}

	@GetMapping(value = "/meteo/", params = {"lat", "long"})
	public static ResponseEntity<OpenWeather> meteoForeCast(
			@RequestParam("lat") String lat,
			@RequestParam("long") String longitude) {

		RestTemplate restTemplate = new RestTemplate();
		OpenWeather openWeather = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/forecast/daily?lat={lat}"+"&lon={longitude}&cnt=10&appid="
						+ API_KEY,
				OpenWeather.class, lat, longitude);

		return ResponseEntity.ok().body(openWeather);
	}


}
