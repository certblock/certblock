<template>
  <div>
    <h1>헤더</h1>
    <div v-if="!isLogin">
      <router-link :to="{ name: 'Login' }">로그인</router-link> |
      <router-link :to="{ name: 'Regist' }">회원가입</router-link>
    </div>
    <div v-if="this.$store.state.isLogin">
      <p>{{ this.$store.state.id }} 님 환영합니다</p>
      <button @click="logout">로그아웃</button> |
      <router-link :to="{ name: 'Regist' }">마이페이지</router-link>
    </div>
    <div style="border: 1px solid green; margin: 10px">
      <h3>메뉴</h3>
      <div id="nav">
        <router-link to="/">메인</router-link> |
        <router-link to="/certificate">증명서</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  computed: {
    ...mapState(["isLogin"]),
  },
  methods: {
    ...mapActions(["logout"]),
  },
};
</script>
