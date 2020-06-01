<template>
  <div class="exam">
    <div class="noover" v-if="!over">
      <div v-for="(son,index) in question" :key="index" class="div">
        {{index+1}}、
        <span class="type" v-if="son.qtype== 1">单选</span>
        <span
          class="type"
          v-if="son.qtype== 2"
          style="background: #ffe2d9;border: 1px solid #ffb399;"
        >多选</span>
        <span class="type" v-if="son.qtype== 3" style="background: #eee;border: 1px solid #ddd;">判断</span>
        <span class="type" v-if="son.qtype== 4">简答</span>
        <span class="type" v-if="son.qtype== 5" style="background: #eee;border: 1px solid #ddd;">填空</span>
        <span>({{son.qscore}}分)</span>
        <div class="question">{{son.question }}</div>
        <!-- 单选题 -->
        <div v-if="son.qtype==1">
          <ul>
            <li>
              <input
                type="radio"
                :name="son.name"
                :value="son.oa"
                v-model="one[index]"
                @change="getAnswer('A',index,son.qtype)"
              />
              <span class="sql">{{son.oa}}</span>
            </li>
            <li>
              <input
                type="radio"
                :name="son.name"
                :value="son.ob"
                v-model="one[index]"
                @change="getAnswer('B',index,son.qtype)"
              />
              <span class="sql">{{son.ob}}</span>
            </li>
            <li>
              <input
                type="radio"
                :name="son.name"
                :value="son.oc"
                v-model="one[index]"
                @change="getAnswer('C',index,son.qtype)"
              />
              <span class="sql">{{son.oc}}</span>
            </li>
            <li>
              <input
                type="radio"
                :name="son.name"
                :value="son.od"
                v-model="one[index]"
                @change="getAnswer('D',index,son.qtype)"
              />
              <span class="sql">{{son.od}}</span>
            </li>
          </ul>
        </div>
        <!-- 多选题 -->
        <div v-if="son.qtype==2">
          <ul>
            <li>
              <input
                type="checkbox"
                :name="son.name"
                :value="son.oa"
                v-model="many[0]"
                @change="getAnswer(son.oa,index,son.qtype)"
              />
              <span class="sql">{{son.oa}}</span>
            </li>
            <li>
              <input
                type="checkbox"
                :name="son.name"
                :value="son.ob"
                v-model="many[1]"
                @change="getAnswer(son.ob,index,son.qtype)"
              />
              <span class="sql">{{son.ob}}</span>
            </li>
            <li>
              <input
                type="checkbox"
                :name="son.name"
                :value="son.oc"
                v-model="many[2]"
                @change="getAnswer(son.oc,index,son.qtype)"
              />
              <span class="sql">{{son.oc}}</span>
            </li>
            <li>
              <input
                type="checkbox"
                :name="son.name"
                :value="son.od"
                v-model="many[3]"
                @change="getAnswer(son.od,index,son.qtype)"
              />
              <span class="sql">{{son.od}}</span>
            </li>
          </ul>
        </div>
        <!-- //判断题 -->
        <div v-if="son.qtype==3">
          <ul>
            <li>
              <input
                type="radio"
                :name="son.name"
                value="right"
                v-model="adjust"
                @change="getAnswer('1',index,son.qtype)"
              />
              <span class="sql">√</span>
            </li>
            <li>
              <input
                type="radio"
                :name="son.name"
                value="wrong"
                v-model="adjust"
                @change="getAnswer('0',index,son.qtype)"
              />
              <span class="sql">×</span>
            </li>
          </ul>
        </div>
        <!-- 填空题 -->
        <div v-if="son.qtype==5">
          <ul>
            <li style="margin: 8px 0 8px 20px;">
              <textarea
                type="text"
                :name="son.name"
                v-model="answer[index]"
                @change="getAnswer(son.oa,index,son.qtype)"
                style="width: 240px;height: 25px;line-height: 25px;padding: 5px;font-size: 12px;border: none;overflow-y: auto;overflow-x: hidden;resize: none;border:1px solid grey;outline:none"
              ></textarea>
            </li>
          </ul>
        </div>
        <!-- 简答题 -->
        <div v-if="son.qtype==4">
          <ul>
            <li style="margin: 8px 0px 8px 20px;">
              <textarea
                v-model="answer[index]"
                @change="getAnswer(son.oa,index,son.qtype)"
                style="width: 600px;height: 50px;line-height: 25px;padding: 5px;font-size: 12px;border: none;overflow-y: auto;overflow-x: hidden;resize: none;border:1px solid grey;outline:none"
              ></textarea>
            </li>
          </ul>
        </div>
      </div>
      <el-button type="success" @click="submit" style="margin-top:20px">提交</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "exam",
  methods: {
       getAnswer(e, index, type) {
      if (type == 1) {
        console.log(e);
        this.answer[index] = e;
      }
      if (type == 2) {
        //拼接字符串
        this.answer[index] = "";
        for (var i = 0; i < 4; i++) {
          if (this.many[i] == true) {
            if (i == 0) {
              this.answer[index] = "A" + this.answer[index];
              console.log(this.answer[index]);
            }
            if (i == 1) {
              this.answer[index] = "B" + this.answer[index];
            }
            if (i == 2) {
              this.answer[index] = "C" + this.answer[index];
            }
            if (i == 3) {
              this.answer[index] = "D" + this.answer[index];
            }
          }
        }
      }
      if (type == 3) {
        this.answer[index] = e;
      }
      //简答题
      if (type == 4) {
      }
      //填空题
      if (type == 5) {
      }
    },
  },
};
</script>

<style scoped>
.exam {
  color: red;
}
</style>