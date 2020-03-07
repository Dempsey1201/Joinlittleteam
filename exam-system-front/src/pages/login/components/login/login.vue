<template>
    <div id="login">
        <el-form
                ref="form"
                :model="form"
                label-width="60px"
                :label-position="labelPosition"
        >
            <el-form-item label="用户名">
                <el-input placeholder="请输入用户名" v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input placeholder="请输入密码" v-model="form.password" show-password></el-input>
            </el-form-item>
            <el-form-item>
                <el-radio v-model="radio" label="1">学生</el-radio>
                <el-radio v-model="radio" label="2">教师</el-radio>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">立即创建</el-button>
            </el-form-item>

        </el-form>
    </div>

</template>

<script>
	export default {
		name: "login",
        data(){
			return{
				form: {//表单数据内容
					username: '',
                    password: ''
				},
				labelPosition:"left",
                radio:"1"
            }
        },
        methods:{
	        onSubmit(){

            }
        }
	}
</script>

<style scoped lang="less">
    #login{
        padding-top: 20px;
    }
</style>