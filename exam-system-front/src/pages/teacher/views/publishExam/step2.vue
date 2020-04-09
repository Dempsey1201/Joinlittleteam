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
            <el-form-item label="测试说明" prop="description">
                <el-col class="line" :span="6">
                    <el-input
                            type="textarea"
                            :rows="2"
                            placeholder="请输入测试说明"
                            v-model="form.description">
                    </el-input>
                </el-col>
            </el-form-item>
            <el-form-item label="截止时间" prop="endTime">
                <el-date-picker
                        v-model="form.endTime"
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
                            <el-input-number v-show="form.check==='有限制'" v-model="form.testDuringTime" :step="10" :min="10" :max="240" label="描述文字"></el-input-number>
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
            <el-form-item label="尝试次数">
                <el-col class="line" :span="5">
                    <el-input-number v-model="form.tryTimes" :min="1" :max="5" label="描述文字"></el-input-number>
                </el-col>
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
					description:"",//试卷描述
					endTime: "",//截至时间
					check: "无限制",//试卷时候限制时间
					testDuringTime:10,// 考试时间/分钟
                    choiceOne:0,// 单选题
					choiceMany:0,// 多选题
					judgeTest:0,// 判断题
					feedFull:0,// 填空题
                    shortAnswer:0,// 简答题
                    tryTimes:1,//尝试次数
				},
                num:1,
				labelPosition: "right",
				rules: {
					//表单基础验证内容
					description: [
						{
							required: true,
							message: '请输入测试说明',
							trigger: 'blur'
						}
					],
					endTime: [
						{type: 'date', required: true, message: '请选择日期时间', trigger: 'blur'}
					],
					testDuringTime:[
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
			this.form.description = this.newExamDetail.description;//试卷描述
			this.form.endTime = this.newExamDetail.endTime ? new Date(this.newExamDetail.endTime):"";//截至时间
			this.form.check = this.newExamDetail.check;//试卷时候限制时间
			this.form.testDuringTime = this.newExamDetail.testDuringTime;// 考试时间/分钟
			this.form.choiceOne = this.newExamDetail.choiceOne;// 单选题
			this.form.choiceMany = this.newExamDetail.choiceMany;// 多选题
			this.form.judgeTest = this.newExamDetail.judgeTest;// 判断题
			this.form.feedFull = this.newExamDetail.feedFull;// 填空题
			this.form.shortAnswer = this.newExamDetail.shortAnswer;// 简答题
			this.form.tryTimes = this.newExamDetail.tryTimes;//尝试次数
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
			        	if((this.form.choiceOne+this.form.choiceMany+this.form.judgeTest
                            + this.form.feedFull+ this.form.shortAnswer)<=0){
					        this.$message.error('请添加题目');
					        return;
                        }
			        	this.stepTwo({
					        description:this.form.description,//试卷描述
					        endTime: this.form.endTime.toString(),//截至时间
					        check: this.form.check,//试卷是否限制时间
					        testDuringTime:this.form.testDuringTime,// 考试时间/分钟
					        choiceOne:this.form.choiceOne,// 单选题
					        choiceMany:this.form.choiceMany,// 多选题
					        judgeTest:this.form.judgeTest,// 判断题
					        feedFull:this.form.feedFull,// 填空题
					        shortAnswer:this.form.shortAnswer,// 简答题
					        tryTimes:this.form.tryTimes,//尝试次数
                        })
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