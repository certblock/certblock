<template>
  <div class="card shadow" :class="type === 'dark' ? 'bg-default' : ''">
    <div
      class="card-header border-0"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
      <div class="col align-items-center">
        <div class="row">
          <div class="col-md-6">
            <h3 class="mb-0" :class="type === 'dark' ? 'text-white' : ''">
              지원자 증명서 제출 현황
            </h3>
          </div>
          <div class="col-md-6">
            <div v-for="(item, index) in title" :key="index" class="text-right">
              <div v-if="item.noticeId == noticeId">
                <h4>
                  공고 마감일 <b>{{ item.endApply }}</b>
                </h4>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row justify-content-md-center">
      <div class="col-xl-12 col-lg-12 col-md-12">
        <div v-for="(item, index) in certificate" :key="index">
          <stats-card v-if="item.noticeId == noticeId">
            <div class="row align-items-center">
              <div class="col-md-4">
                <span class="text-primary"
                  >지원자 <b>{{ item.applicantName }}</b></span
                >
              </div>
              <div class="row col-md-4">
                <div v-if="item.certType == 1">
                  학사 <badge type="default"><i class="ni ni-hat-3"></i></badge
                  ><b> 졸업증명서</b>
                </div>
                <div v-if="item.certType == 2">
                  학사
                  <badge type="default"
                    ><i class="ni ni-paper-diploma"></i></badge
                  ><b> 성적증명서</b>
                </div>
                <div v-if="item.certType == 3">
                  석사 <badge type="default"><i class="ni ni-hat-3"></i></badge
                  ><b> 졸업증명서</b>
                </div>
                <div v-if="item.certType == 4">
                  석사
                  <badge type="default"
                    ><i class="ni ni-paper-diploma"></i></badge
                  ><b> 성적증명서</b>
                </div>
                <div v-if="item.certType == 5">
                  박사 <badge type="default"><i class="ni ni-hat-3"></i></badge
                  ><b> 졸업증명서</b>
                </div>
                <div v-if="item.certType == 6">
                  박사
                  <badge type="default"
                    ><i class="ni ni-paper-diploma"></i></badge
                  ><b> 성적증명서</b>
                </div>
                &nbsp;&nbsp;&nbsp;
                <span class="text-default"
                  >발급기관 <b>{{ item.univName }}</b></span
                >
              </div>

              <div class="col-md-4 text-right">
                발급일:
                {{ certificate[index].createdTime.substring(0, 10) }}&nbsp;

                <div v-if ="endShow">
                  <base-button @click="showImage(item.certificateId)"
                    >증명서 이미지 보기</base-button
                  >
                  <modal v-model:show="modals.modal0" modal-classes="modal-xl">
                    <div class="modal-all">
                      <img :src="this.imageSrc" class="certImage" />
                    </div>
                  </modal>
                </div>
                <div v-else>
                  <p> 증명서 보관일이 만료되었습니다.</p>
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
  name: "company-table",
  props: [ "noticeId", "endShow" ],
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
      title: [
       {
          noticeId: 3,
          noticeTitle: "2021 하반기 공개 채용",
          endApply: "2021-11-01",
        },
        {
          noticeId: 2,
          noticeTitle: "2021 상반기 공개 채용",
          endApply: "2021-04-20",
        },
        {
          noticeId: 1,
          noticeTitle: "2020 하반기 공개 채용",
          endApply: "2020-11-12",
        },
      ],
    };
  },
  methods: {
    showImage(num) {
      axios
        .get(`https://j5a507.p.ssafy.io/api/certificate/${num}`)
        .then((res) => {
          this.imageSrc = res.data.message;
          this.modals.modal0 = true;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
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
