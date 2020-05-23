<template>
    <div id="headerTab">
        <div class="left">
            <img :src="managerInfo.url" alt="">
            <span>{{managerInfo.name}}</span>
        </div>
        
        <div class="head">成才考试系统管理</div>
    </div>
</template>

<script>
    import tab from "./tab";
	export default {
		name: "headerTab"
        ,data(){
			return{
				managerInfo:{
					url:"../../common/images/1.jpg",
					name:"sdvav"
                }
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
    #headerTab{
        padding: 0 20px;
        position: relative;
        z-index: 1;
        height: 100%;
        background-color: #fff;
        border-bottom:2px solid #409eff;
        line-height: 80px;
        .left
        {
            float: left;
            img{
                height: 40px;
                width: 40px;
                border-radius: 50%;
                margin-top: 20px;
                vertical-align: top;
            }
            > span{
                margin: 0 15px 0 8px;
                font-size: 14px;
                color: #424242;
            }
        }
    }
    .el-icon-s-unfold{
        font-size: 20px;
        cursor: pointer;
        color: rgb(64, 158, 255) !important;
    }
    .head{
        font-size: 30px !important;
        color: #409eff !important;
        opacity: 1 !important;
        float: right !important;
        height: 80px;
        line-height: 80px;
        background-color: #fff;
    }
</style>