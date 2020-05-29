<template>
    <div class="exam">
        <el-button style="display: block;margin-bottom: 10px" type="primary" size="mini" @click="prevStep">返回</el-button>
        <div class="formList">
            <el-row class="choiceOne">
                <p class="title" v-show="questionList.filter(item=>item.type===1).length">单选题</p>
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type===1)" :key="index"
                        :ref="'choiceOne'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                >
                    <p class="question">{{index+1}}、{{item.question}}</p>
                    <el-form-item>
                        <el-radio-group v-model="item.answer">
                            <el-radio :label="'A'">A:{{item.oa}}</el-radio>
                            <el-radio :label="'B'">B:{{item.ob}}</el-radio>
                            <el-radio :label="'C'">C:{{item.oc}}</el-radio>
                            <el-radio v-show="item.od" :label="'D'">D:{{item.od}}</el-radio>
                        </el-radio-group>
                    </el-form-item>
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
                        <el-checkbox-group v-model="item.answer.split('')">
                            <el-checkbox :label="'A'">A:{{item.oa}}</el-checkbox>
                            <el-checkbox :label="'B'">B:{{item.ob}}</el-checkbox>
                            <el-checkbox :label="'C'">C:{{item.oc}}</el-checkbox>
                            <el-checkbox v-show="item.od" :label="'D'">D:{{item.od}}</el-checkbox>
                        </el-checkbox-group>
                    </el-form-item>
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
                        <el-radio-group v-model="item.answer">
                            <el-radio label="1">正确</el-radio>
                            <el-radio label="0">错误</el-radio>
                        </el-radio-group>
                    </el-form-item>
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
                        <el-col class="line" :span="6" v-model="item.answer">
                            <el-input placeholder="答案内容，多个答案用空格分隔" v-model="item.question"
                            ></el-input>
                        </el-col>
                    </el-form-item>
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
                        <el-col class="line" :span="6" v-model="item.answer">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="答案内容" v-model="item.question"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                </el-form>
            </el-row>

        </div>
    </div>
</template>

<script>
    export default {
        name: "exam",
        data(){
            return{
                labelPosition:"left",
            }
        },
        props:{
            questionList:{
                type:Array,
                require:true
            }
        },
        created() {
            this.questionList.forEach(item=>{
                if(item.type==='2'){
                    item.answer = item.answer.join('')
                }
            })
        },
        methods:{
            prevStep(){
                this.$emit("back")
            }
        }
    }
</script>

<style scoped>
.title{
    font-weight: bold;
    color: #409EFF;
}
</style>