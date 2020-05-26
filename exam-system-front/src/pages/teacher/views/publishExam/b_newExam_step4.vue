<template>
    <div class="step4">
        <p>
            <el-button style="display: block" type="primary" size="mini" @click="prevStep">上一步</el-button>
            试题预览
        </p>
        <exam :examDetail="newExamDetail"></exam>
        <el-button type="primary"
                   @click="complete"
        >完成
        </el-button>
    </div>
</template>

<script>
    import {mapMutations,mapGetters,mapActions} from "vuex";
    import exam from "../exam";
	export default {
		name: "b_newExam_step4",
        data(){
			return{
				questionList:[],
				labelPosition:"left"
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

        },
        methods:{
			...mapMutations({
				setStep:"SET_STEP"
            }),
            ...mapActions([
            	"stepFour"
            ]),
	        complete(){
		        this.$message({
			        message: '新建试卷成功',
			        type: 'success'
		        });
		        this.stepFour();// 清空数据，初始化
		        this.setStep(1);// 返回到第一步
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