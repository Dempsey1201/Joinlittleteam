<template>
    <div id="stuGrade">
        <router-view></router-view>
    </div>
</template>

<script>
	export default {
		name: "stuGrade",
        created() {
            document.title = "成绩管理"
        }
    }
</script>

<style scoped>

</style>