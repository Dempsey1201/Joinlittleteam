<template>
    <div class="step1">
        <el-form
                ref="form"
                :model="form"
                label-width="80px"
                :label-position="labelPosition"
                :rules="rules"
        >
            <el-form-item label="试卷名称" prop="examName">
                <el-col class="line" :span="6">
                    <el-input placeholder="请输入试卷名称" v-model="form.examName"
                    ></el-input>
                </el-col>
            </el-form-item>
            <el-form-item label="发布时间" required>
                <el-row >
                    <el-col :span="5">
                        <el-form-item
                                prop="startDate"
                        >
                            <el-date-picker
                                    v-model="form.startDate"
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
                                prop="startTime"
                        >
                            <el-time-select
                                    v-model="form.startTime"
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
            <el-form-item>
                <el-button type="primary" @click="submitForm('form')">立即创建</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import {mapGetters} from "vuex";
    import {mapActions} from "vuex";
	export default {
		name: "step1",
		data() {
			return {
				form: {//表单数据内容
					examName: "",//试卷名称
					startDate: "",//开始日期
					startTime: ""//开始时间
				},
				labelPosition: "left",
				rules: {
					//表单基础验证内容
					examName: [
						{
							required: true,
							message: '请输入试卷名称',
							trigger: 'blur'
						}
					],
					startDate: [
						{type: 'date', required: true, message: '请选择日期', trigger: 'blur'}
					],
					startTime: [
						{type: 'string', required: true, message: '请选择时间', trigger: 'blur'}
					],
				}
			}
		},
        computed:{
			...mapGetters([
				"newExamDetail"
            ]),
        },
        created() {
	        this.from.examName=this.newExamDetail.examName;//试卷名称
		    this.from.startDate=new Date(this.newExamDetail.startDate);//开始日期
            this.from.startTime=this.newExamDetail.startTime;//开始时间
            console.log("初始化")
        },
		methods: {
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

<style scoped>

</style>