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
  <button @click="login()">로그인</button>
</template>

<script>
export default {
  data: () => ({
    id: "",
    pwd: "",
  }),
  methods: {
    login() {
      alert("id : " + this.id + "\npassword : " + this.pwd + "\n" + this.$store.state.isLogin);
      this.$store.state.id = this.id;
      this.$store.state.pwd = this.pwd;
      this.$store.state.isLogin = true;
    },
  },
};
</script>

<style></style>
