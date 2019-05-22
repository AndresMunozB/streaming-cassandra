<template>
    <v-container>
        <v-layout align-center justify-center row wrap>

            <v-text-field
                    label="Tipo del pokemon"
                    v-model="pokemonType"
            ></v-text-field>



            <v-btn @click="findByType(pokemonType)" color="primary" fab small>
                <v-icon>search</v-icon>
            </v-btn>

        </v-layout>
        <v-layout>
            <v-flex xs12>

                <PokemonTable :pokemones="pokemones" :loading="loading"/>
            </v-flex>
        </v-layout>


    </v-container>
</template>

<script>
    import pokemonService from '../../services/pokemonService';
    import PokemonTable from './PokemonTable';

    export default {
        name: "PokemonListByType",
        components:{
            PokemonTable
        },
        data(){
            return {
                pokemonType: '',
                loading:false,
                pokemones:[],
            }
        },
        methods:{
            findByType(type){
                this.loading = true;
                pokemonService.findByType(type,(res)=>{
                    this.pokemones = res.data;
                    this.loading = false;
                    console.log(this.pokemones);
                })
            }
        }
    }
</script>

<style scoped>

</style>