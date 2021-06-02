import Vuex from 'vuex'
import Vue from "vue";
import {user} from "@/store/user";
import {authHeader} from "@/util/auth";

Vue.use(Vuex)


const axios = require('axios')

const store = new Vuex.Store({
    state: {
        razvojiInicijalni:[],
        razvojiUToku:[],
        razvojiPauzirani:[],
        razvojiZaustavljeni:[],
    },
    getters: {
        getRazvojiInicijalni(state){
            return state.razvojiInicijalni;
        },
        getRazvojiUToku(state){
            return state.razvojiUToku;
        },
        getRazvojiPauzirani(state){
            return state.razvojiPauzirani;
        },
        getRazvojiZaustavljeni(state){
            return state.razvojiZaustavljeni;
        }
    },
    mutations: {
        setRazvojiInicijalni(state, razvoji) {
            state.razvojiInicijalni = razvoji
        },
        setRazvojiUToku(state, razvoji) {
            state.razvojiUToku = razvoji
        },
        setRazvojiPauzirani(state, razvoji) {
            state.razvojiPauzirani = razvoji
        },
        setRazvojiZaustavljeni(state, razvoji) {
            state.razvojiZaustavljeni = razvoji
        }
    },
    actions: {

        async fetchRazvojiInicijalni(context) {
            return new Promise(
                (resolve, reject) => {
                    axios.get("/api/razvoj/INICIJALNO", authHeader())
                        .then((res:any) => {
                            context.commit("setRazvojiInicijalni", res.data)
                            resolve(res);
                        })
                        .catch((err:any) => {
                            console.log(err)
                            reject(err)
                        })
                }
            )
        }
        ,async fetchRazvojiUToku(context) {
            return new Promise(
                (resolve, reject) => {
                    axios.get("/api/razvoj/U_TOKU", authHeader())
                        .then((res:any) => {
                            context.commit("setRazvojiUToku", res.data)
                            resolve(res);
                        })
                        .catch((err:any) => {
                            console.log(err)
                            reject()
                        })
                }
            )
        }
        ,async fetchRazvojiPauzirani(context) {
            return new Promise(
                (resolve, reject) => {
                    axios.get("/api/razvoj/PAUZIRANO", authHeader())
                        .then((res:any) => {
                            context.commit("setRazvojiPauzirani", res.data)
                            resolve(res);
                        })
                        .catch((err:any) => {
                            console.log(err)
                            reject()
                        })
                }
            )
        }
        ,async fetchRazvojiZaustavljeni(context) {
            return new Promise(
                (resolve, reject) => {
                    axios.get("/api/razvoj/ZAVRSENO", authHeader())
                        .then((res:any) => {
                            context.commit("setRazvojiZaustavljeni", res.data)
                            resolve(res);
                        })
                        .catch((err:any) => {
                            console.log(err)
                            reject()
                        })
                }
            )
        }
        ,
        async addRazvoj(context, razvoj) {
            return new Promise(
                (resolve, reject) => {
                    axios.post("/api/razvoj", razvoj, authHeader())
                        .then((res:any) => {
                            context.dispatch("fetchRazvoji")
                            resolve(res);
                        })
                        .catch((err:any) => {
                            console.log(err)
                            reject()
                        })

                }
            )
        }
    },
    modules: {
        user: user,
    }
})

export default store;
