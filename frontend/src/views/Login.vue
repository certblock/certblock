<template>
  <div class="row justify-content-center">
    <div class="col-lg-5 col-md-7">
      <div class="card bg-secondary shadow border-0">
        <div class="card-body px-lg-5 py-lg-5">
          <div class="text-center text-muted mb-4">
            <h1>Login</h1>
          </div>
          <form v-on:submit.prevent>
            <base-input
              type="email"
              name="email"
              id="email"
              v-model="loginuser.email"
              placeholder="이메일"
            /><br />
            <base-input
              type="password"
              name="password"
              id="password"
              v-model="loginuser.password"
              placeholder="비밀번호"
              autocomplete="off"
            /><br />

            <base-checkbox class="custom-control-alternative">
              <span class="text-muted">Remember me</span>
            </base-checkbox>
            <br />
            <div class="text-center">
              <base-button outline type="primary">
                <router-link to="/signup" class="text-light">
                  회원가입
                </router-link>
              </base-button>

              <button class="btn btn-primary" @click="check()">로그인</button>
            </div>
          </form>
        </div>
      </div>
      <div class="row mt-3">
        <div class="col-6">
          <a href="#" class="text-light"><small>Forgot password?</small></a>
        </div>
        <div class="col-6 text-right">
          <router-link to="/signup" class="text-light"
            ><small>Create new account</small></router-link
          >
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapActions } from "vuex";
export default {
  data() {
    return {
      loginuser: {
        email: "",
        password: "",
      },
    };
  },
  computed: {},
  methods: {
    ...mapActions(["login"]),
    check() {
      if (!this.validEmail(this.loginuser.email)) {
        alert("이메일 형식을 확인하세요.");
      } else if (this.loginuser.password == "") {
        alert("비밀번호를 입력하세요.");
      } else {
        this.login(this.loginuser);
      }
    },
    validEmail: function (email) {
      var re =
        /^(([^<>()\\[\]\\.,;:\s@"]+(\.[^<>()\\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
  },
};
</script>
<style></style>
