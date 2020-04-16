import {mapGetters} from "vuex";

export const newExamDetail = state => state.newExamDetail;// 获取新建试卷信息
export const step = state => state.step;// 获取新建试卷信息

// computed:{
// ...mapGetters([
// 		"newExamDetail"
// 	]),
// },

// this.newExamDetail 获取