package distribuidos.cassandra.streaming.controllers;

import distribuidos.cassandra.streaming.models.Pokemon;
import distribuidos.cassandra.streaming.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "/pokemones")
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/")
    public ResponseEntity<List<Pokemon>> getList(){
        return ResponseEntity.ok(pokemonService.listAll());
    }
    @PostMapping("/")
    public ResponseEntity<Pokemon> create(@RequestBody Pokemon pokemon){
        return ResponseEntity.ok(pokemonService.create(pokemon));
    }

    @GetMapping("/type")
    public ResponseEntity<List<Pokemon>> getPokemonesByType(@RequestParam String type)
    {
        return ResponseEntity.ok(pokemonService.getPokemonesByType(type));
    }

    @GetMapping("/name")
    public ResponseEntity<List<Pokemon>> getPokemonesByName(@RequestParam String name)
    {
        return ResponseEntity.ok(pokemonService.getPokemonesByName(name));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countPokemones()
    {
        return ResponseEntity.ok(pokemonService.countPokemons());
    }

}
