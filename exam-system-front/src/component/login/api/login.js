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

// 学生获取验证码
export const getcode  = (info)=>{
    return axios.get("/user/email",{
        params:info
    })
}

// 学生注册
export const addUser  = (info)=>{
    return axios.get("/user/addUser",{
        params:info
    })
}