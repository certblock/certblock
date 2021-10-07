<template>
  <div>
    <base-header
      class="header pb-8 pt-5 pt-lg-12 d-flex align-items-center"
      style="
        min-height: 600px;
        background-image: url(img/theme/profile-cover.png);
        background-size: cover;
        background-position: center top;
      "
    >
      <!-- Mask -->
      <span class="mask bg-gradient-warning opacity-8"></span>
      <!-- Header container -->
      <div class="container-fluid align-items-center">
        <div class="row justify-content-md-center">
          <div class="col-lg-2 col-md-10">
            <h1 class="display-2 text-white">지원하기</h1>
            <p class="text-white mt-0 mb-5">
              안녕하세요, 반갑습니다.<br />
              지원해주셔서 감사합니다.
            </p>
          </div>
        </div>
      </div>
    </base-header>

    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col-xl-12 order-xl-1">
          <card shadow type="secondary">
            <template v-slot:header>
              <div class="bg-white border-0">
                <div class="row align-items-center">
                  <div class="col-8">
                    <h3 class="mb-0">지원서 작성</h3>
                  </div>
                </div>
              </div>
            </template>

            <form>
              <h6 class="heading-small text-muted mb-4">개인 정보</h6>
              <div class="pl-lg-4">
                <div class="row">
                  <div class="col-lg-6">
                    <base-input
                      alternative=""
                      label="이름"
                      placeholder="이름"
                      input-classes="form-control-alternative"
                      v-model="model.username"
                    />
                  </div>
                  <div class="col-lg-6">
                    <base-input
                      alternative=""
                      label="이메일"
                      placeholder="certblock@example.com"
                      input-classes="form-control-alternative"
                      v-model="model.email"
                    />
                  </div>
                </div>

                <div class="row">
                  <div class="col-lg-6">
                    <base-input
                      type="date"
                      alternative=""
                      label="생년월일"
                      input-classes="form-control-alternative"
                      v-model="model.birth"
                    />
                  </div>
                  <div class="col-lg-6">
                    <base-input
                      alternative=""
                      label="전화번호"
                      placeholder="전화번호"
                      input-classes="form-control-alternative"
                      v-model="model.phone"
                    />
                  </div>
                </div>

                <div class="row">
                  <div class="col-lg-4">
                    <base-button class="col-lg-12"
                      >자기 소개서 작성하기</base-button
                    >
                  </div>
                  <div class="col-lg-4">
                    <base-button class="col-lg-12"
                      >경력 기술서 작성하기</base-button
                    >
                  </div>
                  <div class="col-lg-4">
                    <base-button class="col-lg-12"
                      >어학 및 자격증 등록하기</base-button
                    >
                  </div>
                </div>
              </div>

              <!-- 증명서 제출 구간 -->
              <hr class="my-4" />
              <!-- Address -->
              <h6 class="heading-small text-muted mb-4">증명서 제출</h6>
              <div class="pl-lg-4">
                <div class="row">
                  <div class="col-lg-4 text-center">
                    <h4>포트폴리오</h4>
                    <base-button class="col-lg-12">제출하기</base-button>
                  </div>
                  
                  <div class="col-lg-4 text-center">
                    <h4>졸업증명서</h4>
                    <div v-if="submitStatus.includes(1)">
                       <base-input
                      placeholder="제출완료입니다."
                      :valid="true"
                      disabled
                      formClasses="input-group-alternative"
                    ></base-input>
                    </div>
                    <div v-else>
                    <base-input
                      placeholder="현재 미제출상태입니다. 제출해주세요."
                      disabled
                    ></base-input>
                    </div>
                  </div>
                   <div class="col-lg-4 text-center">
                    <h4>성적증명서</h4>
                    <div v-if="submitStatus.includes(2)">
                       <base-input
                      placeholder="제출완료입니다."
                      :valid="true"
                      disabled
                      formClasses="input-group-alternative"
                    ></base-input>
                    </div>
                    <div v-else>
                    <base-input
                      placeholder="현재 미제출상태입니다. 제출해주세요."
                      disabled
                    ></base-input>
                    </div>
                  </div>
                </div>

                <hr class="my-4" />
                <div class="row">
                  <div class="col-6 col-md-4">
                    <span></span>
                  </div>
                  <div class="col-6 col-md-8">
                    <span>
                      <div class="pl-lg-6">
                        <base-button @click="showModal()"
                          >Certblock으로 증명서 제출</base-button
                        >
                        <base-button type="warning">지원하기</base-button>

                        <modal v-model:show="modals.modal0">
                          <template v-slot:header> </template>
                          <apply-login
                            v-on:certLogin="changeStatus"
                          ></apply-login>
                          <template v-slot:footer>
                            <base-button
                              type="secondary"
                              @click="modals.modal0 = false"
                              >Close</base-button
                            >
                          </template>
                        </modal>

                        <modal
                          v-model:show="modals.modal1"
                          modal-classes="modal-xl"
                        >
                          <apply-submit
                            v-bind:userInfo="this.info"
                             v-on:certSubmit="submitComplete"
                          ></apply-submit>
                        </modal>
                      </div>
                    </span>
                  </div>
                  <div class="col-6 col-md-4">
                    <span></span>
                  </div>
                </div>
              </div>
            </form>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import ApplyLogin from "../components/Modals/ApplyLogin.vue";
import ApplySubmit from "../components/Modals/ApplySubmit.vue";

export default {
  name: "user-profile",
  components: {
    ApplyLogin,
    ApplySubmit,
  },
  data() {
    return {
      model: {
        username: "",
        email: "",
        birth: "",
        phone: "",
      },
      info: [],
      modals: {
        modal0: false,
        modal1: false,
      },
      modalNum: "modals.modal0",
      loginStatus: false,
      submitStatus:[],
    };
  },
  computed: {
    ...mapState(["user"]),
  },
  methods: {
    showModal() {
      this.modals.modal0 = true;
    },
    changeStatus(info) {
      this.info = info;
      this.modals.modal0 = false;
      this.modals.modal1 = true;
    },
    submitComplete(cert){
      this.submitStatus = cert;
      alert(this.submitStatus);
      this.modals.modal1 = false;
    }
  },
};
</script>
<style></style>
