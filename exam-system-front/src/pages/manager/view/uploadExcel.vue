<template>
  <div class="excel">
      <upload/>
  </div>
</template>

<script>
import upload from '../../../component/base/upload'
export default {
  name: 'excel',
  components:{
    upload
  },
  created() {
    document.title="导入老师名单"
  },
 }
</script>

<style scoped>
  .excel{

    color: red
  }
</style>