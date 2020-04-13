<template>
  <div class="upload">
    <el-upload
      class="avatar-uploader"
      action="string"
      :show-file-list="false"
      :before-upload="beforeUpload"
      ref="upload"
      :auto-upload="false"
      :http-request="httpRequest"
    >
      <img v-if="imageUrl" :src="imageUrl" class="avatar" />
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
  </div>
</template>

<script>
export default {
  name: "upload",
  data() {
    return {
      //上传图像的路径
      imageUrl: ""
    };
  },
  methods: {
    //上传图像前的限制
    beforeUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    //文件上传,覆盖默认上传行为
    httpRequest(params) {
      let fd = new FormData();
      console.log(params);
      fd.append("picture", params.file);
      fd.forEach((value, key) => {
        console.log(key, typeof value, value);
      });
      let config = {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      };
      axios
        .post("", fd, config)
        .then(res => {
          if (res.data == true) {
            this.$message({
              type: "success",
              message: "修改失败，请输入完整信息"
            });
            getAll().then(res => {
              console.log(res.data);
              this.tableData = res.data;
              this.length = this.tableData.length;
            });
            this.$refs.upload.clearFiles();
            this.$refs.uploadForm.resetFields();
          } else {
            this.$message({
              type: "success",
              message: "修改成功，请输入完整信息"
            });
          }
        })
        .catch(res => {
          console.log(res);
        });
    }
  }
};
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>