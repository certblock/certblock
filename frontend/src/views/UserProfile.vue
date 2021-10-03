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
      <card shadow type="secondary">
        <div class="row">
          <div
            v-for="(item, index) in inuniv"
            :key="index"
            @click="univarrnum =index"
            class="col-md-3"
          >
            <base-button class="col-md-12">
              {{ item.universityName }}
            </base-button>
          </div>

          <base-button @click="add()" class="col-md-1">
            {{ this.click ? "-" : "+" }}</base-button
          ><br />
        </div>

        <projects-table
          :univarrnum="this.univarrnum"
          title="Light Table"
        ></projects-table>
        <add-univ v-if="click" :univ="this.univ" />
      </card>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import registUniv from "../components/common/registUnivModal.vue";
import axios from "axios";
import AddUniv from "../components/AddUniv";
import ProjectsTable from "./Tables/ProjectsTable";

export default {
  name: "user-profile",
  components: {
    registUniv,
    AddUniv,
    ProjectsTable,
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
      click: false,
      univ: null,
      univarrnum: 0,
    };
  },
  computed: {
    ...mapState(["user"]),
    ...mapState(["inuniv"]),
  },
  methods: {
    async getuniv() {
      await axios
        .get(`https://j5a507.p.ssafy.io/api/universities`)
        .then(({ data }) => {
          console.log(data);
          this.univ = data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    add() {
      this.click = !this.click;
    },
  },
};
</script>
<style></style>
