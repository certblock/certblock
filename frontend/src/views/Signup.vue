<template>
  <div class="row justify-content-center">
    <div class="col-lg-5 col-md-7">
      <div class="card bg-secondary shadow border-0">
        <div class="card-body px-lg-5 py-lg-5">
          <div class="text-center text-muted mb-4">
            <h1>Sign up</h1>
          </div>

          <base-input
            type="text"
            name="name"
            id="name"
            v-model="name"
            placeholder="이름"
          /><br />
          <base-input
            type="text"
            name="phone"
            id="phone"
            v-model="phone"
            placeholder="전화번호"
          /><br />
          <base-input
            type="date"
            name="birth"
            id="birth"
            v-model="birth"
          /><br />
          <base-input
            type="email"
            name="email"
            id="email"
            v-model="email"
            placeholder="이메일"
          /><br />
          <base-input
            type="password"
            name="pass"
            id="pass"
            v-model="pass"
            placeholder="비밀번호"
          /><br />

          <div class="container ct-example-row">
            <div class="row">
              <div class="col-sm">
                <base-radio id="university" value="1" v-model="type"
                  >학교</base-radio
                >
              </div>
              <div class="col-sm">
                <base-radio id="student" value="2" v-model="type" checked
                  >학생</base-radio
                >
              </div>
              <div class="col-sm">
                <base-radio id="company" value="3" v-model="type"
                  >기업</base-radio
                >
              </div>
            </div>
          </div>
          <br />

          <div class="text-center">
            <base-button outline type="primary">
              <router-link to="/login" class="text-light"> 로그인 </router-link>
            </base-button>

            <button
              class="btn btn-primary"
              @click="regist({ name, phone, birth, email, pass, type })"
            >
              회원가입
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
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
        .post(`https://j5a507.p.ssafy.io/api/users`, data)
        .then(() => {
          alert("회원가입 완료");
          router.push({ name: "Login" });
        })
        .catch((error) => {
          console.log("catch:");
          console.log(error);
        });
      console.log(data);
    },
  },
};
</script>
<style></style>
