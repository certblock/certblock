// 페이지를 이동할 때 사용할 설정들, <router-link :to="{ name: 'route의 name' }">로 링크를 만들 수 있음
import { createRouter, createWebHistory } from "vue-router";
import Main from "../views/TheMain";
import Certificate from "../views/Certificate.vue";
import Login from "../views/Login.vue";
import Regist from "../views/Regist.vue";
// import ErrorPage from "../views/ErrorPage.vue";

// path : url경로, name : 해당 route의 이름, component : 해당 route가 호출되면 표시할 페이지(위에 import한 페이지)
const routes = [
  {
    path: "/",
    name: "Main",
    component: Main,
  },
  {
    path: "/certificate",
    name: "Certificate",
    component: Certificate,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/regist",
    name: "Regist",
    component: Regist,
  },
  // 존재하지 않는 url 접근시 에러페이지로 이동
  // {
  //   path: "*",
  //   component: ErrorPage,
  // },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

// 라우터 네비게이션 가드 : 로그인을 해야 접근할 수 있는 페이지 등 라우터를 통한 페이지 이동시 조건 설정
// to : 이동하려는 페이지, from : 현재 페이지, next() : 페이지 이동
router.beforeEach((to, from, next) => {
  if (to.name == "Certificate") {
    alert("로그인 후 사용 가능합니다.");
    next("/login");
  } else {
    next();
  }
});

export default router;
