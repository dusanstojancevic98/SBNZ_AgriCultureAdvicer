import axios from "axios";

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
    },
    actions: {
        async fetchAlertNum(context) {
            return new Promise(
                (resolve, reject) => {
                    axios.get("/api/alerts/count")
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
        }, async fetchAlerts(context) {
            return new Promise(
                (resolve, reject) => {
                    axios.post("api/alerts")
                        .then((res) => {
                            context.state.alerts = res.data;
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
