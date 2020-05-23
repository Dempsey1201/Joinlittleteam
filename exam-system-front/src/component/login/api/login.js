// 学生登陆
import axios from "axios"
export const studentLogin  = (info)=>{
    return axios.get("/user/login",{
        params:info
    })
}

// 教师登录
export const teacherLogin  = (info)=>{
    return axios.get("/teacher/login",{
        params:info
    })
}