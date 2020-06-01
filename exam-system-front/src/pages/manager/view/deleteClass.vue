<template>
  <div class="class">
    <el-table :data="currentList" style="width: 100%">
      <el-table-column type="index" min-width="50"></el-table-column>
      <el-table-column prop="classname" class="name" label="班级名称" min-width="140"></el-table-column>
      <el-table-column prop="classno" label="邀请码" min-width="140"></el-table-column>
      <el-table-column prop="college" label="学校" min-width="140"></el-table-column>
      <el-table-column prop="major" label="专业" min-width="140"></el-table-column>
      <el-table-column prop="teachername" label="老师" min-width="140"></el-table-column>
      <el-table-column align="center" min-width="200" label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">查看</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pageTool :step="step" :list="classList" @check="changeList"></pageTool>
  </div>
</template>

<script>
import axios from "axios";
import pageTool from "../components/pageTool";
export default {
  name: "class",
  components: {
    pageTool
  },
  data() {
    return {
      currentList: [],
      url: axios.defaults.baseURL,
      search: "",
      classList: [],
      step: 8
    };
  },
  created() {
    document.title="班级管理"
    axios.get(this.url + "/class/list").then(res => {
      this.classList = res.data.filter(item => item!=null);
      this.currentList = this.classList.slice(0, this.step);
    });
  },
  methods: {
    // 删除班级
    handleDelete(index, row) {
      if (confirm("确定要删除班级嘛？")) {
        axios
          .get(this.url + "/class/delete", {
            params: {
              id: row.id
            }
          })
          .then(res => {
            axios.get(this.url + "/class/list").then(res => {
              console.log(res);
              this.classList = res.data;
              this.classList = res.data.filter(item => item!=null);
              this.currentList = this.classList.slice(0, this.step);
            });
          });
      }
    },
    //按照班级的名模糊搜索搜索
    searchClass() {
      axios
        .get(this.url + "/class/queryLike", {
          params: {
            name: this.search
          }
        })
        .then(res => {
          this.classList = [];
          this.classList=res.data;
          this.classList = res.data.filter(item => item!=null);
          console.log(this.classList);
          this.currentList = this.classList.slice(0, this.step);
          this.search = "";
        });
    },
    //分页
    changeList(list) {
      this.currentList = list;
    },
    //进入班级
    handleEdit(index,row){
       this.$router.push({
        name: "stu",
        params: {
          row: row
        }
      });
    }
  }
};
</script>
<style scoped>
.el-table {
  background-color: rgba(255, 255, 255, 0.74) !important;
}
.el-table th {
  background: rgba(255, 255, 255, 0.74) !important;
}
.el-table tr {
  background: rgba(255, 255, 255, 0.74) !important;
}
</style>