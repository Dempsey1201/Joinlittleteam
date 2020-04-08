<template>
    <div id="publishExam">
<!--        试卷管理-->
        <template>
            <el-tabs v-model="activeName">
                <el-tab-pane label="我的试卷" name="first">
                    <myExam></myExam>
                </el-tab-pane>
                <el-tab-pane label="新建试卷" name="second">
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
    import myExam from "./myExam";
    import newExam from "./newExam";
    import searchExam from "./searchExam";
	export default {
		name: "publishExam",
        data(){
			return{
				activeName:"first"
            }
        },
        components:{
			myExam,
            newExam,
            searchExam
        }
	}
</script>

<style scoped>

</style>