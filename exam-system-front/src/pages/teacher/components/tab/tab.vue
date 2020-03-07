<template>
    <div id="tab"
    >
        <el-menu
                default-active="/teacher.html/individual"
                class="el-menu-vertical-demo"
                background-color="rgba(255,255,255,0.5)"
                router="true"
        >
            <el-menu-item index="/teacher.html/individual">
                <i class="el-icon-s-custom"></i>
                <span slot="title">个人中心</span>
            </el-menu-item>
            <el-menu-item index="/teacher.html/yoursClass">
                <i class="el-icon-s-grid"></i>
                <span slot="title">你的班级</span>
            </el-menu-item>
            <el-menu-item index="/teacher.html/publishExam">
                <i class="el-icon-edit-outline"></i>
                <span slot="title">发布考试</span>
            </el-menu-item>
            <el-menu-item index="/teacher.html/stuGrade">
                <i class="el-icon-document"></i>
                <span slot="title">学生成绩</span>
            </el-menu-item>
        </el-menu>
    </div>
</template>

<script>
	export default {
		name: "tab",
	}
</script>

<style scoped lang="less">
    #tab{
        height: 100%;
        width: 250px;
        .el-menu-vertical-demo{
            height: 100%;
        }
    }
</style>