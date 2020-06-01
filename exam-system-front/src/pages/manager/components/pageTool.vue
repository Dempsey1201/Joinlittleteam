<template>
<!--    翻页器-->
    <div class="pageTool">
        <el-pagination
                :hide-on-single-page="bool"
                @prev-click="prev"
                @current-change="currentChange"
                @next-click="next"
                :current-page="current"
                :page-size="step"
                :total="list.length"
                layout="prev, pager, next">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        name: "pageTool",
        created() {
            this.bool = this.list.length<this.step
        },
        props:{
            step:{
                type:Number,
                required:true
            },
            list:{
                type: Array,
                required: true
            }
        },
        data(){
            return{
                current:1,
                bool:null
            }
        },
        methods:{
            prev(val){
                this.$emit("check",this.list.slice((val-1)*this.step,val*this.step))
            },
            currentChange(val){
                this.$emit("check",this.list.slice((val-1)*this.step,val*this.step))
            },
            next(val){
                this.$emit("check",this.list.slice((val-1)*this.step,val*this.step))
            }
        }
    }
</script>

<style scoped>

</style>