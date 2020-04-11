import * as types from "./mutations-type"

export const stepOne = function (
	{commit},
	{examName,startTime,className,step}
) {
	commit(types.SET_EXAMNAME,examName);
	commit(types.SET_STARTTIME,startTime);
	commit(types.SET_CLASS,className);
	commit(types.SET_STEP,step);
};

export const stepTwo = function (
	{commit},
	{description,endTime,check,testDuringTime,choiceOne,choiceMany,
		judgeTest,feedFull,shortAnswer,tryTimes,questionList}
) {
	commit(types.SET_DESCRIPTION,description);
	commit(types.SET_ENDTIME,endTime)
	commit(types.SET_CHECK,check)
	commit(types.SET_TESTDURINGTIME,testDuringTime)
	commit(types.SET_CHOICEONE,choiceOne)
	commit(types.SET_CHOICEMANY,choiceMany)
	commit(types.SET_JUDGETEST,judgeTest)
	commit(types.SET_FEEDFULL,feedFull)
	commit(types.SET_SHORTANSWER,shortAnswer)
	commit(types.SET_TRYTIMES,tryTimes)
	commit(types.SET_QUESTIONLIST,questionList)
}

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