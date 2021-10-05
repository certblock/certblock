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
            v-model="user.name"
            placeholder="이름"
          /><br />
          <base-input
            type="text"
            name="phone"
            id="phone"
            v-model="user.phone"
            placeholder="전화번호"
          /><br />
          <base-input
            type="date"
            name="birth"
            id="birth"
            v-model="user.birth"
          /><br />
          <form v-on:submit.prevent>
            <base-input
              type="email"
              name="email"
              id="email"
              v-model="user.email"
              placeholder="이메일"
              @focus="availableemail = false"
            /><br />
            <base-input
              type="password"
              name="pass"
              id="pass"
              v-model="user.pass"
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
                    v-model="user.type"
                    value="1"
                  />
                  <label for="university">학교</label>
                </div>
                <div class="col-md-3">
                  <input
                    type="radio"
                    id="student"
                    v-model="user.type"
                    value="2"
                    checked
                  />
                  <label for="student">학생</label>
                </div>
                <div class="col-md-3">
                  <input
                    type="radio"
                    id="company"
                    v-model="user.type"
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

export default {
  head: {
    script: [
      { src: "https://cdn.iamport.kr/js/iamport.payment-1.2.0.js" },
      { src: "https://code.jquery.com/jquery-1.12.4.min.js" },
    ],
  },
  data() {
    return {
      user: {
        email: "",
        name: "",
        phone: "",
        birth: "",
        pass: "",
        type: 2,
      },
      passconfirm: "",
    };
  },
  methods: {
    registready() {
      if (this.checkemail()) {
        if (this.checkpass()) {
          if (this.checkpassconfirm()) {
            this.regist(this.user);
          }
        }
      }
    },
    checkemail() {
      if (this.user.email == "") {
        alert("이메일을 입력하세요.");
        return false;
      } else if (!this.validEmail(this.user.email)) {
        alert("이메일 형식을 확인하세요.");
        return false;
      } else {
        return true;
      }
    },
    validEmail(email) {
      var re =
        /^(([^<>()\\[\]\\.,;:\s@"]+(\.[^<>()\\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    checkpass() {
      var pw = this.user.pass;
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
      if (this.user.pass != this.passconfirm) {
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
    checkIdentity() {
      let wl = window.location,
        //인증 후 callback URL
        returnUrl = `${this.serverHost}/nice/decrypt/data`,
        //callback 후 WAS에서 최종적으로 redirect 시킬 URL(결과 화면)
        redirectUrl = `${wl.protocol}//${wl.host}/result`;

      axios({
        url: `${this.serverHost}/nice/encrypt/data`,
        method: "GET",
        params: { returnUrl, redirectUrl },
        withCredentials: true,
      })
        .then((res) => {
          //encode data;
          let encodeData = res.data;
          document.form_chk.action =
            "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
          document.form_chk.EncodeData.value = encodeData;
          //submit! (본인인증 화면으로 전환)
          document.form_chk.submit();
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
};
</script>
<style></style>
