<template>
  <v-container>
    <h1>Buscador</h1>
    <PokemonList/>
  </v-container>
</template>

<script>

  import PokemonList from '../components/pokemon/PokemonList';
export default {
  name: "Buscador",
  components:{
    PokemonList
  }

};
</script>

<style scoped></style>
