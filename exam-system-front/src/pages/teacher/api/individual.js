import axios from "axios"
import Qs from "qs";
// 修改其他信息
export const updateOther  = (info)=>{
    return axios.get("/teacher/updateTeacher",{
        params:info
    })
}
// 修改密码
export const updatePwd  = (info)=>{
    return axios.get("/teacher/updatePassword",{
        params:info
    })
}
// 上传头像
export const updateHeadUrl  = (info)=>{
    return axios.post("/teacher/uploadHead",Qs.stringify(info))
}