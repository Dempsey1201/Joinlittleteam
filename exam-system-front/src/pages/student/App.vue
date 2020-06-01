<template>
  <div id="app">
      <background :bg_color="bg_color" :ball_color="ball_color"></background>
      <router-view></router-view>
  </div>
</template>
<script>
    import background from "../../component/base/background";
    export default {
        data(){
            return{
                ball_color:{//小球颜色
                    r: 64,
                    g: 158,
                    b: 255
                },
                bg_color:"rgba(236, 245, 255,0.3)",//背景颜色
            }
        },
        components:{
            background
        },
        created() {
            document.body.style.height = document.documentElement.clientHeight+"px";
        }
    }
</script>

<style lang="less">
    #app{
       min-height: 100%;
    }
    .el-checkbox__input.is-disabled + span.el-checkbox__label{
        color: #606266 !important;
    }
    .el-radio__input.is-disabled+span.el-radio__label{
        color: #606266 !important;
    }
    .el-radio__input.is-disabled.is-checked .el-radio__inner{
        border-color: #409EFF !important;
        background: #409EFF !important;
    }
    .el-checkbox__input.is-disabled.is-checked .el-checkbox__inner{
        border-color: #409EFF !important;
        background: #409EFF !important;
    }
</style>
