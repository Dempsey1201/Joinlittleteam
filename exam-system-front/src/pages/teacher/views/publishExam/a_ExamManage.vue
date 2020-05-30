<template>
    <div id="publishExam">
<!--        试卷管理-->
        <template>
            <el-tabs v-model="activeName">
                <el-tab-pane label="新建试卷" name="first">
                    <newExam></newExam>
                </el-tab-pane>
                <el-tab-pane label="搜索试卷" name="third">
                    <searchExam></searchExam>
                </el-tab-pane>
            </el-tabs>
        </template>
    </div>
</template>

<script>
    import newExam from "./b_newExam";
    import searchExam from "./d_searchExam";
	export default {
		name: "publishExam",
        data(){
			return{
				activeName:"first"
            }
        },
        components:{
            newExam,
            searchExam,
        }
	}
</script>

<style scoped>

</style>