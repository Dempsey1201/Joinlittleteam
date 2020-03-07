<template>
    <div id="headerTab">
        <transition name="el-zoom-in-center">
            <tab class="showTab hidden-md-and-up" v-show="showTabData"></tab>
        </transition>
        <el-menu class="el-menu-demo" mode="horizontal"
                 background-color="rgba(255,255,255,0.5)"
        >
            <el-menu-item index="0"
                          @click="hideIndex"
                          disabled
                          class="head"
            >PTA考试系统</el-menu-item>

            <el-menu-item index="1" class="hidden-md-and-up"
                @click="showTab()"
            >
                <i class="el-icon-s-unfold" v-if="showTabData"></i>
                <i class="el-icon-s-fold" v-else></i>
            </el-menu-item>

            <el-menu-item index="2"
                 @click="hideIndex"
            >处理中心</el-menu-item>
            <el-menu-item index="3" @click="hideIndex">个人中心</el-menu-item>
        </el-menu>
    </div>
</template>

<script>
    import tab from "../tab/tab";
	export default {
		name: "headerTab"
        ,data(){
			return{
				showTabData:false,
            }
        }
        ,components:{
			tab
        },
        methods:{
	        showTab(){
		        this.showTabData = !this.showTabData;
            },
	        hideIndex(){
		        this.showTabData = false
            }
        }
	}
</script>

<style scoped lang="less">
    .el-icon-s-unfold{
        font-size: 20px;
        cursor: pointer;
        color: rgb(64, 158, 255) !important;
    }
    .showTab{
        position: absolute;
        top: 60px;
        left: 0;
        z-index: 10;
    }
    .head{
        font-size: 35px !important;
        cursor: default !important;
        color: #409eff !important;
        font-weight: bold !important;
        opacity: 1 !important;
    }
</style>