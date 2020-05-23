import * as types from "./mutations-type"
const mutations = {
	// 试卷相关
	// step1
	[types.SET_EXAMNAME](state,examName){// 试卷名称
		state.newExamDetail.examName = examName;
	},
	[types.SET_STARTTIME](state,startTime){// 发布时间
		state.newExamDetail.startTime = startTime;
	},
	[types.SET_CLASS](state,className){//发布到哪些班级
		state.newExamDetail.class = className;
	},
	[types.SET_SHARE](state,share){
		state.newExamDetail.share = share;
	},
	// step2
	[types.SET_DESCRIPTION](state,description){// 试卷描述
		state.newExamDetail.description = description;
	},
	[types.SET_ENDTIME](state,endTime){// 考试截止时间
		state.newExamDetail.endTime = endTime;
	},
	[types.SET_CHECK](state,check){//试卷是否限制时间
		state.newExamDetail.check = check;
	},
	[types.SET_TESTDURINGTIME](state,testDuringTime){//考试时间/分钟
		state.newExamDetail.testDuringTime = testDuringTime;
	},
	[types.SET_CHOICEONE](state,choiceOne){//单选题
		state.newExamDetail.choiceOne = choiceOne;
	},
	[types.SET_CHOICEMANY](state,choiceMany){//多选题
		state.newExamDetail.choiceMany = choiceMany;
	},
	[types.SET_JUDGETEST](state,judgeTest){// 判断题
		state.newExamDetail.judgeTest = judgeTest;
	},
	[types.SET_FEEDFULL](state,feedFull){//填空题
		state.newExamDetail.feedFull = feedFull;
	},
	[types.SET_SHORTANSWER](state,shortAnswer){//简答题
		state.newExamDetail.shortAnswer = shortAnswer;
	},
	[types.SET_TRYTIMES](state,tryTimes){//可尝试次数
		state.newExamDetail.tryTimes = tryTimes;
	},
	[types.SET_QUESTIONLIST](state,questionList){
		state.newExamDetail.questionList = JSON.stringify(questionList);
	},
	
	
	// 生成试卷进行到的步骤
	[types.SET_STEP](state,step){//设置id
		state.step = step;
	},
	
	// 班级
	[types.SET_CLASSINFO](state,data){// 可能会添加班级或是删除班级
		if(data.operation==="添加"){// 如果是添加就传info
			state.classInfo.push(data.info);
		}else {//如果是删除就传index
			state.classInfo.splice(data.info,1);
		}
	},

	// 用户信息
	[types.SET_USERINFO](state,data){
		state.userInfo = data;
	}
}

export default mutations;//设置state

// ...mapMutations({//获取 vuex 中的mutation方法
// 	setExamName:"SET_EXAMNAME"
// })
// 使用
// this.setExamName(参数);