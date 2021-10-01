<template>
  <h1>증명서</h1>
  <button
    v-for="(item, index) in inuniv"
    :key="index"
    @click="univarrnum = index"
  >
    {{ item.universityName }}
  </button>
  <button
    @click="
      if (univ == null) getuniv();
      add();
    "
  >
    {{ this.click ? "-" : "+" }}</button
  ><br />
  <certificate-in-univ :univarrnum="this.univarrnum" />
  <add-univ v-if="click" :univ="this.univ" />
</template>

<script>
import axios from "axios";
import AddUniv from "../components/AddUniv";
import CertificateInUniv from "../components/CertificateInUniv";
import { mapState } from "vuex";
export default {
  components: {
    AddUniv,
    CertificateInUniv,
  },

  data() {
    return {
      click: false,
      univ: null,
      univarrnum: 0,
    };
  },

  computed: {
    ...mapState(["inuniv"]),
  },

  methods: {
    async getuniv() {
      await axios
        .get(`https://j5a507.p.ssafy.io/api/universities`)
        .then(({ data }) => {
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
