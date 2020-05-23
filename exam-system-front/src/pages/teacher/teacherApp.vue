<template>
    <div id="app">
        <background :bg_color="bg_color" :ball_color="ball_color"></background>
        <router-view></router-view>
    </div>
</template>
<script>
    import background from "../../component/base/background";
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
		    background
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
