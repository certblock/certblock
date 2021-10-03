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
      <span class="mask bg-gradient-default opacity-8"></span>
      <!-- Header container -->
      <div class="container-fluid align-items-center">
        <div class="row justify-content-md-center">
          <div class="col col-lg-1"></div>
          <div class="col-lg-4 col-md-10">
            <h1 class="display-2 text-white">Hello {{ user.name }}</h1>
            <p class="text-white mt-0 mb-5">
              안녕하세요, 반갑습니다.<br />
              CERTBLOCK에서 쉽게 증명서를 발급해보세요.
            </p>
          </div>

          <div class="col-lg-6 col-md-10">
            <div class="card card-profile shadow">
              <div class="card-body pt-0 pt-md-4">
                <div class="text-right">
                  <h3>
                    Phone
                    <span class="font-weight-light">{{ user.phone }}</span>
                  </h3>
                  <h3>
                    Birth
                    <span class="font-weight-light">{{ user.birth }}</span>
                  </h3>
                  <h3>
                    Email
                    <span class="font-weight-light">{{ user.email }}</span>
                  </h3>
                  <hr class="my-4" />

                  <base-button type="primary" @click="modals.modal0 = true">
                    학교 등록
                  </base-button>
                  <modal v-model:show="modals.modal0">
                    <template v-slot:header>
                      <h5 class="modal-title" id="exampleModalLabel">
                        학교 등록
                      </h5>
                    </template>
                    <div>
                      <regist-univ></regist-univ>
                    </div>
                    <template v-slot:footer>
                      <base-button
                        type="secondary"
                        @click="modals.modal0 = false"
                        >Close</base-button
                      >
                    </template>
                  </modal>
                </div>
              </div>
            </div>
          </div>
          <div class="col col-lg-1"></div>
        </div>
      </div>
    </base-header>

    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col-xl-8 order-xl-1">
          <card shadow type="secondary">
            <template v-slot:header>
              <div class="bg-white border-0">
                <div class="row align-items-center">
                  <div class="col-8">
                    <h3 class="mb-0">My account</h3>
                  </div>
                  <div class="col-4 text-right">
                    <a href="#!" class="btn btn-sm btn-primary">Settings</a>
                  </div>
                </div>
              </div>
            </template>

            <form>
              <h6 class="heading-small text-muted mb-4">User information</h6>
              <div class="pl-lg-4">
                <div class="row">
                  <div class="col-lg-6">
                    <base-input
                      alternative=""
                      label="Username"
                      placeholder="Username"
                      input-classes="form-control-alternative"
                      v-model="model.username"
                    />
                  </div>
                  <div class="col-lg-6">
                    <base-input
                      alternative=""
                      label="Email address"
                      placeholder="jesse@example.com"
                      input-classes="form-control-alternative"
                      v-model="model.email"
                    />
                  </div>
                </div>
                <div class="row">
                  <div class="col-lg-6">
                    <base-input
                      alternative=""
                      label="First name"
                      placeholder="First name"
                      input-classes="form-control-alternative"
                      v-model="model.firstName"
                    />
                  </div>
                  <div class="col-lg-6">
                    <base-input
                      alternative=""
                      label="Last name"
                      placeholder="Last name"
                      input-classes="form-control-alternative"
                      v-model="model.lastName"
                    />
                  </div>
                </div>
              </div>
              <hr class="my-4" />
              <!-- Address -->
              <h6 class="heading-small text-muted mb-4">Contact information</h6>
              <div class="pl-lg-4">
                <div class="row">
                  <div class="col-md-12">
                    <base-input
                      alternative=""
                      label="Address"
                      placeholder="Home Address"
                      input-classes="form-control-alternative"
                      v-model="model.address"
                    />
                  </div>
                </div>
                <div class="row">
                  <div class="col-lg-4">
                    <base-input
                      alternative=""
                      label="City"
                      placeholder="City"
                      input-classes="form-control-alternative"
                      v-model="model.city"
                    />
                  </div>
                  <div class="col-lg-4">
                    <base-input
                      alternative=""
                      label="Country"
                      placeholder="Country"
                      input-classes="form-control-alternative"
                      v-model="model.country"
                    />
                  </div>
                  <div class="col-lg-4">
                    <base-input
                      alternative=""
                      label="Postal code"
                      placeholder="Postal code"
                      input-classes="form-control-alternative"
                      v-model="model.zipCode"
                    />
                  </div>
                </div>
              </div>
              <hr class="my-4" />
              <!-- Description -->
              <h6 class="heading-small text-muted mb-4">About me</h6>
              <div class="pl-lg-4">
                <div class="form-group">
                  <base-input alternative="" label="About Me">
                    <textarea
                      rows="4"
                      class="form-control form-control-alternative"
                      placeholder="A few words about you ..."
                    >
A beautiful Dashboard for Bootstrap 4. It is Free and Open Source.</textarea
                    >
                  </base-input>
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
import registUniv from "../components/common/registUnivModal.vue";

export default {
  name: "user-profile",
  components:{
    registUniv
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
