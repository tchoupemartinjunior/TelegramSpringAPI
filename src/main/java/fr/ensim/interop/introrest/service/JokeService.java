package fr.ensim.interop.introrest.service;

import fr.ensim.interop.introrest.model.telegram.model.Joke;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Random;

@Service
public class JokeService {
    private static HashMap<Integer, Joke> mapper = new HashMap<Integer, Joke>();
    private static int nbJoke =0;




    @PostConstruct
    public void initJokeList(){
        save(new Joke(0,"Que demande un footballeur à son coiffeur ?\n","La coupe du monde s’il vous plait"));
        save(new Joke(1,"Pourquoi les canards sont toujours à l'heure ?\n","Parce qu’ils sont dans l’étang"));
        save(new Joke(2,"Pourquoi les pêcheurs ne sont pas gros ?\n","Parce qu’ils surveillent leur ligne."));
        save(new Joke(3,"Qu'est-ce qui n'est pas un steak ?\n","Une pastèque."));
    }

    public static ResponseEntity<Joke> save(Joke blague){
        if(mapper.containsKey(blague.getId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        nbJoke++;
        mapper.put(nbJoke, blague);
        System.out.println(mapper);
        return ResponseEntity.ok().body(blague);
    }

    public static ResponseEntity<Joke> getRandomJoke(){
        int min = 0;
        int max = mapper.size();
        Random random = new Random();
        int nombreAleatoire = random.nextInt(max + min) + min;
        if(!mapper.containsKey(nombreAleatoire)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(mapper.get(nombreAleatoire));
    }


}
