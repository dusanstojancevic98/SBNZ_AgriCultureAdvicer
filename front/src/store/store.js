import Vuex from 'vuex'
import Vue from "vue";
import {user} from "@/store/user";
import {authHeader} from "@/util/auth";
import razvoj from "@/store/razvoj";

Vue.use(Vuex)


const axios = require('axios')

const store = new Vuex.Store({
    state: {
        count: 0,
        ponude: [],
        routes: [
            {
                id: 1,
                name: "Home",
                path: "/",
                icon:"mdi-home",
                main:false
            },
            {
                id: 2,
                name: "Razvoj",
                path: "/razvoj",
                color: "green",
                icon:"mdi-reload",
                main: true,
                img:require('../../public/razvoj.jpg')
            },
            {
                id: 3,
                name: "PredlogUseva",
                path: "/predloguseva",
                color: "light-green",
                icon:"mdi-format-list-bulleted",
                main: true,
                img:require('../../public/usevi.jpg')
            },
        ]
    },
    getters: {
        getRoutes(state) {
            return state.routes;
        },

        getMainRoutes(state) {
            return state.routes.filter(r=>r.main);
        },
        getPonudeUseva(state){
            return state.ponude;
        },
    },
    mutations: {
        increment(state) {
            state.count++
        },
        setPonude(state, ponude) {
            state.ponude = ponude
        },
    },
    actions: {
        async fetchPonudeUseva(context) {
            return new Promise(
                (resolve, reject) => {
                    axios.get("/api/odabirUseva", authHeader())
                        .then((res) => {
                            context.commit("setPonude", res.data)
                            resolve();
                        })
                        .catch((err) => {
                            console.log(err)
                            reject()
                        })

                }
            )
        },
        async addRazvoj(context, razvoj) {
            return new Promise(
                (resolve, reject) => {
                    axios.post("/api/razvoj", razvoj, authHeader())
                        .then(() => {
                            context.dispatch("fetchRazvoji")
                            resolve();
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
        razvoj:razvoj
    }
})

export default store;
