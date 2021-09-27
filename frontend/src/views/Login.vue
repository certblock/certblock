<template>
  <h1>로그인</h1>
  <input
    type="text"
    name="id"
    id="id"
    v-model="id"
    placeholder="id입력"
  /><br />
  <input
    type="password"
    name="password"
    id="password"
    v-model="pwd"
    placeholder="password입력"
  /><br />
  <button @click="login({ id, pwd })">로그인</button>
  <button @click="axiostest(id)">axiostest</button>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  data() {
    return {
      id: "",
      pwd: "",
    };
  },

  computed: {
    ...mapState(["isLogin"]),
  },

  methods: {
    ...mapActions(["login", "axiostest"]),
  },
};
</script>

<style></style>
