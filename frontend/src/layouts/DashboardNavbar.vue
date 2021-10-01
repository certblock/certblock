<template>
  <base-nav
    class="navbar-top navbar-dark"
    id="navbar-main"
    :show-toggle-button="false"
    expand
  >
    <!-- <router-link class="navbar-brand" to="/">
      <img :src="logo" class="navbar-brand-img" alt="..." />
    </router-link> -->

    <form
      class="
        navbar-search navbar-search-dark
        form-inline
        mr-3
        d-none d-md-flex
        ml-md-auto
      "
    >
      <div class="row">
        <div class="form-group mb-0">
          <router-link class="navbar-brand" to="/service">
            서비스 소개
          </router-link>
        </div>
        <div class="form-group mb-0">
          <router-link class="navbar-brand" to="/"> 마이페이지 </router-link>
        </div>
      </div>
    </form>

    <form
      class="
        navbar-search navbar-search-dark
        form-inline
        mr-3
        d-none d-md-flex
        ml-lg-auto
      "
    >
      <div class="form-group mb-0">
        <base-input
          placeholder="Search"
          class="input-group-alternative"
          alternative=""
          addon-right-icon="fas fa-search"
        >
        </base-input>
      </div>
    </form>
    <ul class="navbar-nav align-items-center d-none d-md-flex">
      <li class="nav-item dropdown">
        <base-dropdown class="nav-link pr-0">
          <template v-slot:title>
            <div class="media align-items-center">
              <base-button type="primary" icon="fa fa-user"
                >회원가입</base-button
              >
              <base-button type="primary" icon="fa fa-user">로그인</base-button>
            </div>
          </template>
          <div class="dropdown-header noti-title">
            <h6 class="text-overflow m-0">Welcome!</h6>
          </div>
          <router-link to="/profile" class="dropdown-item">
            <i class="ni ni-single-02"></i>
            <span>My profile</span>
          </router-link>
          <router-link to="/profile" class="dropdown-item">
            <i class="ni ni-settings-gear-65"></i>
            <span>Settings</span>
          </router-link>
          <router-link to="/profile" class="dropdown-item">
            <i class="ni ni-calendar-grid-58"></i>
            <span>Activity</span>
          </router-link>
          <router-link to="/profile" class="dropdown-item">
            <i class="ni ni-support-16"></i>
            <span>Support</span>
          </router-link>
          <div class="dropdown-divider"></div>
          <router-link to="/profile" class="dropdown-item">
            <i class="ni ni-user-run"></i>
            <span>Logout</span>
          </router-link>
        </base-dropdown>
      </li>
    </ul>
  </base-nav>
</template>
<script>
export default {
  data() {
    return {
      activeNotifications: false,
      showMenu: false,
      searchQuery: "",
    };
  },
  methods: {
    toggleSidebar() {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
    },
    hideSidebar() {
      this.$sidebar.displaySidebar(false);
    },
    toggleMenu() {
      this.showMenu = !this.showMenu;
    },
  },
  props: {
    logo: {
      type: String,
      default: "img/brand/logo.png",
      description: "Sidebar app logo",
    },
  },
};
</script>
