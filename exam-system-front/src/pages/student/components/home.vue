<template>
  <div class="home">
      <div class="content">
      <el-col class="row-bg">
        <el-col class="hidden-md-and-down" :span="5">
          <leftContent />
        </el-col>
        <el-col :span="19">
          <rightContent />
        </el-col>
      </el-col>
    </div>
  </div>
</template>

<script>
import leftContent from "./leftContent"
import rightContent from "./rightContent"

export default {
  components: {
    leftContent,
    rightContent
  },
  data() {
    return {};
  }
};
</script>

<style scoped>
  .content{
  margin-top: 5px;
  padding: 10px;
}
</style>