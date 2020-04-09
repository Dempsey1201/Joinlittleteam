<template>
    <div class="step3">
        <el-button style="display: block" type="primary" size="mini" @click="prevStep">上一步</el-button>

    </div>
</template>

<script>
	import {mapActions, mapMutations,mapGetters} from "vuex";
	
	export default {
		name: "step3",
        methods:{
	        ...mapMutations({
		        setStep:"SET_STEP"
	        }),
	        prevStep(){// 点击上一步
		        this.setStep(2);
	        }
        }
	}
</script>

<style scoped>

</style>