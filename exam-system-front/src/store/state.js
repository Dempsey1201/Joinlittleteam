const state = {
	step:1,// 创建步骤
	classInfo:[],// 班级信息
	userInfo:JSON.parse(sessionStorage.getItem("userInfo")),
}
export default state;