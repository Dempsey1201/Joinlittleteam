import {mapGetters} from "vuex";
import state from "./state";

export const newExamDetail = state => state.newExamDetail;// 获取新建试卷信息
export const step = state => state.step;// 获取新建试卷信息
export const classInfo = state => state.classInfo// 获取班级信息

// computed:{
// ...mapGetters([
// 		"newExamDetail"
// 	]),
// },

// this.newExamDetail 获取