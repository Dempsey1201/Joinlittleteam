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
        >
            <el-form-item label="用户名">
                <el-input placeholder="请输入用户名" v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input placeholder="请输入密码" v-model="form.password" show-password></el-input>
            </el-form-item>
            <el-form-item label="选择身份">
                <el-radio v-model="radio" label="1">学生</el-radio>
                <el-radio v-model="radio" label="2">教师</el-radio>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">立即登陆</el-button>
            </el-form-item>
        </el-form>
    </div>

</template>

<script>
    import confirmation from "../base/confirmation";
	export default {
		name: "login",
        data(){
			return{
				form: {//表单数据内容
					username: '',
                    password: ''
				},
				labelPosition:"left",
                radio:"1",
                coverShow:true
            }
        },
        components:{
		    confirmation
        },
        methods:{
	        onSubmit(){//点击显示验证窗口
	        	//先做正则匹配再显示验证窗口
                this.coverShow = true;
            },
	        idenCode(code){
	        	if(code.code){
			        alert("验证成功");
			        this.coverShow = false;
		        }else{
			        this.coverShow = false;
                }


            }
        }
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