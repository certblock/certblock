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
            {{ inuniv[univarrnum].universityName }} 증명서 목록
          </h3>
        </div>
      </div>
    </div>

    <div class="row justify-content-md-center">
      <div class="col-xl-12 col-lg-12">
        <stats-card>
          <div class="row" v-if="certificate[certnum].hash != null">
            <div class="col-md-3">
              <badge type="primary"><i class="ni ni-app"></i></badge>
              <span class="text-primary">졸업 증명서</span>
            </div>
            <div class="col">
              <a>{{ certificate[certnum].hash }}</a
              >&nbsp;
              <base-button size="sm" @click="doCopy()"> Copy! </base-button>
              <base-button @click="showImage()">증명서 이미지 보기</base-button>
               <modal v-model:show="modals.modal0">
                 <div class="modal-all">
                  <img :src="this.imageSrc" class="certImage"/>
                 </div>
              </modal>
            </div>
            <div class="col"></div>
          </div>
          <div class="row" v-else>
            <div class="col-md-3">
              <badge type="secondary"><i class="ni ni-app"></i></badge>
              <span class="text-default">졸업 증명서</span>
            </div>
            <div class="col">
              <a>
                <base-button>발급받기</base-button>
              </a>
            </div>
            <div class="col"></div>
          </div>
        </stats-card>
      </div>
      <div class="col-xl-12 col-lg-12">
        <stats-card>
          <div class="row" v-if="certificate[certnum + 1].hash != null">
            <div class="col-md-3">
              <badge type="primary"><i class="ni ni-app"></i></badge>
              <span class="text-primary">성적 증명서</span>
            </div>
            <div class="col">
              <a>{{ certificate[certnum + 1].hash }}</a
              >&nbsp;
              <base-button size="sm" @click="doCopy1()"> Copy! </base-button>
              <base-button @click="showImage1()">증명서 이미지 보기</base-button>
               <modal v-model:show="modals.modal0">
                 <div class="modal-all">
                  <img :src="this.imageSrc" class="certImage"/>
                 </div>
              </modal>
            </div>
            <div class="col"></div>
          </div>
          <div class="row" v-else>
            <div class="col-md-3">
              <badge type="secondary"><i class="ni ni-app"></i></badge>
              <span class="text-default">졸업 증명서</span>
            </div>
            <div class="col">
              <a>
                <base-button>발급받기</base-button>
              </a>
            </div>
            <div class="col"></div>
          </div>
        </stats-card>
      </div>
    </div>

    <div
      class="card-footer d-flex justify-content-end"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
      <base-pagination total="30"></base-pagination>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import axios from "axios";

export default {
  components: {},
  name: "projects-table",
  props: {
    univarrnum: Number,
  },
  watch: {
    univarrnum: function (val) {
      this.updatecertnum(val);
    },
  },
  computed: {
    ...mapState(["user", "inuniv", "certificate"]),
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
    updatecertnum(univarrnum) {
      this.certnum = univarrnum * 2;
    },
    doCopy() {
      console.log(this.certificate[this.certnum]);
      alert(this.certificate[this.certnum].hash);
      this.$copyText(this.certificate[this.certnum].hash);
      alert("복사했습니다.");
    },
    doCopy1() {
      alert(this.certificate[this.certnum + 1].hash);
      this.$copyText(this.certificate[this.certnum + 1].hash);
      alert("복사했습니다.");
    },
    showImage() {
      axios
        .get(
          `https://j5a507.p.ssafy.io/api/certificate/${
            this.certificate[this.certnum].certificateId
          }`
        )
        .then((res) => {
          console.log(res.data.message);
          this.imageSrc = res.data.message;
          this.modals.modal0 = true;
        })
        .catch((error) => {
          console.log(error);
        });
    },
     showImage1() {
      axios
        .get(
          `https://j5a507.p.ssafy.io/api/certificate/${
            this.certificate[this.certnum+1].certificateId
          }`
        )
        .then((res) => {
          console.log(res.data.message);
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
