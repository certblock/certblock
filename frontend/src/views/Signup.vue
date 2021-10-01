<template>
  <div class="row justify-content-center">
    <div class="col-lg-5 col-md-7">
      <div class="card bg-secondary shadow border-0">
        <div class="card-body px-lg-5 py-lg-5">
          <div class="text-center text-muted mb-4">
            <h1>Sign up</h1>
          </div>
          <form role="form">
            <!-- <base-input
              formClasses="input-group-alternative mb-3"
              placeholder="Email"
              addon-left-icon="ni ni-email-83"
              v-model="model.email"
            >
            </base-input>

            <base-input
              formClasses="input-group-alternative mb-3"
              placeholder="Password"
              type="password"
              addon-left-icon="ni ni-lock-circle-open"
              v-model="model.password"
            > 
            </base-input> -->

            <base-input
              type="text"
              name="id"
              id="id"
              v-model="id"
              placeholder="id입력"
            />
            <base-input
              type="password"
              name="password"
              id="password"
              v-model="pwd"
              placeholder="password입력"
            />
            <base-checkbox class="custom-control-alternative">
              <span class="text-muted">Remember me</span>
            </base-checkbox>
            <br />
            <div class="text-center">
              <base-button outline type="primary">
                <router-link to="/login" class="text-light">
                  로그인
                </router-link>
              </base-button>

              <base-button type="primary">
                <router-link to="/signup" class="text-light">
                  회원가입
                </router-link>
              </base-button>
            </div>
          </form>
        </div>
      </div>
      <div class="row mt-3">
        <div class="col-6">
          <a href="#" class="text-light"><small>Forgot password?</small></a>
        </div>
        <div class="col-6 text-right">
          <router-link to="/register" class="text-light"
            ><small>Create new account</small></router-link
          >
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "login",
  data() {
    return {
      model: {
        id: "",
        pwd: "",
      },
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
