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
    <div class="info" v-for="item,index in examList" @click="goPaper(item)">
      <div class="top">
        <div class="logo">
          <i class="el-icon-monitor"></i>
        </div>
        <div class="name">{{item.pname}}</div>
        <el-button type="info" v-if="item.subject<0">已结束</el-button>
        <el-button type="success" v-else>进行中</el-button>
      </div>
      <div class="bottom">
        <div class="time">结束时间：{{item.end_time}}</div>
        <div class="detail">
          <!-- <i class="el-icon-s-shop"></i>
          <span class="school">{{item.classno}}</span>-->
          <i class="el-icon-s-check"></i>
          <span class="teacher">{{item.teacher}}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "rightContent",
  data() {
    return {
      url: axios.defaults.baseURL,
      student: JSON.parse(sessionStorage.getItem("userInfo")), //学生的信息
      //查询班级
      input: "",
      //是否查询了班级
      isShow: false,
      //试卷信息
      examList: [],
      over: false, // 获取当前时间
      timer: "", //定时器，
      year: "",
      month: "",
      date: "",
      hour: "",
      min: "",
      sec: ""
    };
  },
  created() {
    var that = this;
    //计时时间
    this.timer = setInterval(() => {
      // that.year=new Date().getFullYear();
      // that.month=new Date().getMonth()+1;
      // that.date=new Date().getDate();
      // that.hour=new Date().getHours();
      // that.min=new Date().getMinutes()
      // that.sec=new Date().getSeconds();
      //获取所有的试卷信息
      axios
        .get(this.url + "/paper/getPaperByNo", {
          params: {
            sid: this.student.id
          }
        })
        .then(res => {
          console.log(res);
          this.examList = res.data;
        });
    }, 1000);
  },
  methods: {
    searchClass(e) {
      //向后端发送班级号，后端返回试卷列表
      const classNumber = this.input;
      this.input = "";
    },
    goPaper(item) {
      // console.log(pid);
      this.$router.push({
        path: "/student.html/detailPaper",
        query: {
          item: item
        },
      });
    }
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer); // 在Vue实例销毁前，清除我们的定时器
    }
  }
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
    background: rgba(255, 255, 255, 0.7);
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
  .noneExam {
    color: tomato;
    font-size: 16px;
    margin-top: 10px;
    padding: 10px;
    background: rgba(255, 255, 255, 0.7);
    border-radius: 5px;
  }
  .info {
    margin-top: 10px;
    padding: 10px;
    background: rgba(255, 255, 255, 0.7);
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
        background-color: rgb(153, 153, 153.8);
        border-color: rgb(153, 153, 153, 0.8);
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