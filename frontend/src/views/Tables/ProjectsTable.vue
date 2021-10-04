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

    <div>
      졸업 증명서 :
      <a v-if="certificate[certnum].hash != null">{{
        certificate[certnum].hash
      }}</a
      ><a v-else><base-button>발급받기</base-button></a
      ><br />
      성적 증명서 :
      <a v-if="certificate[certnum + 1].hash != null">{{
        certificate[certnum + 1].hash
      }}</a
      ><a v-else><base-button>발급받기</base-button></a>
    </div>

    <div class="table-responsive">
      <base-table
        class="table align-items-center table-flush"
        :class="type === 'dark' ? 'table-dark' : ''"
        :thead-classes="type === 'dark' ? 'thead-dark' : 'thead-light'"
        tbody-classes="list"
        :data="inCert"
      >
        <template v-slot>
          <th scope="row">
            <div
              class="media align-items-center"
              v-for="index in univarrnum + 1"
              :key="index"
            >
              <badge type="default"><i class="ni ni-app"></i></badge>
              <div class="media-body">
                <span class="name mb-0 text-sm"
                  >{{ inuniv[univarrnum].universityName }}증명서 이름</span
                >
              </div>
            </div>
          </th>
          <td class="budget" v-for="index in univarrnum + 1" :key="index">
            발급여부
          </td>
        </template>
      </base-table>
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
import BaseButton from "../../components/BaseButton.vue";

export default {
  components: { BaseButton },
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
  },
};
</script>
<style></style>
