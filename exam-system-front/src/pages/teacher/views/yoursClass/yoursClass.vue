<template>
    <div id="yoursClass">
        <div v-show="!currentClass.classname" class="table">
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
                        min-width="210"
                >
                    <template slot="header" slot-scope="scope">
                        <el-button
                                style="float: right;display: inline-block"
                                size="mini"
                        >Search</el-button>
                        <el-input
                                style="float: right;width: 143px"
                                v-model="search"
                                size="mini"
                                placeholder="搜索"/>

                    </template>
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                type="danger"
                                @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                        <el-button
                                size="mini"
                                @click="handleEdit(scope.$index, scope.row)">查看</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <checkClass v-if="currentClass.classname" :currentClass="currentClass"
                    @back="back"
        ></checkClass>
    </div>
</template>

<script>
    import checkClass from "./checkClass";
    export default {
        name: "yoursClass",
        data(){
            return{
                classList:[
                    {
                        id:1,
                        classname:"软件工程",
                        college:"东北师范大学",
                        major:"信息科学与技术"
                    },
                    {
                        id:2,
                        classname:"软件工程",
                        college:"东北师范大学",
                        major:"信息科学与技术"
                    },
                    {
                        id:3,
                        classname:"软件工程",
                        college:"东北师范大学",
                        major:"信息科学与技术"
                    },
                    {
                        id:4,
                        classname:"软件工程",
                        college:"东北师范大学",
                        major:"信息科学与技术"
                    }],
                currentClass:{},
                search:""
            }
        },
        methods:{
            handleEdit(index, row){
                this.currentClass = row;
            },
            back(){
                this.currentClass = {};
            },
            handleDelete(index, row){
                console.log(row);
            }
        },
        components:{
            checkClass
        }
    }
</script>

<style scoped>

</style>