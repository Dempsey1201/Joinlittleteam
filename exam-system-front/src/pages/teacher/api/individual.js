import axios from "axios"
export const updateOther  = (info)=>{
    return axios.get("/teacher/updateTeacher",{
        params:info
    })
}
export const updatePwd  = (info)=>{
    return axios.get("/teacher/updatePassword",{
        params:info
    })
}