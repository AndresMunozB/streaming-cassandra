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
    private Integer pokemonId;

    @Column
    private String firstType;

    @Column
    private String secondType;

    @Column
    private Integer HP;

    @Column
    private Integer attack;

    @Column
    private Integer defense;

    @Column
    private Integer speed;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

}
