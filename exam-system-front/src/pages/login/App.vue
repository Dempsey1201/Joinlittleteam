<template>
    <div id="app">
        <background></background>
    </div>
</template>
<script>
    import background from "../../component/base/background";
	export default {
		data () {
			return {
			};
		},
        components:{
			background
        },
        created() {

        }
	}
</script>

<style lang="less">
    body,html{
        margin: 0;
        padding: 0;
        overflow: hidden;
    }
</style>
