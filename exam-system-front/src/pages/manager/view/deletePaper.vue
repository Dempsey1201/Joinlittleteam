<template>
  <div class="showPaper">
    <el-table :data="currentList" style="width: 100%">
      <el-table-column type="index" min-width="50"></el-table-column>
      <el-table-column prop="pname" class="name" label="试卷名称" min-width="180"></el-table-column>
      <el-table-column prop="start_time" label="开始时间" min-width="180"></el-table-column>
      <el-table-column prop="end_time" label="结束时间" min-width="180"></el-table-column>
      <el-table-column align="center" min-width="210" label="操作">
        <template slot="header" slot-scope="scope">
          <el-button
            style="float: right;display: inline-block"
            size="mini"
            @click="searchPaper"
          >Search</el-button>
          <el-input
            style="float: right;width: 143px"
            v-model="search"
            size="mini"
            placeholder="搜索"
          />
        </template>
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pageTool :step="step" :list="paperList" @check="changeList"></pageTool>
  </div>
</template>

<script>
import axios from "axios";
import pageTool from "../components/pageTool";
export default {
  name: "showPaper",
  data() {
    document.title="试卷管理"
    return {
      url: axios.defaults.baseURL,
      paperList: [],
      currentList: [],
      step: 8,
      search: ""
    };
  },
  created() {
    axios.get(this.url + "/paper/getAllPaper").then(res => {
      this.paperList = res.data;
      this.currentList = this.paperList.slice(0, this.step);
    });
  },
  methods: {
    searchPaper() {
      axios
        .get(this.url + "/paper/searchPaper", {
          params: {
            pname: this.search
          }
        })
        .then(res => {
          this.paperList = res.data;
          this.currentList = this.paperList.slice(0, this.step);
          this.search = "";
        });
    },
    handleDelete(index, row) {
      if (confirm("确定要删除试卷吗？")) {
        axios
          .get(this.url + "/paper/deletePaper", {
            params: {
              pid: row.pid
            }
          })
          .then(res => {
            this.paperList.splice(index, 1);
            this.currentList = this.paperList.slice(0, this.step);
          });
      }
    },
    changeList(list) {
      this.currentList = list;
    }
  },
  components: {
    pageTool
  }
};
</script>

<style scoped>
</style>