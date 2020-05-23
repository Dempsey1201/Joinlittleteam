<template>
  <div class="navBar">
    <el-row>
      <el-col :span="8">
        <div class="left">
          成才考试系统
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
    background-color: rgba(255,255,255,0.7);
    height: 64px;
    line-height: 64px;
    border-bottom: 1px;
    box-shadow: 0 2px 3px 0 rgba(155, 155, 155, 0.29);
    .left {
      font-size: 30px !important;
      color: #409eff !important;
      opacity: 1 !important;
      height: 64px;
      line-height: 64px;
      background-color:rgba(255,255,255,0.7);
      margin-left: 10%;
    }
    .right {
      float: right;
    }
  }
}
</style>