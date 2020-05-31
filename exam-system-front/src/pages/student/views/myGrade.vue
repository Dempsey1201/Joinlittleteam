<template>
  <div class="myGrade">
    <div class="showClass" style="height:500px;">
      <el-table :data="currentList" style="width: 100%">
        <el-table-column type="index" min-width="50"></el-table-column>
        <el-table-column prop="classname" class="name" label="班级名称" min-width="170"></el-table-column>
        <el-table-column prop="college" label="学校" min-width="170"></el-table-column>
        <el-table-column prop="teachername" label="老师" min-width="170"></el-table-column>
        <el-table-column prop="major" label="科目" min-width="170"></el-table-column>
        <el-table-column align="center" min-width="230">
          <template slot="header" slot-scope="scope">
            <span>操作</span>
          </template>
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">查看</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">退出</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pageTool :step="step" :list="classList" @check="changeList"></pageTool>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import pageTool from "../components/pageTool";
export default {
  name: "showClass",
  components: {
    pageTool
  },
  data() {
    return {
      currentList: [],
      url: axios.defaults.baseURL,
      student: JSON.parse(sessionStorage.getItem("userInfo")),
      classList: [],
      search: "",
      step: 8
    };
  },
  created() {
    axios
      .get(this.url + "/class/queryUserClassroom", {
        params: {
          id: this.student.id
        }
      })
      .then(res => {
        console.log(res);
        this.classList = res.data;
        this.currentList = this.classList.slice(0, this.step);
      });
  },
  methods: {
    // 退出班级
    handleDelete(index, row) {
      if (confirm("你想要退出这个班级嘛？")) {
        console.log(row);
        axios
          .get(this.url + "/class/outClassRoom", {
            params: {
              id: this.student.id,
              cid: row.id
            }
          })
          .then(res => {
            console.log(res);
            axios
              .get(this.url + "/class/queryUserClassroom", {
                params: {
                  id: this.student.id
                }
              })
              .then(res => {
                console.log(res);
                this.classList = res.data;
              });
          });
      }
    },
    // 进入班级
    handleEdit(index, row) {
      this.$router.push({
        name: "paper",
        params: {
          row: row
        }
      });
    },
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