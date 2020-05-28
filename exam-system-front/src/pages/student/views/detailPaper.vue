<template>
  <div class="detailPaper">
    <div class="ti">
      <div class="title">{{item.pname}}</div>
      <div class="info">
        <span class="teacher">老师：{{item.teacher}}</span>
        <span class="endtime">截至时间：{{item.end_time}}</span>
      </div>
    </div>
    <div class="content">
      <div class="left" style="width:30%">
        <div class="card">
          <div class="title">答题卡</div>
          <div class="detail">
            <div class="small" v-for="item,index in question">{{1+index}}</div>
          </div>
        </div>
      </div>
      <div class="right" style="width:70%">
        <div class="over"></div>
        <div class="noover">
          <div
            class="div"
            v-for="(son,index) in question"
            :key="index"
            style="border-bottom: 1px dashed #d9d9d9; margin-top:10px;"
          >
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
                    v-model="many[index][0]"
                    @change="getAnswer(son.oa,index,son.qtype)"
                  />
                  <span class="sql">{{son.oa}}</span>
                </li>
                <li>
                  <input
                    type="checkbox"
                    :name="son.name"
                    :value="son.ob"
                    v-model="many[index][1]"
                    @change="getAnswer(son.ob,index,son.qtype)"
                  />
                  <span class="sql">{{son.ob}}</span>
                </li>
                <li>
                  <input
                    type="checkbox"
                    :name="son.name"
                    :value="son.oc"
                    v-model="many[index][2]"
                    @change="getAnswer(son.oc,index,son.qtype)"
                  />
                  <span class="sql">{{son.oc}}</span>
                </li>
                <li>
                  <input
                    type="checkbox"
                    :name="son.name"
                    :value="son.od"
                    v-model="many[index][3]"
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
                    style="width: 240px;height: 25px;line-height: 25px;padding: 5px;font-size: 12px;border: none;overflow-y: auto;overflow-x: hidden;resize: none;border:1px solid grey;outline:none"></textarea>
                </li>
              </ul>
            </div>
            <!-- 简答题 -->
            <div v-if="son.qtype==4">
              <ul>
                <li style="margin: 8px 0px 8px 20px;">
                  <textarea v-model="answer[index]" @change="getAnswer(son.oa,index,son.qtype)" 
                  style="width: 600px;height: 50px;line-height: 25px;padding: 5px;font-size: 12px;border: none;overflow-y: auto;overflow-x: hidden;resize: none;border:1px solid grey;outline:none"></textarea>
                </li>
              </ul>
            </div>
          </div>
          <el-button type="success" @click="submit">提交</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "",
  data() {
    return {
      url: axios.defaults.baseURL,
      student: JSON.parse(sessionStorage.getItem("userInfo")), //学生的信息
      item: {
        id: 4,
        username: "啊啊啊啊啊",
        password: "98f6bcd4621d373cade4e832627b4f6",
        email: "aaa",
        sex: "女",
        headUrl: "/img/4user5859876.jpg",
        path: "/yfn/4user5859876.jpg",
        classno: "2,11",
        college: null,
        status: 0
      },
      question: "",
      answer: [],
      //   多选问题
      many: [[]],
      //单选问题
      one: [],
      adjust: [],
      storePaper: []
    };
  },
  created() {
    this.item = this.$route.query.item;
    console.log(this.item);
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
    for(var i=0;i<this.question.length;i++){
        this.many[i]=''
        for(var j=0;j<4;j++){
            this.many[i][j]=false;
        }
    }
  },
  methods: {
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
.detailPaper {
  margin-top: 4px;
  padding: 4px;
  box-sizing: border-box;
  min-height: 100%;
  width: 100%;
  background-color: rgba(255, 255, 255, 0.74);
  box-shadow: 0 2px 12px 0 rgba(64, 158, 255, 0.18);
  .ti {
    box-sizing:border-box;
    padding-bottom: 10px;
    border-bottom: 1px solid #eee;
    .title {
      padding: 10px 0;
      font-size: 22px;
      font-weight: bold;
      text-align: center;
    }
    .info {
      text-align: center;
      font-size: 12px;
      color: grey;
      line-height: 20px;
      line-height: 20px;
      .endtime {
        margin-left: 20px;
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