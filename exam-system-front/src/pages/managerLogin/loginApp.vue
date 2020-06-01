<template>
    <div id="app">
        <background :bg_color="bg_color" :ball_color="ball_color"></background>
        <el-row class="card">
            <el-col class="con"
                    :xs="{span:12,offset:5}"
                    :sm="{span:10,offset:6}"
                    :md="{span:8,offset:7}"
                    :lg="{span:6,offset:8}"
                    :xl="{span:6,offset:8}"
            >
                <!-- 导航 -->
                <el-menu
                        class="el-menu-demo" mode="horizontal"
                        :default-active="'1'"
                        :router="bool"
                >
                    <el-menu-item :index="'1'" class="nav" style="margin:auto !important;float: none">
                        登陆
                    </el-menu-item>
                </el-menu>
                <managerLogin></managerLogin>
            </el-col>
        </el-row>
    </div>
</template>
<script>
    import background from "../../component/base/background";
    import managerLogin from "./components/login/managerLogin";
	export default {
		data () {
			return {
				ball_color:{//小球颜色
					r: 64,
					g: 158,
					b: 255
                },
				bg_color:"rgba(236, 245, 255,0.3)",//背景颜色
                bool:true,
		    }
		},
        components:{
			background,
            managerLogin,
        },
        created() {
            document.title = "管理员登陆"
        }
	}
</script>

<style lang="less" scoped>
    html{
        margin: 0;
        padding: 0;
        width: 100%;
        min-height: 100%;
        /*overflow: hidden;*/
    }
    body{
        width: 100%;
        height: 100%;
        margin: 0;
        padding: 0;
        #app{
            box-sizing: border-box;
            padding-top: 100px;
            width: 100%;
            min-height: 100%;

            .card{
                /*height:400px;*/
                .titleL{
                    position: relative;
                    top: 120px;
                    margin: 0 auto;
                    width: 570px;
                    text-align: center;
                    font-family: "PingFang SC";
                    font-size: 55px;
                    font-weight: bold;
                    color: #fff;
                    background-color: #409EFF;
                    letter-spacing: 20px;
                }
                .con{
                    padding: 0 20px 20px;
                    height: 100%;
                    background-color: #fff;
                    box-shadow: 4px 6px 12px 0 rgba(64, 158, 255, 0.25);
                    border-radius:10px;
                    min-width: 300px !important;
                    .nav{
                        text-align: center;
                    }
                }

            }
        }
    }
</style>
