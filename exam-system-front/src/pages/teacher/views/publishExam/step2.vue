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
            <el-form-item label="截止时间" required>
                <el-row >
                    <el-col :span="5">
                        <el-form-item
                                prop="endDate"
                        >
                            <el-date-picker
                                    v-model="form.endDate"
                                    type="date"
                                    placeholder="选择日期"
                                    style="margin-right: 15px;"
                            >
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="1"></el-col>
                    <el-col :span="5">
                        <el-form-item
                                prop="endTime"
                        >
                            <el-time-select
                                    v-model="form.endTime"
                                    :picker-options="{
                            start: '07:00',
                            step: '00:15',
                            end: '23:30'
                        }"
                                    placeholder="选择时间"
                            >
                            </el-time-select>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form-item>
            <el-form-item label="测试时间" required>
                <el-radio-group v-model="form.check">
                    <el-radio label="无限制"></el-radio>
                    <el-radio label="有限制"></el-radio>
                </el-radio-group>
                <el-row>
                    <el-col :span="4">
                        <el-form-item
                            prop="testDuringTime"
                        >
                            <el-input v-show="form.check==='有限制'" v-model="form.testDuringTime" placeholder="请输入考试时长/分钟"></el-input>
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
                            <el-input prop="number" v-model="form.singleSelect" placeholder="单选题"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="单选题：">
                            <el-input prop="number" v-model="form.singleSelect" placeholder="单选题"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="单选题：">
                            <el-input prop="number" v-model="form.singleSelect" placeholder="单选题"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="6">
                        <el-form-item label="单选题：">
                            <el-input prop="number" v-model="form.singleSelect" placeholder="单选题"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="单选题：">
                            <el-input prop="number" v-model="form.singleSelect" placeholder="单选题"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form-item>
            <el-form-item label="尝试次数" required>
                <el-col class="line" :span="5">
                    <el-input placeholder="请输入试卷可尝试次数" v-model="form.examName"
                    ></el-input>
                </el-col>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('form')">下一步</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
	import {mapActions, mapMutations} from "vuex";
	export default {
		name: "step2",
		data() {
			return {
				form: {//表单数据内容
					description:"",//试卷描述
					check: "无限制",//试卷名称
					endDate: "",//截至日期
					endTime: "",//截至时间
					testDuringTime:"",// 考试时间/分钟
					singleSelect:""
				},
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
					endDate: [
						{type: 'date', required: true, message: '请选择日期', trigger: 'blur'}
					],
					endTime: [
						{type: 'string', required: true, message: '请选择时间', trigger: 'blur'}
					],
                    number:[
                        {type:"number",required:true,message:"请输入数字",trigger:'blur'}
                    ]
				}
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
				        this.stepOne({
					        examName: this.form.examName,
					        startDate: this.form.startDate.toString(),
					        startTime: this.form.startTime,
					        step:2// 当前是第一步，现在是第二部
				        })
				        // 跳转到下一环节
			        } else {
				        return false;
			        }
		        });
	        },
	        ...mapActions([
		        "stepOne"
	        ]),
        }
	}
</script>

<style scoped lang="less">
    textarea{
        resize:none;
    }
</style>