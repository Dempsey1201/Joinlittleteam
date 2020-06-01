import axios from "axios"
// 获取该老师所在班级
export const allClass  = (info)=>{
    return axios.get("/class/queryTeaClassroom",{
        params:info
    })
}
// 通过班级获取学生
export const getStudent  = (info)=>{
    return axios.get("/user/queryClass",{
        params:info
    })
}
// 创建班级
export const createClass  = (info)=>{
    return axios.get("/class/addClassroom",{
        params:info
    })
}
// 删除班级
export const deleteClass  = (info)=>{
    return axios.get("/class/delete",{
        params:info
    })
}
// 删除学生
export const deleteUSer  = (info)=>{
    return axios.get("/class/outClassRoom",{
        params:info
    })
}