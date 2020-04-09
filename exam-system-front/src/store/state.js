const state = {
	step:1,// 创建步骤
	newExamDetail:{
		examName:"",// 试卷名称
		startTime:"",//发布时间
		class:[],//发布班级
		description:"",//试卷描述
		endTime: "",//截至时间
		check: "无限制",//试卷是否限制时间
		testDuringTime:10,// 考试时间/分钟
		choiceOne:0,// 单选题
		choiceMany:0,// 多选题
		judgeTest:0,// 判断题
		feedFull:0,// 填空题
		shortAnswer:0,// 简答题
		tryTimes:1,//尝试次数
	},
	classInfo:[// 班级信息
		{
			id:1,
			name:"班级1"
		},
		{
			id:2,
			name:"班级2"
		},
		{
			id:3,
			name:"班级3"
		}
	]
}
export default state;