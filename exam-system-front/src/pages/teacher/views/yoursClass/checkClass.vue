<template>
    <div class="checkClass">
        <el-button style="display: block" type="primary" size="mini" @click="back">返回</el-button>
        <el-table
                :data="stuList"
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
    </div>
</template>

<script>
    import {getStudent} from "../../api/yourClass";

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
                url:"http://47.94.210.131:8080/"
            }
        },
        created() {
            console.log(this.currentClass)
            getStudent({
                id:this.currentClass.id
            }).then(res=>{
                this.stuList = res.data;
            }).catch(err=>{
                throw err;
            })
        },
        methods:{
            back(){
                this.$emit("back");
            }
        }
    }
</script>

<style scoped>

</style>