import Vuex from 'vuex'

const axios = require('axios')

const store = new Vuex.Store({
    state: {
        count: 0,
        ponude:[]
    },
    mutations: {
        increment (state) {
            state.count++
        },
        setPonude(state, ponude){
            state.ponude = ponude
        }
    },
    actions:{
        async getPonudeUseva(context){
            return new Promise(
                (resolve, reject)=>{
                    axios.get("/ponudeUseva")
                        .then((res)=>{
                            context.commit("setPonude", res.data)
                            resolve();
                        })
                        .catch((err)=>{
                            console.log(err)
                            reject()
                        })

                }
            )
        }
    }
})

export default store;
