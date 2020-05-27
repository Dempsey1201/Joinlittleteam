const state = {
	step:1,// 创建步骤
	newExamDetail:{
		examName:"",// 试卷名称
		startTime:"",//发布时间
		class:[],//发布班级
		description:"",//试卷描述
		endTime: "",//截至时间
		share:1,// 是否共享
		check: "无限制",//试卷是否限制时间
		testDuringTime:10,// 考试时间/分钟，默认10分钟
		choiceOne:0,// 单选题
		choiceMany:0,// 多选题
		judgeTest:0,// 判断题
		feedFull:0,// 填空题
		shortAnswer:0,// 简答题
		tryTimes:1,//尝试次数
		questionList:""// 试卷题目列表
		
		// {
			// 	ExamId:1,// 所属试卷id
			// 	type:0,// 题目类型
				// question:"",// 题目问题
				// answerA:"",// 选项A
				// answerB:"",// 选项B
				// answerC:"",// 选项C
				// answerD:"",// 选项D
				// rightAnswer:""// 正确答案
			// }
	},
	classInfo:[],// 班级信息
	userInfo:sessionStorage.getItem("userInfo")
}
export default state;