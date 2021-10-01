import { createStore } from "vuex";
import router from "../router";
import axios from "axios";

// vuex helper를 활용하여 각 route에서 호출 가능 (mapState, mapMutations, mapActions, mapGetters를 import하여 사용)
export default createStore({
  // SPA에서 사용하는 변수들의 중앙 저장소
  state: {
    isLogin: false,
    id: "",
    pwd: "",
    axios: [],
  },

  // state의 상태를 변경하는 함수들의 모음 (동기 methods)
  mutations: {
    login(state, data) {
      state.id = data.id;
      state.pwd = data.pwd;
      state.isLogin = true;
    },
    logout(state) {
      state.id = "";
      state.pwd = "";
      state.isLogin = false;
    },
    axios(state, data) {
      state.axios.push(data);
    },
  },

  // vuex에서 사용할 함수들의 모음 (비동기 methods)
  // state의 상태를 변경할 때는 commit함수를 이용해서 mutations를 호출하는 것을 권장
  actions: {
    login({ commit }, data) {
      commit("login", data);
      router.push({ name: "Home" });
    },
    logout({ commit }) {
      commit("logout");
      router.push({ name: "Home" });
    },
    axiostest({ commit }, test) {
      console.log(test);
      axios
        .get(`http://localhost:8099/api/v1/admin/getUserByName/` + test)
        .then(({ data }) => {
          console.log(data);
          commit("axios", data);
        });
      console.log(this.state.axios);
    },
  },

  // state를 기반으로 계산 후 return을 돌려줄 함수들의 모음 (computed)
  getters: {},
});
