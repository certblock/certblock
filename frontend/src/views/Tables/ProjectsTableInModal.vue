<template>
  <div class="card shadow" :class="type === 'dark' ? 'bg-default' : ''">
    <div
      class="card-header border-0"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
      {{ user.name }}님의 발급된 증명서 목록
    </div>

    <div class="row justify-content-md-center">
      <div class="col-xl-12 col-lg-12">
        <div v-for="(item, index) in certificate" :key="index">
          <div v-if="certificate[index].flg == true" class="row">
            <div class="col-xl-12 col-lg-12">
              <stats-card>
                <div class="row">
                  <div
                    class="
                      row
                      align-items-center
                      col-xl-4
                      justify-content-md-center
                    "
                  >
                    <!-- 발급 대학교 -->
                    <h3
                      class="mb-0"
                      :class="type === 'dark' ? 'text-white' : ''"
                    >
                      <div v-for="(item, idx) in inuniv" :key="idx">
                        <div
                          v-if="
                            inuniv[idx].universityId ==
                            certificate[index].universityId
                          "
                        >
                          {{ inuniv[idx].universityName }}
                        </div>
                      </div>
                    </h3>
                    &nbsp;
                    <!-- 발급 증명서 -->
                    <div
                      v-if="
                        certificate[index].type == 1 ||
                        certificate[index].type == 3 ||
                        certificate[index].type == 5
                      "
                    >
                      졸업증명서
                    </div>
                    <div v-else>성적증명서</div>
                  </div>
                  <div
                    class="
                      row
                      align-items-center
                      col-xl-8
                      justify-content-md-center
                      text-right
                    "
                  >
                    발급일 &nbsp;{{
                      certificate[index].date.substring(0, 10)
                    }}&nbsp;&nbsp;
                    <base-button @click="showImage(index)"
                      >증명서 이미지 보기</base-button
                    >
                    <modal v-model:show="modals.modal0">
                      <div class="modal-all">
                        <img :src="this.imageSrc" class="certImage" />
                      </div>
                    </modal>
                    <input
                      type="checkbox"
                      class="mb-3"
                      v-model="selectCert"
                      v-bind:value="certificate[index].certificateId"
                    />
                  </div>
                </div>
              </stats-card>
            </div>
          </div>
        </div>
      </div>
      <a style="padding: 10px">제출된 증명서는 SSAFY소프트웨어에서 6개월간 열람 가능합니다.</a>
    </div>
    <base-button @click="submit()">제출하기</base-button>
    <div
      class="card-footer d-flex justify-content-end"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    ></div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  components: {},
  name: "projects-table",
  props: ["univarrnum", "user", "inuniv", "certificate"],
  watch: {
    univarrnum: function (val) {
      this.updatecertnum(val);
    },
  },
  data() {
    return {
      inCert: [],
      certnum: this.univarrnum * 2,
      modals: {
        modal0: false,
      },
      imageSrc: "",
      submits: {},
      selectCert: [],
      sendType: [],
    };
  },
  methods: {
    showImage(num) {
      axios
        .get(
          `https://j5a507.p.ssafy.io/api/certificate/${this.$props.certificate[num].certificateId}`
        )
        .then((res) => {
          this.imageSrc = res.data.message;
          this.modals.modal0 = true;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    submit() {
      for (let index in this.selectCert) {
        for (let idx in this.certificate) {
          if (this.certificate[idx].certificateId == this.selectCert[index])
            this.sendType.push(this.certificate[idx].type);
        }
      }
      axios
        .post(
          `https://j5a507.p.ssafy.io/api/companies/20/certificates`,
          this.selectCert
        )
        .then((res) => {
          alert("제출이 완료되었습니다.");
          this.$emit("submit", this.sendType);
          console.log(res);
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
