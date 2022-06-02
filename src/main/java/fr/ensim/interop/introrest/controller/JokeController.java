package fr.ensim.interop.introrest.controller;

import fr.ensim.interop.introrest.model.telegram.model.Joke;
import fr.ensim.interop.introrest.service.JokeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class JokeController {

    @GetMapping("/joke")
    public static ResponseEntity<Joke> getRandomJoke(){
        return JokeService.getRandomJoke();
    }
}
