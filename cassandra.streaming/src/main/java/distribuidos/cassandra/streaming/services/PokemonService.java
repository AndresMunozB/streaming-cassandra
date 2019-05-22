package distribuidos.cassandra.streaming.services;

import com.datastax.driver.core.utils.UUIDs;
import distribuidos.cassandra.streaming.models.Pokemon;
import distribuidos.cassandra.streaming.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        pokemon.setId(UUIDs.timeBased());
        return pokemonRepository.save(pokemon);
    }

    public List<Pokemon> getPokemonesByType(String type)
    {
        List<Pokemon> firstType = pokemonRepository.findAllByFirstType(type);
        List<Pokemon> secondType = pokemonRepository.findAllBySecondType(type);
        return Stream.concat(firstType.stream(), secondType.stream()).collect(Collectors.toList());
    }

    public List<Pokemon> getPokemonesByName(String name)
    {
        return pokemonRepository.findAllByName(name);
    }
}
