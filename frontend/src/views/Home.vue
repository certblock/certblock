<template>
  <div>
    <base-header type="gradient-default" class="pb-6 pb-8 pt-5 pt-md-8">
    </base-header>

    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col">
          <div class="card shadow">
            <div class="card-header bg-transparent">
              <img class="mainImage" src="img/brand/CERTBLOCK.jpg" />
              <router-link class="navbar-brand" to="/login">
                <base-button outline type="primary"
                  >CertBlock 시작하기</base-button
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
</style>
