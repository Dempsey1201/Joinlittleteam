<template>
  <div class="prifole">
    <div class="title">我的账号</div>
    <div class="content">
      <el-form label-position="left" label-width="80px" :model="formLabelAlign">
        <el-form-item label="昵称">
          <label v-if="isShow">{{student.username}}</label>
          <el-input v-model="formLabelAlign.name" style="width: 200px" v-else></el-input>
          <el-button
            type="primary"
            plain
            @click="change1"
            style="margin-left: 10px"
            v-if="isShow"
          >修改昵称</el-button>
          <el-button type="primary" plain @click="confirm1" style="margin-left: 10px" v-else>确定</el-button>
        </el-form-item>
        <el-form-item label="学校">
          <label>{{student.college}}</label>
        </el-form-item>
        <el-form-item label="头像">
          <img :src="student.headUrl" alt />
          <el-button
            type="primary"
            @click="upload"
            style="margin-left: 10px;vertical-align:text-bottom"
          >
            上传
            <i class="el-icon-upload el-icon--right"></i>
          </el-button>
        </el-form-item>
        <el-dialog
          title="上传头像"
          :visible.sync="dialogVisible2"
          width="30%"
          center
          style="padding: 10px"
        >
          <upload-img/>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="confirm2">确 定</el-button>
          </span>
        </el-dialog>

        <el-form-item label="邮箱地址">
          <label>{{student.email}}</label>
          <el-button type="primary" plain @click="change2" style="margin-left: 10px">修改邮箱</el-button>
        </el-form-item>
        <el-form-item label="密码管理">
          <el-button type="danger" plain @click="change3">重置密码</el-button>
          <el-dialog
            title="提示"
            :visible.sync="dialogVisible"
            width="30%"
            center
            style="padding: 10px"
          >
            <el-form
              :model="ruleForm"
              status-icon
              ref="ruleForm"
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item label="密码" prop="pass">
                <el-input type="password" v-model="ruleForm.pass"></el-input>
              </el-form-item>
              <el-form-item label="确认密码" prop="checkPass" style="margin-top: 10px">
                <el-input type="password" v-model="ruleForm.checkPass"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="confirm2">确 定</el-button>
            </span>
          </el-dialog>
        </el-form-item>
        <el-form-item label="应邀做题">
          <el-input v-model="formLabelAlign.type" style="width: 200px"></el-input>
          <el-button type="primary" plain @click="change4" style="margin-left: 10px">接受邀请</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import uploadImg from "../components/upload";
export default {
  name: "prifole",
  components: {
    uploadImg
  },
  data() {
    return {
      //从后端请求的学生信息
      student: {
        username: "vampire",
        email: "1559114369@qq.com",
        college: "东北师范大学",
        headUrl:
          "https://images.ptausercontent.com/9724e874-2854-49ae-bd8d-71c5cc4a0968.jpg"
      },
      formLabelAlign: {
        name: "",
        type: ""
      },
      isShow: true,
      isShow2: true,
      //修改密码的弹窗
      dialogVisible: false,
      //修改密码
      ruleForm: {
        pass: "",
        checkPass: ""
      },
      //图片上传的弹窗
      dialogVisible2: false
    };
  },
  methods: {
    //点击上传
    upload() {
      this.dialogVisible2 = true;
    },
    change1() {
      this.isShow = !this.isShow;
    },
    confirm1() {
      this.isShow = !this.isShow;
      //像后端传送数据
    },
    //修改邮箱
    change2() {
      this.$prompt("请输入邮箱", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        inputErrorMessage: "邮箱格式不正确"
      })
        .then(({ value }) => {
          this.$message({
            type: "success",
            message: "你的邮箱是: " + value
            //像后端传输
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消输入"
          });
        });
    },
    //修改密码
    change3() {
      this.dialogVisible = true;
    },
    confirm2() {
      console.log(this.ruleForm.pass, this.ruleForm.checkPass);
      if (this.ruleForm.pass != this.ruleForm.checkPass) {
        this.$message({
          message: "两个密码不一样",
          type: "warning"
        });
      } else {
        this.$message({
          message: "修改成功",
          type: "success"
        });
        this.dialogVisible = false;
        //像后端传送数据
      }
      this.ruleForm.pass = "";
      this.ruleForm.checkPass = "";
    },
    change4() {
      if (this.formLabelAlign.type == "") {
        this.$message({
          message: "请输入邀请码",
          type: "warning"
        });
      } else {
        this.formLabelAlign.type = "";
        this.$message({
          message: "传送成功",
          type: "success"
        });

        //像后端传送数据
      }
    }
  }
};
</script>

<style lang="less">
.prifole {
  color: #333333;
  border: 1px solid #e7eaf1;
  box-shadow: 0 1px 3px rgba(0, 37, 55, 0.05);
  box-sizing: border-box;
  .title {
    font-size: 14px;
    padding: 10px;
    background-color: rgba(0, 0, 0, 0.03);
    border-bottom: 1px solid rgba(0, 0, 0, 0.125);
  }
  .content {
    padding: 10px;
    .el-dialog__body {
      padding: 10px;
    }
  }
}
</style>