<template>
    <div class="showStuPaper">
        <el-button style="display: block" type="primary" size="mini" @click="prevStep">返回</el-button>
        <div class="formList">
            <el-row class="choiceOne">
                <p class="title" v-show="questionList.filter(item=>item.qtype===1).length">单选题</p>
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type===1)" :key="index"
                        :ref="'choiceOne'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                >
                    <p class="question">{{index+1}}、{{item.question}}</p>
                    <el-form-item>
                        <el-radio-group v-model="item.stuAnswer" :disabled="true">
                            <el-radio :label="'A'">A:{{item.oa}}</el-radio>
                            <el-radio :label="'B'">B:{{item.ob}}</el-radio>
                            <el-radio :label="'C'">C:{{item.oc}}</el-radio>
                            <el-radio v-show="item.od" :label="'D'">D:{{item.od}}</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <p class="result" :class="item.stuAnswer===item.answer?'right':'wrong'">
                        <span :class="item.stuAnswer===item.answer?'el-icon-circle-check':'el-icon-circle-close'">
                            {{item.stuAnswer===item.answer?"正确":"错误"}}
                        </span>
                        <span style="margin-left: 10px">答案：{{item.answer}}</span>
                        <span style="position: absolute;right: 10px">得分：{{item.getScore}}</span>
                    </p>
                </el-form>
            </el-row>
            <el-row class="choiceMany">
                <p  class="title" v-show="questionList.filter(item=>item.type===2).length">多选题</p>
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type===2)" :key="index"
                        :ref="'choiceMany'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                >
                    <p class="question">{{index+1}}、{{item.question}}</p>
                    <el-form-item>
                        <el-checkbox-group v-model="item.stuAnswer.split('')" :disabled="true">
                            <el-checkbox :label="'A'">A:{{item.oa}}</el-checkbox>
                            <el-checkbox :label="'B'">B:{{item.ob}}</el-checkbox>
                            <el-checkbox :label="'C'">C:{{item.oc}}</el-checkbox>
                            <el-checkbox v-show="item.od" :label="'D'">D:{{item.od}}</el-checkbox>
                        </el-checkbox-group>
                    </el-form-item>
                    <p class="result" :class="item.stuAnswer.split('').sort().join('')===item.answer?'right':'wrong'">
                        <span :class="item.stuAnswer.split('').sort().join('')===item.answer?'el-icon-circle-check':'el-icon-circle-close'">
                            {{item.stuAnswer.split('').sort().join('')===item.answer?"正确":"错误"}}
                        </span>
                        <span style="margin-left: 10px">答案：{{item.answer.split('').join('、')}}</span>
                        <span style="position: absolute;right: 10px">得分：{{item.getScore}}</span>
                    </p>
                </el-form>

            </el-row>
            <el-row class="judgeTest">
                <p class="title" v-show="questionList.filter(item=>item.type===3).length">判断题</p>
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type===3)" :key="index"
                        :ref="'judgeTest'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                >
                    <p class="question">{{index+1}}、{{item.question}}</p>
                    <el-form-item prop="rightAnswer">
                        <el-radio-group v-model="item.stuAnswer" :disabled="true">
                            <el-radio label="1">正确</el-radio>
                            <el-radio label="0">错误</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <p class="result" :class="item.stuAnswer===item.answer?'right':'wrong'">
                        <span :class="item.stuAnswer===item.answer?'el-icon-circle-check':'el-icon-circle-close'">
                            {{item.stuAnswer===item.answer?"正确":"错误"}}
                        </span>
                        <span style="margin-left: 10px">答案：{{item.answer==='1'?"正确":"错误"}}</span>
                        <span style="position: absolute;right: 10px">得分：{{item.getScore}}</span>
                    </p>
                </el-form>
            </el-row>
            <el-row class="feedFull">
                <p class="title" v-show="questionList.filter(item=>item.type===5).length">填空题</p>
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type===5)" :key="index"
                        :ref="'feedFull'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                >
                    <p class="question">{{index+1}}、{{item.question}}</p>
                    <el-form-item prop="rightAnswer">
                        <el-col class="line" :span="6">
                            <el-input
                                    :readonly="true"
                                    placeholder="答案内容，多个答案用空格分隔" v-model="item.stuAnswer"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                    <p class="result" :class="item.stuAnswer===item.answer?'right':'wrong'">
                        <span :class="item.stuAnswer===item.answer?'el-icon-circle-check':'el-icon-circle-close'">
                            {{item.stuAnswer===item.answer?"正确":"错误"}}
                        </span>
                        <span style="margin-left: 10px">答案：{{item.answer}}</span>
                        <span style="position: absolute;right: 10px">得分：{{item.getScore}}</span>
                    </p>
                </el-form>
            </el-row>
            <el-row class="shortAnswer">
                <p class="title" v-show="questionList.filter(item=>item.type===4).length">简答题</p>
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type===4)" :key="index"
                        :ref="'shortAnswer'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                        size="mini"
                >
                    <p class="question">{{index+1}}、{{item.question}}</p>
                    <el-form-item prop="rightAnswer">
                        <el-col class="line" :span="6">
                            <el-input
                                    :readonly="true"
                                    type="textarea"
                                    :rows="2"
                                    placeholder="答案内容" v-model="item.stuAnswer"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                    <p
                            v-if="item.getScore>=0"
                            class="result" :class="item.getScore>=item.qscore?'right':'wrong'">
                        <span :class="item.getScore >= item.qscore?'el-icon-circle-check':'el-icon-circle-close'">
                            {{item.getScore >= item.qscore?"正确":"错误"}}
                        </span>
                        <span style="margin-left: 10px">答案：{{item.answer}}</span>
                        <span style="position: absolute;right: 10px">得分：{{item.getScore}}</span>
                    </p>
                    <el-form-item v-else label="给出得分">
                        <el-radio-group  v-model="score[index].getScore">
                            <el-radio :label="0">0分</el-radio>
                            <el-radio :label="1">1分</el-radio>
                            <el-radio :label="2">2分</el-radio>
                            <el-radio :label="3">3分</el-radio>
                            <el-radio :label="4">4分</el-radio>
                            <el-radio :label="5">5分</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-button v-if="score.length" style="display: block;margin-top: 10px;" type="primary" size="small" @click="submitScore">提交评分</el-button>
        </div>
    </div>
</template>

<script>
    import {getStuPaper,addScore} from "../../api/stuGrade";

    export default {
        name: "showStuPaper",
        data(){
            return{
                questionList:[],
                labelPosition:"left",
                score:[]
            }
        },
        created() {
            let list = JSON.parse(sessionStorage.getItem("paperInfo"))
            let que = []
            getStuPaper({
                sid:this.$route.params.sid,
                qid:0,
                pid:this.$route.params.pid
            }).then(res=>{
                let answer = res.data.map(item=>({
                    stuAnswer:item.answer,
                    getScore:item.getscore
                }))
                for (let i = 0; i < answer.length; i++) {
                    que[i] = Object.assign(list[i],answer[i]);
                }
                this.questionList = que;
                this.score = this.questionList.filter(item=>item.getScore===-1)
                    .map(item=>({
                    getScore:0,
                    qid:item.qid
                }))
            }).catch(err=>{
                throw err;
            })
        },
        methods:{
            prevStep(){
                this.$router.push({
                    name: 'showStudent',
                    params: {
                        classInfo:{
                            id:this.$route.params.classno,
                        },
                        row:{
                            pid:this.$route.params.pid,
                            full_score:this.$route.params.fullScore
                        }
                    }
                })
            },
            submitScore(){
                if(confirm("确定要提交吗")){
                    this.score.forEach((item)=>{
                        addScore({
                            pid:this.$route.params.pid,
                            qid:item.qid,
                            sid:this.$route.params.sid,
                            getscore:item.getScore
                        }).then(res=>{
                            if(res.data){
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$router.push({
                                    name: 'showStudent',
                                    params: {
                                        classInfo:{
                                            id:this.$route.params.classno,
                                        },
                                        row:{
                                            pid:this.$route.params.pid,
                                            full_score:this.$route.params.fullScore
                                        }
                                    }
                                })
                            }
                        }).catch(err=>{
                            throw err;
                        })
                    })

                }
            }
        }
    }
</script>

<style scoped>
    .result{
        padding: 5px;
        border-radius: 5px;
        height: 20px;
        line-height: 20px;
    }
    .result.right{
        color: #89c255;
        border:1px solid #89c255;
    }
    .result.wrong{
        color: #f5746c;
        border:1px solid #f5746c;
    }
</style>