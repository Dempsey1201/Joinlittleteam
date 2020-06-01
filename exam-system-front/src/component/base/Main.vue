<template>
  <div class="main">
    <!-- <div class="main-box"> -->
    <router-view class="main-content" @fromChild="getImg"></router-view>
    <!-- </div> -->
  </div>
</template>
<style lang="less">
.main {
  padding: 10px !important;
  .main-content {
    box-sizing: border-box;
    min-height: 100%;
    width: 100%;
    box-shadow: 0 2px 12px 0 rgba(64, 158, 255, 0.18);
  }
}
</style>
<script>
export default {
  data() {
    return {
      imgUrl:''
    }
  },
  created() {
    console.log(this.imgUrl);
  },
  methods: {
    getImg(value){
      this.imgUrl=value;
      consloe.log('imgUrl----------------'+this.imgUrl)
    }
  },
};
</script>