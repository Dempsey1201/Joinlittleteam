<template>
    <div class="step3">
        <el-button style="display: block" type="primary" size="mini" @click="prevStep">上一步</el-button>
        <div class="formList">
            <el-row class="choiceOne">
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type===1)" :key="index"
                        :ref="'choiceOne'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                        :rules="rules"
                        size="mini"
                >
                    <el-form-item :label="'单选'+(1+index)" prop="question">
                        <el-col class="line" :span="6">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="题目内容" v-model="item.question"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="填写选项" required>
                        <template>
                            <el-form-item label="选项A" prop="oa">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="请输入选项A" v-model="item.oa"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                            <el-form-item label="选项B" prop="ob">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="请输入选项B" v-model="item.ob"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                            <el-form-item label="选项C" prop="oc">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="请输入选项C" v-model="item.oc"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                            <el-form-item label="选项D">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="od" v-model="item.od"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                        </template>
                    </el-form-item>
                    <el-form-item label="填写答案" prop="answer">
                        <el-radio-group v-model="item.answer">
                            <el-radio :label="'A'">A:{{item.oa}}</el-radio>
                            <el-radio :label="'B'">B:{{item.ob}}</el-radio>
                            <el-radio :label="'C'">C:{{item.oc}}</el-radio>
                            <el-radio :label="'D'">D:{{item.od}}</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary"
                                   @click="submitForm('choiceOne'+index,item)"
                        >添加试题</el-button>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-row class="choiceMany">
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type===2)" :key="index"
                        :ref="'choiceMany'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                        :rules="rules"
                        size="mini"
                >
                    <el-form-item :label="'多选'+(1+index)" prop="question">
                        <el-col class="line" :span="6">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="题目内容" v-model="item.question"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="填写选项" required>
                        <template>
                            <el-form-item label="选项A" prop="oa">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="请输入选项A" v-model="item.oa"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                            <el-form-item label="选项B" prop="ob">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="请输入选项B" v-model="item.ob"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                            <el-form-item label="选项C" prop="oc">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="请输入选项C" v-model="item.oc"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                            <el-form-item label="选项D">
                                <el-col class="line" :span="8">
                                    <el-input placeholder="请输入选项D" v-model="item.od"
                                    ></el-input>
                                </el-col>
                            </el-form-item>
                        </template>
                    </el-form-item>
                    <el-form-item label="填写答案" prop="answer">
                        <el-checkbox-group v-model="item.answer">
                            <el-checkbox :label="'A'">A:{{item.oa}}</el-checkbox>
                            <el-checkbox :label="'B'">B:{{item.ob}}</el-checkbox>
                            <el-checkbox :label="'C'">C:{{item.oc}}</el-checkbox>
                            <el-checkbox :label="'D'">D:{{item.od}}</el-checkbox>
                        </el-checkbox-group>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary"
                                   @click="submitForm('choiceMany'+index,item)"
                        >添加试题</el-button>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-row class="judgeTest">
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type===3)" :key="index"
                        :ref="'judgeTest'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                        :rules="rules"
                        size="mini"
                >
                    <el-form-item :label="'判断'+(1+index)" prop="question">
                        <el-col class="line" :span="6">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="题目内容" v-model="item.question"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="填写答案" prop="answer">
                        <el-radio-group v-model="item.answer">
                            <el-radio label="1">正确</el-radio>
                            <el-radio label="0">错误</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary"
                                   @click="submitForm('judgeTest'+index,item)"
                        >添加试题</el-button>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-row class="feedFull">
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type===5)" :key="index"
                        :ref="'feedFull'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                        :rules="rules"
                        size="mini"
                >
                    <el-form-item :label="'填空题'+(1+index)" prop="question">
                        <el-col class="line" :span="6">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="题目内容" v-model="item.question"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="填写答案" prop="answer">
                        <el-col class="line" :span="6">
                            <el-input placeholder="答案内容，用逗号分隔" v-model="item.answer"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary"
                                   @click="submitForm('feedFull'+index,item)"
                        >添加试题</el-button>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-row class="shortAnswer">
                <el-form
                        v-for="(item,index) in questionList.filter(it=>it.type===4)" :key="index"
                        :ref="'shortAnswer'+index"
                        :model="item"
                        label-width="80px"
                        :label-position="labelPosition"
                        :rules="rules"
                        size="mini"
                >
                    <el-form-item :label="'简答题'+(1+index)" prop="question">
                        <el-col class="line" :span="6">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="答案内容" v-model="item.question"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="填写答案" prop="answer">
                        <el-col class="line" :span="6">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="答案内容" v-model="item.answer"
                            ></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary"
                                   @click="submitForm('shortAnswer'+index,item)"
                        >添加试题</el-button>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-button type="primary"
                       @click="finalSubmit"
            >提交试题</el-button>
        </div>
    </div>
</template>

<script>
	import {mapMutations} from "vuex";
	
	export default {
		name: "step3",
        data(){
			return{
				labelPosition: "left",
                questionNum:0,
				questionList:[],
				rules:{
					question:[
                        {type: 'string', required: true, message: '请输入题目', trigger: 'blur'},
                    ],
					oa:[
						{type: 'string', required: true, message: '请填写选项', trigger: 'blur'}
                    ],
					ob:[
						{type: 'string', required: true, message: '请填写选项', trigger: 'blur'}
					],
					oc:[
						{type: 'string', required: true, message: '请填写选项', trigger: 'blur'}
					],
					answer:[
                        {required:true,message:"请选择答案", trigger: 'change'},
                        
                    ]
                },
                finalList:[]
            }
        },
        created() {
            this.questionList = JSON.parse(sessionStorage.getItem("newExamInfo")).questionList;// 题目列表
        },
		methods:{
	        ...mapMutations({
		        setStep:"SET_STEP",// 设置阶段
	        }),
	        prevStep(){// 点击上一步
		        this.setStep(2);
	        },
			submitForm(formName,item) {// 每道试题的表单验证
				this.$refs[formName][0].validate((valid) => {
					if (valid) {//表单验证成功
						item.isOK = true;
						this.finalList.push(item);// 可以在这里改变属性
						this.$message({
							message: '添加成功',
							type: 'success'
						});
					} else {
						return false;
					}
				});
			},
			finalSubmit(){
	        	let list = this.questionList.filter(item=>!item.isOK);
	        	console.log(list)
	        	if(list.length){
			        this.$message.error('请填写所有的试题并提交');
                }else{
	        		// 在这里用 finalList 向后端提交
                    let form = JSON.parse(sessionStorage.getItem("newExamInfo"));
                    form.questionList = this.questionList;
                    sessionStorage.setItem("newExamInfo",JSON.stringify(form));
                    this.setStep(4);
                }
            }
        }
	}
</script>

<style scoped lang="less">
    .formList .el-form-item--mini.el-form-item{
        margin-bottom: 15px !important;
    }
    .el-form{
        margin-top: 10px;
    }
</style>