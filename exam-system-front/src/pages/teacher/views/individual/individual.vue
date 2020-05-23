<template>
    <div id="individual">
        <!--        个人中心上传头像、修改密码、绑定邮箱-->

        <div class="show">
            <div
                    class="avatar-uploader"

            >
                <input type="file" @change="uploadImg(files)">
                <img v-if="info.headUrl" :src="imageUrl" class="avatar">
                <i v-if="!info.headUrl" class="el-icon-plus"></i>
            </div>
            <div class="info">
                <p><span class="ti">昵称：</span><span class="con">{{info.teachername}}</span></p>
                <p><span class="ti">工号</span><span class="con">{{info.card}}</span></p>
                <p><span class="ti">学校：</span><span class="con">{{info.college}}</span></p>
                <p><span class="ti">性别：</span><span class="con">{{info.sex}}</span></p>
            </div>
        </div>
        <div class="change">
            <el-form ref="username" label-position="left" label-width="80px" :model="info">
                <el-form-item label="用户名">
                    <el-input v-model="info.teachername" ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('username')">修改</el-button>
                </el-form-item>
            </el-form>
            <el-form ref="email" label-position="left" label-width="80px" :model="info">
                <el-form-item label="邮箱">
                    <el-input v-model="info.email" placeholder="邮箱"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('email')">修改</el-button>
                </el-form-item>
            </el-form>
            <el-form ref="pwd" label-position="left" label-width="80px" :model="info">
                <el-form-item label="密码">
                    <el-input v-model="info.password" show-password placeholder="密码" :value="info.password"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('pwd')">修改</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {updateOther,updatePwd} from "../../api/individual";

    export default {
        name: "individual",
        data(){
            return{
                isActive: !this.imageUrl,
                info:JSON.parse(sessionStorage.getItem("userInfo"))
            }
        },
        created() {
            // 获取 数据以及图片路径等信息

        },
        computed:{

        },
        methods: {
            uploadImg(files){
                console.log(files);
                const formData = new FormData();
                formData.append("file",files.file);
                console.log(formData.get("file"))// formData 本就是个空对象，要用 get 方法获取其中内容
                // 将formData传到后端，后端再返回图片地址
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // alert('submit!');
                        if(formName==="email"||formName==="username"){
                            if(confirm("确定要修改吗？")===true){
                                updateOther({
                                    college:this.info.password,
                                    email:this.info.email,
                                    teachername:this.info.teachername,
                                    id:this.info.id
                                }).then(res=>{
                                    console.log(res.data)
                                    if(res.data.msg===1){
                                        this.$message({
                                            message: '修改密码成功',
                                            type: 'success'
                                        });
                                    }
                                })
                            }
                        }
                        if(formName=="pwd"){
                            if(confirm("确定要修改密码吗？")===true){
                                updatePwd({
                                    password:this.info.password,
                                    id:this.info.id
                                }).then(res=>{
                                    console.log(res.data)
                                    if(res.data===1){
                                        this.$message({
                                            message: '修改密码成功',
                                            type: 'success'
                                        });
                                    }
                                })
                            }
                        }
                        sessionStorage.setItem("userInfo",JSON.stringify(this.info))
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
        }
    }
</script>

<style scoped lang="less">

    #individual{
        display: flex;
        justify-content: space-between;
        .show{
            width: 39%;
            border-top:1px solid #409EFF;
            border-right:1px solid #409EFF;
            .avatar-uploader{
                position: relative;
                width: 150px;
                height: 150px;
                margin: 15px auto;
                overflow: hidden;
                cursor: pointer;
                border-radius: 50%;
                border: 1px solid #ddd;
                input[type="file"]{
                    display: block;
                    width: 100%;
                    height: 100%;
                    opacity: 0;
                    position: relative;
                    z-index: 1;
                    cursor: pointer;
                }
            }
            .avatar-uploader .el-icon-plus{
                position: absolute;
                top: 0;
                left: 0;
                font-size: 28px;
                color: #8c939d;
                width: 150px;
                height: 150px;
                line-height: 150px;
                text-align: center;

            }
            .avatar-uploader:hover .el-icon-plus{
                display: block !important;
                color: #424242;
            }
            .avatar-uploader:hover{
                background-color: rgba(0,0,0,0.2);
            }
            .avatar {
                width: 150px;
                height: 150px;
                display: block;
            }
            .info{
                padding:30px 20px;
                span{
                    display: inline-block;
                }
                .ti{
                    width: 100px;
                    color: #409EFF;
                }
                .con{
                    /*width: 70%;*/
                }
            }
        }
        .change{
            box-sizing: border-box;
            padding-left: 20px;
            padding-top: 20px;
            width: 59%;
            border-left:1px solid #409EFF;
            border-bottom:1px solid #409EFF;
        }
    }
</style>