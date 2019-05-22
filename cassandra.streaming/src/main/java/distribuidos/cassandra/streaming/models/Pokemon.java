package distribuidos.cassandra.streaming.models;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Table
public class Pokemon {

    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    @Column
    private String name;

    @Column
    private int pokemonId;

    @Column
    private String firstType;

    @Column
    private String secondType;

    @Column
    private int HP;

    @Column
    private int attack;

    @Column
    private int defense;

    @Column
    private int speed;

    @Column
    private Long latitude;

    @Column
    private Long longitude;

}
