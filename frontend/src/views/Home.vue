<template>
  <div>
    <base-header type="gradient-default" class="pb-6 pb-8 pt-5 pt-md-8">
    </base-header>

    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col">
          <div class="card shadow">
            <div class="card-header bg-transparent">
               <vue-particles color="#dedede"/>
              <router-link class="navbar-brand" to="/signup">
                <base-button outline type="primary" class="startBtn"
                  >CertBlock 시작하기</base-button
                >
              </router-link>
              <router-link class="navbar-brand" to="/company">
                <base-button outline type="primary" class="startBtn"
                  >기업회원</base-button
                >
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      nav: null,
    };
  }
};
</script>
<style>
.card-header {
  width: 100%;
  height: 100%;
  overflow: hidden;
  margin: 0 auto;
}
.mainImage {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
#hello{
  z-index : 999;
  color : black;
}
.startBtn{
  margin: 0 auto;
}
</style>
