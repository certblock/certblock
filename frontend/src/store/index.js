import { createStore } from "vuex";
import router from "../router";
import axios from "axios";
import createPersistedState from "vuex-persistedstate";

// vuex helper를 활용하여 각 route에서 호출 가능 (mapState, mapMutations, mapActions, mapGetters를 import하여 사용)
export default createStore({
  // SPA에서 사용하는 변수들의 중앙 저장소
  state: {
    user: null,
    inuniv: [],
    certificate: [],
    confirm: [],
    signup: {
      name: "",
      phone: "",
      birth: "",
      email: "",
      pass: "",
      type: 2,
    },
  },

  // state의 상태를 변경하는 함수들의 모음 (동기 methods)
  mutations: {
    login(state, data) {
      state.user = data;
    },

    logout(state) {
      state.user = null;
      state.inuniv = [];
      state.certificate = [];
    },

    inuniv(state, data) {
      state.inuniv = data;
    },

    certificate(state, data) {
      state.certificate = data;
    },

    confirmNum(state, data) {
      state.confirm = data;
    },
  },

  // vuex에서 사용할 함수들의 모음 (비동기 methods)
  // state의 상태를 변경할 때는 commit함수를 이용해서 mutations를 호출하는 것을 권장
  // actions 내에서 dispatch를 통해 다른 action 호출 가능
  // `${변수명}`으로 url 내에 변수 사용 가능
  actions: {
    async login({ commit, dispatch }, data) {
      await axios
        .post(`https://j5a507.p.ssafy.io/api/users/login`, data)
        .then(async ({ data }) => {
          await commit("login", data);
          if (data.type == 2) {
            await dispatch("studentinuniv", data.id);
            await dispatch("getcertificate", data.id);
          } else if (data.type == 1) {
            await dispatch("getcertificatehistory", data.id);
          }
          router.push({ name: "profile" });
        })
        .catch((error) => {
          console.log(error);
          alert("이메일 또는 비밀번호가 틀립니다.");
        });
    },

    async logout({ commit }) {
      await router.push({ name: "home" });
      commit("logout");
    },

    async deleteuser({ state, commit }) {
      await axios
        .delete(`https://j5a507.p.ssafy.io/api/users/${state.user.id}`)
        .then(() => {
          alert("탈퇴 완료");
          commit("logout");
          router.push({ name: "Main" });
        });
    },

    async studentinuniv({ commit }, studentId) {
      await axios
        .get(`https://j5a507.p.ssafy.io/api/students/${studentId}/universities`)
        .then(({ data }) => {
          commit("inuniv", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async getcertificate({ commit }, studentId) {
      await axios
        .get(`https://j5a507.p.ssafy.io/api/students/${studentId}/certificates`)
        .then(({ data }) => {
          commit("certificate", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async getcertificatehistory({ commit }, universityId) {
      await axios
        .get(
          `https://j5a507.p.ssafy.io/api/universities/${universityId}/certificates`
        )
        .then(({ data }) => {
          commit("certificate", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },

  // state를 기반으로 계산 후 return을 돌려줄 함수들의 모음 (computed)
  getters: {},

  // vuex-persistedstate : paths에 입력된 state값을 localstorage와 연결해주는 plugin -> 새로고침 해도 로그아웃 되지 않음
  plugins: [createPersistedState({ paths: ["user", "inuniv", "certificate"] })],
});
