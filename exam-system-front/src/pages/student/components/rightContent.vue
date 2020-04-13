<template>
  <div class="rightContent">
    <div class="title">
      <span>输入班级号</span>
      <el-input
        v-model="input"
        placeholder="请输入内容"
        class="input"
        @keydown.enter.native="searchClass"
      ></el-input>
    </div>
    <!-- <div class="noneExam" v-if="isShow == false">请选择班级查看试卷信息！</div> -->
    <!-- <div class="info" v-else> -->
    <div class="info">
      <div class="top">
        <div class="logo">
          <i class="el-icon-monitor"></i>
        </div>
        <div class="name">第一次上机联系</div>
        <el-button type="info">已结束</el-button>
      </div>
      <div class="bottom">
        <div class="time">结束时间：2019年12月15日</div>
        <div class="detail">
          <i class="el-icon-s-shop"></i>
          <span class="school">东北师范大学</span>
          <i class="el-icon-s-check"></i>
          <span class="teacher">王李洪</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "rightContent",
  data() {
    return {
      //查询班级
      input: "",
      //是否查询了班级
      isShow: false,
      //试卷信息
      examList: [
        {
          examName: "", //试卷名称
          start_time: "", //开始时间
          end_time: "", //截止时间
          teacher: "" //老师
        }
      ]
    };
  },
  methods: {
    searchClass(e){
      //向后端发送班级号，后端返回试卷列表
      const classNumber=this.input;
      this.input="";
    }
  },
};
</script>

<style lang="less">
.clearfix:after {
  /*伪元素是行内元素 正常浏览器清除浮动方法*/
  content: "";
  display: block;
  height: 0;
  clear: both;
  visibility: hidden;
}
.rightContent {
  margin-left: 10px;
  .title {
    display: flex;
    padding: 7px;
    background: #fff;
    border-radius: 5px;
    span {
      padding: 3px;
      line-height: 36px;
      font-size: 14px;
      justify-content: center;
    }
    .input {
      width: 100px;
    }
  }
  .noneExam{
    color:tomato;
    font-size: 16px;
    margin-top: 10px;
    padding: 10px;
    background: #fff;
    border-radius: 5px;
  }
  .info {
    margin-top: 10px;
    padding: 10px;
    background: #fff;
    border-radius: 5px;
    .top {
      display: flex;
      .logo {
        // flex: 1;
        margin-right: 3px;
        i {
          font-size: 20px;
        }
      }
      .name {
        flex: 1;
        font-size: 14px;
        color: #1b75dc;
      }
      button {
        // flex: 1;
        background-color: rgb(153, 153, 153);
        border-color: rgb(153, 153, 153);
        padding: 3px 5px;
        font-size: 12px;
      }
    }
    .bottom {
      display: flex;
      color: #666;
      margin-top: 3px;
      .time {
        flex: 1;
        font-size: 12px;
      }
      .detail {
        font-size: 12px;
        color: #666;
      }
    }
  }
}
</style>