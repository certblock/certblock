<template>
  <div class="row">
    <div class="col-lg-10 search">
      <div class="row">
        <div class="col-lg-3">학교</div>
        <div class="col-lg-9 input">
          <base-input
            alternative=""
            input-classes="form-control-alternative"
            placeholder="학교 검색"
            v-model="univName"
          />
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3">학번</div>
        <div class="col-lg-9 input">
          <base-input
            alternative=""
            placeholder="학번"
            input-classes="form-control-alternative"
            v-model="studentId"
          />
        </div>
      </div>
    </div>

    <div class="col-lg-2">
      <base-button type="primary" class="mr-4">인증 하기</base-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "regist-univ",
  data() {
    return {
      univName: "",
      studentId: "",
    };
  },
};
</script>
<style>
.search *{
  align-items: center;
}

.input {
  align-items: center;
  padding : 0;
}
.mr-4{
  height : 100%;
}
</style>
