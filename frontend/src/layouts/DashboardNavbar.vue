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
      class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex ml-md-auto"
    >
      <div class="row">
        <div class="form-group mb-0">
          <router-link class="navbar-brand" to="/home">
          <base-button outline type="primary">About CERTBLOCK</base-button>
          </router-link>
        </div>
      </div>
    </form>

    <div v-if="user == null">
      <div class="media align-items-center">
        <router-link class="navbar-brand" to="/signupAuth">
          <base-button type="primary" icon="fa fa-user">회원가입</base-button>
        </router-link>
        <router-link class="navbar-brand" to="/login">
          <base-button type="primary" icon="fa fa-user">로그인</base-button>
        </router-link>
      </div>
    </div>

    <div v-else-if="user != null">
      <ul class="navbar-nav align-items-center d-none d-md-flex">
        <li class="nav-item dropdown">
          <base-dropdown class="nav-link pr-0">
            <template v-slot:title>
              <div class="media align-items-center">
                <div class="media-body ml-2 d-none d-lg-block">
                  <base-button type="primary">
                    {{ user.name }}님 환영합니다
                  </base-button>
                </div>
<!--               
                <button class="btn btn-primary" @click="deleteuser">
                  회원탈퇴
                </button>  -->
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
            <router-link to="" class="dropdown-item"  @click="logout">
              <i class="ni ni-user-run"></i>
              <span>Logout</span>
            </router-link>
          </base-dropdown>
        </li>
      </ul>
    </div>
  </base-nav>
</template>

<script>
import { mapState, mapActions } from "vuex";
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
    ...mapActions(["logout", "deleteuser"]),
  },
  props: {
    logo: {
      type: String,
      default: "img/brand/logo.png",
      description: "Sidebar app logo",
    },
  },
  computed: {
    ...mapState(["user"]),
  },
};
</script>
