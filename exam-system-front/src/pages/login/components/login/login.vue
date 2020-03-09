<template>
    <div id="login">
        <div class="cover" v-show="coverShow" >
            <confirmation class="confirmation" @success="idenCode"></confirmation>
        </div>
        <el-form
                ref="form"
                :model="form"
                label-width="70px"
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
            <el-form-item label="选择身份">
                <el-radio v-model="radio" label="1">学生</el-radio>
                <el-radio v-model="radio" label="2">教师</el-radio>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('form')">立即登陆</el-button>
            </el-form-item>
        </el-form>
    </div>

</template>

<script>
    import confirmation from "../base/confirmation";
	export default {
		name: "login",
        data(){
	        let validatePass2 = (rule, value, callback) => {
		        let reg1 = /[0-9]+/ //数字字母下划线
		        let reg2 = /[,\.?~`!@#$%^&*(){}\[\]\-+/|\\]+///特殊字符
		        let reg3 = /[A-z]+/
                let reg4 = /.{6,16}/
		        let bool = reg4.test(this.form.password)&&reg1.test(this.form.password)&&reg2.test(this.form.password)&&reg3.test(this.form.password);
		        if (!bool) {
			        callback(new Error('密码包含数字字母及特殊字符长度6~16位'));
		        } else {
			        callback();
		        }
	        };
			return{
				form: {//表单数据内容
					username: '',//用户名
                    password: ''//密码
				},
				labelPosition:"left",
                radio:"1",// 1 为学生 2 为老师
                coverShow:false,
                rules:{
					//表单基础验证内容
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
                        { validator:validatePass2,trigger: 'blur'}
                    ]
                }
            }
        },
        components:{
		    confirmation
        },
        methods:{
	        submitForm(formName) {
		        this.$refs[formName].validate((valid) => {
			        if (valid) {//表单验证成功
			        	//开始机器人验证
				        this.coverShow = true;
				        // alert('submit!');
			        } else {
				        return false;
			        }
		        });
	        },
	        idenCode(code){
	        	if(code.code){
			        alert("验证成功");
			        this.coverShow = false;
			        //进行下一步，向后端发送请求
		        }else{
			        this.coverShow = false;
                }
            }
        },
	}
</script>

<style scoped lang="less">
    #login{
        padding-top: 20px;
        .cover{
            position: fixed;
            top: 0;
            left: 0;
            z-index: 2;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.2);
        }
    }
</style>