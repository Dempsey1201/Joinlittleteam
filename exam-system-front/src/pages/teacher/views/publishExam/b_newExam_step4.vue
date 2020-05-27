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
                let pid = [],no = [],qid=[],qscore=[];
                this.questionList.forEach((item,index)=>{
                    no.push(index);
                    switch (item.type) {
                        case 1:
                            qscore.push(3);
                            score+=3;
                            break;
                        case 2:
                            qscore.push(3)
                            score+=3;
                            break;
                        case 3:
                            qscore.push(2)
                            score+=2;
                            break;
                        case 4:
                            qscore.push(5)
                            score+=5;
                            break;
                        case 5:
                            qscore.push(2)
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
                    full_score:score
                }
                // console.log(data,no)

                this.questionList.forEach((item,index)=>{
                    addQuestion({
                        question: item.question,
                        oa: item.oa,
                        ob: item.ob,
                        oc: item.oc,
                        od: item.od,
                        subject: item.subject,
                        qtype: item.type,
                        author: item.author,
                        answer:item.answer
                    }).then(res=>{
                        // qid.push(res.data.data)
                        console.log(res.data)
                        // if(index === this.questionList.length-1){
                        //     newPaper(data).then(res=>{
                        //         let paperId = res.data
                        //         for (let i = 0; i < this.questionList.length; i++) {
                        //             pid.push(paperId);
                        //         }
                        //         console.log(pid,qid,no,qscore)
                        //         questionToPaper({
                        //             pid:pid,
                        //             qid:qid,
                        //             no:no,
                        //             qscore:qscore
                        //         }).then(res=>{
                        //             console.log("成功！！",res.data)
                        //         }).catch(err=>{
                        //             throw err;
                        //         })
                        //     }).catch(err=>{
                        //         throw err;
                        //     })
                        //
                        // }
                    }).catch(err=>{
                        throw err;
                    })
                })

		        this.$message({
			        message: '新建试卷成功',
			        type: 'success'
		        });
		        //this.setStep(1);// 返回到第一步
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