package distribuidos.cassandra.streaming.repositories;

import distribuidos.cassandra.streaming.models.Pokemon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PokemonRepository extends CrudRepository<Pokemon, UUID> {

    List<Pokemon> findAllByFirstType(String type);
    List<Pokemon> findAllBySecondType(String type);
    List<Pokemon> findAllByName(String name);
}
