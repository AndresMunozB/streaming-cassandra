<template>
  <v-content
    fluid
    :style="{ backgroundImage: `url('${img}')`, backgroundSize: 'cover' }"
  >
    <router-view></router-view>
  </v-content>
</template>

<script>
export default {
  name: "Body",
  data() {
    return {
      img: ""
      //https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-449963.jpg
    };
  }
};
</script>

<style scoped></style>
