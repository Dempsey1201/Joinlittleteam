<template>
    <div id="login">
        <el-form
                ref="form"
                :model="form"
                label-width="80px"
                :label-position="labelPosition"
                :rules="rules"
        >
            <el-form-item label="账号" prop="card">
                <el-input placeholder="请输入账号" v-model="form.card"
                ></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input placeholder="请输入密码" v-model="form.password" show-password></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('form')">立即登陆</el-button>
            </el-form-item>
        </el-form>
    </div>

</template>

<script>
    import {mapMutations} from "vuex";
    import {managerLogin} from "../../api/managerLogin";
    export default {
		name: "login",
        data(){
	        let validatePass = (rule, value, callback) => {
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
					email: '123@qq.com',//邮箱
                    password: 'aefbwetb35243@',//密码
                    card:'grq3g4a',// 教师学工号
					radio:"1",// 1 为学生 2 为老师
				},
				labelPosition:"left",
                coverShow:false,
                rules:{
                    password:[
	                    {
		                    required:true,
		                    message: '请输入密码',
		                    trigger: 'blur'
	                    },
                        { validator:validatePass,trigger: 'blur'}
                    ],
                    card:[
                        {
                            required:true,
                            message: '请选择输入工号',
                            trigger: 'blur'
                        }
                    ]
                }
            }
        },
        created() {
        },
        components:{
        },
        methods:{
	        submitForm(formName) {
		        this.$refs[formName].validate((valid) => {
			        if (valid) {//表单验证成功
                        managerLogin({
                            admin_card:"123123",
                            password:"test"
                        }).then(res=>{
                            if(res.data){
                                sessionStorage.setItem("userInfo",JSON.stringify(res.data))
                                window.location = window.location.href.split("manager")[0]+"manager.html"
                            }else {
                                this.$message.error('账号或密码错误');
                            }
                        })
			        } else {
				        return false;
			        }
		        });
	        },
            ...mapMutations({
                setUserInfo:"SET_USERINFO"
            })
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