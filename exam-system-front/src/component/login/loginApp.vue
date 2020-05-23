<template>
    <div id="app">
        <el-row class="card">
            <el-col class="hidden-md-and-down"
                :xs="{span:5}"
                :sm="{span:7}"
                :md="{span:12}"
                :lg="{span:15}"
                :xl="{span:17}"
            >
                <div class="titleL ">
                    PTA考试系统<i class="el-icon-check"></i>
                </div>
            </el-col>
            <el-col class="con"
                    :xs="{span:11,offset:5}"
                    :sm="{span:9,offset:7}"
                    :md="{span:8,offset:12}"
                    :lg="{span:6,offset:0}"
                    :xl="{span:6,offset:0}"
            >
                <!-- 导航 -->
                <el-menu
                        :default-active="router[1].path"
                        class="el-menu-demo" mode="horizontal"
                        :router="bool"
                >
                    <el-menu-item :index="router[1].path" class="nav">
                        <router-link class="tag" tag="div" to="">
                        登陆
                    </router-link>
                    </el-menu-item>
                    <el-menu-item :index="router[2].path" class="nav">
                        注册
                    </el-menu-item>
                </el-menu>
                <router-view></router-view>
            </el-col>
        </el-row>
    </div>
</template>
<script>
    import background from "../../component/base/background";
    import login from "./components/login/login";
    import register from "./components/register/register";
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
                router:this.$root._router.options.routes[1].children
		    }
		},
        components:{
			background,
            login,
            register
        },
        created() {
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
