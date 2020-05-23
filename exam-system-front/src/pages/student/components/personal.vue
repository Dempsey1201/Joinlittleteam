<template>
  <div class="personal">
    <background :ball_color="ball_color" :bg_color="bg_color"></background>
    <el-container>
      <el-aside width="18%">
        <Slidebar></Slidebar>
      </el-aside>
      <el-main>
        <Main></Main>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import background from "../../../component/base/background";
import Slidebar from "../../../component/base/Slidebar";
import Main from "../../../component/base/Main";
export default {
  name: "personal",
  components: {
    Slidebar,
    Main,
    background
  },
  data() {
    return {
      ball_color: {
        //小球颜色
        r: 64,
        g: 158,
        b: 255
      },
      bg_color: "rgba(236, 245, 255,0.3)", //背景颜色
      activeIndex: "1",
      bool: true
    };
  },
  computed: {},
  created() {}
};
</script>

<style lang="less">
.el-container {
  margin-top: 1px;
}
.el-aside {
  border-right: 1px solid #eee;
  // position: fixed;
  height: calc(100vh - 70px);
  transition: all 0.5s ease 0s;
  -moz-transition: all 0.5s ease 0s; /* Firefox 4 */
  -webkit-transition: all 0.5s ease 0s; /* Safari 和 Chrome */
  -o-transition: all 0.5s ease 0s;
}
.el-main {
  // float: right;
  height: calc(100vh - 70px);
  overflow-y: auto;
  padding: 0px !important;
}
</style>