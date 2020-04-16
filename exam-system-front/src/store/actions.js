import * as types from "./mutations-type"

export const stepOne = function (
	{commit},
	{examName,startDate,startTime,step}
) {
	commit(types.SET_EXAMNAME,examName);
	commit(types.SET_STARTDATE,startDate);
	commit(types.SET_STARTTIME,startTime);
	commit(types.SET_STEP,step);
};

// methods 中
// ...mapActions([
// 	"selectPlay"
// ]),
// 使用
// this.stepOne({
//      examName:"",
//      startDate:"",
//      startTime:""
// })