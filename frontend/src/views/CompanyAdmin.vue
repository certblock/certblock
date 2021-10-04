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
              </div>
              <hr class="my-4" />
              <!-- Address -->
              <h6 class="heading-small text-muted mb-4">증명서 제출</h6>
              <div class="pl-lg-4">
                <div class="row">
                  <div class="col-lg-4">
                    <base-input
                      alternative=""
                      label="자기소개서"
                      placeholder="자기소개서"
                      input-classes="form-control-alternative"
                      v-model="model.city"
                    />
                  </div>
                  <div class="col-lg-4">
                    <base-input
                      alternative=""
                      label="경력기술서"
                      placeholder="경력기술서"
                      input-classes="form-control-alternative"
                      v-model="model.country"
                    />
                  </div>
                  <div class="col-lg-4">
                    <base-input
                      alternative=""
                      label="포트폴리오"
                      placeholder="포트폴리오"
                      input-classes="form-control-alternative"
                      v-model="model.zipCode"
                    />
                  </div>
                </div>

                <div class="row">
                  <div class="col-6 col-md-4">
                    <span></span>
                  </div>
                  <div class="col-6 col-md-8">
                    <span>
                      <div class="pl-lg-6">
                        <base-button @click="modals.modal0 = true"
                          >Certblock으로 증명서 제출</base-button
                        >
                        <base-button type="warning">지원하기</base-button>

                        <modal v-model:show="modals.modal0">
                          <template v-slot:header>
                          </template>
                            <apply-login></apply-login>
                          <template v-slot:footer>
                            <base-button
                              type="secondary"
                              @click="modals.modal0 = false"
                              >Close</base-button
                            >
                          </template>
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

export default {
  name: "user-profile",
  components: {
    ApplyLogin,
  },
  data() {
    return {
      model: {
        username: "",
        email: "",
        firstName: "",
        lastName: "",
        address: "",
        city: "",
        country: "",
        zipCode: "",
        about: "",
      },
      modals: {
        modal0: false,
      },
    };
  },
  computed: {
    ...mapState(["user"]),
  },
};
</script>
<style></style>
