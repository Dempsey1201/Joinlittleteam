<template>
    <div id="searchExam">
        <div v-if="!pid" class="list">
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
        <d_reuse v-if="pid" :pid1="pid"></d_reuse>
    </div>
</template>

<script>
    import {getPublicPaper,searchPublicPaper} from "../../api/publishExam";
    import d_reuse from "./d_reuse";
    export default {
		name: "searchExam",
        data(){
		    return{
                paperList:[],
                search:"",
                pid:null
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
                // console.log(row)
                this.pid = row.pid;
            }
        },
        components:{
            d_reuse
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