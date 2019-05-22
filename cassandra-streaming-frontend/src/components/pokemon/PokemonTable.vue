<template>

    <v-data-table
            :headers="headers"
            :items="pokemones"
            :loading="loading"
            class="elevation-1"
    >
        <v-progress-linear v-slot:progress color="blue" indeterminate></v-progress-linear>
        <template v-slot:items="props">
            <td class="text-xs-center">{{ props.item.pokemonId }}</td>
            <td class="text-xs-center">{{ props.item.name }}</td>
            <td class="text-xs-center">{{ props.item.firstType }}</td>
            <td class="text-xs-center">{{ props.item.secondType }}</td>
            <td class="text-xs-center">{{ props.item.hp }}</td>
            <td class="text-xs-center">{{ props.item.attack }}</td>
            <td class="text-xs-center">{{ props.item.defense }}</td>
            <td class="text-xs-center">{{ props.item.speed }}</td>
            <td class="text-xs-center">{{ props.item.latitude }}</td>
            <td class="text-xs-center">{{ props.item.longitude }}</td>
        </template>
    </v-data-table>

</template>

<script>
    export default {
        name: "PokemonTable",
        props:['pokemones','loading'],
        data(){
            return {
                headers: [
                    {
                        text: 'ID',
                        align: 'left',
                        sortable: true,
                        value: 'pokemonId'
                    },
                    { text: 'Nombre', value: 'name' },
                    { text: 'Tipo 1', value: 'firstType' },
                    { text: 'Tipo 2', value: 'secondType' },
                    { text: 'Vida', value: 'hp' },
                    { text: 'Ataque', value: 'attack' },
                    { text: 'Defensa', value: 'defense' },
                    { text: 'Velocidad', value: 'speed' },
                    { text: 'Latitud', value: 'latitude' },
                    { text: 'Longitud', value: 'longitude' }

                ]
            }
        },
    }
</script>

<style scoped>

</style>