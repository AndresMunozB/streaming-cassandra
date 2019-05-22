<template>
    <v-container>
        <v-layout align-center justify-center row wrap>

                <v-text-field
                        label="Nombre del pokemon"
                        v-model="pokemonName"
                ></v-text-field>



                <v-btn @click="findByName(pokemonName)" color="primary" fab small>
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
        name: "PokemonListByName",
        components:{
            PokemonTable
        },
        data(){
            return {
                pokemonName: '',
                loading:false,
                pokemones:[],
            }
        },
        methods:{
            findByName(name){
                this.loading = true;
                pokemonService.findByName(name,(res)=>{
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