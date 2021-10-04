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
              <a>{{ certificate[certnum].hash }}</a>&nbsp;
              <base-button size="sm" @click="doCopy()">
                Copy!
              </base-button>
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
              <a>{{ certificate[certnum + 1].hash }}</a>&nbsp;
              <base-button size="sm"
                @click="doCopy1()"
              >
                Copy!
              </base-button>
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
    };
  },
  methods: {
    updatecertnum(univarrnum) {
      this.certnum = univarrnum * 2;
    },
    doCopy() {
      alert(this.certificate[this.certnum].hash);
      this.$copyText(this.certificate[this.certnum].hash);
      alert("복사했습니다.");
    },
    doCopy1() {
      this.$copyText(this.certificate[this.certnum + 1].hash);
      alert("복사했습니다.");
    },
  },
};
</script>
<style></style>
