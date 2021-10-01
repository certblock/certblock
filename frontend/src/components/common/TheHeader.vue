<template>
  <div>
    <h1>헤더</h1>
    <div v-if="user == null">
      <router-link :to="{ name: 'Login' }">로그인</router-link> |
      <router-link :to="{ name: 'Regist' }">회원가입</router-link>
    </div>
    <div v-if="user != null">
      <p>{{ user.name }} 님 환영합니다</p>
      {{ user.phone }}<br />
      {{ user.birth }}<br />
      {{ user.email }}<br />
      <button @click="logout">로그아웃</button> |
      <button @click="deleteuser">회원탈퇴</button>
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
    ...mapState(["user"]),
  },
  methods: {
    ...mapActions(["logout", "deleteuser"]),
  },
};
</script>
