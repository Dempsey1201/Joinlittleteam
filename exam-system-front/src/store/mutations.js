import * as types from "./mutations-type"
const mutations = {
	[types.SET_EXAMNAME](state,examName){//设置id
		state.newExamDetail.examName = examName;
	},
	[types.SET_STARTDATE](state,startDate){//设置id
		state.newExamDetail.startDate = startDate;
	},
	[types.SET_STARTTIME](state,startTime){//设置id
		state.newExamDetail.startTime = startTime;
	},
	[types.SET_STEP](state,step){//设置id
		state.step = step;
	},
}

export default mutations;//设置state

// ...mapMutations({//获取 vuex 中的mutation方法
// 	setExamName:"SET_EXAMNAME"
// })
// 使用
// this.setExamName(参数);