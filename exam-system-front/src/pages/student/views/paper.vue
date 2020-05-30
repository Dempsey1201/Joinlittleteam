<template>
  <div class="paper" style="padding:10px;height:500px">
    <div class="showPaper">
      <el-button
        style="display: block;margin-left:10px;margin-top:4px"
        type="primary"
        size="mini"
        @click="prevStep"
      >上一步</el-button>
      <el-table :data="currentList" style="width: 100%">
        <el-table-column type="index" min-width="50"></el-table-column>
        <el-table-column prop="pname" class="name" label="试卷名称" min-width="180"></el-table-column>
        <el-table-column prop="teacher" label="老师" min-width="180"></el-table-column>
        <el-table-column prop="end_time" label="结束时间" min-width="180"></el-table-column>
        <el-table-column align="center" min-width="210">
          <template slot="header" slot-scope="scope">
            操作
            <!-- <el-button style="float: right;display: inline-block" size="mini">Search</el-button>
            <el-input
              style="float: right;width: 143px"
              v-model="search"
              size="mini"
              placeholder="搜索"
            />-->
          </template>
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">查看</el-button>
            <!-- <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button> -->
          </template>
        </el-table-column>
      </el-table>
      <pageTool :step="step" :list="paperList" @check="changeList"></pageTool>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import pageTool from "../components/pageTool";
export default {
  name: "showPaper",
  components: {
    pageTool
  },
  data() {
    return {
      currentList: [],
      step: 8,
      url: axios.defaults.baseURL,
      student: JSON.parse(sessionStorage.getItem("userInfo")),
      paperList: [],
      search: "",
      progress:''
    };
  },
  created() {
    //获取试卷信息
    axios
      .get(this.url + "/paper/getPaperByClass2", {
        params: {
          classno: this.$route.params.row.id,
          sid:this.student.id
        }
      })
      .then(res => {
        console.log(res);
        this.paperList = res.data;
        this.currentList = this.paperList.slice(0, this.step);
      });
  },
  methods: {
    //分页
    changeList(list) {
      this.currentList = list;
    },
    prevStep() {
      this.$router.go(-1);
    },
    //进入试卷页面
    handleEdit(index, row) {
      console.log("uauauauauaua"+row.done);
      this.progress=row.done;
      this.$router.push({
        path: "/student.html/detailPaper",
        query: {
          item: row,
          progress: this.progress
        }
      });
    },
    //点击进入试卷
    goPaper(item, progress) {
      // console.log(pid);
      this.$router.push({
        path: "/student.html/detailPaper",
        query: {
          item: item,
          progress: progress
        }
      });
    }
  }
};
</script>

<style scoped>
.paper {
  color: red;
}
</style>