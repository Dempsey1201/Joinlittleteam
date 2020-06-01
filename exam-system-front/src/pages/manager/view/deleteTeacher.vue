<template>
  <div class="teacher">
    <el-table :data="currentList" style="width: 100%">
      <el-table-column type="index" min-width="50"></el-table-column>
      <el-table-column prop="teachername" class="name" label="老师姓名" min-width="170"></el-table-column>
      <el-table-column prop="card" label="老师工号" min-width="170"></el-table-column>
      <el-table-column prop="college" label="学校" min-width="170"></el-table-column>
      <el-table-column prop="email" label="邮箱" min-width="170"></el-table-column>
      <el-table-column align="center" min-width="240">
        <template slot="header" slot-scope="scope">
          <el-button
            style="float: right;display: inline-block"
            size="mini"
            @click="searchClass"
          >Search</el-button>
          <el-input
            style="float: right;width: 143px"
            v-model="search"
            size="mini"
            placeholder="搜索"
          />
        </template>
        <template slot-scope="scope">
          <!-- <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">查看</el-button> -->
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pageTool :step="step" :list="teacherList" @check="changeList"></pageTool>
  </div>
</template>

<script>
import axios from "axios";
import pageTool from "../components/pageTool";
export default {
  name: "teacher",
  components: {
    pageTool
  },
  data() {
    return {
      currentList: [],
      url: axios.defaults.baseURL,
      search: "",
      teacherList: [],
      step: 8
    };
  },
  created() {
    document.title="教师管理"
    axios.get(this.url + "/teacher/list").then(res => {
      this.teacherList = res.data;
      this.currentList = this.teacherList.slice(0, this.step);
    });
  },
  methods: {
    // 删除老师
    handleDelete(index, row) {
      if (confirm("确定要删除这位老师嘛？")) {
        axios
          .get(this.url + "/teacher/delete", {
            params: {
              id: row.id
            }
          })
          .then(res => {
            axios.get(this.url + "/teacher/list").then(res => {
              this.teacherList = res.data;
              this.currentList = this.teacherList.slice(0, this.step);
            });
          });
      }
    },
    //按照老师的card搜索
    searchClass() {
      axios
        .get(this.url + "/teacher/query", {
          params: {
            card: this.search
          }
        })
        .then(res => {
          this.teacherList = res.data;
          this.currentList = this.teacherList.slice(0, this.step);
          this.search = "";
        });
    },
    //分页
    changeList(list) {
      this.currentList = list;
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