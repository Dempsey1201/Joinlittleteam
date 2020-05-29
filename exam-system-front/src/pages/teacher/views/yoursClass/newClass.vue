<template>
    <div class="newClass">
        <el-form
                ref="form1"
                :model="form"
                label-width="80px"
                label-position="left"
                :rules="rules"
        >
            <el-form-item label="班级名称" prop="classname">
                <el-col class="line" :span="10">
                    <el-input placeholder="请输入创建班级名称" v-model="form.classname"
                    ></el-input>
                </el-col>
            </el-form-item>
            <el-form-item label="科目" prop="major">
                <el-col class="line" :span="10">
                    <el-input placeholder="请输入创建班级名称" v-model="form.major"
                    ></el-input>
                </el-col>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('form1')">创建班级</el-button>
            </el-form-item>
        </el-form>
        <div v-if="classno" class="classno">
            班级邀请码： <span>{{classno}}</span>
        </div>
    </div>
</template>

<script>
    import {createClass} from "../../api/yourClass";
    import {mapMutations} from "vuex";

    export default {
        name: "newClass",
        data(){
            let validateClass = (rule, value, callback) =>{
                if(this.classList.filter(item=>item.classname===value).length){
                    callback(new Error('班级名不能重复！'));
                }else {
                    callback();
                }
            }
            return{
                form:{
                    classname: "",
                    major: "",
                },
                classno:"",
                rules: {
                    classname:[
                        {
                            required: true,
                            message: '请输入班级名称',
                            trigger: 'blur'
                        },
                        { validator:validateClass,trigger: 'blur'}
                    ],
                    major:[
                        {
                            required: true,
                            message: '请输入所属科目',
                            trigger: 'blur'
                        }
                    ],
                }
            }
        },
        props:{
            classList:{
                type:Array,
                required:true
            }
        },
        methods:{
            submitForm(formName){
                this.$refs[formName].validate((valid) =>{
                    if(valid){
                        let info = JSON.parse(sessionStorage.getItem("userInfo"))
                        createClass({
                            classname:this.form.classname,
                            major:this.form.major,
                            teachername:info.teachername,
                            teacherno:info.id,
                            college:info.college
                        }).then(res=>{
                            this.classList.push(res.data);
                            this.classno = res.data.classno;
                            this.$message({
                                message: '创建成功',
                                type: 'success'
                            });
                            sessionStorage.setItem("classInfo",JSON.stringify(this.classList))
                            this.$emit("add")
                        }).catch(err=>{
                            throw err;
                        })
                    }
                })
            },
            ...mapMutations({
                setClassInfo: "SET_CLASSINFO"
            })
        }
    }
</script>

<style scoped lang="less">
    .classno{
        font-size: 14px;
        color: #606266;
        line-height: 40px;
        padding: 0 12px 0 0;
        span{
            box-sizing: border-box;
            padding: 0 12px;
            display: inline-block;
            border: 1px solid #DCDFE6;
            border-radius: 4px;
        }
    }
</style>