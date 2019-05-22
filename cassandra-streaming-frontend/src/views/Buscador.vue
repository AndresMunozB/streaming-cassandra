<template>
  <v-container>
    <h1>Buscador</h1>
  </v-container>
</template>

<script>
  import pokemonService from  '../services/pokemonService';
export default {
  name: "Buscador",
  data(){
    return {
      pokemones:[]
    }
  },
  created() {
      pokemonService.findAll((res)=>{
        this.pokemones = res.data;
      })
  }
};
</script>

<style scoped></style>
