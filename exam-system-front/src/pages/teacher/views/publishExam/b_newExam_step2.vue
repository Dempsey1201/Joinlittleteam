<template>
    <div class="step2">
        <el-button style="display: block" type="primary" size="mini" @click="prevStep">上一步</el-button>
        <el-form
                ref="form"
                :model="form"
                label-width="80px"
                :label-position="labelPosition"
                :rules="rules"
                style="margin-top: 15px"
        >
            <el-form-item label="截止时间" prop="end_time">
                <el-date-picker
                        v-model="form.end_time"
                        type="datetime"
                        placeholder="选择日期时间">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="测试时间" required>
                <el-radio-group v-model="form.check">
                    <el-radio label="无限制"></el-radio>
                    <el-radio label="有限制"></el-radio>
                </el-radio-group>
                <el-row>
                    <el-col :span="4">
                        <el-form-item >
                            <el-input-number v-show="form.check==='有限制'" v-model="form.last_time" :step="10" :min="10" :max="240" label="描述文字"></el-input-number>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form-item>
            <el-form-item label="题目数量" required>
                <el-row>
                    输入每种题型的数量，例如：选择题：5 个
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="6">
                        <el-form-item label="单选题：">
                            <el-input-number v-model="form.choiceOne" :min="0" :max="10" label="描述文字"></el-input-number>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="多选题：">
                            <el-input-number v-model="form.choiceMany" :min="0" :max="10" label="描述文字"></el-input-number>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="判断题：">
                            <el-input-number v-model="form.judgeTest" :min="0" :max="10" label="描述文字"></el-input-number>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="6">
                        <el-form-item label="填空题：">
                            <el-input-number v-model="form.feedFull" :min="0" :max="10" label="描述文字"></el-input-number>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="简答题：">
                            <el-input-number v-model="form.shortAnswer" :min="0" :max="10" label="描述文字"></el-input-number>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('form')">下一步</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
	import {mapActions, mapMutations,mapGetters} from "vuex";
	export default {
		name: "step2",
		data() {
			let validateNumber = (rule, value, callback) => {
				let reg = /\d+/g;
				
				if (!reg.test(value)) {
					callback(new Error('请输入数字'));
				} else {
					callback();
				}
			};
			return {
				form: {//表单数据内容
					end_time: "",//截至时间
					check: "无限制",//试卷时候限制时间
					last_time:10,// 考试时间/分钟
                    choiceOne:0,// 单选题
					choiceMany:0,// 多选题
					judgeTest:0,// 判断题
					feedFull:0,// 填空题
                    shortAnswer:0,// 简答题
                    questionList:[]
				},
                num:1,
				labelPosition: "right",
				rules: {
					//表单基础验证内容
					end_time: [
						{type: 'date', required: true, message: '请选择日期时间', trigger: 'blur'}
					],
                    last_time:[
						{
							required: true,
							message: '考试时间',
							trigger: 'blur'
						}
                    ],
                    number:[
                        {validator:validateNumber,required:true,trigger: 'blur'}
                    ]
				}
			}
		},
        computed:{
			...mapGetters([
				"newExamDetail"
            ])
        },
		created() {
            if(JSON.parse(sessionStorage.getItem("newExamInfo")).end_time){
                this.form = JSON.parse(sessionStorage.getItem("newExamInfo"));
            }else {
                this.form = Object.assign(JSON.parse(sessionStorage.getItem("newExamInfo")),this.form);
            }
            console.log(this.form);
            if(this.form.end_time){
                this.form.end_time = new Date(this.form.end_time);
            }
        },
        methods:{
			...mapMutations({
                setStep:"SET_STEP"
			}),
	        prevStep(){// 点击上一步
				this.setStep(1);
            },
	        submitForm(formName) {
		        this.$refs[formName].validate((valid) => {
			        if (valid) {//表单验证成功
			        	let num = this.form.choiceOne+this.form.choiceMany+this.form.judgeTest
					        + this.form.feedFull+ this.form.shortAnswer;
			        	if(num<=0){
					        this.$message.error('请添加题目');
					        return;
                        }
                        let teacher = JSON.parse(sessionStorage.getItem("userInfo")).teachername;
                        let subject = JSON.parse(sessionStorage.getItem("classInfo")).filter(item=>item.id===this.form.classno)[0].major

                        let question = [];
                        for (let i = 0; i < num; i++) {
                            let data = {
                                type: '1',
                                question: "",// 题目问题
                                oa: "",// 选项A
                                ob: "",// 选项B
                                oc: "",// 选项C
                                od: "",// 选项D
                                isOK: false,
                                answer: "",// 正确答案
                                author: teacher,
                                subject: subject
                            }
                            let a = this.form.choiceOne,
                                b = this.form.choiceOne + this.form.choiceMany,
                                c = this.form.choiceOne + this.form.choiceMany + this.form.judgeTest,
                                d = this.form.choiceOne + this.form.choiceMany + this.form.judgeTest
                                    + this.form.feedFull;
                            let n = 0
                            if (0<=i&&i < a) {
                                data.type = '1';
                            } else if (a<=i&&i < b) {
                                data.type = '2';
                                data.answer = [];
                            } else if (b<=i&&i < c) {
                                data.type = '3';
                            } else if (c<=i&&i < d) {
                                data.type = '5';
                            } else {
                                data.type = '4';
                            }
                            question.push(data);
                            this.form.questionList = question;
                        }
                        sessionStorage.setItem("newExamInfo",JSON.stringify(this.form));
				        this.setStep(3);//进入到第三环节
				        // 跳转到下一环节
			        } else {
				        return false;
			        }
		        });
	        },
	        ...mapActions([
                "stepTwo"
	        ]),
        }
	}
</script>

<style scoped lang="less">
    textarea{
        resize:none;
    }
</style>