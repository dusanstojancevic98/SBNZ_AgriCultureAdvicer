import {user} from "@/store/user";
import {authHeader} from "@/util/auth";



const axios = require('axios')

export const razvoj = {
    state: {
        razvojiInicijalni:[],
        razvojiUToku:[],
        razvojiPauzirani:[],
        razvojiZaustavljeni:[],
    },
    getters: {
        getRazvojiInicijalni:function(state){
            return state.razvojiInicijalni;
        },
        getRazvojiUToku:function(state){
            return state.razvojiUToku;
        },
        getRazvojiPauzirani:function(state){
            return state.razvojiPauzirani;
        },
        getRazvojiZaustavljeni:function(state){
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
                        .then((res) => {
                            context.commit("setRazvojiInicijalni", res.data)
                            resolve(res);
                        })
                        .catch((err) => {
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
                        .then((res) => {
                            context.commit("setRazvojiUToku", res.data)
                            resolve(res);
                        })
                        .catch((err) => {
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
                        .then((res) => {
                            context.commit("setRazvojiPauzirani", res.data)
                            resolve(res);
                        })
                        .catch((err) => {
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
                        .then((res) => {
                            context.commit("setRazvojiZaustavljeni", res.data)
                            resolve(res);
                        })
                        .catch((err) => {
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
                        .then((res) => {
                            context.dispatch("fetchRazvoji")
                            resolve(res);
                        })
                        .catch((err) => {
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
}

