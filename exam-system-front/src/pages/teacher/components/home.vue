<template>
    <el-container class="con">
        <el-header class="elHeader">
            <!-- 头部导航 -->
            <headerTab></headerTab>
        </el-header>
        <el-container>
            <el-aside
                    width="250px"
                    class="hidden-sm-and-down"
            >
                <!-- 左侧导航 -->
                <tab :check="!bool"></tab>
            </el-aside>
            <el-aside
                    width="64px"
                    class="hidden-md-and-up"
            >
                <!-- 左侧导航 -->
                <tab :check="bool"></tab>
            </el-aside>
            <el-main class="main-con">
                <!-- 中间内容区 -->
                <router-view class="main-content"></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    import tab from "./tab";
    import background from "../../../component/base/background";
    import headerTab from "./headerTab";

    export default {
        name: "home",
        data(){
            return{
                bool:true
            }
        },
        created() {
            // 创建的时候获取教师的所有信息，并存入 store 中
            // 获取教师个人信息
            // teacherInfo(12).then((res)=>{
            // 	console.log(res);
            // })
            // // 获取班级管理信息
            // classInfo(12).then(res=>{
            // 	console.log(res);
            // })
            // // 获取试卷信息
            // testPaper(12).then(res=>{
            // 	console.log(res);
            // })
            // // 获取学生成绩信息
            // stuGrade(12).then(res=>{
            // 	console.log(res);
            // })
        },
        components:{
            tab,
            background,
            headerTab
        }
    }
</script>

<style scoped>

</style>