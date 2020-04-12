<template>
    <div class="step3">
        <el-button style="display: block" type="primary" size="mini" @click="prevStep">上一步</el-button>
        <div class="formList">
            <el-form
                    
                    ref="questionList"
                    :model="questionList"
                    label-width="80px"
                    :label-position="labelPosition"
                    :rules="rules"
                    size="mini"
            >
                <el-row class="choiceOne"
                        v-for="(item,index) in questionList.filter(it=>it.type==='choiceOne')" :key="'choiceOne'+index"
                >
                    <el-form-item :label="'单选'+(1+index)" prop="question">
                        <el-col class="line" :span="6">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="题目内容" v-model="item.question"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="填写选项" required>
                        <template>
                            <el-form-item label="选项A" prop="answerA">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="请输入选项A" v-model="item.answerA"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                            <el-form-item label="选项B" prop="answerB">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="请输入选项B" v-model="item.answerB"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                            <el-form-item label="选项C" prop="answerC">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="请输入选项C" v-model="item.answerC"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                            <el-form-item label="选项D">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="请输入选项D" v-model="item.answerD"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                        </template>
                    </el-form-item>
                    <el-form-item label="填写答案" prop="rightAnswer">
                        <el-radio-group v-model="item.rightAnswer">
                            <el-radio label="A"></el-radio>
                            <el-radio label="B"></el-radio>
                            <el-radio label="C"></el-radio>
                            <el-radio label="D"></el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-row>
                <el-row class="choiceMany"
                        v-for="(item,index) in questionList.filter(it=>it.type==='choiceMany')" :key="'choiceMany'+index"
                >
                    <el-form-item :label="'多选'+(1+index)" prop="question">
                        <el-col class="line" :span="6">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="题目内容" v-model="item.question"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="填写选项" required>
                        <template>
                            <el-form-item label="选项A" prop="answerA">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="请输入选项A" v-model="item.answerA"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                            <el-form-item label="选项B" prop="answerB">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="请输入选项B" v-model="item.answerB"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                            <el-form-item label="选项C" prop="answerC">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="请输入选项C" v-model="item.answerC"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                            <el-form-item label="选项D">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="请输入选项D" v-model="item.answerD"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                        </template>
                    </el-form-item>
                    <el-form-item label="填写答案" prop="rightAnswer">
                        <el-checkbox-group v-model="item.rightAnswer">
                            <el-checkbox label="A"></el-checkbox>
                            <el-checkbox label="B"></el-checkbox>
                            <el-checkbox label="C"></el-checkbox>
                            <el-checkbox label="D"></el-checkbox>
                        </el-checkbox-group>
                    </el-form-item>
                </el-row>
                <el-row class="judgeTest"
                        v-for="(item,index) in questionList.filter(it=>it.type==='judgeTest')" :key="'judgeTest'+index"
                >
                    
                    <el-form-item :label="'判断'+(1+index)" prop="question">
                        <el-col class="line" :span="6">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="题目内容" v-model="item.question"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="填写答案" prop="rightAnswer">
                        <el-radio-group v-model="item.rightAnswer">
                            <el-radio label="正确"></el-radio>
                            <el-radio label="错误"></el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-row>
                <el-row class="feedFull"
                        v-for="(item,index) in questionList.filter(it=>it.type==='feedFull')" :key="'feedFull'+index"
                >
                    <el-form-item :label="'填空题'+(1+index)" prop="question">
                        <el-col class="line" :span="6">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="题目内容" v-model="item.question"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="填写答案" prop="rightAnswer">
                        <el-col class="line" :span="6" v-model="item.rightAnswer">
                            <el-input placeholder="答案内容，用逗号分隔" v-model="item.question"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                </el-row>
                <el-row class="shortAnswer"
                        v-for="(item,index) in questionList.filter(it=>it.type==='shortAnswer')" :key="'shortAnswer'+index"
                >
                    <el-form-item :label="'填空题'+(1+index)" prop="question">
                        <el-col class="line" :span="6">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="答案内容" v-model="item.question"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="填写答案" prop="rightAnswer">
                        <el-col class="line" :span="6" v-model="item.rightAnswer">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="答案内容" v-model="item.question"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                </el-row>
                <el-form-item>
                    <el-button type="primary"
                               @click="submitForm('questionList')"
                    >
                        生成试卷
                    </el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
	import {mapMutations, mapGetters} from "vuex";
	
	export default {
		name: "step3",
		data() {
			return {
				labelPosition: "left",
				questionNum: 0,
				questionList: [],
				rules: {
					question: [
						{type: 'string', required: true, message: '请输入题目', trigger: 'blur'},
					],
					answerA: [
						{type: 'string', required: true, message: '请填写选项', trigger: 'blur'}
					],
					answerB: [
						{type: 'string', required: true, message: '请填写选项', trigger: 'blur'}
					],
					answerC: [
						{type: 'string', required: true, message: '请填写选项', trigger: 'blur'}
					],
					rightAnswer: [
						{required: true, message: "请选择答案", trigger: 'change'},
					
					]
				},
				questionListFinal: []
			}
		},
		computed: {
			...mapGetters([
				"newExamDetail"
			])
		},
		created() {
			this.questionList = JSON.parse(this.newExamDetail.questionList);// 题目列表
			console.log(this.questionList)
		},
		methods: {
			...mapMutations({
				setStep: "SET_STEP",// 设置阶段
				//setQuestions:"SET_QUESTIONLIST"// 设置考试问题
			}),
			prevStep() {// 点击上一步
				this.setStep(2);
			},
			submitForm(formName) {// 每道试题的表单验证
				this.$refs[formName].validate((valid) => {
					if (valid) {//表单验证成功
						// this.questionListFinal.push(item);
                        alert("成功")
					} else {
						return false;
					}
				});
			},
		}
	}
</script>

<style scoped lang="less">
    .formList .el-form-item--mini.el-form-item {
        margin-bottom: 15px !important;
    }
    .el-form{
        margin-top: 10px;
    }
    .el-form > .el-row{
        margin-top: 10px;
        border-bottom: 1px solid #dddddd;
    }
</style>