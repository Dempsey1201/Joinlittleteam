<template>
    <div id="publishExam">
<!--        试卷管理-->
        <template>
            <el-tabs v-model="activeName" @tab-click="start">
                <el-tab-pane label="新建试卷" name="first">
                    <newExam></newExam>
                </el-tab-pane>
                <el-tab-pane  label="搜索试卷" name="third">
                    <searchExam v-if="bool"></searchExam>
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
				activeName:"first",
                bool:false
            }
        },
        methods:{
            start(tab){
                console.log(tab)
                if(tab.name=="third"){
                    this.bool = true;
                }else {
                    this.bool = false;
                }
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