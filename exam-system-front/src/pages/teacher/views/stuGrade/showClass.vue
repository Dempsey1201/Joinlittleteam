<template>
    <div class="showClass">
        <el-table
                :data="classList"
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
                    min-width="50"
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
    import {allClass} from "../../api/yourClass";

    export default {
        name: "showClass",
        data(){
            return{
                classList:[],
                search:""
            }
        },
        created() {
            allClass({
                id:JSON.parse(sessionStorage.getItem("userInfo")).id
            }).then(res=>{
                this.classList = res.data
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
            }
        },

    }
</script>

<style scoped>

</style>