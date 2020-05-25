<template>
    <div class="showStudent">
        <el-button style="display: block" type="primary" size="mini" @click="prevStep">上一步</el-button>
        <el-table
                :data="studentList"
                style="width: 100%">
            <el-table-column
                    type="index"
                    min-width="50">
            </el-table-column>
            <el-table-column
                    prop="exam_name"
                    class="name"
                    label="试卷名称"
                    min-width="180">
            </el-table-column>
            <el-table-column
                    prop="classno"
                    label="所属班级"
                    min-width="180">
            </el-table-column>
            <el-table-column
                    prop="end_time"
                    label="结束时间"
                    min-width="180"
            >
            </el-table-column>
            <el-table-column
                    align="right"
                    min-width="210"
                    label="操作"
            >
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">查看</el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
                // console.log(res.data);
            }).catch(err=>{
                throw err;
            })
        },
        data(){
            return{
                studentList:[
                    {

                    }
                ]
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
        }
    }
</script>

<style scoped>

</style>