package distribuidos.cassandra.streaming.repositories;

import distribuidos.cassandra.streaming.models.Pokemon;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PokemonRepository extends CrudRepository<Pokemon, UUID> {

}
