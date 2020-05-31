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
        }
    }
</script>

<style lang="less">
</style>
