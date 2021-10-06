<template>
  <div class="row justify-content-center">
    <div class="col-lg-5 col-md-7">
      <div class="card bg-secondary shadow border-0">
        <div class="card-body px-lg-5 py-lg-5">
          <div class="text-center text-muted mb-4">
            <h1>본인 인증</h1>
          </div>

          <div class="col">
            <base-input
              type="text"
              name="name"
              id="name"
              v-model="signup.name"
              placeholder="이름"
            />
            <base-input
              type="text"
              name="phone"
              id="phone"
              v-model="signup.phone"
              placeholder="전화번호"
            />
            <base-input
              type="date"
              name="birth"
              id="birth"
              v-model="signup.birth"
            />

            <!-- 본인인증 전 -->
            <div v-if="!certFlg">
              <div class="text-center">
                <base-button outline type="primary">
                  <router-link to="/login" class="text-light">
                    로그인
                  </router-link>
                </base-button>
                <base-button @click="sendMsg()"> 문자 인증 받기 </base-button>
              </div>
            </div>

            <div v-else>
              <div class="row text-center">
                <div class="col-5">
                  <base-input
                    type="text"
                    name="inputNum"
                    id="inputNum"
                    v-model="inputNum"
                    placeholder="인증번호 입력"
                  />
                </div>

                <div class="col-7">
                  <base-button size="md" @click="confirmNum()"
                    >인증</base-button
                  >
                  <base-button size="md" @click="sendMsg()">재전송</base-button>
                </div>
              </div>

              <br />
              <div class="text-center">
                <base-button outline type="primary">
                  <router-link to="/login" class="text-light">
                    로그인
                  </router-link>
                </base-button>
                <!-- <base-button type="primary">
                  <router-link to="/signup" class="text-light">
                    회원가입하기
                  </router-link>
                </base-button> -->
              </div>
            </div>
          </div>
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
  components: {},
  data() {
    return {
      inputNum: "",
      certNum: "",
      certFlg: false,
    };
  },
  computed: { ...mapState(["signup"]) },
  methods: {
    sendMsg() {
      this.certFlg = true;
      axios
        .get(`https://j5a507.p.ssafy.io/api/users/auth/${this.signup.phone}`)
        .then((res) => {
          this.certNum = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    confirmNum() {
      if (this.inputNum == this.certNum) {
        router.push({ name: "signup" });
      } else alert("인증번호를 다시 입력해주세요.");
    },
  },
};
</script>
<style></style>
