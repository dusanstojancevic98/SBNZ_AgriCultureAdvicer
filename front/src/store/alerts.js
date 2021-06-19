import axios from "axios";
import {authHeader} from "@/util/auth";

export const alerts = {
    state: {
        alertNum:0,
        alerts:null
    },
    getters: {
        getAlertNum(state){
            return state.alertNum;
        },
        getAlerts(state){
            return state.alerts;
        }
    },
    mutations: {
        setAlerts(state, alerts){
            state.alerts = alerts;
            state.alertNum = alerts.length;
        },
        
        removeAlert(state, id){
            state.alerts = state.alerts.filter( val => val.id != id);
            state.alertNum--;
        }
    },
    actions: {
        async fetchAlertNum(context) {
            return new Promise(
                (resolve, reject) => {
                    axios.get("/api/alerts/count", authHeader())
                        .then((res) => {
                             context.state.alertNum = res.data.count;
                            resolve();
                        })
                        .catch((err) => {
                            console.log(err)
                            reject()
                        })

                }
            )
        }, async fetchAlerts({ commit }) {
            return new Promise(
                (resolve, reject) => {
                    axios.get("api/alerts", authHeader())
                        .then((res) => {
                            commit("setAlerts", res.data)
                            resolve(res.data);
                        })
                        .catch((err) => {
                            console.log(err)
                            reject()
                        })

                }
            )
        },

        async setSeen({ commit }, id) {
            console.log("USAO: " + id);
            return new Promise(
                (resolve, reject) => {
                    axios.patch("api/alerts/" + id, null, authHeader())
                        .then(() => {
                            commit("removeAlert", id)
                            resolve(id);
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
