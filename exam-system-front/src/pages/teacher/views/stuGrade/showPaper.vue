<template>
    <div class="showPaper">
        <el-button style="display: block" type="primary" size="mini" @click="prevStep">上一步</el-button>
        <el-table
                :data="paperList"
                style="width: 100%">
            <el-table-column
                    type="index"
                    min-width="50">
            </el-table-column>
            <el-table-column
                    prop="pname"
                    class="name"
                    label="试卷名称"
                    min-width="180">
            </el-table-column>
            <el-table-column
                    prop="start_time"
                    label="开始时间"
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
    import {getPaper} from "../../api/stuGrade";

    export default {
        name: "showPaper",
        data(){
          return{
              paperList:[],
          }
        },
        created() {
            getPaper({
                classno:this.$route.params.row.id
            }).then(res=>{
                this.paperList = res.data;
            }).catch(err=>{
                throw err;
            })
        },
        methods:{
            prevStep(){
                this.$router.push({
                    name: 'showClass'
                })
            },
            handleEdit(index,row){
                this.$router.push({
                    name: 'showStudent',
                    params: {
                        row: row,
                        classInfo:this.$route.params.row
                    }
                })
            },
            handleDelete(index,row){

            }
        }
    }
</script>

<style scoped>

</style>