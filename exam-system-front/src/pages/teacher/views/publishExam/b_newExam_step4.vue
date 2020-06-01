<template>
    <div class="step4">
        <p>
            试题预览
        </p>
        <exam :questionList="questionList" @back="prevStep"></exam>
        <el-button type="primary"
                   @click="complete"
        >完成
        </el-button>
    </div>
</template>

<script>
    import {mapMutations,mapGetters,mapActions} from "vuex";
    import {addQuestion,newPaper,questionToPaper} from "../../api/publishExam";
    import exam from "../exam";
	export default {
		name: "b_newExam_step4",
        data(){
			return{
				questionList:[],
				labelPosition:"left",
                examInfo:{}
            }
        },
        components:{
		   exam
        },
        computed:{
			...mapGetters([
				"newExamDetail"
            ])
        },
        created() {
            // 先将题目都添加进去
            this.examInfo = JSON.parse(sessionStorage.getItem("newExamInfo"))
            this.questionList = this.examInfo.questionList
            this.questionList.forEach(item=>{
                if(item.type===2){
                    item.answer = item.answer.join('');
                }
            })
        },
        methods:{
			...mapMutations({
				setStep:"SET_STEP"
            }),
            ...mapActions([
            	"stepFour"
            ]),
	        complete(){
			    // 创建试卷
                let reg = /\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}/g;
                let score = 0;
                let list = this.questionList.map(item=>({
                    question: item.question,
                    oa: item.oa,
                    ob: item.ob,
                    oc: item.oc,
                    od: item.od,
                    subject: item.subject,
                    qtype: item.type,
                    author: item.author,
                    answer:item.type==='2'?item.answer.join(""):item.answer
                }))
                let list2 = [];
                this.questionList.forEach((item,index)=>{
                    switch (item.type) {
                        case 1:
                            score+=3;
                            break;
                        case 2:
                            score+=3;
                            break;
                        case 3:
                            score+=2;
                            break;
                        case 4:
                            score+=5;
                            break;
                        case 5:
                            score+=2;
                            break;
                    }
                })
                let data = {
                    pname: this.examInfo.pname,//试卷名称
                    start_time: JSON.stringify(this.examInfo.start_time).split('"')[1].match(reg)[0].split("T").join(" "),//开始日期时间
                    classno:this.examInfo.classno,
                    share:this.examInfo.share,
                    end_time: JSON.stringify(this.examInfo.end_time).split('"')[1].match(reg)[0].split("T").join(" "),//截至时间
                    last_time:this.examInfo.check=="无限制"?0:this.examInfo.last_time,// 考试时间/分钟
                    full_score:score,
                    teacher:JSON.parse(sessionStorage.getItem("userInfo")).teachername
                };
                addQuestion(list).then(res=>{
                    let qid = res.data
                    newPaper(data).then(res=>{
                        this.questionList.forEach((item,index)=>{
                            let data = {};
                            switch (item.type) {
                                case 1:
                                    data.pid = res.data
                                    data.no = index
                                    data.qid = qid[index]
                                    data.qscore = 2
                                    break;
                                case 2:
                                    data.pid = res.data
                                    data.no = index
                                    data.qid = qid[index]
                                    data.qscore = 3
                                    break;
                                case 3:
                                    data.pid = res.data
                                    data.no = index
                                    data.qid = qid[index]
                                    data.qscore = 2
                                    break;
                                case 4:
                                    data.pid = res.data
                                    data.no = index
                                    data.qid = qid[index]
                                    data.qscore = 5
                                    break;
                                case 5:
                                    data.pid = res.data
                                    data.no = index
                                    data.qid = qid[index]
                                    data.qscore = 2
                                    break;
                            }
                            list2.push(data)
                        })
                        questionToPaper(list2).then(res=>{
                            if(res.data){
                                this.$message({
                                    message: '新建试卷成功',
                                    type: 'success'
                                });
                                sessionStorage.removeItem("newExamInfo")
                                this.setStep(1);// 返回到第一步
                            }
                        }).catch(err=>{
                            throw err;
                        })
                    })
                }).catch(err=>{
                    throw err;
                })

            },
	        prevStep(){// 点击上一步
		        this.setStep(3);
	        },
        }
	}
</script>

<style scoped lang="less">
    .step4 > p{
        font-size: 16px;
        color: #409eff;
    }
    .formList{
        color: #424242;
        
    }
    .el-form{
        background-color: #eee;
        padding: 10px;
        border-radius: 10px;
        .el-form-item{
            margin-bottom: 0!important;
        }
    }
    p> .el-button{
        display: inline-block !important;
    }
    .formList .el-button{
        margin-top: 15px;
    }
</style>