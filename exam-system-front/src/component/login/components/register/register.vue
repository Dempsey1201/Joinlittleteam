<template>
    <div id="register">
        <el-form
                ref="form"
                :model="form"
                label-width="80px"
                :label-position="labelPosition"
                :rules="rules"
        >
            <el-form-item label="邮箱" prop="email">
                <el-input placeholder="请输入邮箱" v-model="form.email"
                ></el-input>
            </el-form-item>
            <el-form-item label="" prop="identifyCode">
                <el-input style="display:inline-block;width: 50%" placeholder="请输入验证码" v-model="form.identifyCode"
                ></el-input>
                <el-button
                        style="display: inline-block;float: right"
                        type="primary"
                        @click="getCode"
                >获取验证码{{time>0?' ('+time+') ':""}}</el-button>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input placeholder="请输入密码" v-model="form.password" show-password></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="idenPassword">
                <el-input placeholder="请输入密码" v-model="form.idenPassword" show-password></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('form')">注册</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import {getcode,addUser} from "../../api/login";
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
            let checkEmail = (rule, value, callback) => {
                const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
                if (!value) {
                    return callback(new Error('邮箱不能为空'))
                }
                setTimeout(() => {
                    if (mailReg.test(value)) {
                        callback()
                    } else {
                        callback(new Error('请输入正确的邮箱格式'))
                    }
                }, 100)
            }
			return{
				form:{
					email:"2873435684@qq.com",
                    password:"",
					idenPassword:"",
                    radio:"1",
                    identifyCode:"",
                    rightCode:""
                },
                time:0,
				labelPosition:"left",
                rules:{
                    email:[
						{
							required:true,
							message: '请输入邮箱',
							trigger: 'blur'
						},
                        { validator:checkEmail,trigger: 'blur'}
                    ],
                    password:[
	                    {
		                    required:true,
		                    message: '请输入密码',
		                    trigger: 'blur'
	                    },
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
                    ],
                    identifyCode:[
                        {
                            required:true,
                            message: '请输入验证码',
                            trigger: 'blur'
                        },
                    ]
                }
            }
        },
        created() {
            if(location.href.indexOf("student")>=0){
                this.form.radio="1"
            }else {
                this.form.radio="2"
            }
            if(sessionStorage.getItem("code")){
                this.form.rightCode = sessionStorage.getItem("code");
            }
        },
        methods:{
	        submitForm(formName){
		        this.$refs[formName].validate((valid) => {
			        if (valid) {//表单验证成功
			        	//开始进行服务器验证
                        if(this.form.identifyCode!=this.form.rightCode){
                            this.$message.error('验证码不正确');
                            return ;
                        }
                        addUser({
                            email:this.form.email,
                            password:this.form.password
                        }).then(res=>{
                            if(res.data){
                                // 跳转到
                                this.$message({
                                    message: '注册成功',
                                    type: 'success'
                                });
                                this.$router.push({
                                    path:"/student.html/login"
                                })

                            }
                        })
			        } else {
				        return false;
			        }
		        });
            },
            getCode(){
	            if(this.time>0){
                    this.$message({
                        message: '请稍后再进行操作',
                        type: 'warning'
                    });
                    return;
                }

                getcode({
                    email:this.form.email
                }).then(res=>{
                    this.form.rightCode = res.data+"";
                    sessionStorage.setItem("code",this.form.rightCode);
                    this.time = 60;
                    let timer = setInterval(()=>{
                        this.time -= 1;
                        if(this.time===0)clearInterval(timer);
                    },1000)
                }).catch(err=>{
                    throw err;
                })
            }
        }
	}
</script>

<style scoped>
    #register{
        margin-top: 20px;
    }
</style>