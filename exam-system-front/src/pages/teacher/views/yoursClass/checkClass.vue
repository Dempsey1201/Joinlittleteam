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
                            style="width: 100px; height: 100px;border-radius: 50%;border:1px solid red;"
                            :src="url"
                            :fit="fit"></el-image>
                </template>
            </el-table-column>

            <el-table-column
                    prop="classname"
                    class="name"
                    label="姓名"
                    min-width="180">
            </el-table-column>
            <el-table-column
                    prop="college"
                    label="性别"
                    min-width="180">
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
                            @click="handleEdit(scope.$index, scope.row)">查看</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
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
                stuList:[]
            }
        },
        created() {
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