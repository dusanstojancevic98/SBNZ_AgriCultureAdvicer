import Vuex from 'vuex'
import Vue from "vue";
import {user} from "@/store/user";
import {authHeader} from "@/util/auth";
import {razvoj} from "@/store/razvoj";
import {alerts} from "@/store/alerts";
import {stanja} from "@/store/stanja"

Vue.use(Vuex)


const axios = require('axios')

const store = new Vuex.Store({
    state: {
        ponude: [],
        routes: [
            {
                id: 1,
                name: "Home",
                path: "/",
                icon: "mdi-home",
                main: false
            },
            {
                id: 2,
                name: "Razvoj",
                path: "/razvoj",
                color: "green",
                icon: "mdi-reload",
                main: true,
                img: require('../../public/razvoj.jpg')
            },
        ]
    },
    getters: {
        getRoutes(state) {
            return state.routes;
        },

        getMainRoutes(state) {
            return state.routes.filter(r => r.main);
        },
        getPonudeUseva(state) {
            return state.ponude;
        },
    },
    mutations: {
        setPonude(state, ponude) {
            state.ponude = ponude
        },
    },
    actions: {
        async fetchPonudeUseva(context, id) {
            return new Promise(
                (resolve, reject) => {
                    axios.get("/api/ponudeUseva/" + id, authHeader())
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
    },
    modules: {
        user: user,
        razvoj: razvoj,
        alerts:alerts,
        stanja: stanja
    }
})

export default store;
