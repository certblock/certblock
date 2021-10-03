<template>
  <div v-if="inuniv.length == 0">등록된 대학교가 없습니다</div>
  <div v-else>
    {{ inuniv[univarrnum].universityName }} 증명서 목록
    <div v-for="index in univarrnum + 1" :key="index">{{ index }}</div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  props: {
    univarrnum: Number,
  },
  computed: { ...mapState(["inuniv"]) },
};
</script>

<style></style>
