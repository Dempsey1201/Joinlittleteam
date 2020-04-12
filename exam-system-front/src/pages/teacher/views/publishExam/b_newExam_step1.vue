<template>
    <div class="step1">
        <el-form
                ref="form1"
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
            <el-form-item label="是否共享" required>
                <el-radio-group v-model="form.share">
                    <el-radio :label="1">是</el-radio>
                    <el-radio :label="0">否</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="选择班级" prop="selectClass">
                <el-col :span="6">
                    <el-select
                            v-model="form.class"
                            multiple
                            filterable
                            allow-create
                            default-first-option
                            placeholder="请选择班级">
                        <el-option
                                v-for="item in classInfo"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-col>
            </el-form-item>
            <el-form-item label="发布时间" prop="startTime">
                <el-date-picker
                        v-model="form.startTime"
                        type="datetime"
                        placeholder="选择日期时间">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('form1')">立即创建</el-button>
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
			
			let validateClass = (rule, value, callback) => {
				if (!this.form.class.length) {
					callback(new Error('请选择班级'));
				} else {
					callback();
				}
			};
			
			return {
				form: {//表单数据内容
					examName: "",//试卷名称
					startTime: "",//开始日期时间
                    class:[],
                    share:0
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
					startTime: [
						{type: 'date', required: true, message: '请选择日期时间', trigger: 'blur'}
					],
					selectClass:[
						{ validator:validateClass,required:true,trigger: 'blur'}
                    ]
				},
			}
		},
        computed:{
			...mapGetters([
				"newExamDetail",
                "classInfo"
            ]),
        },
        created() {
	        this.form.examName=this.newExamDetail.examName;//试卷名称
		    this.form.startTime= this.newExamDetail.startTime ? new Date(this.newExamDetail.startTime):"";//开始日期
            this.form.class = this.newExamDetail.class;
	        this.form.share = this.newExamDetail.share;
        },
		methods: {
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {//表单验证成功
						this.stepOne({
							examName: this.form.examName,
							startTime: this.form.startTime.toString(),
                            className:this.form.class,
                            share:this.form.share,
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