<template>
  <v-container>
    <v-layout row wrap>
      <v-flex xs12>
        <PokemonList/>
      </v-flex>

      <v-flex xs12 md6>
        <PokemonListByName/>
      </v-flex>

      <v-flex xs12 md6>
        <PokemonListByType/>
      </v-flex>

    </v-layout>


  </v-container>
</template>

<script>

  import PokemonList from '../components/pokemon/PokemonList';
  import PokemonListByName from '../components/pokemon/PokemonListByName';
  import PokemonListByType from '../components/pokemon/PokemonListByType';
export default {
  name: "Buscador",
  components:{
    PokemonList,
    PokemonListByName,
    PokemonListByType
  }

};
</script>

<style scoped></style>
