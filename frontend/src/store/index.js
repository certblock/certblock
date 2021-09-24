import { createStore } from "vuex";

export default createStore({
  // SPA에서 사용하는 변수들의 중앙 저장소
  state: {
    isLogin: false,
    id: "",
    pwd: "",
  },

  // state를 기반으로 계산 후 return을 돌려줌 (computed)
  getters: {},

  // state의 상태를 변경하는 method들의 모음 (동기 methods)
  mutations: {},

  // 상태를 변이 시키는 대신 액션으로 변이에 대한 커밋 처리 (비동기 methods)
  // state의 상태를 변경할 때는 commit함수를 이용해서 mutations를 호출하는 것을 권장
  actions: {},

  // 
  modules: {},
});
