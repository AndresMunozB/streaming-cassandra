<template>
  <v-toolbar dark color="primary" id="header">
    <v-toolbar-title>Pokémon</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-toolbar-items class="hidden-sm-and-down">
      <v-btn append flat to="/">
        Inicio
      </v-btn>
      <v-btn append flat to="/login">
        Login
      </v-btn>

      <v-btn append flat to="/registro">
        Registro
      </v-btn>

      <v-btn append flat to="/buscador">
        Buscador
      </v-btn>
    </v-toolbar-items>
    <v-menu class="hidden-md-and-up">
      <v-toolbar-side-icon slot="activator"></v-toolbar-side-icon>
      <v-list>
        <v-list-tile to="/">
          <v-list-tile-content>
            <v-list-tile-title>Inicio</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile to="/login">
          <v-list-tile-content>
            <v-list-tile-title>Login</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile to="/registro">
          <v-list-tile-content>
            <v-list-tile-title>Registro</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-menu>
  </v-toolbar>
</template>

<script>
export default {
  name: "Header"
};
</script>

<style scoped></style>
