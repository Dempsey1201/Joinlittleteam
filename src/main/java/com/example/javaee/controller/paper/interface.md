## 试卷类接口
### 1 关于问题类  接口均测试过
#### 接口头 /question 
|接口地址|作用|输入|输出|
|---|---|---|---|
|/getAllQuestion|获得所有的问题|NONE|question类|
|/insertNewQuestion|添加问题|String question, String oa, String ob, String oc, String od, String answer, Integer qtype, String author, String subject|布尔值
|/deleteQuestion|删除一个问题|Integer|布尔值|
|/searchSubject|查找科目，模糊搜索|String subject|question类|
|/searchQuestion|查找问题，模糊搜索|String question|question类|
|/searchQuestionBySubject|同时限定科目和问题|String question,String subject|question类|
|/updateQuestion|更新问题|String question, String oa, String ob, String oc, String od, String answer, Integer qtype, String author, String subject|布尔值|
|/getAnswer|获取一个题目的答案|Integer qid|字符串答案|
### 2 关于成绩类  接口均测试过
#### 接口头 /paper
|接口地址|作用|输入|输出|
|---|---|---|---|
|/getScore|获取学生的某一张试卷的成绩，为空表示尚未考试|Integer sid,Integer pid|Integer|
|/getScoreByUser|获取某一个学生的所有试卷分数|Integer sid|考试的名字，成绩，考试的id等|
|/getClassAnswer|直接获取某一个学生对于某一张试卷的从1到最后一题的答案|Integer sid,Integer qid,Integer pid|考试的题号，成绩，问题描述等等|
|/insertAnswer|作答完了后直接存储答案（一个）|Integer pid,Integer sid,Integer qid,String answer|布尔值|
|/storeAnswerAndJudge|作答完了后直接存储答案（数组批量）|Integer pid,List<Integer>qid,Integer sid,List<String>answer|布尔值|
|/correctByTeacher|老师批改作业|Integer qid,Integer sid,Integer getscore)|布尔值|
|/getDone|查看是否完成了试卷|Integer sid,Integer pid|布尔值|
### 3 关于试卷类
#### 接口头 /paper
|接口地址|作用|输入|输出|
|---|---|---|---|
|/getAllPaper|获取所有试卷信息|NONE|paper类|
|/insertNewPaper|插入新试卷|String pname,Integer share, String teacher, String classno, Date start_time,Date end_time,Double last_time,Integer full_score,String subject|布尔值|
|/deletePaper|删除试卷|pid|布尔值|
|/searchPaper|查找试卷，注意一旦选择了试卷还是要调用insertNewPaper来创建对应的试卷.同时注意搜索的只有共享的试卷|pname|paper类|