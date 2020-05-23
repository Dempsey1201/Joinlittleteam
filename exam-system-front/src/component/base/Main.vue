<template>
  <div class="main">
    <!-- <div class="main-box"> -->
    <router-view class="main-content"></router-view>
    <!-- </div> -->
  </div>
</template>
<style lang="less">
.main {
  padding: 2px !important;
  .main-content {
    box-sizing: border-box;
    min-height: 100%;
    width: 100%;
    box-shadow: 0 2px 12px 0 rgba(64, 158, 255, 0.18);
  }
}
</style>
<script>
export default {};
</script>