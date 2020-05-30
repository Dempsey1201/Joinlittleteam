<template>
    <div class="showStudent">
        <el-button style="display: block" type="primary" size="mini" @click="prevStep">返回</el-button>
        <el-table
                :data="studentList"
                style="width: 100%">
            <el-table-column
                    type="index"
                    min-width="50">
            </el-table-column>
            <el-table-column
                    prop="pname"
                    class="name"
                    label="姓名"
                    min-width="180">
            </el-table-column>
            <el-table-column
                    prop="question"
                    label="email"
                    min-width="180">
            </el-table-column>
            <el-table-column
                    label="得分"
                    min-width="180"
            >
                <template slot-scope="scope">
                    {{scope.row.score?scope.row.score+"/"+fullScore:'暂无成绩'}}
                </template>
            </el-table-column>
            <el-table-column
                    align="right"
                    min-width="100"
                    label="操作"
            >
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">查看</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import {getGrades} from "../../api/stuGrade";

    export default {
        name: "showStudent",
        created() {
            getGrades({
                pid:this.$route.params.row.pid,
                classno:this.$route.params.classInfo.id
            }).then(res=>{
                this.studentList = res.data;
            }).catch(err=>{
                throw err;
            })
        },
        data(){
            return{
                studentList:[],
                fullScore:this.$route.params.row.full_score
            }
        },
        methods:{
            prevStep(){
                this.$router.push({
                    name: 'showPaper',
                    params: {
                        row: this.$route.params.classInfo,
                    }
                })
            },
            handleEdit(index,row){// 查看
                if(!row.score){
                    this.$message.error('该学生尚未交卷');
                    return;
                }
                this.$router.push({
                    name: 'showStuPaper',
                    params: {
                        pid:this.$route.params.row.pid,// pid
                        classno:this.$route.params.classInfo.id,// 班级id
                        sid:row.uid,
                        fullScore:this.$route.params.row.full_score
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>