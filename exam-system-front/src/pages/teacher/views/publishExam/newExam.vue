<template>
    <div id="newExam">
<!--        新建试卷-->
        <step1 v-show="step==1"></step1>
        <step2 v-show="step==2"></step2>
        <step3 v-show="step==3"></step3>
    </div>
</template>

<script>
    import step1 from "./step1";
    import step2 from "./step2";
    import step3 from "./step3";
    import {mapGetters} from "vuex"
	export default {
		name: "newExam",
        components:{
			step1,
            step2,
            step3
        },
        computed:{
			...mapGetters([
				"step"
            ])
        }
	}
</script>

<style scoped>

</style>