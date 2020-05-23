<template>
  <div id="app">
    <background :bg_color="bg_color" :ball_color="ball_color"></background>
    <navBar />
    <!-- <div class="main"> -->
      <router-view></router-view>
    <!-- </div> -->
  </div>
</template>
<script>
import background from "../../component/base/background";
import navBar from "./components/nav";

export default {
  components: {
    navBar,
    background
  },
  data() {
    return {
      ball_color:{//小球颜色
					r: 64,
					g: 158,
					b: 255
				},
				bg_color:"rgba(236, 245, 255,0.3)",//背景颜色
    };
  }
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
