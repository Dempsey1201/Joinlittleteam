import axios from "axios";

const baseUrl = '';// 基础路径
const requestTeacher = axios.create({
	baseURL:baseUrl,
});

// 获取教师个人信息
export const teacherInfo  = (id)=>{// 上传 url
	return requestTeacher.get("/insert",{
		params:id
	})
};

// 获取教师发布的试卷信息
export const testPaper = (id)=>{
	return requestTeacher.get("/",{
		params:id
	})
};

// 获取教师管理的学生成绩信息
export const stuGrade = (id)=>{
	return requestTeacher.get("/",{
		params:id
	})
};

// 获取教师管理的班级信息
export const classInfo = (id)=>{
	return requestTeacher.get("/",{
		params:id
	})
};