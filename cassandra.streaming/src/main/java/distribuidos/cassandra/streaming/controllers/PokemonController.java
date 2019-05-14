package distribuidos.cassandra.streaming.controllers;

import distribuidos.cassandra.streaming.models.Pokemon;
import distribuidos.cassandra.streaming.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

@RequestMapping(value = "pokemons")
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/")
    public ResponseEntity<List<Pokemon>> getPokemons(){
        return ResponseEntity.ok(pokemonService.listAll());
    }

}
