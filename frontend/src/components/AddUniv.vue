<template>
  <h1>대학등록</h1>
  대학 :
  <select v-model="universityId">
    <option
      v-for="(item, index) in univ"
      :key="index"
      :value="item.universityId"
    >
      {{ item.name }}
    </option></select
  ><br />
  학번 :
  <input
    type="text"
    name="studentIdInUniv"
    id="studentIdInUniv"
    v-model="studentIdInUniv"
  /><br />
  졸업 :
  <input type="radio" id="1" value="1" v-model="type" checked />
  <label for="1">학사</label>
  <input type="radio" id="2" value="2" v-model="type" />
  <label for="2">석사</label>
  <input type="radio" id="3" value="3" v-model="type" />
  <label for="3">박사</label><br />
  <button @click="regist(universityId, { type, studentIdInUniv })">등록</button>
</template>

<script>
import axios from "axios";
import { mapActions, mapState } from "vuex";
export default {
  props: {
    univ: Array,
  },

  data() {
    return {
      universityId: null,
      type: 1,
      studentIdInUniv: "",
    };
  },

  computed: {
    ...mapState(["user"]),
  },

  methods: {
    ...mapActions(["studentinuniv"]),
    async regist(universityId, data) {
      await axios
        .post(
          `https://j5a507.p.ssafy.io/api/students/${this.user.id}/universities/${universityId}`,
          data
        )
        .then(() => {
          alert("등록 완료");
          this.studentinuniv(this.user.id);
        })
        .catch(() => {
          alert("해당 대학의 졸업생이 아니거나 이미 등록되어 있습니다.");
        });
    },
  },
};
</script>

<style></style>
