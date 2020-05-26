<template>
    <div class="exam">
        <div class="formList">
            <el-row class="choiceOne">
                <p v-show="examDetail.choiceOne">一、单选题</p>
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type==='choiceOne')" :key="index"
                        :ref="'choiceOne'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                >
                    <p class="question">{{item.question}}</p>
                    <el-form-item>
                        <el-radio-group v-model="item.rightAnswer">
                            <el-radio :label="'A:'+item.answerA"></el-radio>
                            <el-radio :label="'B:'+item.answerB"></el-radio>
                            <el-radio :label="'C:'+item.answerC"></el-radio>
                            <el-radio v-show="item.answerD" :label="'D:'+item.answerD"></el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-row class="choiceMany">
                <p v-show="examDetail.choiceMany">二、多选题</p>
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type==='choiceMany')" :key="index"
                        :ref="'choiceMany'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                >
                    <p class="question">{{item.question}}</p>
                    <el-form-item>
                        <el-checkbox-group v-model="item.rightAnswer">
                            <el-checkbox :label="'A:'+item.answerA"></el-checkbox>
                            <el-checkbox :label="'B:'+item.answerB"></el-checkbox>
                            <el-checkbox :label="'C:'+item.answerC"></el-checkbox>
                            <el-checkbox v-show="item.answerD" :label="'D:'+item.answerD"></el-checkbox>
                        </el-checkbox-group>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-row class="judgeTest">
                <p v-show="examDetail.judgeTest">三、判断题</p>
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type==='judgeTest')" :key="index"
                        :ref="'judgeTest'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                >
                    <p class="question">{{item.question}}</p>
                    <el-form-item label="填写答案" prop="rightAnswer">
                        <el-radio-group v-model="item.rightAnswer">
                            <el-radio label="正确"></el-radio>
                            <el-radio label="错误"></el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-row class="feedFull">
                <p v-show="examDetail.feedFull">四、填空题</p>
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type==='feedFull')" :key="index"
                        :ref="'feedFull'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                >
                    <p class="question">{{item.question}}</p>
                    <el-form-item prop="rightAnswer">
                        <el-col class="line" :span="6" v-model="item.rightAnswer">
                            <el-input placeholder="答案内容，多个答案用空格分隔" v-model="item.question"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-row class="shortAnswer">
                <p v-show="examDetail.shortAnswer">五、简答题</p>
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type==='shortAnswer')" :key="index"
                        :ref="'shortAnswer'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                        :rules="rules"
                        size="mini"
                >
                    <p class="question">{{item.question}}</p>
                    <el-form-item label="填写答案" prop="rightAnswer">
                        <el-col class="line" :span="6" v-model="item.rightAnswer">
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
                questionList:[],
                labelPosition:"left"
            }
        },
        props:{
            examDetail:{
                type:Object,
                require:true
            }
        },
        created() {
            this.questionList = JSON.parse(this.examDetail.questionList);
        }
    }
</script>

<style scoped>

</style>