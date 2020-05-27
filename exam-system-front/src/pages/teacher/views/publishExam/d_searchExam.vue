<template>
    <div id="searchExam">
        <div v-if="!paper&&!questionList.length" class="list">
            <el-col :span="8" style="position: relative">
                <el-input
                        placeholder="搜索试卷"
                        v-model="search"
                        clearable>
                </el-input>
                <i class="el-icon-search"
                   @click="searchPaper"
                ></i>
            </el-col>
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
                                @click="handlePublish(scope.$index, scope.row)">发布</el-button>
                        <el-button
                                size="mini"
                                @click="handleCheck(scope.$index, scope.row)">查看</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <d_reuse v-if="paper" @back="back" :paper="paper"></d_reuse>
        <exam v-if="questionList.length" :questionList="questionList" @back="back"></exam>
    </div>
</template>

<script>
    import {getPublicPaper,searchPublicPaper,getPaperInfo} from "../../api/publishExam";
    import d_reuse from "./d_reuse";
    import exam from "../exam";
    export default {
		name: "searchExam",
        data(){
		    return{
                paperList:[],
                search:"",
                paper:null,
                questionList:[]
            }
        },
        created() {
		    getPublicPaper().then(res=>{
		        this.paperList = res.data
            }).catch(err=>{
                throw err;
            })
        },
        methods:{
            searchPaper(){
                searchPublicPaper({
                    pname:this.search
                }).then(res=>{
                    this.paperList = res.data;
                }).catch(err=>{
                    throw err;
                })
            },
            handlePublish(index,row){
                this.paper = row;
            },
            handleCheck(index,row){
                getPaperInfo({
                    pid:row.pid
                }).then(res=>{
                    this.questionList = res.data;
                    if(!res.data){
                        this.$message.error('当前没有内容');
                    }
                }).catch(err=>{
                    throw err;
                })
            },
            back(){
                this.paper = null;
                this.questionList = [];
            }
        },
        components:{
            d_reuse,
            exam
        }
    }
</script>

<style scoped>
    .el-icon-search{
        position: absolute;
        right: 10px;
        top:15px;
        cursor: pointer;
    }
</style>