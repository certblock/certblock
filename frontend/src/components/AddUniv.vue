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
  졸업 :
  <input type="radio" id="1" value="1" v-model="status" checked />
  <label for="1">학사</label>
  <input type="radio" id="2" value="2" v-model="status" />
  <label for="2">석사</label>
  <input type="radio" id="3" value="3" v-model="status" />
  <label for="3">박사</label><br />
  <button @click="regist(universityId, status)">등록</button>
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
      status: 1,
    };
  },

  computed: {
    ...mapState(["user"]),
  },

  methods: {
    ...mapActions(["studentinuniv"]),
    async regist(universityId, status) {
      await axios
        .post(
          `https://j5a507.p.ssafy.io/api/students/${this.user.id}/universities/${universityId}`,
          { type: status }
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
