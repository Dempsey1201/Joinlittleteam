<template>
    <div id="individual">
        <!--        个人中心上传头像、修改密码、绑定邮箱-->

        <div class="show">
            <div
                    class="avatar-uploader"
            >
                <input ref="file" type="file" @change="uploadImg">
                <img v-if="info.headUrl" :src="url+info.headUrl" class="avatar">
                <i v-show="!info.headUrl" class="el-icon-plus"></i>
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
                    <el-input v-model="info.teachername"></el-input>
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
                <el-form-item  label="密码">
                    <el-button type="primary" @click="()=>{
                        this.bool = true;
                    }">重置密码</el-button>
                </el-form-item>
            </el-form>
            <el-form ref="pwd"
                     v-if="bool"
                     label-position="left" label-width="80px"
                     :model="form"
                     :rules="rules"
            >
                <el-form-item label="原密码" prop="password">
                    <el-input  placeholder="请输入原密码" v-model="form.password"
                               show-password
                    ></el-input>
                </el-form-item>
                <el-form-item label="新密码" prop="newPassword">
                    <el-input placeholder="请输入新密码" v-model="form.newPassword"
                              show-password
                    ></el-input>
                </el-form-item>
                <el-form-item label="再次输入" prop="rePassword">
                    <el-input placeholder="请再次输入新密码"
                              v-model="form.rePassword" show-password
                    ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submit('pwd')">提交</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {updateOther, updatePwd,updateHeadUrl} from "../../api/individual";
    import {allClass} from "../../api/yourClass";
    import {mapMutations} from "vuex";
    import {teacherLogin} from "../../../../component/login/api/login";

    export default {
        name: "individual",
        data() {
            let validatePass = (rule, value, callback) => {
                if(value!=this.form.newPassword){
                    callback(new Error('两次密码不一致'));
                }else {
                    callback();
                }
            };
            return {
                isActive: !this.imageUrl,
                info: JSON.parse(sessionStorage.getItem("userInfo")),
                url:"http://47.94.210.131:8080",
                bool:false,
                form:{
                    password:"",
                    newPassword:"",
                    rePassword:""
                },
                rules:{
                    rePassword:[
                        {
                            required:true,
                            message: '请输入新密码',
                            trigger: 'blur'
                        },
                        { validator:validatePass,trigger: 'blur'}
                    ],
                    password:[
                        {required:true, message: '请输入原密码', trigger: 'blur'},
                    ],
                    newPassword:[
                        {required:true, message: '请输入新密码', trigger: 'blur'},
                    ]
                }
            }
        },
        created() {
            document.title = "个人中心"
            // 获取 数据以及图片路径等信息
            allClass({
                id:JSON.parse(sessionStorage.getItem("userInfo")).id
            }).then(res=>{
                sessionStorage.setItem("classInfo",JSON.stringify(res.data));
            }).catch(err=>{
                throw err;
            })
        },
        methods: {
            uploadImg() {
                let file = this.$refs.file.files[0];
                let reader = new FileReader(file);
                reader.readAsDataURL(file);
                reader.onload = (e)=>{
                    let data = e.currentTarget.result.split("base64,")[1];
                    updateHeadUrl({
                        imgStr:data,
                        id:this.info.id
                    }).then(res=>{
                        this.info.headUrl = res.data;
                        sessionStorage.setItem("userInfo", JSON.stringify(this.info))
                        this.setUserInfo(this.info)
                    }).catch(err=>{
                        throw err;
                    })
                }
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    console.log(valid)
                    if (valid) {
                        if (formName === "email" || formName === "username") {
                            if (confirm("确定要修改吗？") === true) {
                                updateOther({
                                    college: this.info.password,
                                    email: this.info.email,
                                    teachername: this.info.teachername,
                                    id: this.info.id
                                }).then(res => {
                                    if (res.data == 1) {
                                        this.$message({
                                            message: '修改成功',
                                            type: 'success'
                                        });
                                        sessionStorage.setItem("userInfo", JSON.stringify(this.info))
                                        this.setUserInfo(this.info)
                                    }
                                })
                            }
                        }
                    }
                });
            },
            ...mapMutations({
                setUserInfo:"SET_USERINFO"
            }),
            submit(formName){
                this.$refs.pwd.validate((valid)=>{
                    if (valid){
                        if (confirm("确定要修改密码吗？") === true) {
                            teacherLogin({
                                card:this.info.card,
                                password:this.form.password
                            }).then(res=>{
                                if(res.data){
                                    updatePwd({
                                        password: this.form.rePassword,
                                        id: this.info.id
                                    }).then(res => {
                                        if (res.data) {
                                            this.$message({
                                                message: '修改密码成功',
                                                type: 'success'
                                            });
                                            this.bool = false;
                                            this.info.password = this.form.rePassword;
                                            sessionStorage.setItem("userInfo", JSON.stringify(this.info))
                                            this.setUserInfo(this.info)
                                        }
                                    })
                                }else {
                                    this.$message.error('密码不正确');
                                }
                            })

                        }
                    }
                })
            }
        }
    }
</script>

<style scoped lang="less">

    #individual {
        display: flex;
        justify-content: space-between;

        .show {
            width: 39%;
            border-top: 1px solid #409EFF;
            border-right: 1px solid #409EFF;

            .avatar-uploader {
                position: relative;
                width: 150px;
                height: 150px;
                margin: 15px auto;
                overflow: hidden;
                cursor: pointer;
                border-radius: 50%;
                border: 1px solid #ddd;

                input[type="file"] {
                    display: block;
                    width: 100%;
                    height: 100%;
                    opacity: 0;
                    position: relative;
                    z-index: 1;
                    cursor: pointer;
                }
            }

            .avatar-uploader .el-icon-plus {
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

            .avatar-uploader:hover .el-icon-plus {
                display: block !important;
                color: #424242;
            }

            .avatar-uploader:hover {
                background-color: rgba(0, 0, 0, 0.2);
            }

            .avatar {
                position: absolute;
                top: 0;
                width: 150px;
                height: 150px;
                display: block;
            }

            .info {
                padding: 30px 20px;

                span {
                    display: inline-block;
                }

                .ti {
                    width: 100px;
                    color: #409EFF;
                }

                .con {
                    /*width: 70%;*/
                }
            }
        }

        .change {
            box-sizing: border-box;
            padding-left: 20px;
            padding-top: 20px;
            width: 59%;
            border-left: 1px solid #409EFF;
            border-bottom: 1px solid #409EFF;
        }
    }
</style>