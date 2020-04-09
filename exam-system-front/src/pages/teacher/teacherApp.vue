<template>
    <div id="app">
        <background :bg_color="bg_color" :ball_color="ball_color"></background>
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
    </div>
</template>
<script>
    import tab from "./components/tab";
    import background from "../../component/base/background";
    import headerTab from "./components/headerTab";
    // import {teacherInfo,testPaper,stuGrade,classInfo} from "./api/getTeacherInfo"
	export default {
		data () {
			return {
				ball_color:{//小球颜色
					r: 64,
					g: 158,
					b: 255
				},
				bg_color:"rgba(236, 245, 255,0.3)",//背景颜色
                bool:true
			};
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

<style lang="less">
    html{
        margin: 0;
        padding: 0;
        width: 100%;
        height: 100%;
        /*overflow: hidden;*/
    }
    textarea.el-textarea__inner{
        resize:none;
    }
    .el-select{
        width: 100%;
    }
    body {
        width: 100%;
        height: 100%;
        margin: 0;
        padding: 0;
        #app{
            height: 100%;
            .con{
                height: 100%;
                .elHeader{
                    padding: 0;
                    height: 80px !important;
                }
                .main-con{
                    padding: 20px;
                    .main-content{
                        box-sizing:border-box;
                        padding: 20px;
                        min-height: 100%;
                        width: 100%;
                        background-color: rgba(255, 255, 255, 0.74);
                        box-shadow: 0 2px 12px 0 rgba(64, 158, 255, 0.18);
                    }
                }
            }

        }

    }
</style>
