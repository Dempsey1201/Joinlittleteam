<template>
  <div class="detailPaper">
    <div class="wrap">
      <div
        class="clock"
        style="text-align: center;margin-top:5px;font-size:16px;color:#00c758"
      >剩余时间：{{d}}天 {{h}}时 {{m}}分 {{s}}秒</div>
      <div class="ti">
        <div class="title">{{item.pname}}</div>
        <div class="info">
          <span class="teacher">老师：{{item.teacher}}</span>
          <span class="endtime">截至时间：{{item.end_time}}</span>
        </div>
      </div>
    </div>
    <div class="content">
      <div class="left" style="width:30%">
        <div class="card">
          <div class="title" style="text-align:center">答题卡</div>
          <div class="detail">
            <div v-for="item,index in question" style="display: inline-block;">
              <div
                :class="{'small':true,'show':(answer[index]!=undefined && answer[index] != '')}"
              >{{1+index}}</div>
            </div>
          </div>
        </div>
      </div>
      <div class="right" style="width:70%">
        <div class="over" v-if="over">
          <overexam :questionList="question"/>
        </div>
        <div class="noover" v-if="!over">
          <div v-for="(son,index) in question" :key="index" class="div">
            {{index+1}}、
            <span class="type" v-if="son.qtype== 1">单选</span>
            <span
              class="type"
              v-if="son.qtype== 2"
              style="background: #ffe2d9;border: 1px solid #ffb399;"
            >多选</span>
            <span
              class="type"
              v-if="son.qtype== 3"
              style="background: #eee;border: 1px solid #ddd;"
            >判断</span>
            <span class="type" v-if="son.qtype== 4">简答</span>
            <span
              class="type"
              v-if="son.qtype== 5"
              style="background: #eee;border: 1px solid #ddd;"
            >填空</span>
            <span>({{son.qscore}}分)</span>
            <div class="question">{{son.question }}</div>
            <!-- 单选题 -->
            <div v-if="son.qtype==1">
              <ul>
                <li>
                  <input
                    type="radio"
                    :name="son.name"
                    :value="son.oa"
                    v-model="one[index]"
                    @change="getAnswer('A',index,son.qtype)"
                  />
                  <span class="sql">{{son.oa}}</span>
                </li>
                <li>
                  <input
                    type="radio"
                    :name="son.name"
                    :value="son.ob"
                    v-model="one[index]"
                    @change="getAnswer('B',index,son.qtype)"
                  />
                  <span class="sql">{{son.ob}}</span>
                </li>
                <li>
                  <input
                    type="radio"
                    :name="son.name"
                    :value="son.oc"
                    v-model="one[index]"
                    @change="getAnswer('C',index,son.qtype)"
                  />
                  <span class="sql">{{son.oc}}</span>
                </li>
                <li>
                  <input
                    type="radio"
                    :name="son.name"
                    :value="son.od"
                    v-model="one[index]"
                    @change="getAnswer('D',index,son.qtype)"
                  />
                  <span class="sql">{{son.od}}</span>
                </li>
              </ul>
            </div>
            <!-- 多选题 -->
            <div v-if="son.qtype==2">
              <ul>
                <li>
                  <input
                    type="checkbox"
                    :name="son.name"
                    :value="son.oa"
                    v-model="many[0]"
                    @change="getAnswer(son.oa,index,son.qtype)"
                  />
                  <span class="sql">{{son.oa}}</span>
                </li>
                <li>
                  <input
                    type="checkbox"
                    :name="son.name"
                    :value="son.ob"
                    v-model="many[1]"
                    @change="getAnswer(son.ob,index,son.qtype)"
                  />
                  <span class="sql">{{son.ob}}</span>
                </li>
                <li>
                  <input
                    type="checkbox"
                    :name="son.name"
                    :value="son.oc"
                    v-model="many[2]"
                    @change="getAnswer(son.oc,index,son.qtype)"
                  />
                  <span class="sql">{{son.oc}}</span>
                </li>
                <li>
                  <input
                    type="checkbox"
                    :name="son.name"
                    :value="son.od"
                    v-model="many[3]"
                    @change="getAnswer(son.od,index,son.qtype)"
                  />
                  <span class="sql">{{son.od}}</span>
                </li>
              </ul>
            </div>
            <!-- //判断题 -->
            <div v-if="son.qtype==3">
              <ul>
                <li>
                  <input
                    type="radio"
                    :name="son.name"
                    value="right"
                    v-model="adjust"
                    @change="getAnswer('1',index,son.qtype)"
                  />
                  <span class="sql">√</span>
                </li>
                <li>
                  <input
                    type="radio"
                    :name="son.name"
                    value="wrong"
                    v-model="adjust"
                    @change="getAnswer('0',index,son.qtype)"
                  />
                  <span class="sql">×</span>
                </li>
              </ul>
            </div>
            <!-- 填空题 -->
            <div v-if="son.qtype==5">
              <ul>
                <li style="margin: 8px 0 8px 20px;">
                  <textarea
                    type="text"
                    :name="son.name"
                    v-model="answer[index]"
                    @change="getAnswer(son.oa,index,son.qtype)"
                    style="width: 240px;height: 25px;line-height: 25px;padding: 5px;font-size: 12px;border: none;overflow-y: auto;overflow-x: hidden;resize: none;border:1px solid grey;outline:none"
                  ></textarea>
                </li>
              </ul>
            </div>
            <!-- 简答题 -->
            <div v-if="son.qtype==4">
              <ul>
                <li style="margin: 8px 0px 8px 20px;">
                  <textarea
                    v-model="answer[index]"
                    @change="getAnswer(son.oa,index,son.qtype)"
                    style="width: 600px;height: 50px;line-height: 25px;padding: 5px;font-size: 12px;border: none;overflow-y: auto;overflow-x: hidden;resize: none;border:1px solid grey;outline:none"
                  ></textarea>
                </li>
              </ul>
            </div>
          </div>
          <el-button type="success" @click="submit" style="margin-top:20px">提交</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import overexam from "../components/overexam"
export default {
  name: "",
  components:{
    overexam
  },
  data() {
    return {
      url: axios.defaults.baseURL,
      student: JSON.parse(sessionStorage.getItem("userInfo")), //学生的信息
      item: {},
      question: "",
      answer: [],
      //   多选问题
      many: [],
      //单选问题
      one: [],
      adjust: [],
      storePaper: [],
      d: "",
      h: "",
      m: "",
      s: "",
      endtime: "",
      over:false
    };
  },
  created() {
    this.item = this.$route.query.item;
    console.log(this.item);
    this.endtime = this.item.end_time;
    axios
      .get(this.url + "/paper/getPaperQuestion", {
        params: {
          pid: this.item.pid
        }
      })
      .then(res => {
        console.log(res);
        this.question = res.data;
      });
    for (var i = 0; i < this.question.length; i++) {
      this.many[i] = "";
      for (var j = 0; j < 4; j++) {
        this.many[i][j] = false;
      }
    }
  },
  mounted() {
    this.countTime();
  },
  methods: {
    // 时间倒计时
    countTime: function() {
      // 定义结束时间戳
      const end = Date.parse(new Date(this.endtime));
      // 定义当前时间戳
      const now = Date.parse(new Date());
      // 做判断当倒计时结束时都为0
      if (now >= end) {
        this.d = 0;
        this.h = 0;
        this.m = 0;
        this.s = 0;
        //回到开始的页面
        this.over=true;
        return;
      }
      // 用结束时间减去当前时间获得倒计时时间戳
      const msec = end - now;
      let d = parseInt(msec / 1000 / 60 / 60 / 24); //算出天数
      let h = parseInt((msec / 1000 / 60 / 60) % 24); //算出小时数
      let m = parseInt((msec / 1000 / 60) % 60); //算出分钟数
      let s = parseInt((msec / 1000) % 60); //算出秒数
      //给数据赋值
      this.d = d;
      this.h = h > 9 ? h : "0" + h;
      this.m = m > 9 ? m : "0" + m;
      this.s = s > 9 ? s : "0" + s;
      //定义this指向
      const that = this;
      // 使用定时器 然后使用递归 让每一次函数能调用自己达到倒计时效果
      setTimeout(function() {
        that.countTime();
      }, 1000);
    },
    getAnswer(e, index, type) {
      if (type == 1) {
        console.log(e);
        this.answer[index] = e;
      }
      if (type == 2) {
        //拼接字符串
        this.answer[index] = "";
        for (var i = 0; i < 4; i++) {
          console.log(this.many[i]);
          if (this.many[i] == true) {
            if (i == 0) {
              this.answer[index] = "A" + this.answer[index];
              console.log(this.answer[index]);
            }
            if (i == 1) {
              this.answer[index] = "B" + this.answer[index];
            }
            if (i == 2) {
              this.answer[index] = "C" + this.answer[index];
            }
            if (i == 3) {
              this.answer[index] = "D" + this.answer[index];
            }
          }
        }
        console.log(this.answer[index]);
      }
      if (type == 3) {
        this.answer[index] = e;
      }
      //简答题
      if (type == 4) {
        console.log(this.answer[index]);
      }
      //填空题
      if (type == 5) {
        console.log(this.answer[index]);
      }
    },
    submit() {
      for (var x = 0; x < this.question.length; x++) {
        if (this.answer[x] == undefined || this.answer[x] == "") {
          this.$message({
            message: "警告哦，同学您还没有答完题，快去答题吧",
            type: "warning"
          });
          return;
        }
      }
      for (var i = 0; i < this.question.length; i++) {
        let pid = this.item.pid;
        let sid = this.student.id;
        let qid = i + 1;
        let answer = this.answer[i];
        this.storePaper[i] = { pid, qid, sid, answer };
      }
      let arrey = this.storePaper;
      axios
        .post(this.url + "/paper/storeAnswerAndJudge", this.storePaper)
        .then(res => {
          console.log(res);
          //回到开始的页面
          this.$router.push({
            path: "/student.html/home"
          });
        });
      // .fail(error => {
      //     console.log(res);
      // })
    }
  }
};
</script>

<style lang="less">
ul,
li {
  text-decoration: none !important;
  list-style-type: none !important;
  padding: 0px !important;
}
.type {
  color: #666;
  font-size: 12px;
  background: #e3f8ff;
  border: 1px solid #bfdfff;
  border-radius: 2px;
  padding: 2px 4px;
  margin-right: 5px;
}
input {
  width: 15px;
  height: 15px;
  float: left;
  // opacity: 0;
  margin: 10px 18px 0 9px;
}
.sql {
  padding: 6px 15px 6px 5px;
  display: block;
  width: 690px;
  height: auto;
}
//动态绑定样式
.div {
  border-bottom: 1px dashed #d9d9d9;
  margin-top: 10px;
}
//答题卡的动态样式
.show {
  background-color: #e3f8ff !important;
  border: 1px solid #bfdfff;
}
.detailPaper {
  margin-top: 4px;
  padding: 4px;
  box-sizing: border-box;
  min-height: 100%;
  width: 100%;
  background-color: rgba(255, 255, 255, 0.74);
  box-shadow: 0 2px 12px 0 rgba(64, 158, 255, 0.18);
  .wrap {
    display: flex;
    align-content: center;
    border-bottom: 1px solid #eee;
    .clock {
      padding-top: 20px;
      vertical-align: middle;
      width: 35%;
      text-align: center;
    }
    .ti {
      margin-left: 40px;
      box-sizing: border-box;
      padding-bottom: 10px;
      .title {
        padding: 10px 0;
        font-size: 22px;
        font-weight: bold;
      }
      .info {
        font-size: 12px;
        color: grey;
        line-height: 20px;
        line-height: 20px;
        .endtime {
          margin-left: 20px;
        }
      }
    }
  }
  .content {
    display: flex;
    margin-top: 10px;
    .left {
      display: inline-block;
      .card {
        cursor: pointer;
        padding: 10px;
        .title {
          font-size: 16px;
          height: 30px;
          line-height: 30px;
          text-align: center;
        }
        .detail {
          .small {
            display: inline-block;
            margin: 5px;
            font-size: 14px;
            width: 35px !important;
            height: 35px !important;
            text-align: center;
            line-height: 35px;
            background-color: #f0f0f0;
            color: #8590a6;
          }
        }
      }
    }
    .right {
      color: rgb(55, 58, 60);
      font-family: OpenSans, Arial, sans-serif;
      font-size: 14px;
      display: inline-block;
      padding: 5px;
      .noover {
        .question {
          display: inline-block;
        }
      }
    }
  }
}
</style>