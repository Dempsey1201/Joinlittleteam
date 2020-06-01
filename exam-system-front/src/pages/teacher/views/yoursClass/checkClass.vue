<template>
    <div class="checkClass">
        <el-button style="display: block" type="primary" size="mini" @click="back">返回</el-button>
        <el-table
                :data="currentList"
                style="width: 100%">
            <el-table-column
                    type="index"
                    min-width="50">
            </el-table-column>
            <el-table-column
                    prop="major"
                    label="头像"
                    min-width="180"
            >
                <template slot-scope="scope">
                    <el-image
                            style="width: 50px; height: 50px;border-radius: 50%;"
                            :src="url+scope.row.headUrl"
                            ></el-image>
                </template>
            </el-table-column>
            <el-table-column
                    prop="username"
                    class="name"
                    label="姓名"
                    min-width="180">
            </el-table-column>
            <el-table-column
                    prop="college"
                    label="学校"
                    min-width="180">
            </el-table-column>
            <el-table-column
                    prop="email"
                    label="邮箱"
                    min-width="180">
            </el-table-column>
            <el-table-column
                    align="right"
                    min-width="210"
                    label="操作"
            >
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <pageTool :step="step" :list="stuList" @check="changeList"></pageTool>
    </div>
</template>

<script>
    import {getStudent,deleteUSer} from "../../api/yourClass";
    import pageTool from "../pageTool";
    export default {
        name: "checkClass",
        props:{
            currentClass:{
                type:Object,
                require:true
            }
        },
        data(){
            return{
                stuList:[],
                url:"http://47.94.210.131:8080/",
                currentList:[],
                step:5
            }
        },
        components:{
            pageTool
        },
        created() {
            getStudent({
                id:this.currentClass.id
            }).then(res=>{
                this.stuList = res.data.filter(item=>item);
                this.currentList = this.stuList.slice(0,this.step);
            }).catch(err=>{
                throw err;
            })
        },
        methods:{
            back(){
                this.$emit("back");
            },
            changeList(list){
                this.currentList = list;
            },
            handleDelete(index,row){
                if(confirm("确定要删除该学生吗？")){
                    deleteUSer({
                        id:row.id,
                        cid:this.currentClass.id
                    }).then(res=>{
                        if(res.data){
                            this.$message({
                                message: '删除成功',
                                type: 'success'
                            });
                            this.stuList.splice(index,1);
                            this.currentList = this.stuList.slice(0,this.step);
                        }else{
                            this.$message.error('删除失败');
                        }
                    })
                }
            }
        }
    }
</script>

<style scoped>

</style>