<template>
  <div>
    <base-header
      class="header pb-8 pt-5 pt-lg-12 d-flex align-items-center"
      style="
        min-height: 600px;
        background-image: url(img/theme/profile-cover.png);
        background-size: cover;
        background-position: center top;
      "
    >
      <!-- Mask -->
      <span class="mask bg-gradient-default opacity-8"></span>
      <!-- Header container -->
      <div class="container-fluid align-items-center">
        <div class="row justify-content-md-center">
          <div class="col col-lg-1"></div>
          <div class="col-lg-7 col-md-10">
            <div v-if="user.type == 2">
              <h1 class="display-2 text-white">Hello {{ user.name }}</h1>
              <p class="text-white mt-0 mb-5">
                안녕하세요, 반갑습니다.<br />
                CERTBLOCK에서 쉽게 증명서를 발급해보세요.
              </p>
            </div>
            <div v-if="user.type == 1">
              <h1 class="display-2 text-white">Hello {{ user.name }}</h1>
              <p class="text-white mt-0 mb-5">
                안녕하세요, 반갑습니다.<br />
                CERTBLOCK에서 쉽게 증명서를 관리해보세요.<br />
                증명서 발급 현황이 확인가능합니다.
              </p>
            </div>
            <div v-if="user.type == 3">
              <h1 class="display-2 text-white">Hello {{ user.name }}</h1>
              <p class="text-white mt-0 mb-5">
                안녕하세요, 반갑습니다.<br />
                CERTBLOCK에서 쉽게 지원자의 증명서를 관리해보세요.<br />
                지원자가 제출한 증명서가 확인가능합니다.
              </p>
            </div>
          </div>

          <div class="col-lg-4 col-md-10">
            <div class="card card-profile shadow">
              <div class="card-body pt-0 pt-md-4">
                <div class="text-right">
                  <h3>
                    Phone
                    <span class="font-weight-light">{{ user.phone }}</span>
                  </h3>
                  <h3>
                    Birth
                    <span class="font-weight-light">{{
                      user.birth.substring(0, 10)
                    }}</span>
                  </h3>
                  <h3>
                    Email
                    <span class="font-weight-light">{{ user.email }}</span>
                  </h3>
                  <hr class="my-4" />

                  <div v-if="user.type == 2">
                    <base-button type="primary" @click="modals.modal0 = true">
                      학교 등록
                    </base-button>
                    <modal v-model:show="modals.modal0">
                      <template v-slot:header>
                        <h5 class="modal-title" id="exampleModalLabel">
                          학교 등록
                        </h5>
                      </template>
                      <div>
                        <regist-univ></regist-univ>
                      </div>
                      <template v-slot:footer>
                        <base-button
                          type="secondary"
                          @click="modals.modal0 = false"
                          >Close</base-button
                        >
                      </template>
                    </modal>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col col-lg-1"></div>
        </div>
      </div>
    </base-header>

    <div class="container-fluid mt--7 bg-darkblack">
      <card shadow type="secondary">
        <!-- 대학교 -->
        <div v-if="user.type == 1">
          <school-table></school-table>
        </div>

        <!-- 회사 -->
        <div v-if="user.type == 3" class="row">
          <div
            v-for="(item, index) in title"
            :key="index"
            @click="noticeId = item.noticeId"
            class="col-md-4"
          >
            <div v-if="item.noticeId == noticeId">
              <base-button class="col-md-12" >
                {{ item.noticeTitle }} 
              </base-button>
            </div>
            <div v-else>
              <base-button class="col-md-12" type="secondary" @click="checkMonths(index)">
                {{ item.noticeTitle }}
              </base-button>
            </div>
          </div>

          <company-table
            v-if="certificate != null"
            :noticeId="noticeId"
            :endShow="endShow"
            title="Light Table"
            class="col-md-12"
          ></company-table>
        </div>

        <!-- 학생 -->
        <!-- <div v-if="inuniv.length==0"> 
          <h3>등록된 학교가 없습니다. 학교등록 버튼을 통해 학교인증을 해주세요.</h3>
        </div>
        <div v-else> -->
        <div v-if="user.type == 2">
          <div v-if="inuniv.length==0"><h3>등록된 학교가 없습니다. 학교등록 버튼을 통해 학교인증을 해주세요.</h3></div>
          <div v-else class="row">
            <div
              v-for="(item, index) in inuniv"
              :key="index"
              @click="univNum = index"
              class="col-md-3"
            >
              <div v-if="index == univNum">
                <base-button class="col-md-12">
                  {{ item.universityName }}
                  (<a v-if="item.type == 1">학사</a
                  ><a v-if="item.type == 2">석사</a
                  ><a v-if="item.type == 3">박사</a>)
                </base-button>
              </div>
              <div v-else>
                <base-button class="col-md-12" type="secondary">
                  {{ item.universityName }}
                  (<a v-if="item.type == 1">학사</a
                  ><a v-if="item.type == 2">석사</a
                  ><a v-if="item.type == 3">박사</a>)
                </base-button>
              </div>
            </div>
          </div>

          <projects-table
            v-if="certificate != null"
            :univarrnum="univNum"
            title="Light Table"
          ></projects-table>
        </div>
          <!-- </div> -->
      </card>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import registUniv from "../components/common/registUnivModal.vue";
import ProjectsTable from "./Tables/ProjectsTable";
import SchoolTable from "./Tables/schoolTable.vue";
import CompanyTable from "./Tables/companyTable.vue";

export default {
  name: "user-profile",
  components: {
    registUniv,
    ProjectsTable,
    SchoolTable,
    CompanyTable,
  },
  data() {
    return {
      model: {
        username: "",
        email: "",
        firstName: "",
        lastName: "",
        address: "",
        city: "",
        country: "",
        zipCode: "",
        about: "",
      },
      modals: {
        modal0: false,
      },
      noticeId:3,
      univNum: 0,
      endShow:true,
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
  computed: {
    ...mapState(["user", "inuniv", "certificate"]),
  },
  methods: {
    checkMonths(num) {
      const moment = require("moment");
      // eslint-disable-next-line no-unused-vars
      const today = moment();

      var diff = moment.duration(today.diff(this.title[num].endApply)).months();
      
      if (diff > 6) this.endShow = false;
      else this.endShow = true;
    },
  },
};
</script>
<style></style>
