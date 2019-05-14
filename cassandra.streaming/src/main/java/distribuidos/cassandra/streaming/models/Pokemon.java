package distribuidos.cassandra.streaming.models;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Pokemon {

    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;
    private String description;
    private BigDecimal price;
    private String imageUrl;
}
