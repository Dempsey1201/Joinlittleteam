<template>
    <div class="showPaper">
        <el-button style="display: block" type="primary" size="mini" @click="prevStep">上一步</el-button>
        <el-table
                :data="currentList"
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
        <pageTool :step="step" :list="paperList" @check="changeList"></pageTool>
    </div>
</template>

<script>
    import {getPaper,delPaper} from "../../api/stuGrade";
    import {getPaperInfo} from "../../api/publishExam";
    import pageTool from "../pageTool";
    export default {
        name: "showPaper",
        data(){
          return{
              paperList:[],
              currentList:[],
              step:9
          }
        },
        created() {
            getPaper({
                classno:this.$route.params.row.id
            }).then(res=>{
                this.paperList = res.data;
                this.currentList = this.paperList.slice(0,this.step);
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
                getPaperInfo({
                    pid:row.pid
                }).then(res=>{
                    sessionStorage.setItem("paperInfo",JSON.stringify(res.data))
                }).catch(err=>{
                    throw err;
                })
                this.$router.push({
                    name: 'showStudent',
                    params: {
                        row: row,
                        classInfo:this.$route.params.row
                    }
                })
            },
            handleDelete(index,row){
                if(confirm("确定要删除试卷吗？")){
                    delPaper({
                        pid:row.pid
                    }).then(res=>{
                        if(res.data){
                            this.paperList.splice(index,1);
                            this.currentList = this.paperList.slice(0,this.step);
                        }
                    })
                }
            },
            changeList(list){
                this.currentList = list;
            }
        },
        components:{
            pageTool
        }
    }
</script>

<style scoped>

</style>