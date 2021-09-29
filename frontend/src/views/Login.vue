<template>
  <h1>로그인</h1>
  <input
    type="text"
    name="email"
    id="email"
    v-model="email"
    placeholder="이메일"
  /><br />
  <input
    type="password"
    name="password"
    id="password"
    v-model="password"
    placeholder="비밀번호"
  /><br />
  <button @click="login({ email, password })">로그인</button>
</template>

<script>
import { mapActions } from "vuex";
export default {
  data() {
    return {
      email: "",
      password: "",
    };
  },

  computed: {},

  methods: {
    ...mapActions(["login"]),
  },
};
</script>

<style></style>
