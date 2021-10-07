<template>
  <div class="card shadow" :class="type === 'dark' ? 'bg-default' : ''">
    <div
      class="card-header border-0"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
      <div class="row align-items-center">
        <div class="col">
          <h3 class="mb-0" :class="type === 'dark' ? 'text-white' : ''">
            증명서 발급 현황
          </h3>
        </div>
      </div>
    </div>

    <div class="col justify-content-md-right align-items-center">
      <div v-for="(item, index) in certificate" :key="index">
        <div class="col-xl-12 col-lg-12 ">
          <stats-card>
             <div class="row align-items-center justify-content-md-left">
            <div class="col-md-3">
              <span class="text-default"
                >지원자 {{ certificate[index].id }}</span
              >
            </div>
            <div class="col-md-5">
              <div v-if="certificate[index].certType == 1">
                <span class="text-primary">졸업 증명서</span>
              </div>
              <div v-else>
                <span class="text-primary">성적 증명서</span>
              </div>
            </div>
            <div class="col-md-4">
            <div class="row align-items-center">
              발급일: {{ certificate[index].createdTime.substring(0, 10) }}&nbsp;&nbsp;&nbsp;
              <base-button @click="showImage(index)">증명서 이미지 보기</base-button>
              <modal v-model:show="modals.modal0">
                <div class="modal-all">
                  <img :src="this.imageSrc" class="certImage" />
                </div>
              </modal>
            </div>
            </div>
            </div>
          </stats-card>
        </div>
      </div>
    </div>

    <div
      class="card-footer d-flex justify-content-end"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    ></div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import axios from "axios";

export default {
  components: {},
  name: "school-table",
  props: {},
  computed: {
    ...mapState(["certificate","inuniv"]),
  },
  data() {
    return {
      inCert: [],
      certnum: this.univarrnum * 2,
      modals: {
        modal0: false,
      },
      imageSrc: "",
    };
  },
  methods: {
    showImage(num) {
      axios
        .get(
          `https://j5a507.p.ssafy.io/api/certificate/${this.certificate[num].certId}`
        )
        .then((res) => {
          this.imageSrc = res.data.message;
          this.modals.modal0 = true;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
<style>
.modal-all {
  width: 100%;
  height: 100%;
  overflow: hidden;
  margin: 0 auto;
}
.certImage {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
