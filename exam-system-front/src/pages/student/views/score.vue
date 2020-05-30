<template>
  <div class="score" style="height:500px;">
    <el-table :data="currentList" style="width: 100%">
      <el-table-column type="index" min-width="50"></el-table-column>
      <el-table-column prop="pname" label="试卷名称" min-width="170"></el-table-column>
      <el-table-column prop="teacher" label="老师" min-width="170"></el-table-column>
      <el-table-column prop="score" label="成绩" min-width="170"></el-table-column>
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
      student: JSON.parse(sessionStorage.getItem("userInfo")),
      scoreList: [],
      search: ""
    };
  },
  created() {
      //获取我的成绩
    axios
      .get(this.url + "/paper/getScoreByUser", {
        params: {
          sid: this.student.id
        }
      })
      .then(res => {
        console.log(res);
         this.scoreList = res.data;
        this.currentList = this.scoreList.slice(0, this.step);
      });
  },
  methods: {
     //分页
    changeList(list) {
      this.currentList = list;
    },
    handleEdit(index, row) {
      this.$router.push({
        name: "paper",
        params: {
          row: row
        }
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