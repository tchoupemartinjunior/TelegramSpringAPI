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
        save(new Joke(0,"Que demande un footballeur à son coiffeur ?\n","La coupe du monde s’il vous plait",2));
        save(new Joke(1,"Pourquoi les canards sont toujours à l'heure ?\n","Parce qu’ils sont dans l’étang",3));
        save(new Joke(2,"Pourquoi les pêcheurs ne sont pas gros ?\n","Parce qu’ils surveillent leur ligne.",4));
        save(new Joke(3,"Qu'est-ce qui n'est pas un steak ?\n","Une pastèque.",5));
        save(new Joke(4,"C'est l'histoire d'un poil.\n","Avant il était bien, maintenant il est pubien.",3));
        save(new Joke(5,"Pourquoi un chasseur emmène-t-il son fusil aux toilettes ?\n" ,"Pour tirer la chasse.",5));
        save(new Joke(6,"Pourquoi est-ce que Napoléon n'a pas voulu acheter de maison ?\n","Parce qu'il avait deja un Bonaparte",3));
        save(new Joke(7,"Quel est l'animal le plus connecté?\n","Le porc USB",3));
        save(new Joke(8,"Que dit un informaticien quand il s'ennuie ?\n"," Je me fichier",4));
        save(new Joke(9,"Que fait un geek quand il descend du métro ?\n","Il libère la RAMe",3));
        save(new Joke(10,"Que fait un geek qui a peur ?\n","Il URL",2));
        save(new Joke(11,"Qu'est-ce qui est jaune et qui fait 'crac crac' ?\n","Un poussin qui mange des chips.",5));

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
