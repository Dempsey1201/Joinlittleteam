<template>
  <div class="personal">
    <el-container>
      <el-aside width="200px">
        <Slidebar></Slidebar>
      </el-aside>
      <el-main>
        <Main></Main>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Slidebar from "../../../component/base/Slidebar"
import Main from "../../../component/base/Main"
export default {
  name: "personal",
  components: {
    Slidebar,
    Main
  },
  computed:{
    
  },created(){

  }
};
</script>

<style lang="less">
.personal {
  color: red;
}
.el-container{
  margin-top: 1px;
}
.el-aside {
  // position: fixed;
  height: calc(100vh - 70px);
 transition: all .5s ease 0s ;
  -moz-transition:  all .5s ease 0s ; /* Firefox 4 */
  -webkit-transition:  all .5s ease 0s ; /* Safari 和 Chrome */
  -o-transition:  all .5s ease 0s;
}
.el-main {
  // float: right;
  height: calc(100vh - 70px);
  background-color: #ebeef5;
      overflow-y: auto;
}
</style>