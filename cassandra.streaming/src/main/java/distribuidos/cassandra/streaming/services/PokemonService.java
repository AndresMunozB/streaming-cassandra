package distribuidos.cassandra.streaming.services;

import distribuidos.cassandra.streaming.models.Pokemon;
import distribuidos.cassandra.streaming.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<Pokemon> listAll() {
        List<Pokemon> products = new ArrayList<>();
        pokemonRepository.findAll().forEach(products::add); //fun with Java 8
        return products;
    }
    public Pokemon create(Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }
}
