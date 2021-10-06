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
          <div class="col-lg-4 col-md-10">
            <h1 class="display-2 text-white text-center">CERTBLOCK</h1> 
             <h1 class="display-2 text-white text-center">"{{ userInfo.name }}"</h1> 
            <p class="text-white mt-0 mb-5 text-center">
              증명서 불러오기 버튼을 클릭해 <br>발급된 증명서를 불러오세요
            </p>
            <div class="d-flex justify-content-center">
            <base-button @click="receive()" class="justify-content-center" type="secondary">증명서 불러오기</base-button>
            </div>
          </div>
        </div>
      </div>
    </base-header>
    
    <div class="container-fluid mt--9">
      <card shadow type="secondary">
 
        <projects-table 
          :user="this.userInfo"
          :certificate="this.certificate"
          :univarrnum="this.univarrnum"
          :inuniv="this.inUniv"
          title="Light Table"
        ></projects-table>
        <add-univ v-if="click" :univ="this.univ" /> 
      </card>
    </div> 
  </div>
</template>
<script>
import axios from "axios";
import ProjectsTable from "../../views/Tables/ProjectsTableInModal.vue"
import BaseButton from '../BaseButton.vue';

export default {
  name: "user-profile",
  components: {
    ProjectsTable,
    BaseButton
  },
  props: ["userInfo"],
  data() {
    return {
      model: {
        username: "",
        email: "",
        birth: "",
        phone: "",
      },
      inUniv: [],
      certificate:[],
      modals: {
        modal0: false,
      },
      click: false,
      univ: null,
      univarrnum: 0,
    };
  },
  computed: {},
  methods: {
    add() {
      this.click = !this.click;
    },
    receive(){
      axios
      .get(
        `https://j5a507.p.ssafy.io/api/students/${this.userInfo.id}/universities`
      )
      .then(({ data }) => {
        this.inUniv = data;
        console.log(this.inUniv);
      })
      .catch((error) => {
        console.log(error);
      }),
      axios
        .get(`https://j5a507.p.ssafy.io/api/students/${this.userInfo.id}/certificates`)
        .then(({ data }) => {
         this.certificate = data;
        })
        .catch((error) => {
          console.log(error);
        });
    }
  },
  mounted() {
  },
};
</script>
<style></style>
