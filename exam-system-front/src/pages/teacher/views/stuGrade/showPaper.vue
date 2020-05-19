<template>
    <div class="showPaper">
        <el-button style="display: block" type="primary" size="mini" @click="prevStep">上一步</el-button>
        <el-table
                :data="paperList"
                style="width: 100%">
            <el-table-column
                    type="index"
                    min-width="50">
            </el-table-column>
            <el-table-column
                    prop="exam_name"
                    class="name"
                    label="试卷名称"
                    min-width="180">
            </el-table-column>
            <el-table-column
                    prop="classno"
                    label="所属班级"
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
    export default {
        name: "showPaper",
        data(){
          return{
              paperList:[
                  {
                      exam_name:"期中考试",
                      classno:"软件二班",
                      end_time:"2020-09-10"
                  },
                  {
                      exam_name:"期中考试",
                      classno:"软件二班",
                      end_time:"2020-09-10"
                  },
                  {
                      exam_name:"期中考试",
                      classno:"软件二班",
                      end_time:"2020-09-10"
                  },
                  {
                      exam_name:"期中考试",
                      classno:"软件二班",
                      end_time:"2020-09-10"
                  },
                  {
                      exam_name:"期中考试",
                      classno:"软件二班",
                      end_time:"2020-09-10"
                  },
              ],
              search:""
          }
        },
        created() {
            console.log(this.$route.params.row)
        },
        methods:{
            prevStep(){
                this.$router.go(-1);
            },
            handleEdit(index,row){
                this.$router.push({
                    name: 'showStudent',
                    params: {
                        row: row
                    }
                })
            },
            handleDelete(index,row){

            }
        }
    }
</script>

<style scoped>

</style>