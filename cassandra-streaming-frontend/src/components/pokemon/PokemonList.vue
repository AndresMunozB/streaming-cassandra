<template>
    <PokemonTable :pokemones="pokemones" :loading="loading"></PokemonTable>
</template>

<script>
    import pokemonService from '../../services/pokemonService';
    import PokemonTable from './PokemonTable';

    export default {
        name: "PokemonList",
        components:{
            PokemonTable
        },
        data(){
            return {
                loading:true,
                pokemones:[],
            }
        },
        created() {
            pokemonService.findAll((res)=>{
                this.pokemones = res.data;
                this.loading = false;
                console.log(this.pokemones);
            })
        }
    }
</script>

<style scoped>

</style>