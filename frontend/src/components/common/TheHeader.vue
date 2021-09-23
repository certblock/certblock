<template>
  <div>
    <h1>헤더</h1>
    <router-link :to="{ name: 'Login' }">로그인</router-link> | 
    <router-link :to="{ name: 'Regist' }">회원가입</router-link>
    <div style="border: 1px solid green; margin: 10px">
      <h3>메뉴</h3>
      <div id="nav">
        <router-link to="/">메인</router-link> |
        <router-link to="/certificate">증명서</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>