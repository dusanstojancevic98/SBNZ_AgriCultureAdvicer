import axios from "axios";
import {authHeader} from "@/util/auth";

export const stanja = {
    state: {
        stanja: []
    },
    getters: {
        getStanja(state){
            return state.stanja;
        },
    },
    mutations: {
        setStanja(state, stanja){
            state.stanja = stanja;
        },
        addStanje(state, stanje){
            state.stanja.push(stanje);
        }
    },
    actions: {
        async fetchStanja({ commit }, idRazvoj) {
            return new Promise(
                (resolve, reject) => {
                    axios.get("api/stanja/" + idRazvoj, authHeader())
                        .then((res) => {
                            commit("setStanja", res.data)
                            resolve(res.data);
                        })
                        .catch((err) => {
                            console.log(err)
                            reject()
                        })

                }
            )
        },

        async addStanje({ commit }, stanje) {
            return new Promise(
                (resolve, reject) => {
                    axios.post("api/stanja", stanje, authHeader())
                        .then((res) => {
                            commit("addStanje", res.data)
                            resolve(res.data);
                        })
                        .catch((err) => {
                            console.log(err)
                            reject()
                        })

                }
            )
        },
    },
}
