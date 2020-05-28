<template>
  <div class="myGrade">
    <div class="showClass">
      <el-table :data="classList" style="width: 100%">
        <el-table-column type="index" min-width="50"></el-table-column>
        <el-table-column prop="classname" class="name" label="班级名称" min-width="170"></el-table-column>
        <el-table-column prop="college" label="学校" min-width="170"></el-table-column>
        <el-table-column prop="teachername" label="老师" min-width="170"></el-table-column>
        <el-table-column prop="major" label="科目" min-width="170"></el-table-column>
        <el-table-column align="right" min-width="230">
          <template slot="header" slot-scope="scope">
            <el-button style="float: right;display: inline-block" size="mini">Search</el-button>
            <el-input
              style="float: right;width: 143px"
              v-model="search"
              size="mini"
              placeholder="搜索"
            />
          </template>
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">查看</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">退出</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "showClass",
  data() {
    return {
      url: axios.defaults.baseURL,
      student: JSON.parse(sessionStorage.getItem("userInfo")),
      classList: [],
      search: ""
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
      });
  },
  methods: {
    handleDelete(index, row) {
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