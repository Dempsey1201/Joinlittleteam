<template>
    <div id="register">
        <el-form
                ref="form"
                :model="form"
                label-width="80px"
                :label-position="labelPosition"
                :rules="rules"
        >
            <el-form-item label="用户名" prop="username">
                <el-input placeholder="请输入用户名" v-model="form.username"
                ></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input placeholder="请输入密码" v-model="form.password" show-password></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="idenPassword">
                <el-input placeholder="请输入密码" v-model="form.idenPassword" show-password></el-input>
            </el-form-item>
            <el-form-item label="注册身份" prop="radio">
                <el-radio v-model="form.radio" label="1">学生</el-radio>
                <el-radio v-model="form.radio" label="2">教师</el-radio>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('form')">注册</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
	export default {
		name: "register",
        data(){
	        let validatePass = (rule, value, callback) => {
		        let reg1 = /[0-9]+/ //数字字母下划线
		        let reg2 = /[,\.?~`!@#$%^&*(){}\[\]\-+/|\\]+///特殊字符
		        let reg3 = /[A-z]+/
		        let reg4 = /.{6,16}/
		        let bool = reg4.test(value)&&reg1.test(value)&&reg2.test(value)&&reg3.test(value);
		        if (!bool) {
			        callback(new Error('密码包含数字字母及特殊字符长度6~16位'));
		        } else {
			        callback();
		        }
	        };
	        let validateIdenPass = (rule, value, callback)=>{
	        	if(this.form.password !== value){
			        callback(new Error('密码不一致'));
                }else{
			        callback();
                }
            }
			return{
				form:{
					username:"",
                    password:"",
					idenPassword:"",
                    radio:"1"
                },
				labelPosition:"left",
                rules:{
					username:[
						{
							required:true,
							message: '请输入用户名',
							trigger: 'blur'
						}
                    ],
                    password:[
	                    {
		                    required:true,
		                    message: '请输入密码',
		                    trigger: 'blur'
	                    },
	                    { validator:validatePass,trigger: 'blur'}
                    ],
	                idenPassword:[
		                {
			                required:true,
			                message: '请确认密码',
			                trigger: 'blur'
		                },
		                { validator:validateIdenPass,trigger: 'blur'}
                    ],
	                radio:[
		                {
			                required:true,
			                message: '请选择身份',
			                trigger: 'blur'
		                }
                    ]

                }
            }
        },
        methods:{
	        submitForm(formName){
		        this.$refs[formName].validate((valid) => {
			        if (valid) {//表单验证成功
			        	//开始进行服务器验证
				        alert('submit!');
			        } else {
				        return false;
			        }
		        });
            }
        }
	}
</script>

<style scoped>
    #register{
        margin-top: 20px;
    }
</style>