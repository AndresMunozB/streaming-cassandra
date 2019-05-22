<template>
  <v-app>
    <Header></Header>
    <Body></Body>
    <Footer></Footer>
  </v-app>
</template>

<script>
import Header from "./components/global/Header";
import Body from "./components/global/Body";
import Footer from "./components/global/Footer";

export default {
  name: "App",
  components: {
    Header,
    Body,
    Footer
  },
  data() {
    return {
      //
    };
  }
};
</script>
