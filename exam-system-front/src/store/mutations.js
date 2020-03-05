import * as types from "./mutations-type"
const mutations = {
	[types.SET_ID](state,id){//设置id
		state.id = id;
	}
}

export default mutations;//设置state