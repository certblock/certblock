<template>
  <div class="row justify-content-center">
    <div class="col-lg-5 col-md-7">
      <div class="card bg-secondary shadow border-0">
        <div class="card-body px-lg-5 py-lg-5">
          <div class="text-center text-muted mb-4">
            <h1>Login</h1>
          </div>
          <!-- <form role="form"> -->
          <base-input
            type="text"
            name="email"
            id="email"
            v-model="email"
            placeholder="이메일"
          /><br />
          <base-input
            type="password"
            name="password"
            id="password"
            v-model="password"
            placeholder="비밀번호"
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

            <button class="btn btn-primary" @click="login({ email, password })">
              로그인
            </button>
          </div>
          <!-- </form> -->
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
