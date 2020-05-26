<template>
    <div class="reuse">
<!--        复用试卷-->
        <el-form
                ref="form1"
                :model="form"
                label-width="80px"
                :label-position="labelPosition"
                :rules="rules"
        >
            <el-form-item label="试卷名称" prop="examName">
                <el-col class="line" :span="6">
                    <el-input placeholder="请输入试卷名称" v-model="form.examName"
                    ></el-input>
                </el-col>
            </el-form-item>
            <el-form-item label="是否共享" required>
                <el-radio-group v-model="form.share">
                    <el-radio :label="1">是</el-radio>
                    <el-radio :label="0">否</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="选择班级" prop="selectClass">
                <el-col :span="6">
                    <el-select
                            v-model="form.class"
                            multiple
                            filterable
                            allow-create
                            default-first-option
                            placeholder="请选择班级">
                        <el-option
                                v-for="item in classInfo"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-col>
            </el-form-item>
            <el-form-item label="发布时间" prop="startTime">
                <el-date-picker
                        v-model="form.startTime"
                        type="datetime"
                        placeholder="选择日期时间">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('form1')">立即创建</el-button>
            </el-form-item>
            <el-form-item label="截止时间" prop="endTime">
                <el-date-picker
                        v-model="form.endTime"
                        type="datetime"
                        placeholder="选择日期时间">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="测试时间" required>
                <el-radio-group v-model="form.check">
                    <el-radio label="无限制"></el-radio>
                    <el-radio label="有限制"></el-radio>
                </el-radio-group>
                <el-row>
                    <el-col :span="4">
                        <el-form-item >
                            <el-input-number v-show="form.check==='有限制'" v-model="form.testDuringTime" :step="10" :min="10" :max="240" label="描述文字"></el-input-number>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form-item>
            <el-form-item label="尝试次数">
                <el-col class="line" :span="5">
                    <el-input-number v-model="form.tryTimes" :min="1" :max="5" label="描述文字"></el-input-number>
                </el-col>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('form')">下一步</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import {newPaper} from "../../api/publishExam";

    export default {
        name: "reuse",
        data(){
            let validateClass = (rule, value, callback) => {
                if (!this.form.class.length) {
                    callback(new Error('请选择班级'));
                } else {
                    callback();
                }
            };
            return{
                form: {//表单数据内容
                    pname: "",//试卷名称
                    start_time: "",//开始日期时间
                    classno:"",
                    share:0,
                    end_time: "",//截至时间
                    check: "无限制",//试卷时候限制时间
                    last_time:10,// 考试时间/分钟
                    tryTimes:1,//尝试次数
                    teacher:""
                },
                num:1,
                labelPosition: "right",
                rules: {
                    //表单基础验证内容
                    //表单基础验证内容
                    examName: [
                        {
                            required: true,
                            message: '请输入试卷名称',
                            trigger: 'blur'
                        }
                    ],
                    startTime: [
                        {type: 'date', required: true, message: '请选择日期时间', trigger: 'blur'}
                    ],
                    selectClass:[
                        { validator:validateClass,required:true,trigger: 'blur'}
                    ],
                    description: [
                        {
                            required: true,
                            message: '请输入测试说明',
                            trigger: 'blur'
                        }
                    ],
                    endTime: [
                        {type: 'date', required: true, message: '请选择日期时间', trigger: 'blur'}
                    ],
                    testDuringTime: [
                        {
                            required: true,
                            message: '考试时间',
                            trigger: 'blur'
                        }
                    ],
                }
            }
        },
        props: {
            pid1:{//新建的试卷的id
                required:true
            }
        },
        methods:{
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {//表单验证成功
                        console.log(this.form)
                    } else {
                        return false;
                    }
                });
            },
        }
    }
</script>

<style scoped>

</style>