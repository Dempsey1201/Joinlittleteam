<template>
    <div class="step4">
        <p>
            <el-button style="display: block" type="primary" size="mini" @click="prevStep">上一步</el-button>
            试题预览</p>
        <div class="formList">
            <el-row class="choiceOne">
                <p v-show="newExamDetail.choiceOne">一、单选题</p>
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
                <p v-show="newExamDetail.choiceMany">二、多选题</p>
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
                <p v-show="newExamDetail.judgeTest">三、判断题</p>
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
                <p v-show="newExamDetail.feedFull">四、填空题</p>
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
                <p v-show="newExamDetail.shortAnswer">五、简答题</p>
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
            <el-button type="primary"
                       @click="complete"
            >完成
            </el-button>
        </div>
    </div>
</template>

<script>
    import {mapMutations,mapGetters,mapActions} from "vuex";
	export default {
		name: "b_newExam_step4",
        data(){
			return{
				questionList:[],
				labelPosition:"left"
            }
        },
        computed:{
			...mapGetters([
				"newExamDetail"
            ])
        },
        created() {
			this.questionList = JSON.parse(this.newExamDetail.questionList);
        },
        methods:{
			...mapMutations({
				setStep:"SET_STEP"
            }),
            ...mapActions([
            	"stepFour"
            ]),
	        complete(){
		        this.$message({
			        message: '新建试卷成功',
			        type: 'success'
		        });
		        this.stepFour();// 清空数据，初始化
		        this.setStep(1);// 返回到第一步
            },
	        prevStep(){// 点击上一步
		        this.setStep(3);
	        },
        }
	}
</script>

<style scoped lang="less">
    .step4 > p{
        font-size: 16px;
        color: #409eff;
    }
    .formList{
        color: #424242;
        
    }
    .el-form{
        background-color: #eee;
        padding: 10px;
        border-radius: 10px;
        .el-form-item{
            margin-bottom: 0!important;
        }
    }
    p> .el-button{
        display: inline-block !important;
    }
    .formList .el-button{
        margin-top: 15px;
    }
</style>