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
                <base-button type="secondary" class="dropdown-toggle" v-model="selectUniv">
                 {{this.selectUniv}}
                </base-button>
              </template>
            <div v-for="(item, index) in univ" :key="index" @click="selectUniv(item.name)">
              <a class="dropdown-item">{{ item.name }}</a>
            </div>
          </base-dropdown>
        </div>
      </div>
<br>
      <div class="row">
        <div class="col-lg-3">학번</div>
        <div class="col-lg-9 input">
          <base-input
            alternative=""
            placeholder="학번"
            input-classes="form-control-alternative"
            v-model="studentId"
          />
        </div>
      </div>
    </div>

    <div class="col-lg-1">
      <base-button type="primary" class="mr-2">인증 하기</base-button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "regist-univ",
  data() {
    return {
      univName: "",
      studentId: "",
      selectUniv:"학교 검색",
      univ: [],
    };
  },
  methods: {
    async getuniv() {
      await axios
        .get(`https://j5a507.p.ssafy.io/api/universities`)
        .then(({ data }) => {
          this.univ = data;
          console.log(this.univ);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    changeUniv(name){
      this.selectUniv=name;
    }
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
