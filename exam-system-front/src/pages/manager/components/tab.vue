<template>
    <div id="tab"
    >
        <el-menu
                :collapse="check"
                :default-active="route[0].path"
                class="el-menu-vertical-demo"
                background-color="rgba(255,255,255,0.5)"
                :router="bool"
        >
            <el-menu-item
                    v-for="(item,index) in route" :key="index"
                    :index="item.path">
                <i :class="item.meta.icon"></i>
                <span slot="title">{{item.meta.text}}</span>
            </el-menu-item>
        </el-menu>
        
    </div>
</template>

<script>
	export default {
		name: "tab",
        data(){
			return{
				bool:true,
                route:this.$router.options.routes[1].children.slice(1,this.$router.options.routes[1].children.length)
            }
        },
        props:{
			check:{
				type:Boolean
            }
        },
        created() {
        }
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