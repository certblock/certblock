<template>
  <div>
    <base-header type="gradient-default" class="pb-6 pb-8 pt-5 pt-md-8">
    </base-header>

    <div class="container-fluid mt--7 bg-darkblack">
      <div class="row">
        <div class="col">
          <div class="card-shadow">
            <div class="card-header bg-darkblack">
              <vue-particles color="#dedede"></vue-particles>
              <div class="centered-text">
                <h2 class="text-secondary">블록체인 기반 증명서 발급/조회 서비스</h2>
                <h1 class="title text-primary">CERTBLOCK</h1>
                <router-link class="navbar-brand" to="/signupAuth">
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
  </div>
</template>
<script>

export default {
  components: {
  },
  data() {
    return {
      nav: null,
    };
  },
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
#hello {
  z-index: 999;
  color: black;
}
.startBtn {
  margin: 0 auto;
}
#particles-js {
  height: 100vh;
}

.centered-text {
  color: #000000;
  position: absolute;
  text-align: center;
  top: 40%;
  width: 100%;
}

.title{
  font-size: 100px;
}
</style>
