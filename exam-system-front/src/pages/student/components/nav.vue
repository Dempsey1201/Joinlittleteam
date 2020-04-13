<template>
  <div class="navBar">
    <el-row>
      <el-col :span="8">
        <div class="left">
          <img src="../../../../public/img/下载.png" alt width="80%" />
        </div>
      </el-col>
      <el-col :span="15">
        <div class="right">
          <el-dropdown trigger="click">
            <span class="el-dropdown-link">
                下拉菜单
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item icon="el-icon-s-home"><router-link tag="span" to="/student.html/personal">个人中心</router-link></el-dropdown-item>
              <el-dropdown-item icon="el-icon-switch-button">登出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "navBar"
};
</script>

<style lang="less">
html {
  padding: 0px;
  margin: 0px;
  width: 100%;
}

body {
  padding: 0px;
  margin: 0px;
  width: 100%;
  background-color: #eff4f7;
  .navBar {
    background-color: #fff;
    height: 64px;
    line-height: 64px;
    border-bottom: 1px;
    box-shadow: 0 2px 3px 0 rgba(155, 155, 155, 0.29);
    .left {
      margin-left: 10%;
      img {
        vertical-align: middle;
      }
    }
    .right {
      float: right;
    }
  }
}
</style>