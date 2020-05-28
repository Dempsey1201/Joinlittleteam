<template>
  <div class="score">
    <el-table :data="scoreList" style="width: 100%">
      <el-table-column type="index" min-width="50"></el-table-column>
      <el-table-column prop="classname" class="name" label="班级名称" min-width="170"></el-table-column>
      <el-table-column prop="college" label="学校" min-width="170"></el-table-column>
      <el-table-column prop="teachername" label="老师" min-width="170"></el-table-column>
      <el-table-column prop="major" label="科目" min-width="170"></el-table-column>
      <el-table-column align="right" min-width="230">
        <template slot="header" slot-scope="scope">
          <el-button style="float: right;display: inline-block" size="mini">Search</el-button>
          <el-input
            style="float: center;width: 143px"
            v-model="search"
            size="mini"
            placeholder="搜索"
          />
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">查看</el-button>
          <!-- <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">退出</el-button> -->
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "score",
  data() {
    return {
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
          id: this.student.id
        }
      })
      .then(res => {
        console.log(res);
        // this.classList = res.data;
      });
  },
  methods: {
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