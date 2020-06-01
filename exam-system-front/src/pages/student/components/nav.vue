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
              <span>我的信息</span>
                <!-- <span style="margin-right:5px">{{student.username}}</span>
                <img :src="url+student.headUrl" alt="" width="50px" height="50px" style="border-radius:100%;vertical-align:middle"/> -->
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item icon="el-icon-folder">
                <router-link tag="span" to="/student.html/home">返回首页</router-link>
              </el-dropdown-item>
              <el-dropdown-item icon="el-icon-s-home"><router-link tag="span" to="/student.html/personal">个人中心</router-link></el-dropdown-item>
              <el-dropdown-item icon="el-icon-switch-button">
                <a href="http://localhost:8080/student.html">登出</a>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: "navBar",
  data() {
    return {
      url: axios.defaults.baseURL,
    }
  },
  props:{
    student:Object
  },
  created() {
    console.log(this.student)
  },
};
</script>

<style lang="less">
a{
  color:#606266;
  text-decoration: none;
}
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
      cursor: pointer;
    }
  }
}
</style>