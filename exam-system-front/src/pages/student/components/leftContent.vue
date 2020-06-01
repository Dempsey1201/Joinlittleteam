<template>
  <div class="leftContent">
    <div class="top">
      <div class="pic">
        成才考试
      </div>
      <div class="detail" style="color:grey;padding:8px;font-size:12px;line-height:20px;">
        学生可以通过老师提供的邀请码加入班级，十分方便；
		    学生可以在线作答老师发布的试卷，在作答结束后可以查看自己分数与正确答案；
		    学生在答题时可以看到距离结束的剩余时间，方便学生在答题时可以合理利用时间。

        <!-- <div class="one">
          <div class="name">老师</div>
          <div class="num">233</div>
        </div>
        <div class="one">
          <div class="name">老师</div>
          <div class="num">233</div>
        </div>
        <div class="one">
          <div class="name">老师</div>
          <div class="num">233</div>
        </div>
        <div class="one">
          <div class="name">老师</div>
          <div class="num">233</div>
        </div> -->
      </div>
    </div>
    <div class="middle">
      <div class="content">
        <i class="el-icon-s-order"></i>
        <span>题目集</span>
      </div>
    </div>
    <div class="bottom">
      <div class="exam">
        <i class="el-icon-box"></i>
        <a href="https://www.patest.cn/"><span>PTA考试</span></a>
      </div>
      <div class="exam">
        <i class="el-icon-sell"></i>
        <a href="https://zoj.pintia.cn/home"><span>ZOJ</span></a>
      </div>
      <div class="exam">
        <i class="el-icon-wallet"></i>
        <a href="https://pintia.cn/market"><span>教育超市</span></a>
      </div>
      
    </div>
  </div>
</template>

<script>
export default {
  name: "leftContent"
};
</script>

<style lang="less">
a{
  text-decoration: none;
}
.leftContent {
  // height: 500px;
  // background-color: #fff;
  .top {
    background-color: rgba(255,255,255,0.7);
    // border: 1px solid red;
    border-radius: 5px;
    overflow: hidden;
    .pic {
      color: #fff;
      background: #409eff !important;
      text-align: center;
      padding: 8px;
    }
    .detail {
      padding-top: 0.5rem;
      padding-bottom: 0.5rem;
      display: flex;
      flex-wrap: wrap;
      .one {
        width: 50%;
        .name {
          font-size: 0.875rem;
          color: #666;
          font-family: -apple-system, "PingFang SC", "Lantinghei SC",
            "Helvetica Neue", "Microsoft Yahei", "Hiragino Sans GB",
            "Microsoft Sans Serif", "WenQuanYi Micro Hei", sans-serif;
          font-weight: 400;
          text-align: center;
          line-height: 1.5rem;
        }
        .num {
          color: #333;
          font-weight: 700;
          font-size: 0.875rem;
          text-align: center;
          line-height: 1.5rem;
        }
      }
    }
  }
  .middle {
    cursor: pointer;
    margin-top: 10px;
    background:rgba(255,255,255,0.7);
    padding: 7px;
    border-radius: 5px;
    overflow: hidden;
    .content {
      font-size: 0.875rem;
      font-weight: 400;
      line-height: 1.5;
      background: #409eff !important;
      color: #fff;
      .el-icon-s-order{
        margin-left: 5%;
        margin-right: 5%;
      }
    }
  }
  .bottom{
    margin-top: 10px;
    background: rgba(255,255,255,0.7);
    overflow: hidden;
    border-radius: 6px;
    padding: 10px;
    display: flex;
    .exam{
      flex: 1;
      text-align: center;
      i{
        color: hsl(199, 88%, 57%);
        font-weight: 400;
        line-height: 1.5;
        font-size: 0.9rem;
      }
      .el-icon-sell{
        color:hsl(227, 59%, 40%);
      }
      .el-icon-wallet{
        color:hsl(45, 100%, 50%);
      }
      span{
        font-size: 0.8rem;
        color: hsla(0, 0%, 0%, 0.75);
        cursor: pointer;
        display: block;
        width: 100%;
      }
    }
  }
}
</style>