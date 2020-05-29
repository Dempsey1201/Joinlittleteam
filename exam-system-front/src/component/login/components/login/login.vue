<template>
    <div id="login">
        <div class="cover" v-show="coverShow" >
            <confirmation class="confirmation" @success="idenCode"></confirmation>
        </div>
        <el-form
                ref="form"
                :model="form"
                label-width="80px"
                :label-position="labelPosition"
                :rules="rules"
        >
            <el-form-item v-if="form.radio=='1'" label="邮箱" prop="email">
                <el-input  placeholder="请输入邮箱" v-model="form.email"
                ></el-input>
            </el-form-item>
            <el-form-item v-else label="账号" prop="card">
                <el-input placeholder="请输入工号" v-model="form.card"
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
    import confirmation from "../base/confirmation";
    import {studentLogin,teacherLogin} from "../../api/login"
    import {mapMutations} from "vuex";
	export default {
		name: "login",
        data(){
	        let validatePass = (rule, value, callback) => {
		        let reg1 = /[0-9]+/ //数字字母下划线
                let reg2 = /[,\.?~`!@#$%^&*(){}\[\]\-+/|\\]+/
                //特殊字符
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
					//表单基础验证内容
					email:[
                        { required: true, message: '请输入邮箱地址', trigger: 'blur' },
                        { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
                    ],
                    password:[
	                    {
		                    required:true,
		                    message: '请输入密码',
		                    trigger: 'blur'
	                    },
                        // { validator:validatePass,trigger: 'blur'}
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
            if(location.href.indexOf("student")>=0){
                this.form.radio="1"
            }else {
                this.form.radio="2"
            }
            console.log()
        },
        components:{
		    confirmation
        },
        methods:{
	        submitForm(formName) {
		        this.$refs[formName].validate((valid) => {
			        if (valid) {//表单验证成功
			        	//开始机器人验证
				        // this.coverShow = true;
                        this.idenCode({code:true});
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
                    if(this.form.radio=='1'){
                        studentLogin({
                            email:"aaa",
                            password:"test"
                        }).then(res=>{
                            if(!res.data){
                                this.$message.error('密码或用户名不正确');
                            }else {
                                sessionStorage.setItem("userInfo",JSON.stringify(res.data))
                                this.setUserInfo(JSON.stringify(res.data))
                                this.$router.push({
                                    path:this.$root._router.options.routes[2].path
                                })
                            }
                        }).catch(err=>{
                            throw err;
                        })
                    }else if(this.form.radio=='2'){
                        teacherLogin({
                            card:"201800",
                            password:"1234"
                        }).then(res=>{
                            sessionStorage.setItem("userInfo",JSON.stringify(res.data))
                            this.setUserInfo(JSON.stringify(res.data))
                            this.$router.push({
                                path:this.$root._router.options.routes[2].path
                            })
                        }).catch(err=>{
                            throw err;
                        })
                    }
		        }else{
			        this.coverShow = false;
                }
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