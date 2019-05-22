import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import Login from "./views/Login";
import Registro from "./views/Registro";
import Buscador from "./views/Buscador";
Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home
    },
    {
      path: "/login",
      name: "login",
      component: Login
    },
    {
      path: "/registro",
      name: "registro",
      component: Registro
    },
    {
      path: "/buscador",
      name: "buscador",
      component: Buscador
    }
  ]
});
