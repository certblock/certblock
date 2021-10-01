<!-- main.js를 통해 표시되는 페이지 -->
<template>
  <!-- header 표시 -->

  <!-- router페이지 표시 -->
  <div id="app">
    <the-header id="header" />
    <router-view />
    <the-footer id="footer" />
  </div>

  <!-- footer 표시 -->
</template>

<script>
import TheFooter from "./components/common/TheFooter.vue";
import TheHeader from "./components/common/TheHeader.vue";

export default {
  components: {
    TheHeader,
    TheFooter,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

#header {
  border: 1px solid red;
}

#footer {
  border: 1px solid red;
}
</style>