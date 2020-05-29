// 管理员登陆
import axios from "axios"
export const managerLogin  = (info)=>{
    return axios.get("/admin/login",{
        params:info
    })
}

