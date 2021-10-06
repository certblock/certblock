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
            v-model="signup.name"
            placeholder="이름"
            disabled="true"
          /><br />
          <base-input
            type="text"
            name="phone"
            id="phone"
            v-model="signup.phone"
            placeholder="전화번호"
            disabled="true"
          /><br />
          <base-input
            type="date"
            name="birth"
            id="birth"
            v-model="signup.birth"
            disabled="true"
          /><br />
          <form v-on:submit.prevent>
            <base-input
              type="email"
              name="email"
              id="email"
              v-model="signup.email"
              placeholder="이메일"
              @focus="availableemail = false"
            /><br />
            <base-input
              type="password"
              name="pass"
              id="pass"
              v-model="signup.pass"
              placeholder="비밀번호"
              autocomplete="off"
            /><br />
            <base-input
              type="password"
              name="passconfirm"
              id="passconfirm"
              v-model="passconfirm"
              placeholder="비밀번호확인"
              autocomplete="off"
            /><br />

            <div class="container ct-example-row">
              <div class="row justify-content-center">
                <div class="col-md-3">
                  <input
                    type="radio"
                    id="university"
                    v-model="signup.type"
                    value="1"
                  />
                  <label for="university">학교</label>
                </div>
                <div class="col-md-3">
                  <input
                    type="radio"
                    id="student"
                    v-model="signup.type"
                    value="2"
                    checked
                  />
                  <label for="student">학생</label>
                </div>
                <div class="col-md-3">
                  <input
                    type="radio"
                    id="company"
                    v-model="signup.type"
                    value="3"
                  />
                  <label for="company">기업</label>
                </div>
              </div>
            </div>

            <div class="text-center">
              <base-button outline type="primary">
                <router-link to="/login" class="text-light">
                  로그인
                </router-link>
              </base-button>

              <button class="btn btn-primary" @click="registready()">
                회원가입
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "../router";
import { mapState } from "vuex";

export default {
  head: {
    script: [
      { src: "https://cdn.iamport.kr/js/iamport.payment-1.2.0.js" },
      { src: "https://code.jquery.com/jquery-1.12.4.min.js" },
    ],
  },
  data() {
    return {
      passconfirm: "",
    };
  },
  computed: { ...mapState(["signup"]) },
  methods: {
    registready() {
      if (this.checkemail()) {
        if (this.checkpass()) {
          if (this.checkpassconfirm()) {
            this.reduplication();
          }
        }
      }
    },
    checkemail() {
      if (this.signup.email == "") {
        alert("이메일을 입력하세요.");
        return false;
      } else if (!this.validEmail()) {
        alert("이메일 형식을 확인하세요.");
        return false;
      } else {
        return true;
      }
    },
    validEmail() {
      var re =
        /^(([^<>()\\[\]\\.,;:\s@"]+(\.[^<>()\\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(this.signup.email);
    },
    async reduplication() {
      await axios
        .get(`https://j5a507.p.ssafy.io/api/users/email/${this.signup.email}`)
        .then(({ data }) => {
          if (data) {
            this.regist(this.signup);
          } else {
            alert("이미 사용중인 이메일입니다.");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    checkpass() {
      var pw = this.signup.pass;
      var num = pw.search(/[0-9]/g);
      var eng = pw.search(/[a-z]/gi);
      var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

      if (pw.length < 8 || pw.length > 20) {
        alert("비밀번호는 8자리 ~ 20자리 이내로 입력해주세요.");
        return false;
      } else if (pw.search(/\s/) != -1) {
        alert("비밀번호는 공백 없이 입력해주세요.");
        return false;
      } else if (num < 0 || eng < 0 || spe < 0) {
        alert("비밀번호는 영문,숫자, 특수문자를 혼합하여 입력해주세요.");
        return false;
      } else {
        return true;
      }
    },
    checkpassconfirm() {
      if (this.signup.pass != this.passconfirm) {
        alert("비밀번호 확인이 일치하지 않습니다.");
        return false;
      } else {
        return true;
      }
    },
    regist(data) {
      axios
        .post(`https://j5a507.p.ssafy.io/api/users`, data)
        .then(() => {
          alert("회원가입 완료");
          router.push({ name: "login" });
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
