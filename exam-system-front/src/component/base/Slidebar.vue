<template>
  <div class="sidebar">
    <el-menu
      default-active="/informationentry"
      class="el-menu-vertical-demo"
      background-color="rgb(6, 58, 110)"
      text-color="rgb(191, 203, 217)"
      active-text-color="#409EFF"
      router
    >
      <template v-for="(item,index) in routers">
        <div v-if="index==0"></div>
        <el-menu-item :index="item.path" :key="index" v-else>
          <i :class="item.meta.icon"></i>
          <span slot="title">{{item.meta.name}}</span>
        </el-menu-item>
      </template>
    </el-menu>
  </div>
</template>

<script>
export default {
  data() {
    return {
      routers: this.$root._router.options.routes[2].children,
    };
  },
  methods: {},
  created() {
    // console.log(this.routers);
  },
  computed: {
    slidechange() {
      return this.$store.state.isCollapse;
    }
  }
};
</script>

<style scoped>
ul {
  min-height: 80px !important;
}

.sidebar {
  background-color: rgb(6, 58, 110);
  color: rgb(37, 23, 23);
  height: calc(100vh - 70px);
}

.el-menu-item:focus {
  background-color: rgb(28, 55, 95) !important;
}

.el-menu-item {
  font-size: 14px;
}

.el-menu {
  border-right: 0px !important;
  list-style: none;
  position: relative;
  margin: 0;
  padding-left: 0;
  background-color: #ffffff;
}
</style>