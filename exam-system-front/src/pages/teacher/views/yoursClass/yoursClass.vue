<template>
    <div id="yoursClass">
        <el-tabs v-model="activeName">
            <el-tab-pane label="班级信息" name="first">
                <div v-show="!currentClass.classname" class="table">
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
                                prop="classno"
                                label="邀请码"
                                min-width="180"
                        >
                        </el-table-column>
                        <el-table-column
                                align="right"
                                min-width="180"
                                label="操作"
                        >
                            <template slot-scope="scope">
                                <el-button
                                        size="mini"
                                        type="danger"
                                        @click="handleDelete(scope.$index, scope.row)">删除
                                </el-button>
                                <el-button
                                        size="mini"
                                        @click="handleEdit(scope.$index, scope.row)">查看
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <pageTool :step="step" :list="classList" @check="changeList"></pageTool>
                </div>
                <checkClass v-if="currentClass.classname" :currentClass="currentClass"
                            @back="back"
                ></checkClass>
            </el-tab-pane>
            <el-tab-pane label="新建班级" name="third">
                <newClass :classList="classList"></newClass>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    import checkClass from "./checkClass";
    import {deleteClass} from "../../api/yourClass";
    import newClass from "./newClass";
    import pageTool from "../pageTool";
    export default {
        name: "yoursClass",
        data(){
            return{
                classList:[],
                currentClass:{},
                search:"",
                activeName:"first",
                currentList:[],
                step:7
            }
        },
        created() {
            this.classList = JSON.parse(sessionStorage.getItem("classInfo"))
            this.currentList = this.classList.slice(0,this.step);
        },
        computed:{
        },
        methods:{
            handleEdit(index, row){
                this.currentClass = row;
            },
            back(){
                this.currentClass = {};
            },
            handleDelete(index, row){
                if(confirm("确定要删除该班级吗？")){
                    deleteClass({
                        id:row.id
                    }).then(res=>{
                        if(res.data){
                            this.classList.splice(index,1);
                        }
                        sessionStorage.setItem("classInfo",JSON.stringify(this.classList.splice(index,1)))
                    }).catch(err=>{
                        throw err;
                    })
                }
            },
            changeList(list){
                this.currentList = list;
            }
        },
        components:{
            checkClass,
            newClass,
            pageTool
        },
    }
</script>

<style scoped>

</style>