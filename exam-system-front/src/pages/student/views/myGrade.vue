<template>
  <div class="myGrade">
    <el-table
      :data="tableData"
      style="width: 100%;"
      :default-sort="{prop: 'date', order: 'descending'}"
    >
      <el-table-column prop="id" label="班级" sortable></el-table-column>
      <el-table-column prop="classname" label="班级名称"></el-table-column>
      <el-table-column prop="teachername" label="老师"></el-table-column>
      <el-table-column prop="college" label="大学"></el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "myGrade",
  data() {
    return {
       tableData: [{
          id:1,
          classname: '软件一体化2',
          college:"东北师范大学",
          teachername:"邹老师",
          
        }, {
          id:2,
          classname: '软件一体化2',
          college:"东北师范大学",
          teachername:"邹老师",
        }, {
          id:3,
          classname: '软件一体化2',
          college:"东北师范大学",
          teachername:"邹老师",
        }, {
          id:4,
          classname: '软件一体化2',
          college:"东北师范大学",
          teachername:"邹老师",
        }]
    }
  },
};
</script>
<style scoped>
.el-table{
  background-color:rgba(255, 255, 255, 0.74) !important;
}
.el-table th{
    background:rgba(255, 255, 255, 0.74) !important;
  }
  .el-table tr{
   background:rgba(255, 255, 255, 0.74) !important;
  }
</style>