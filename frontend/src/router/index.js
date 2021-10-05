// 페이지를 이동할 때 사용할 설정들, <router-link :to="{ name: 'route의 name' }">로 링크를 만들 수 있음
import { createRouter, createWebHistory } from "vue-router";
//import Main from "../views/TheMain";
import Certificate from "../views/Certificate.vue";
import store from "../store";

import DashboardLayout from "../layouts/DashboardLayout";
import AuthLayout from "../layouts/AuthLayout";

import Service from "../views/Service.vue";
import Home from "../views/Home.vue";
import Profile from "../views/UserProfile.vue";
import Tables from "../views/Tables.vue";

import Login from "../views/Login.vue";
import Signup from "../views/Signup.vue";
import SignupAuth from "../views/SignupAuth.vue";
import Register from "../views/Register.vue";

import Company from "../views/CompanyAdmin.vue";
//import ApplySubmit from "../components/Modals/ApplySubmit.vue";
import ErrorPage from "../views/ErrorPage.vue";

// path : url경로, name : 해당 route의 이름, component : 해당 route가 호출되면 표시할 페이지(위에 import한 페이지)
const routes = [
  {
    path: "/",
    redirect: "/home",
    component: DashboardLayout,
    children: [
      {
        path: "/service",
        name: "service",
        components: { default: Service },
      },
      {
        path: "/home",
        name: "home",
        components: { default: Home },
      },
      {
        path: "/profile",
        name: "profile",
        components: { default: Profile },
      },
      {
        path: "/tables",
        name: "tables",
        components: { default: Tables },
      },
      {
        path: "/certificate",
        name: "Certificate",
        component: Certificate,
      },
      {
        path: "*",
        component: ErrorPage,
      },
    ],
  },
  {
    path: "/user",
    redirect: "login",
    component: AuthLayout,
    children: [
      {
        path: "/login",
        name: "login",
        components: { default: Login },
      },
      {
        path: "/signupAuth",
        name: "signupAuth",
        components: { default: SignupAuth },
      },
      {
        path: "/signup",
        name: "signup",
        components: { default: Signup },
      },
      {
        path: "/register",
        name: "register",
        components: { default: Register },
      },
    ],
  },
  {
    path: "/company",
    redirect: "company",
    component: Company,
    children: [
      {
        path: "/company",
        name: "company",
        components: { default: Company },
      },
      {
        path: "/submit",
        name: "submit",
        components: { default: Home },
      },
      {
        path: "/register",
        name: "register",
        components: { default: Register },
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  linkActiveClass: "active",
  routes,
});

// 라우터 네비게이션 가드 : 로그인을 해야 접근할 수 있는 페이지 등 라우터를 통한 페이지 이동시 조건 설정
// to : 이동하려는 페이지, from : 현재 페이지, next() : 페이지 이동(디폴트값: to)
// 현재는 모든 route 요청시에 실행하지만, 각 route마다 각각의 가드를 사용 할 수도 있음
router.beforeEach((to, from, next) => {
  let isLogin = store.state.user != null ? true : false;
  let route = to.name;
  switch (route) {
    case "certificate":
    case "profile":
      if (!isLogin) {
        alert("로그인 후 사용 가능합니다.");
        next("/login");
      } else {
        next();
      }
      break;

    case "login":
    case "signup":
      if (isLogin) {
        alert("이미 로그인 중입니다.");
      } else {
        next();
      }
      break;

    default:
      next();
  }
});

export default router;
