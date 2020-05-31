<template>
  <div>
    <div v-for="(son,index) in question" :key="index" class="div">
      {{index+1}}、
      <span class="type" v-if="son.qtype== 1">单选</span>
      <span
        class="type"
        v-if="son.qtype== 2"
        style="background: #ffe2d9;border: 1px solid #ffb399;"
      >多选</span>
      <span class="type" v-if="son.qtype== 3" style="background: #eee;border: 1px solid #ddd;">判断</span>
      <span class="type" v-if="son.qtype== 4">简答</span>
      <span class="type" v-if="son.qtype== 5" style="background: #eee;border: 1px solid #ddd;">填空</span>
      <span>({{son.qscore}}分)</span>
      <span class="question">{{son.question }}</span>
      <!-- 单选题 -->
      <div v-if="son.qtype==1">
        <el-form
          :ref="'choiceOne'+index"
          :model="son"
          label-width="30px"
          :label-position="labelPosition"
        >
          <el-form-item style="margin-bottom:1px !important">
            <el-radio-group v-model="studentAns[index].answer" v-if="studentAns.length!=0"> 
              <el-radio :label="'A'">A:{{son.oa}}</el-radio>
              <el-radio :label="'B'">B:{{son.ob}}</el-radio>
              <el-radio :label="'C'">C:{{son.oc}}</el-radio>
              <el-radio v-show="son.od" :label="'D'">D:{{son.od}}</el-radio>
            </el-radio-group>
            <el-radio-group v-else>
              <el-radio :label="'A'">A:{{son.oa}}</el-radio>
              <el-radio :label="'B'">B:{{son.ob}}</el-radio>
              <el-radio :label="'C'">C:{{son.oc}}</el-radio>
              <el-radio v-show="son.od" :label="'D'">D:{{son.od}}</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
        <div class="adjust">
             <span v-if="progress">你的答案的是<span style="color:red;" v-if="son.answer==studentAns[index].answer">正确</span><span style="color:red" v-else>错误</span>的,</span>
            正确的答案是<span style="color:green">{{son.answer}}</span>
        </div>
      </div>
      <!-- 多选题 -->
      <div v-if="son.qtype==2">
        <el-form
          :ref="'choiceMany'+index"
          :model="son"
          label-width="30px"
          :label-position="labelPosition"
        >
          <el-form-item style="margin-bottom:1px !important">
            <el-checkbox-group v-model="studentAns[index].answer.split('')" disabled v-if="studentAns.length!=0">
              <el-checkbox :label="'A'">A:{{son.oa}}</el-checkbox>
              <el-checkbox :label="'B'">B:{{son.ob}}</el-checkbox>
              <el-checkbox :label="'C'">C:{{son.oc}}</el-checkbox>
              <el-checkbox v-show="son.od" :label="'D'">D:{{son.od}}</el-checkbox>
            </el-checkbox-group>
            <el-checkbox-group v-else v-model="a" disabled>
              <el-checkbox :label="'A'">A:{{son.oa}}</el-checkbox>
              <el-checkbox :label="'B'">B:{{son.ob}}</el-checkbox>
              <el-checkbox :label="'C'">C:{{son.oc}}</el-checkbox>
              <el-checkbox v-show="son.od" :label="'D'">D:{{son.od}}</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-form>
         <div class="adjust">
             <span v-if="progress">你的答案的是<span style="color:red;" v-if="son.answer==studentAns[index].answer">正确</span><span style="color:red" v-else>错误</span>的,</span>
            正确的答案是<span style="color:green">{{son.answer}}</span>
        </div>
      </div>
      <!-- //判断题 -->
      <div v-if="son.qtype==3">
        <el-form
          :ref="'choiceOne'+index"
          :model="son"
          label-width="30px"
          :label-position="labelPosition"
        >
          <el-form-item style="margin-bottom:1px !important">
            <el-radio-group disabled v-model="studentAns[index].answer" v-if="studentAns.length!=0">
              <el-radio :label="'1'">对</el-radio>
              <el-radio :label="'0'">错</el-radio>
            </el-radio-group >
             <el-radio-group v-else disabled>
              <el-radio :label="'1'">对</el-radio>
              <el-radio :label="'0'">错</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
         <div class="adjust">
             <span v-if="progress">你的答案的是<span style="color:red;" v-if="son.answer==studentAns[index].answer">正确</span><span style="color:red" v-else>错误</span>的,</span>
            正确的答案是<span style="color:green">{{son.answer}}</span>
        </div>
      </div>
      <!-- 填空题 -->
      <div v-if="son.qtype==5">
        <ul>
          <li style="margin: 8px 0 8px 20px;">
            <textarea
              type="text"
              :name="son.name"
              v-model="studentAns[index].answer"
              v-if="studentAns.length!=0"
              readonly
              style="width: 240px;height: 25px;line-height: 25px;padding: 5px;font-size: 12px;border: none;overflow-y: auto;overflow-x: hidden;resize: none;border:1px solid grey;outline:none"
            ></textarea>
            <textarea
                    readonly
              type="text"
              :name="son.name"
              v-else
              style="width: 240px;height: 25px;line-height: 25px;padding: 5px;font-size: 12px;border: none;overflow-y: auto;overflow-x: hidden;resize: none;border:1px solid grey;outline:none"
            ></textarea>
          </li>
        </ul>
          <div class="adjust">
             <span v-if="progress">你的答案的是<span style="color:red;" v-if="studentAns[index].answer.indexOf(son.answer)!=-1">正确</span><span style="color:red" v-else>错误</span>的,</span>
            正确的答案是<span style="color:green">{{son.answer}}</span>
        </div>
      </div>
      <!-- 简答题 -->
      <div v-if="son.qtype==4">
        <ul>
          <li style="margin: 8px 0px 8px 20px;">
            <textarea
                    readonly
              v-model="studentAns[index].answer"
              v-if="studentAns.length!=0"
              style="width: 600px;height: 50px;line-height: 25px;padding: 5px;font-size: 12px;border: none;overflow-y: auto;overflow-x: hidden;resize: none;border:1px solid grey;outline:none"
            ></textarea>
            <textarea
              v-else
              readonly
              style="width: 600px;height: 50px;line-height: 25px;padding: 5px;font-size: 12px;border: none;overflow-y: auto;overflow-x: hidden;resize: none;border:1px solid grey;outline:none"
            ></textarea>
          </li>
        </ul>
         <div class="adjust">
             <!-- <span v-if="progress">你的答案的是<span style="color:red;" v-if="son.answer==studentAns[index].answer">正确</span><span style="color:red" v-else>错误</span>的,</span> -->
            正确的答案是<span style="color:green">{{son.answer}}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "exam",
  data() {
    return {
    a:[],
      url: axios.defaults.baseURL,
      labelPosition: "left",
      student: JSON.parse(sessionStorage.getItem("userInfo")), //学生的信息
      studentAns:[]
    };
  },
  props: {
    question: {
      type: Array,
      require: true
    },
    item: {
       type: Object,
       require: true
    },
    progress:{
        type:Boolean,
        require:true
    }
  },
  created() {
    console.log(this.question);
    console.log(this.item);
    console.log(this.progress);
   //请求你做的答案
   axios.get(this.url+'/paper/getClassAnswer',{
       params:{
           sid:this.student.id,
           pid:this.item.pid,
           qid:1
       }
   }).then(res => {
       console.log(res);
       this.studentAns=res.data;
       for(var i=0;i<this.question.length;i++){
        console.log(this.question[i]);
        if(this.question[i].qtype==3){

            if(this.question[i].answer==0){
                this.question[i].answer='对'
            }
            else{
                this.question[i].answer='错'
            }
        }
    }
   })
  },
  methods: {}
};
</script>

<style scoped>
.title {
  font-weight: bold;
  color: #409eff;
}
.el-form-item__content{
    margin-left:20px !important;
}
.adjust{
    margin-top:5px;
    margin-bottom:20px;
    margin-left:30px;font-size:14px;color:#373a3c;font-weight:bolder
}
</style>