<template>
    <div class="Home" ref="box">
        <background :bg_color="bg_color" :ball_color="ball_color"></background>
        <el-container class="con" :style="'height:'+height+'px'">

            <el-header class="elHeader">
                <!-- 头部导航 -->
                <headerTab></headerTab>
            </el-header>
            <el-container>
                <el-aside width="250px" class="hidden-sm-and-down">
                    <!-- 左侧导航 -->
                    <tab :check="!bool"></tab>
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
    import tab from "./tab";
    import background from "../../../component/base/background";
    import headerTab from "./headerTab";

    export default {
        name: "Home",
        data() {
            return {
                ball_color: {
                    //小球颜色
                    r: 64,
                    g: 158,
                    b: 255
                },
                bg_color: "rgba(236, 245, 255,0.3)", //背景颜色
                bool: true,
                height:0
            };
        },
        components: {
            tab,
            background,
            headerTab
        },
        mounted() {
            this.height = this.$refs["box"].clientHeight
        }
    }
</script>

<style scoped>
    .Home{
        position: relative;
        min-height: 100%;
    }
</style>