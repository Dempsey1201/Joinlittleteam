<template>
    <div class="reuse">
<!--        复用试卷-->
        <el-button style="display: block" type="primary" size="mini" @click="prevStep">上一步</el-button>
        <el-form
                ref="form"
                :model="form"
                label-width="80px"
                :label-position="labelPosition"
                :rules="rules"
        >
            <el-form-item label="试卷名称" prop="pname">
                <el-col class="line" :span="6">
                    <el-input placeholder="请输入试卷名称" v-model="form.pname"
                    ></el-input>
                </el-col>
            </el-form-item>
            <el-form-item label="是否共享" required>
                <el-radio-group v-model="form.share">
                    <el-radio :label="1">是</el-radio>
                    <el-radio :label="0">否</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="选择班级" prop="classno">
                <el-col :span="6">
                    <el-select v-model="form.classno" placeholder="请选择班级">
                        <el-option
                                v-for="item in classList"
                                :key="item.id"
                                :label="item.classname"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-col>
            </el-form-item>
            <el-form-item label="发布时间" prop="start_time">
                <el-date-picker
                        v-model="form.start_time"
                        type="datetime"
                        placeholder="选择日期时间">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="截止时间" prop="end_time">
                <el-date-picker
                        v-model="form.end_time"
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
                            <el-input-number v-show="form.check==='有限制'" v-model="form.last_time" :step="10" :min="10" :max="240" label="描述文字"></el-input-number>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('form')">发布</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import {newPaper,reusePaper} from "../../api/publishExam";
    import {mapGetters} from "vuex";
    export default {
        name: "reuse",
        data(){
            let validateClass = (rule, value, callback) => {
                if (!this.form.classno) {
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
                    share:1,
                    end_time: "",//截至时间
                    check: "无限制",//试卷时候限制时间
                    last_time:10,// 考试时间/分钟
                },
                classList:[],
                num:1,
                labelPosition: "right",
                rules: {
                    //表单基础验证内容
                    //表单基础验证内容
                    pname: [
                        {
                            required: true,
                            message: '请输入试卷名称',
                            trigger: 'blur'
                        }
                    ],
                    start_time: [
                        {type: 'date', required: true, message: '请选择日期时间', trigger: 'blur'}
                    ],
                    classno:[
                        { validator:validateClass,required:true,trigger: 'blur'}
                    ],
                    end_time: [
                        {type: 'date', required: true, message: '请选择日期时间', trigger: 'blur'}
                    ],
                    last_time: [
                        {
                            required: true,
                            message: '考试时间',
                            trigger: 'blur'
                        }
                    ],
                }
            }
        },
        created() {
            this.classList = JSON.parse(sessionStorage.getItem("classInfo"))
        },
        props: {
            paper:{//新建的试卷的id
                required:true
            }
        },
        computed:{
            ...mapGetters([
                "classInfo"
            ])
        },
        methods:{
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {//表单验证成功
                        let reg = /\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}/g;
                        let data = {
                            pname: this.form.pname,//试卷名称
                            start_time: JSON.stringify(this.form.start_time).split('"')[1].match(reg)[0].split("T").join(" "),//开始日期时间
                            classno:this.form.classno,
                            share:this.form.share,
                            end_time: JSON.stringify(this.form.end_time).split('"')[1].match(reg)[0].split("T").join(" "),//截至时间
                            last_time:this.form.check=="无限制"?0:this.form.last_time,// 考试时间/分钟
                            full_score:this.paper.full_score
                        }
                        newPaper(data).then(res=>{
                            reusePaper({
                                pid:this.paper.pid,
                                pid1:res.data
                            }).then(res=>{
                                if(res.data){
                                    this.$message({
                                        message: '发布成功',
                                        type: 'success'
                                    });
                                    this.prevStep();
                                }
                            }).catch(err=>{
                                throw err;
                            })
                        }).catch(err=>{
                            throw err;
                        })
                    } else {
                        return false;
                    }
                });
            },
            prevStep(){
                this.$emit("back")
            }
        }
    }
</script>

<style scoped>

</style>