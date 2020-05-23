<template>
    <div id="individual">
        <!--        个人中心上传头像、修改密码、绑定邮箱-->

        <div class="show">
            <el-upload
                    class="avatar-uploader"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :show-file-list="false"
                    :http-request="uploadImg"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                <i v-show="!imageUrl" class="el-icon-plus"></i>
            </el-upload>
            <div class="info">
                <p><span class="ti">昵称：</span><span class="con">{{userInfo.teachername}}</span></p>
                <p><span class="ti">工号</span><span class="con">{{userInfo.card}}</span></p>
                <p><span class="ti">学校：</span><span class="con">{{userInfo.college}}</span></p>
                <p><span class="ti">性别：</span><span class="con">{{userInfo.sex}}</span></p>
            </div>
        </div>
        <div class="change">
            <el-form ref="username" label-position="left" label-width="80px" :model="userInfo">
                <el-form-item label="用户名">
                    <el-input v-model="userInfo.teachername" ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('username')">修改</el-button>
                </el-form-item>
            </el-form>
            <el-form ref="email" label-position="left" label-width="80px" :model="userInfo">
                <el-form-item label="邮箱">
                    <el-input v-model="userInfo.email" placeholder="邮箱"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('email')">修改</el-button>
                </el-form-item>
            </el-form>
            <el-form ref="pwd" label-position="left" label-width="80px" :model="userInfo">
                <el-form-item label="密码">
                    <el-input v-model="userInfo.password" show-password placeholder="密码" :value="userInfo.password"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('pwd')">修改</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {mapGetters} from "vuex";
    export default {
        name: "individual",
        data(){
            return{
                imageUrl: 'http://imgsrc.baidu.com/forum/w=580/sign=f53607cadc54564ee565e43183df9cde/a23692014c086e06a1f7049203087bf40bd1cb3f.jpg',
                isActive: !this.imageUrl,
                // userInfo:{
                //     teachername:"老师",// 姓名
                //     card:"2018011010",// 工号
                //     headUrl:"",// 头像
                //     email:"29341098@qq.com",// 邮箱
                //     sex:"女",// 性别
                //     college:"东北师范大学",// 学校
                //     password:"evwtwt",// 密码
                // }
            }
        },
        created() {
            // 获取 数据以及图片路径等信息

        },
        computed:{
            ...mapGetters([
               "userInfo"
           ])
        },
        methods: {
            handleAvatarSuccess(res, file) {
                this.imageUrl = URL.createObjectURL(file.raw);
            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isLt2M;// return false 就不会执行后面的内容
            },
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
                        alert('submit!');
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
            }
            .avatar-uploader .el-icon-plus{
                /*display: block;*/
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
                background-color: rgba(0,0,0,0.3);
                color: #424242;
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