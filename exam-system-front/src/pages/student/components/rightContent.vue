<template>
  <div class="rightContent">
    <div class="title">
      <el-button
        :type="progress==false ? 'primary':''"
        style="padding:0px 5px"
        @click="noProgress"
      >未进行</el-button>
      <el-button
        :type="progress==true ? 'primary':''"
        style="padding:3px 5px"
        @click="onProgress"
      >已完成</el-button>
    </div>
    <!-- <div class="noneExam" v-if="isShow == false">请选择班级查看试卷信息！</div> -->
    <!-- <div class="info" v-else> -->
    <div
      class="info"
      v-for="item,index in examList"
      @click="goPaper(item,progress)"
      style="border:1px solid rgba(26, 26, 26, 0.08);box-shadow: 0 1px 4px rgba(26, 26, 26, 0.08);background: #fff"
    >
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
          <el-button
            type="primary"
            plain
            v-if="progress"
            style="padding:6px 3px;font-size:12px;margin-left:5px"
            @click.stop="getScore(item.pid)"
          >查看成绩</el-button>
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
      starttime: "",
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
      progress: false,
      msg: "你的简答题老师已经批改了" //简答题老师是否批改
    };
  },
  created() {
    this.getExam("0");
  },
  methods: {
    //获取试卷列表
    getExam(e) {
      axios
        .get(this.url + "/paper/getPaperByNo", {
          params: {
            sid: this.student.id
          }
        })
        .then(res => {
          console.log(res.data);
          this.examList = res.data.试卷信息;
          // this.starttime = this.examList.start_time;
          // // 定义结束时间戳
          // const start = Date.parse(new Date(this.starttime));
          // // 定义当前时间戳
          // const now = Date.parse(new Date());

          if (e == "0") {
            this.examList = this.examList.filter(item => item.done == false);
            this.examList = this.examList.filter(item => (Date.parse(new Date(item.start_time))<=Date.parse(new Date())));
            console.log(this.examList);
          } else {
            this.examList = this.examList.filter(item => item.done == true);
            console.log(this.examList);
          }
        });
    },
    //点击进入试卷
    goPaper(item, progress) {
      // console.log(pid);
      this.$router.push({
        path: "/student.html/detailPaper",
        query: {
          item: item,
          progress: progress
        }
      });
    },
    //点击进入未完成
    noProgress() {
      this.progress = false;
      this.getExam("0");
    },
    //点击进入已完成
    onProgress() {
      this.progress = true;
      this.getExam("1");
    },
    //已完成界面的成绩显示
    getScore(e) {
      //看看他的简答题是否被批
      axios
        .get(this.url + "/paper/getClassAnswer", {
          params: {
            sid: this.student.id,
            pid: e,
            qid: 1
          }
        })
        .then(res => {
          console.log(res);
          var grade = res.data;
          for (var i = 0; i < grade.length; i++) {
            console.log(grade[i].getscore);
            if (grade[i].getscore == -1) {
              this.msg = "你的简答题老师还没有批改呀";
            }
          }
        });
      axios
        .get(this.url + "/paper/getScore", {
          params: {
            sid: this.student.id,
            pid: e
          }
        })
        .then(res => {
          console.log(res.data);
          this.$alert(
            "你这次考试的成绩为" + res.data + "分," + this.msg,
            "考试成绩",
            {
              confirmButtonText: "确定",
              callback: action => {
                this.msg = "你的简答题老师已经批改了";
              }
            }
          );
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
  height: 600px;
  overflow-x: hidden;
  overflow-y: scroll;
  margin-left: 10px;
  background: rgba(255, 255, 255, 0.7);
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
        // // flex: 1;
        // background-color: rgb(153, 153, 153.8);
        // border-color: rgb(153, 153, 153, 0.8);
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