<template>
    <div class="step1">
        <el-form
                ref="form"
                :model="form"
                label-width="80px"
                :label-position="labelPosition"
                :rules="rules"
        >
            <el-form-item label="试卷名称" prop="pname">
                <el-col class="line" :span="6">
                    <el-input placeholder="请输入试卷名称" v-model="form.pname"
                    ></el-input>
                </el-col>
            </el-form-item>
            <el-form-item label="是否共享" required>
                <el-radio-group v-model="form.share">
                    <el-radio :label="1">是</el-radio>
                    <el-radio :label="0">否</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="选择班级" prop="classno">
                <el-col :span="6">
                    <el-select v-model="form.classno" placeholder="请选择班级">
                        <el-option
                                v-for="item in classInfo"
                                :key="item.id"
                                :label="item.classname"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-col>
            </el-form-item>
            <el-form-item label="发布时间" prop="start_time">
                <el-date-picker
                        v-model="form.start_time"
                        type="datetime"
                        placeholder="选择日期时间">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('form')">立即创建</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>


<script>
    import {mapMutations} from "vuex"
	export default {
		name: "step1",
		data() {
			let validateClass = (rule, value, callback) => {
				if (!this.form.classno) {
					callback(new Error('请选择班级'));
				} else {
					callback();
				}
			};

			return {
				form: {//表单数据内容
					pname: "",//试卷名称
					start_time: "",//开始日期时间
                    classno:"",
                    share:1,
				},
                classInfo:[],
				labelPosition: "left",
				rules: {
					//表单基础验证内容
                    pname: [
						{
							required: true,
							message: '请输入试卷名称',
							trigger: 'blur'
						}
					],
                    start_time: [
						{type: 'date', required: true, message: '请选择日期时间', trigger: 'blur'}
					],
                    classno:[
						{ validator:validateClass,required:true,trigger: 'blur'}
                    ]
				},
			}
		},
        created() {
		    this.classInfo = JSON.parse(sessionStorage.getItem("classInfo"));
		    if(sessionStorage.getItem("newExamInfo")){
		        this.form = JSON.parse(sessionStorage.getItem("newExamInfo"));
            }
		    if(this.form.start_time){
                this.form.start_time = new Date(this.form.start_time);
            }
        },
		methods: {
            submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {//表单验证成功
                        // 跳转到下一环节
                        sessionStorage.setItem("newExamInfo",JSON.stringify(this.form));
                        this.setStep(2);
                    } else {
						return false;
					}
				});
			},
            ...mapMutations({
                setStep:"SET_STEP"
            })
		}
	}
</script>

<style scoped>

</style>