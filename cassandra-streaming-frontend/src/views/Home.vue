<template>
  <v-container>
    <v-layout text-xs-center wrap>
      <v-flex xs12>
        <v-img
          :src="require('../assets/pokemon_logo.png')"
          class="my-3"
          contain
          height="200"
        ></v-img>
      </v-flex>

      <v-flex mb-4>
        <h1 class="display-2 font-weight-bold mb-3">
          Bienvenido a Plataforma Pokémon
        </h1>
        <p class="display-1 font-weight-bold mb-3">
          Pokemones avistados: {{count}}
        </p>
        <p class="subheading font-weight-regular">
          Plataforma como servicio para...
          <br />
          <a
            href="https://github.com/AndresMunozB/streaming-cassandra"
            target="_blank"
            >Código Fuente</a
          >
        </p>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import pokemonService from '../services/pokemonService';
export default {
  name: "Home",
  data(){
    return {
      count:0
    }
  },
  created() {
    setInterval(() => {
      pokemonService.getCount((res)=>{
        this.count =res.data;
      });
    }, 3000);
  }
};
</script>
