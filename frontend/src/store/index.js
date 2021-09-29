import { createStore } from "vuex";
import router from "../router";
import axios from "axios";

// vuex helper를 활용하여 각 route에서 호출 가능 (mapState, mapMutations, mapActions, mapGetters를 import하여 사용)
export default createStore({
  // SPA에서 사용하는 변수들의 중앙 저장소
  state: {
    user: null,
  },

  // state의 상태를 변경하는 함수들의 모음 (동기 methods)
  mutations: {
    login(state, data) {
      state.user = data;
    },

    logout(state) {
      state.user = null;
    },
  },

  // vuex에서 사용할 함수들의 모음 (비동기 methods)
  // state의 상태를 변경할 때는 commit함수를 이용해서 mutations를 호출하는 것을 권장
  // `${변수명}`으로 url 내에 변수 사용 가능
  actions: {
    async login({ commit }, data) {
      await axios
        .post(`http://localhost/api/users/login`, data)
        .then(({ data }) => {
          commit("login", data);
          router.push({ name: "Main" });
        })
        .catch((error) => {
          console.log(error);
          alert("로그인 실패");
        });
    },

    async logout({ commit }) {
      await commit("logout");
      alert("로그아웃");
      router.push({ name: "Main" });
    },

    async deleteuser({ state, commit }) {
      await axios
        .delete(`http://localhost/api/users/${state.user.id}`)
        .then(() => {
          alert("탈퇴 완료");
          commit("logout");
          router.push({ name: "Main" });
        });
    },
  },

  // state를 기반으로 계산 후 return을 돌려줄 함수들의 모음 (computed)
  getters: {},
});
