# Ingesta de datos streaming a una base de datos y disponibilización de interfaz de consulta de datos

## Integrantes del grupo

- [Javier Arredondo](http://github.com/JavierArredondo)
- [Diego Mellis](http://github.com/DiegoMellisG)
- [Andrés Muñoz](http://github.com/AndresMunozB)

## Descripción del problema
En esta nueva era la manipulación de grandes volumenes de datos es de vital importancia, más aún entregar información en tiempo real, en primer lugar por mantener la información actualizada y por otro lado, debido a que los entes que producen la información no les interesa almacenarla. Por ejemplo, hoy en día, diversos telescopios alrededor del mundo producen petabytes de datos al día; estos datos son procesados para obtener información valiosa de los objetos vistos, por ejemplo clasificarlos o ver comportamientos extraños. ¿Cuál es el problema con esto? El problema radica en procesar estos petabytes de datos, ya que podría demorar meses en finalizar. La solución para este desafío implica en procesar la información en tiempo real e ir almacenando la información valiosa en bases de datos convenientes.

En esta experiencia nos hemos sumergido en el mundo Pokémon, en donde se recibe un streaming de datos de diversas criaturas: nombre, tipo, ataque, defensa, velocidad, puntos de vida y las coordenadas donde se ha avistado (latitud y longitud). Dadas estas características y la gran información de datos recibida, los diferentes entrenadores Pokémon a nivel mundial han manifestado su interés para saber en que zonas habitan ciertos Pokémon.

## Enfoque de solución
El enfoque de la solución se orienta en lograr capturar esta información y almacenarla en una base de datos que soporte grandes volumenes de datos y disponibilizar la información a los entrenadores Pokémon del globo. Uno de los objetivos de la solución es realizar búsquedas geoespaciales, con la finalidad de entregar todos los Pokémon cercanos dada unas coordenadas. 

![Alt Text](https://media3.giphy.com/media/39GAXpLVKvYRO/giphy.gif)

## Tecnologías a utilizar

- **Docker:** Es una herramienta que permite empaquetar softwares y sus dependencias de manera sencilla. Son paquetes ejecutables ligeros y autónomos.
- **Kafka:** Es una herramienta que otorga el traspaso de mensajes (bytes de información) de un punto a otro. Esto permite realizar la producción y consumo de streaming de datos.
- **Cassandra:** Base de datos distribuida NoSQL que permite replicar su contenido en diversos nodos.
- **Vue.js:** Framework de javascript para el desarrollo web (frontend).
- **Spring Boot:** Framework de java para el desarrollo web (backend).
- **AWS:** Es una plataforma segura de servicios en la nube desarrollada por Amazon.


## Desarrollo de la actividad
- Principales inconvenientes o barreras detectadas.
    - En primera instancia, adentrarse al mundo de Cassandra fue una barrera, ya que su arquitectura es diferente al ser una base de datos no relacional y además distribuida. Si bien es cierto, se manejan conceptos similares, estos toman rumbos distintos.
    - El uso de los servicios de [AWS](https://aws.amazon.com/es/) (Amazon Web Service) también fue una barrera, ya que se presentaron inconvenientes en la configuración de los servidores, principalmente con la apertura de los puertos necesarios para el desarrollo de esta experiencia. Además, como opinión  grupal se ha llegado a la conclusión de que la plataforma de Amazon Web Services, no es tan intuitiva, por lo que resulta aún más complicado llegar al objetivo deseado. AWS al entregarnos un servidor nos da una IP pública, pero al ingresar a este servidor, se muestra una IP diferente a la ya mencionada, por lo que al configurar ciertos campos para Cassandra, se generó una confusión.
    
- Clases/funciones/procedimientos principales del desarrollo.

A continuación se muestran las principales clases implementadas usando Spring Boot y la dependencia de Cassandra. Estas clases son aquellas que en conjunto, permiten obtener los datos guardados en Cassandra que han sido ingestados a partir de un *streaming* de datos. 
```java
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
```
```java
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

    public Long countPokemons()
    {
        return pokemonRepository.count();
    }
}
```

```java
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
```
## Implementación
La implementación de la solución ha sido desarrollada en microservicios, como se ve en la siguiente figura:

![diagrama](https://i.imgur.com/XuXlO99.png)

Por un lado tenemos la plataforma en sí, que se encuentra en la parte superior del diagrama. Básicamente, se tiene una **base de datos**, la cual almacena información relevante, un **backend** que realiza consultas a la base de datos y un **frontend** que realiza peticiones e interactúa con el usuario.

Por otro lado se tiene el "pipeline" de producción/consumo de información; en donde un productor envía datos de Pokémon a un tópico de **Kafka** y por otro lado está el consumidor, que recibe los datos y los ingesta a la base de datos.


## Resultados
### Backend:
Los servicios expuestos en el backend se presentan en la siguiente tabla:
| path | método | descripción |
| -------- | -------- | -------- |
|`/pokemones/`|GET| Obtiene todos los Pokémon que se encuentran en la base datos|
|`/pokemones/type?type={}`|GET| Obtiene todos los Pokémon de cierto tipo |
|`/pokemon/name?name={}` |GET|Obtiene todos los Pokémon a partir de su nombre científico|

(gráficos de evaluación de tiempos de respuesta, capturas de pantalla de la plataforma funcional, entre otros).
### Frontend
El desarrollo del frontend consta de 2 vistas:

![](https://i.imgur.com/YM55uUv.gif)


![](https://i.imgur.com/3Q2Ywvw.png)

![](https://i.imgur.com/3DlThIq.png)

### Base de datos
Usuario con permisos de lectura a la base de datos
| Usuario | Contraseña | Keyspace | host |
|----------|---------- | ---------| ----- |
|pikachu | pikapika | pokemones | `34.238.53.42:9042` |





## Link de acceso a versión productiva del software.
[Avistamientos de Pokémon](http://34.238.53.42:8080)

## Pasos para desplegar servicio desde cero, considerar:
### Requisitos previos
Para poder implementar la arquitectura desarrollada en este trabajo, es necesario instalar previamente docker y docker-compose en un servidor.
#### Instalando docker
Para instalar docker es necesario ingresar los siguientes comandos en un terminal de linux:

```$ sudo apt-get update
$ sudo apt-get install apt-transport-https ca-certificates curl gnupg-agent software-properties-common
$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
$ sudo apt-key fingerprint 0EBFCD88
$ sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
$ sudo apt-get update
$ sudo apt-get install docker-ce docker-ce-cli containerd.io
```
#### Instalando docker compose
Para instalar docker compose se deben ingresar los siguiente comandos:

```
$ sudo curl -L "https://github.com/docker/compose/releases/download/1.24.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
$ sudo chmod +x /usr/local/bin/docker-compose
```

### Ejecutar las aplicaciones
Dentro del repositorio se tienen diferentes aplicaciones que funcionan en conjunto para obtener la implementación anteriormente explicada. Cabe mencionar que todas las aplicaciones desarrolladas y utilizadas estan dentro de un contenedor de docker. También, es importante recalcar, que todos los comandos realizados a continuación se ejecutan a partir desde la ubicación principal de la carpeta del repositorio clonado.

#### Ejecutando Kafka
Para ejecutar esta aplicación se debe utilizar ingresar los siguiente comandos:
```
$ cd kafka
$ docker-compose -f docker-compose-single-broker.yml -d
```

#### Ejecutando cassandra
Para ejecutar esta aplicación se debe utilizar ingresar los siguiente comandos:
```
$ docker-compose up -d
```

#### Ejecuta el productor de datos
```
$ cd producer
$ python3 producer.py
```

#### Ejecuta el consumidor de datos
```
$ cd consumer
$ python3 consumer.py
```
#### Ejecutando aplicacion backend
Para ejecutar el contenedor de esta aplicación se deben ejecutar los siguientes comandos:
```
$ cd cassandra.streaming
$ sudo chmod 777 Docker.sh
$ ./Docker.sh
```

Con estos comandos anteriores se crea un container con la aplicación backend la cual se puede acceder a través del puerto 3030.

#### Ejecutando la aplicación frontend

Para ejecutar la aplicación frontend se deben seguir los mismo pasos realizados para la aplicación de backend, la unica diferencia es la carpeta a la cual se debe ingresar.
```
$ cd cassandra-streaming-frontend
$ sudo chmod 777 Docker.sh
$ ./Docker.sh
```

Los comandos anteriores crean un contenedor que disponibiliza la aplicación de frontend en el puerto 8080.














- a. Maquina linux estándar (tomar como referencia ubuntu server en su última versión LTS)
- b. Implementar arquitectura como código (Yaml y TerraForm).
