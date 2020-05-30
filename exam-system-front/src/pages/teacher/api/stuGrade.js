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

// 通过学生 id 试卷 id 获取学生成绩
export const getStuPaper  = (info)=>{
    return axios.get("/paper/getClassAnswer",{
        params:info
    })
}

// 删除试卷
export const delPaper  = (info)=>{
    return axios.get("/paper/deletePaper",{
        params:info
    })
}

// 老师批卷
export const addScore  = (info)=>{
    return axios.get("/paper/correctByTeacher",{
        params:info
    })
}