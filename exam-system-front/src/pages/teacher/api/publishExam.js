// 获取所有公开的试卷
import axios from "axios"
// 获取所有公开的试卷
export const getPublicPaper  = ()=>{
    return axios.get("/paper/searchPaper",{
        params: {pname:""}
    })
}

// 搜索公开的试卷
export const searchPublicPaper  = (info)=>{
    return axios.get("/paper/searchPaper",{
        params: info
    })
}

// 添加试题
export const addQuestion  = (info)=>{
    return axios.get("/question/insertNewQuestion",{
        params: info
    })
}
// 新建试卷
export const newPaper  = (info)=>{
    return axios.get("/paper/insertNewPaper",{
        params: info
    })
}
// 建立试卷和试题的映射
export const questionToPaper  = (info)=>{
    return axios.get("/paper/insertQuestionToPaper",{
        params: info
    })
}