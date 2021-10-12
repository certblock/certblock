<template>
  <div class="card shadow" :class="type === 'dark' ? 'bg-default' : ''">
    <div
      class="card-header border-0"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
      <div class="row align-items-center">
        <div class="col">
          <h3
            v-if="inuniv.length != 0"
            class="mb-0"
            :class="type === 'dark' ? 'text-white' : ''"
          >
            {{ inuniv[univarrnum].universityName }} 증명서 발급 목록 
          </h3>
        </div>
      </div>
    </div>

    <div class="row justify-content-md-center">
      <div class="col-xl-12 col-lg-12">
        <div v-for="(item, index) in certificate" :key="index">
          <stats-card
            v-if="item.universityId == inuniv[univarrnum].universityId"
          >
            <div class="row" v-if="item.flg">
              <div class="col-md-7 text-primary">
                <div v-if="item.type == 1 || item.type == 3 || item.type == 5">
                  <badge type="primary"><i class="ni ni-hat-3"></i></badge
                  ><b> 졸업증명서</b>
                </div>
                <div v-else class="text-primary">
                  <badge type="primary"
                    ><i class="ni ni-paper-diploma"></i></badge
                  ><b> 성적증명서</b>
                </div>
              </div>
              <div class="col-md-5 text-right">
                발급일: {{ item.date.substring(0, 10) }}
                <base-button @click="showImage(item.certificateId)"
                  >증명서 이미지 보기</base-button
                >
                <modal v-model:show="modals.modal0" modal-classes="modal-xl">
                  <div class="modal-all">
                    <img :src="this.imageSrc" class="certImage" />
                  </div>
                </modal>
              </div>
            </div>
            <div class="row" v-else>
              <div class="col-md-3">
                <div  v-if="item.type == 1 || item.type == 3 || item.type == 5">
                  <badge type="default"><i class="ni ni-hat-3"></i></badge
                  ><b> 졸업증명서</b>
                </div>
                <div  v-else>
                  <badge type="default"
                    ><i class="ni ni-paper-diploma"></i></badge
                  ><b> 성적증명서</b>
                </div>
              </div>
              <div class="col">
                <base-button @click="Issuedcert(item.certificateId)"
                  >발급받기</base-button
                >
              </div>
              <div class="col"></div>
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
import { mapActions, mapState } from "vuex";
import axios from "axios";

export default {
  components: {},
  name: "projects-table",
  props: ["univarrnum"],
  computed: {
    ...mapState(["user", "inuniv", "certificate"]),
  },
  data() {
    return {
      inCert: [],
      modals: {
        modal0: false,
      },
      imageSrc: "",
    };
  },
  methods: {
    ...mapActions(["getcertificate"]),
    showImage(num) {
      axios
        .get(`https://j5a507.p.ssafy.io/api/certificate/${num}`)
        .then((res) => {
          console.log(res);
          this.imageSrc = res.data.message;
          this.modals.modal0 = true;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async Issuedcert(num) {
      await axios
        .put(`https://j5a507.p.ssafy.io/api/certificate/${num}`)
        .then(() => {
          for (var i in this.certificate) {
            if (this.certificate[i].certificateId == num)
              this.getcertificate(this.certificate[i].studentId);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {},
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
