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
                    <tab></tab>
                </el-aside>
                <el-main>
                    <!-- 中间内容区 -->
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script>
    import tab from "./components/tab/tab";
    import background from "../../component/base/background";
    import headerTab from "./components/headerTab/headerTab";
	export default {
		data () {
			return {
				ball_color:{//小球颜色
					r: 64,
					g: 158,
					b: 255
				},
				bg_color:"rgba(236, 245, 255,0.3)",//背景颜色
			};
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
                }
            }

        }

    }
</style>
