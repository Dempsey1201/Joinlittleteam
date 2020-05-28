<template>
  <div id="app">
    <navBar :student="student"/>
    <!-- <div class="main"> -->
      <router-view></router-view>
    <!-- </div> -->
  </div>
</template>
<script>
import axios from "axios";
// import background from "../../component/base/background";
import navBar from "../components/nav";

export default {
  components: {
    navBar
  },
  data() {
    return {
      url: axios.defaults.baseURL,
      student: JSON.parse(sessionStorage.getItem("userInfo")),
      
    }
  },
  created() {
    console.log(this.student);
    console.log(this.student + this.url);
  },
};
</script>

<style lang="less">
.main {
  background-color: rgba(255, 255, 255, 0.74);
  padding: 20px;
  box-sizing: border-box;
  box-shadow: 0 5px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  min-height: 100%;
}

</style>
