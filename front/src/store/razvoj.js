import {authHeader} from "@/util/auth";


const axios = require('axios')

export const razvoj = {
    state: {
        razvojiInicijalni: [],
        razvojiUToku: [],
        razvojiPauzirani: [],
        razvojiZaustavljeni: [],
        razvojiSpremni: [],
        trenutniRazvoj: null
    },
    getters: {
        getRazvojiInicijalni: function (state) {
            return state.razvojiInicijalni;
        },
        getRazvojiUToku: function (state) {
            return state.razvojiUToku;
        },
        getRazvojiPauzirani: function (state) {
            return state.razvojiPauzirani;
        },
        getRazvojiZaustavljeni: function (state) {
            return state.razvojiZaustavljeni;
        },
        getRazvojiSpremni: function (state) {
            return state.razvojiSpremni;
        },
        getTrenutniRazvoj(state) {
            return state.trenutniRazvoj;
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
        },
        setRazvojiSpremni(state, razvoji) {
            state.razvojiSpremni = razvoji
        },
        setTrenutniRazvoj(state, razvoj) {
            state.trenutniRazvoj = razvoj;
        }
    },
    actions: {
        async odradiAkciju(context, {id, rid}) {
            return new Promise(
                (resolve, reject) => {
                    axios.get('/api/akcija/odradi/'+id+'/'+rid, authHeader())
                        .then((res) => {
                            context.dispatch("fetchTrenutniRazvoj", rid);
                            resolve(res);
                        })
                        .catch((err) => {
                            console.log(err)
                            reject(err)
                        })
                }
            )
        },
        async odbaciAkciju(context, {id, rid}) {
            return new Promise(
                (resolve, reject) => {
                    axios.get(`/api/akcija/odbaci/${id}/${rid}`, authHeader())
                        .then((res) => {
                            context.dispatch("fetchTrenutniRazvoj", rid);
                            resolve(res);
                        })
                        .catch((err) => {
                            console.log(err)
                            reject(err)
                        })
                }
            )
        },
        async fetchTrenutniRazvoj(context, id) {
            if (id) {
                return new Promise(
                    (resolve, reject) => {
                        axios.get("/api/razvoj/trenutni/" + id, authHeader())
                            .then((res) => {
                                context.commit("setTrenutniRazvoj", res.data)
                                console.log("razvoj bateu", res.data);
                                resolve(res);
                            })
                            .catch((err) => {
                                console.log(err)
                                reject(err)
                            })
                    }
                )
            }
        },
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
        },
        async fetchRazvojiSpremni(context) {
            return new Promise(
                (resolve, reject) => {
                    axios.get("/api/razvoj/SPREMNO", authHeader())
                        .then((res) => {
                            context.commit("setRazvojiSpremni", res.data)
                            resolve(res);
                        })
                        .catch((err) => {
                            console.log(err)
                            reject(err)
                        })
                }
            )
        }
        , async fetchRazvojiUToku(context) {
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
        , async fetchRazvojiPauzirani(context) {
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
        , async fetchRazvojiZaustavljeni(context) {
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
        },
        async fetchAll(context) {
            context.commit("setLoading", true)
            await context.dispatch("fetchRazvojiZaustavljeni")
            await context.dispatch("fetchRazvojiUToku")
            await context.dispatch("fetchRazvojiSpremni")
            await context.dispatch("fetchRazvojiPauzirani")
            await context.dispatch("fetchRazvojiInicijalni")
            await context.dispatch("fetchTrenutniRazvoj")
            context.commit("setLoading", false)
        }
        ,
        async addRazvoj(context, razvoj) {
            return new Promise(
                (resolve, reject) => {
                    axios.post("/api/razvoj", razvoj, authHeader())
                        .then(() => {
                            context.dispatch("fetchAll")
                            resolve();
                        })
                        .catch((err) => {
                            console.log(err)
                            reject()
                        })

                }
            )
        }
        ,
        async addStanjeZemljista(context, {rid, stanje}) {
            return new Promise(
                (resolve, reject) => {
                    axios.post("/api/razvoj/stanje-zemljista/" + rid, stanje, authHeader())
                        .then(() => {
                            context.dispatch("fetchAll")
                            resolve();
                        })
                        .catch((err) => {
                            console.log(err)
                            reject()
                        })

                }
            )
        },
        async pokreniRazvoj(context, id) {
            return new Promise(
                (resolve, reject) => {
                    axios.get("/api/razvoj/pokreni/" + id, authHeader())
                        .then(() => {
                            context.dispatch("fetchAll")
                            resolve();
                        })
                        .catch((err) => {
                            console.log(err)
                            reject()
                        })

                }
            )
        },
        async obrisiRazvoj(context, id) {
            return new Promise(
                (resolve, reject) => {
                    axios.delete("/api/razvoj/" + id, authHeader())
                        .then(() => {
                            context.dispatch("fetchAll")
                            resolve();
                        })
                        .catch((err) => {
                            console.log(err)
                            reject()
                        })
                }
            )
        },
        async odaberiUsev(context, {id, usevId}) {
            return new Promise(
                (resolve, reject) => {
                    axios.get("/api/razvoj/odaberi/" + id + "/" + usevId, authHeader())
                        .then(() => {
                            context.dispatch("fetchAll")
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
}

