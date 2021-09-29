<template>
  <h1>회원가입</h1>
  <input
    type="text"
    name="name"
    id="name"
    v-model="name"
    placeholder="이름"
  /><br />
  <input
    type="text"
    name="phone"
    id="phone"
    v-model="phone"
    placeholder="전화번호"
  /><br />
  <input type="date" name="birth" id="birth" v-model="birth" /><br />
  <input
    type="email"
    name="email"
    id="email"
    v-model="email"
    placeholder="이메일"
  /><br />
  <input
    type="password"
    name="pass"
    id="pass"
    v-model="pass"
    placeholder="비밀번호"
  /><br />
  <input type="radio" id="student" value="2" v-model="type" />
  <label for="student">학생</label>
  <input type="radio" id="company" value="3" v-model="type" />
  <label for="company">기업</label><br />
  <button @click="regist({ name, phone, birth, email, pass, type })">
    회원가입
  </button>
</template>

<script>
import axios from "axios";
import router from "../router";
export default {
  data() {
    return {
      name: "",
      phone: "",
      birth: "",
      email: "",
      pass: "",
      type: 2,
    };
  },

  methods: {
    regist(data) {
      axios
        .post(`http://localhost/api/users/`, data)
        .then(() => {
          alert("회원가입 완료");
          router.push({ name: "Login" });
        })
        .catch(({ error }) => {
          console.log("catch:");
          console.log(error);
        });
    },
  },
};
</script>

<style></style>
