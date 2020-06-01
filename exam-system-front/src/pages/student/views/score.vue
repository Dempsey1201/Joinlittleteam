<template>
  <div class="score" style="height:500px;">
    <el-table :data="currentList" style="width: 100%">
      <el-table-column type="index" min-width="50"></el-table-column>
      <el-table-column prop="pname" label="试卷名称" min-width="200"></el-table-column>
      <el-table-column prop="teacher" label="老师" min-width="200"></el-table-column>
      <el-table-column prop="score" label="成绩" min-width="200"></el-table-column>
      <el-table-column align="center" min-width="210">
        <template slot="header" slot-scope="scope">
            <span>操作</span>
          </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">查看</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pageTool :step="step" :list="scoreList" @check="changeList"></pageTool>
  </div>
</template>

<script>
import axios from "axios";
import pageTool from "../components/pageTool";
export default {
  name: "score",
  components: {
    pageTool
  },
  data() {
    return {
      currentList: [],
      step: 8,
      url: axios.defaults.baseURL,
      student: JSON.parse(sessionStorage.getItem("studentInfo")),
      scoreList: [],
      search: "",
      item: ""
    };
  },
  created() {
    document.title="成绩管理"
    //获取我的成绩
    axios
      .get(this.url + "/paper/getScoreByUser", {
        params: {
          sid: this.student.id
        }
      })
      .then(res => {
        this.scoreList = res.data;
        this.currentList = this.scoreList.slice(0, this.step);
      });
  },
  methods: {
    //分页
    changeList(list) {
      this.currentList = list;
    },
    //进入试卷页面
    handleEdit(index, row) {
      //通过pid获取试卷信息
      axios
        .get(this.url + "/paper/getPaperById", {
          params: {
            pid: row.pid
          }
        })
        .then(re => {
          this.$router.push({
            path: "/student.html/detailPaper",
            query: {
              item: re.data[0],
              progress: true
            }
          });
        });
    }
  }
};
</script>

<style scoped>
.score {
  color: red;
}
</style>