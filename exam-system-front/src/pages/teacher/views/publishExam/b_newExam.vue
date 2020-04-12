<template>
    <div id="newExam">
<!--        新建试卷-->
        <step1 v-if="step==1"></step1>
        <step2 v-else-if="step==2"></step2>
        <step3 v-else-if="step==3"></step3>
        <step4 v-else></step4>
    </div>
</template>

<script>
    import step1 from "./b_newExam_step1";
    import step2 from "./b_newExam_step2";
    import step3 from "./b_newExam_step3";
    import step4 from "./b_newExam_step4";
    // import step3 from "./step3";
    import {mapGetters} from "vuex"
	export default {
		name: "newExam",
        components:{
			step1,
            step2,
            step3,
	        step4
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