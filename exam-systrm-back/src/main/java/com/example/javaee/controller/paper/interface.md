# 董明龙的
## 试卷类接口
### 首先关于问题表
所有的参数都是字面值，但是注意qtype 1表示单选题，2表示多选题，3表示判断题，4表示问答题。对于判断题的答案0表示错误，1表示正确
### 其次关于t_done表
里面的done表示用户是否已经完成试卷，0或者查不到表示没有完成，1表示已完成该试卷。
### 关于判题
单选题在storeAnswer的时候就会判定同时生成最终成绩，但是主观题必须老师批改后再自动生成。多选题暂时还没实现判题嘻嘻嘻
### 为了用户访问速度考虑
在生成试卷的时候，如果是直接复用软件直接调用
### 1 关于问题类  接口均测试过
#### 接口头 /question 
|接口地址|作用|输入|输出|
|---|---|---|---|
|/getAllQuestion|获得所有的问题|NONE|question类|
|/insertNewQuestion|添加问题|String question, String oa, String ob, String oc, String od, String answer, Integer qtype, String author, String subject|问题的id|
|/deleteQuestion|删除一个问题|Integer|布尔值|
|/searchSubject|查找科目，模糊搜索|String subject|question类|
|/searchQuestion|查找问题，模糊搜索|String question|question类|
|/searchQuestionBySubject|同时限定科目和问题|String question,String subject|question类|
|/updateQuestion|更新问题|String question, String oa, String ob, String oc, String od, String answer, Integer qtype, String author, String subject|布尔值|
|/getAnswer|获取用户的一个题目的答案|Integer qid|字符串答案|
|/getTrueAnswer|获取正确答案|Integer qid|答案|
### 2 关于成绩类  接口均测试过
#### 接口头 /paper
|接口地址|作用|输入|输出|
|---|---|---|---|
|/getScore|获取学生的某一张试卷的成绩，为空表示尚未考试|Integer sid,Integer pid|Integer|
|/getScoreByUser|获取某一个学生的所有试卷分数|Integer sid|考试的名字，成绩，考试的id等|
|/getClassAnswer|直接获取某一个学生对于某一张试卷的从1到最后一题的答案,qid可以乱传|Integer sid,Integer qid,Integer pid|考试的题号，成绩，问题描述等等|
|/insertAnswer|作答完了后直接存储答案（一个）|Integer pid,Integer sid,Integer qid,String answer|布尔值|
|/storeAnswerAndJudge|作答完了后直接存储答案（数组批量）|Integer pid,List<Integer>qid,Integer sid,List<String>answer|布尔值|
|/correctByTeacher|老师批改作业|Integer qid,Integer sid,Integer getscore)|布尔值|
|/getDone|查看是否完成了试卷|Integer sid,Integer pid|布尔值|
|/updateUserScore|更新某个同学的某张试卷的成绩（一般由系统自动生成）|Integer sid,Integer pid,Integer score|布尔值|
|/getScoreByPaper|按照试卷和班级获取所有成绩|Integer pid,Integer classno|成绩数组|
|/getPaperByClass|按照班级获取所有的试卷|Integer classno|试卷数组,pname是学生名字，pid是学生的id|
|/getPaperByNo|传一个学生id获得他所在班级的所有试卷|Integer sid|试卷组|
### 3 关于试卷类
#### 接口头 /paper
|接口地址|作用|输入|输出|
|---|---|---|---|
|/getAllPaper|获取所有试卷信息|NONE|paper类|
|/insertNewPaper|插入新试卷|String pname, Integer share, String teacher, String classno, Date start_time, Date end_time, Double last_time, Integer full_score|新建试卷的id|
|/getPaperQuestion|获取试卷内容|Integer pid|试卷具体内容，question类|
|/reusePaper直接复用试卷|Integer pid,Integer pid1,pid是复用的，pid1是新建的试卷|布尔值|
|/insertNewPaper|写入试卷的内容，考试名称时间等信息，先添加题目再生成试卷|String pname, Integer share, String teacher, String classno, Date start_time, Date end_time,Double last_time, Integer full_score, String subject||
|/insertQuestionToPaper|在存入试卷信息之后按照顺序存题目与试卷的映射|List<Integer> pid,List<Integer> no,List<Integer> qid,List<Integer> qscore|布尔值|
|/deletePaper|删除试卷,注意删除错误就是试卷不存在或者没有题目对应|pid|布尔值|
|/searchPaper|查找试卷，注意一旦选择了试卷还是要调用insertNewPaper来创建对应的试卷.同时注意搜索的只有共享的试卷|pname|paper类|
