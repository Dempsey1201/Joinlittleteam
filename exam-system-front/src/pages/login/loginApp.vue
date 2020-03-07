<template>
    <div id="app">
        <background :ball_color="ball_color" :bg_color="bg_color"></background>

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
                    :xs="{span:10,offset:5}"
                    :sm="{span:8,offset:7}"
                    :md="{span:7,offset:12}"
                    :lg="{span:5,offset:0}"
                    :xl="{span:5,offset:0}"
            >
                <!-- 导航 -->
                <el-menu
                        :default-active="activeIndex"
                        class="el-menu-demo" mode="horizontal"
                >
                    <el-menu-item index="1" class="nav">
                        <router-link class="tag" tag="div" to="/login.html/login">
                            登陆
                        </router-link>
                    </el-menu-item>
                    <el-menu-item index="2" class="nav">
                        <router-link class="tag" tag="div" to="/login.html/register">
                            注册
                        </router-link>
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
				activeIndex: '1'
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

<style lang="less">
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
                        padding: 0 !important;
                        width: 60px;
                        text-align: center;
                    }
                    .tag{
                        width: 100%;
                        height: 100%;
                    }
                }

            }
        }
    }
</style>
