<template>
  <div id="app">
    <navBar />
    <router-view></router-view>
  </div>
</template>
<script>

import navBar from "./components/nav";

export default {
  components: {
    navBar
  },
  data() {
    return {};
  }
};
</script>

<style lang="less">


</style>
