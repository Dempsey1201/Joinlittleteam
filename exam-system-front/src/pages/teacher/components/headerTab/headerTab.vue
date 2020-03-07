<template>
    <div id="headerTab">
        <transition name="el-zoom-in-center">
            <tab class="showTab" v-show="showTabData"></tab>
        </transition>
        <el-menu class="el-menu-demo" mode="horizontal">
            <el-menu-item index="5" class="hidden-md-and-up"
                @click="showTab"
            >
                <i class="el-icon-s-unfold" ></i>
            </el-menu-item>
            <el-menu-item index="1">处理中心</el-menu-item>
            <el-submenu index="2">
                <template slot="title">我的工作台</template>
                <el-menu-item index="2-1">选项1</el-menu-item>
                <el-menu-item index="2-2">选项2</el-menu-item>
                <el-menu-item index="2-3">选项3</el-menu-item>
                <el-submenu index="2-4">
                    <template slot="title">选项4</template>
                    <el-menu-item index="2-4-1">选项1</el-menu-item>
                    <el-menu-item index="2-4-2">选项2</el-menu-item>
                    <el-menu-item index="2-4-3">选项3</el-menu-item>
                </el-submenu>
            </el-submenu>
            <el-menu-item index="3" disabled>消息中心</el-menu-item>
        </el-menu>
    </div>
</template>

<script>
    import tab from "../tab/tab";
	export default {
		name: "headerTab"
        ,data(){
			return{
				showTabData:false
            }
        }
        ,components:{
			tab
        },
        methods:{
	        showTab(){
		        this.showTabData = (this.showTabData ? false:true);
            }
        }
	}
</script>

<style scoped lang="less">
    .el-icon-s-unfold{
        font-size: 20px;
        cursor: pointer;
    }
    .showTab{
        position: absolute;
        top: 0;
        left: 0;
        /*z-index: 10;*/
    }
</style>