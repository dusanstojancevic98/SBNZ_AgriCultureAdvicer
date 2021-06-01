import axios from "axios";

export const user = {
    state: {},
    getters: {},
    mutations: {},
    actions: {
        async login(context, data) {
            return new Promise(
                (resolve, reject) => {
                    axios.post("/auth/login", data)
                        .then((res) => {
                            localStorage.setItem("jwt", res.data.jwt)
                            resolve();
                        })
                        .catch((err) => {
                            console.log(err)
                            reject()
                        })

                }
            )
        }, async register(context, data) {
            return new Promise(
                (resolve, reject) => {
                    axios.post("/auth/register", data)
                        .then((res) => {

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
