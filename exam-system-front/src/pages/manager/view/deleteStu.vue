<template>
  <div class="teacher">
    <el-button
      style="display: block;margin-left:10px;margin-top:4px"
      type="primary"
      size="mini"
      @click="prevStep"
    >上一步</el-button>
    <el-table :data="currentList" style="width: 100%">
      <el-table-column type="index" min-width="50"></el-table-column>
      <el-table-column prop="username" class="name" label="学生昵称" min-width="200"></el-table-column>
      <!-- <el-table-column prop="sex" label="性别" min-width="170"></el-table-column> -->
      <!-- <el-table-column prop="college" label="学校" min-width="170"></el-table-column> -->
      <el-table-column prop="email" label="邮箱" min-width="200"></el-table-column>
      <el-table-column align="center" min-width="200">
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
    <pageTool :step="step" :list="stuList" @check="changeList"></pageTool>
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
      stuList: [],
      step: 8,
      item: this.$route.params.row
    };
  },
  created() {
    document.title="学生管理";
    axios
      .get(this.url + "/user/queryClass", {
        params: {
          id: this.item.id
        }
      })
      .then(res => {
        this.stuList = res.data;
        this.currentList = this.stuList.slice(0, this.step);
      });
  },
  methods: {
    //回到上一步
    prevStep() {
      this.$router.go(-1);
    },
    // 删除老师
    handleDelete(index, row) {
      if (confirm("确定要删除这位学生嘛？")) {
        axios
          .get(this.url + "/user/delete", {
            params: {
              id: row.id
            }
          })
          .then(res => {
            axios
              .get(this.url + "/user/queryClass", {
                params: {
                  id: this.item.id
                }
              })
              .then(res => {
                this.stuList = res.data;
                this.currentList = this.stuList.slice(0, this.step);
              });
          });
      }
    },
    //按照学生邮箱搜索
    searchClass() {
      axios
        .get(this.url + "/user/query", {
          params: {
            email: this.search
          }
        })
        .then(res => {
          this.stuList = res.data;
          this.currentList = this.stuList.slice(0, this.step);
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