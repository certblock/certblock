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
            <div class="row justify-content-center">
              <div class="col-md-3">
                <input type="radio" id="university" v-model="type" value="1" />
                <label for="html">학교</label>
              </div>
              <div class="col-md-3">
                <input
                  type="radio"
                  id="student"
                  v-model="type"
                  value="2"
                  checked
                />
                <label for="html">학생</label>
              </div>
              <div class="col-md-3">
                <input type="radio" id="company" v-model="type" value="3" />
                <label for="html">기업</label>
              </div>
            </div>
          </div>

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
  head: {
    script: [
      { src: "https://cdn.iamport.kr/js/iamport.payment-1.2.0.js" },
      { src: "https://code.jquery.com/jquery-1.12.4.min.js" },
    ],
  },
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
