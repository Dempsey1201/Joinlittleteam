import axios from "axios"

// 通过班级获取该班级试卷
export const getPaper  = (info)=>{
    return axios.get("/paper/getPaperByClass",{
        params:info
    })
}

// 通过班级和试卷获取该班所有学生成绩
export const getGrades  = (info)=>{
    return axios.get("/paper/getScoreByPaper",{
        params:info
    })
}