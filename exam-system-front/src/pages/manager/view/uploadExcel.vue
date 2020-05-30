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
  }
 }
</script>

<style scoped>
  .excel{

    color: red
  }
</style>