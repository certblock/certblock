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
           {{ inuniv[univarrnum].universityName }} 증명서
            목록
          </h3>
        </div>
      </div>
    </div>

    <!-- <div v-if="inuniv.length == 0">등록된 대학교가 없습니다</div>
    <div v-for="index in univarrnum + 1" :key="index">{{ index }}</div> -->

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

export default {
  name: "projects-table",
  props: {
    univarrnum: Number,
  },
  computed: {
    ...mapState(["user"]),
    ...mapState(["inuniv"]),
  },
  data(){
    return {
      inCert:[],
    }
  },
};
</script>
<style></style>
