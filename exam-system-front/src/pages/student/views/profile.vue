<template>
  <div class="prifole">
    <div class="title">我的账号</div>
    <div class="content">
      <el-form label-position="left" label-width="80px">
        <el-form-item label="昵称">
          <label v-if="isShow">{{student.username}}</label>
          <el-input v-model="nickname" style="width: 200px" v-else></el-input>
          <el-button
            type="primary"
            plain
            @click="change1"
            style="margin-left: 10px"
            v-if="isShow"
          >修改昵称</el-button>
          <el-button type="primary" plain @click="changeNick" style="margin-left: 10px" v-else>确定</el-button>
        </el-form-item>
        <el-form-item label="学校">
          <label v-if="isShowcoll">{{student.college}}</label>
          <el-input v-model="coll" style="width: 200px" v-else></el-input>
          <el-button
            type="primary"
            plain
            @click="changecollege"
            style="margin-left: 10px"
            v-if="isShowcoll"
          >修改学校</el-button>
          <el-button
            type="primary"
            plain
            @click="changecollege2"
            style="margin-left: 10px"
            v-else
          >确定</el-button>
        </el-form-item>
        <el-form-item label="头像">
          <img :src="url+imgUrl" alt width="80px" height="80px" />
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
          <!-- //上传图片 -->
          <!-- <div class="upload"> -->
          <el-upload
            class="upload-demo"
            ref="upload"
            action="string"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :auto-upload="false"
            :http-request="httpRequest"
            :file-list="fileList"
            list-type="picture"
            :limit="1"
            :before-upload="beforeUpload"
          >
            <el-button size="small" type="primary">上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过1MB</div>
          </el-upload>
          <!-- </div> -->
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible2 = false">取 消</el-button>
            <el-button type="primary" @click="changeImg">确 定</el-button>
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
              <el-button type="primary" @click="changePass">确 定</el-button>
            </span>
          </el-dialog>
        </el-form-item>
        <el-form-item label="加入班级">
          <el-input v-model="acceptNum" style="width: 200px"></el-input>
          <el-button type="primary" plain @click="accept" style="margin-left: 10px">接受邀请</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import qs from "qs";
export default {
  name: "prifole",
  data() {
    return {
      url: axios.defaults.baseURL,
      imgUrl: "",
      student: JSON.parse(sessionStorage.getItem("studentInfo")),
      //从后端请求的学生信息
      nickname: "",
      coll: "",
      isShow: true,
      isShow2: true,
      isShowcoll: true,
      //修改密码的弹窗
      dialogVisible: false,
      //修改密码
      ruleForm: {
        pass: "",
        checkPass: ""
      },
      //图片上传的弹窗
      dialogVisible2: false,
      //图片地址
      fileList: [],
      //做题的邀请码
      acceptNum: ""
    };
  },
  created() {
    document.title="个人信息"
    console.log(this.student);
    axios
      .get(this.url + "/user/queryUser", {
        params: {
          id: this.student.id
        }
      })
      .then(res => {
        console.log(res);
        this.student = res.data;
        this.imgUrl = res.data.headUrl;
      });
    console.log(this.student + this.url);
  },
  computed: {},
  methods: {
    //点击上传
    upload() {
      this.dialogVisible2 = true;
    },

    //修改昵称
    change1() {
      this.isShow = !this.isShow;
    },
    //修改学校
    changecollege() {
      this.isShowcoll = !this.isShowcoll;
    },
    changecollege2() {
      console.log("click");
      this.isShowcoll = !this.isShowcoll;
      //像后端传送数据
      axios
        .get(this.url + "/user/updateUser", {
          params: {
            id: this.student.id,
            username: this.student.username,
            email: this.student.email,
            college: this.coll
          }
        })
        .then(res => {
          console.log(res);
          this.student.college = this.coll;
          this.coll = "";
        });
    },

    changeNick() {
      console.log("click");
      this.isShow = !this.isShow;
      //像后端传送数据
      axios
        .get(this.url + "/user/updateUser", {
          params: {
            id: this.student.id,
            email: this.student.email,
            username: this.nickname,
            college: this.student.college
          }
        })
        .then(res => {
          console.log(res);
          this.student.username = this.nickname;
          this.nickname = "";
        });
    },
    //修改邮箱
    change2() {
      var that = this;
      this.$prompt("请输入邮箱", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        inputErrorMessage: "邮箱格式不正确"
      })
        .then(({ value }) => {
          if (value == "") {
            this.$message({
              type: "info",
              message: "邮箱输入不能为空"
            });
          } else {
            this.$message({
              type: "success",
              message: "你的邮箱是: " + value
              //像后端传输邮箱
            });
            axios
              .get(this.url + "/user/updateUser", {
                params: {
                  id: that.student.id,
                  email: value,
                  username: that.student.username
                }
              })
              .then(res => {
                console.log(res);
                that.student.email = value;
              });
          }
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
    changePass() {
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
        axios
          .get(this.url + "/user/updatePassword", {
            params: {
              id: this.student.id,
              password: this.ruleForm.pass
            }
          })
          .then(res => {
            console.log(res);
          });
      }
      this.ruleForm.pass = "";
      this.ruleForm.checkPass = "";
    },
    //做题邀请码
    accept() {
      if (this.acceptNum == "") {
        this.$message({
          message: "请输入邀请码",
          type: "warning"
        });
      } else {
        //像后端传送数据
        console.log(this.acceptNum);
        axios
          .get(this.url + "/class/joinClassRoom", {
            params: {
              id: this.student.id,
              classno: this.acceptNum
            }
          })
          .then(res => {
            console.log(res);
            this.acceptNum = "";
            if (res.data == -1) {
              this.$message({
                message: "不可重复加入班级",
                type: "error"
              });
            }
            if (res.data == 2) {
              this.$message({
                message: "成功加入班级",
                type: "success"
              });
            }
            if (res.data == (0 || 1)) {
              this.$message({
                message: "加入失败，重新输入邀请码",
                type: "error"
              });
            }
          });
      }
    },
    //上传图片问题
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    beforeUpload(file) {
      console.log(file);
      const isSize = file.size / 1024 / 1024 < 1;
      if (!isSize) {
        this.$message({
          message: "上传的文件不能超过1MB",
          type: "error"
        });
      }
      console.log(file);
      return isSize;
    },
    httpRequest(params) {
      var that = this;
      let fd = new FormData();
      console.log(params);
      var file = params.file;
      //声明js的文件流
      var reader = new FileReader();
      if (file) {
        //通过文件流将文件转换成Base64字符串
        reader.readAsDataURL(file);
        //转换成功后
        reader.onloadend = function() {
          //将转换结果赋值给img标签
          that.student.headUrl = reader.result;
          var base64 = reader.result.split(",")[1];
          console.log(base64);
          //像后端传送base64格式的图片
          var img = { id: that.student.id, imgStr: base64 };
          axios
            .post(that.url + "/user/uploadHead", qs.stringify(img), {
              headers: { "Content-Type": "application/x-www-form-urlencoded" }
            })
            .then(res => {
              console.log(res);
              that.imgUrl = res.data;
              console.log(that.imgUrl);
              //清除缓存
              that.$refs.upload.clearFiles();
              that.$emit("imgUrl", that.imgUrl);
            });
          //输出结果
          console.log(reader.result);
        };
      }
      return true;
    },
    //点击修改上传图片
    changeImg() {
      this.dialogVisible2 = false;
      this.$refs.upload.submit(); // 这S里是执行文件上传的函数，其实也就是获取我们要上传的文件
    }
  }
};
</script>

<style lang="less">
.prifole {
  color: #333333;
  // border: 1px solid #e7eaf1;
  // box-shadow: 0 1px 3px rgba(0, 37, 55, 0.05);
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
.upload-demo {
  text-align: center;
}
</style>