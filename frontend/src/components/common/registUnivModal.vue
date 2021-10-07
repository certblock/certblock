<template>
  <div class="row">
    <div class="col-lg-10 search">
      <div class="row">
        <div class="col-lg-3">학교</div>
        <div class="col-lg-1 input">
          <!-- <base-input
            alternative=""
            input-classes="form-control-alternative"
            placeholder="학교 검색"
            v-model="univ"
          /> -->
          <base-dropdown>
            <template v-slot:title>
              <base-button
                type="secondary"
                class="dropdown-toggle"
                v-model="selectUniv"
              >
                {{ this.selectUniv }}
              </base-button>
            </template>
            <div
              v-for="(item, index) in univ"
              :key="index"
              @click="changeUniv(item.universityId, item.name)"
            >
              <a class="dropdown-item">{{ item.name }}</a>
            </div>
          </base-dropdown>
        </div>
      </div>
      <br />
      <div class="row">
        <div class="col-lg-3">학번</div>
        <div class="col-lg-9 input">
          <base-input
            alternative=""
            placeholder="학번"
            input-classes="form-control-alternative"
            v-model="studentIdInUniv"
          />
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3">졸업</div>
        <div class="col-lg-9 input">
          <input type="radio" id="1" value="1" v-model="type" checked />
          <label for="1">학사</label>
          <input type="radio" id="2" value="2" v-model="type" />
          <label for="2">석사</label>
          <input type="radio" id="3" value="3" v-model="type" />
          <label for="3">박사</label>
        </div>
      </div>
    </div>

    <div class="col-lg-1">
      <base-button
        type="primary"
        class="mr-2"
        @click="regist(universityId, { type, studentIdInUniv })"
        >인증 하기</base-button
      >
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapActions, mapState } from "vuex";
export default {
  name: "regist-univ",
  data() {
    return {
      universityId: null,
      type: 1,
      studentIdInUniv: "",
      selectUniv: "학교 검색",
      univ: [],
    };
  },
  computed: {
    ...mapState(["user"]),
  },
  methods: {
    ...mapActions(["studentinuniv", "getcertificate"]),
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
    async regist(universityId, data) {
      await axios
        .post(
          `https://j5a507.p.ssafy.io/api/students/${this.user.id}/universities/${universityId}`,
          data
        )
        .then(() => {
          alert("등록 완료");
          this.studentinuniv(this.user.id);
          this.getcertificate(this.user.id);
        })
        .catch(() => {
          alert("해당 대학의 졸업생이 아니거나 이미 등록되어 있습니다.");
        });
    },
    changeUniv(universityId, selectUniv) {
      this.universityId = universityId;
      this.selectUniv = selectUniv;
    },
  },
  mounted() {
    this.getuniv();
  },
};
</script>
<style>
.search * {
  align-items: center;
}

.input {
  align-items: center;
  padding: 0;
}
.mr-4 {
  height: 100%;
}
</style>
