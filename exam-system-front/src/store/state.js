const state = {
	step:1,// 创建步骤
	classInfo:[],// 班级信息
	userInfo:JSON.parse(sessionStorage.getItem("userInfo")),// 教师
	studentInfo:JSON.parse(sessionStorage.getItem("studentInfo")),
	managerInfo:JSON.parse(sessionStorage.getItem("managerInfo"))
}
export default state;