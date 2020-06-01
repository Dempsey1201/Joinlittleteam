<template>
    <div class="showClass">
        <el-table
                :data="currentList"
                style="width: 100%">
            <el-table-column
                    type="index"
                    min-width="50">
            </el-table-column>
            <el-table-column
                    prop="classname"
                    class="name"
                    label="班级名称"
                    min-width="180">
            </el-table-column>
            <el-table-column
                    prop="college"
                    label="学校"
                    min-width="180">
            </el-table-column>
            <el-table-column
                    prop="major"
                    label="科目"
                    min-width="180"
            >
            </el-table-column>
            <el-table-column
                    align="right"
                    min-width="80"
                    label="操作"
            >
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">查看</el-button>
                </template>
            </el-table-column>
        </el-table>
        <pageTool :step="step" :list="classList" @check="changeList"></pageTool>
    </div>
</template>

<script>
    import {allClass} from "../../api/yourClass";
    import pageTool from "../pageTool";
    export default {
        name: "showClass",
        data(){
            return{
                classList:[],
                search:"",
                currentList:[],
                step:9
            }
        },
        components:{
            pageTool
        },
        created() {
            allClass({
                id:JSON.parse(sessionStorage.getItem("userInfo")).id
            }).then(res=>{
                this.classList = res.data
                this.currentList = this.classList.slice(0,this.step);
            }).catch(err=>{
                throw err;
            })
        },
        methods:{
            handleDelete(index,row){

            },
            handleEdit(index,row){
                this.$router.push({
                    name: 'showPaper',
                    params: {
                        row: row
                    }
                })
            },
            changeList(list){
                this.currentList = list;
            }
        },

    }
</script>

<style scoped>

</style>