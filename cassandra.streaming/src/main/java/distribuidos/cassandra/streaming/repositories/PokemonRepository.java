package distribuidos.cassandra.streaming.repositories;

import distribuidos.cassandra.streaming.models.Pokemon;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PokemonRepository extends CrudRepository<Pokemon, UUID> {

    @AllowFiltering
    List<Pokemon> findAllByFirstType(String type);
    @AllowFiltering
    List<Pokemon> findAllBySecondType(String type);
    @AllowFiltering
    List<Pokemon> findAllByName(String name);
}
