import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/main/Home.vue'

Vue.use(VueRouter)

const routes = [

    {
        path: "/auth",
        children: [
            {
                path: '/login',
                name: 'Login',
                component: () => import('../views/user/Login.vue')
            },
            {
                path: '/register',
                name: 'Register',
                component: () => import('../views/user/Register.vue')
            },
        ],
        component: () => import('../views/user/User.vue')

    },

    {
        path: "/",
        children: [
            {
                path: '/',
                name: 'Home',
                component: Home
            },
            {
                path: '/razvoj',
                name: 'Razvoj',
                component: () => import( '../views/main/Razvoji.vue')
            },
            {
                path: '/predloguseva/:id',
                name: 'PredlogUseva',
                props: true,
                component: () => import( '../views/main/PredlogUseva.vue')
            },
            {
                path: '/razvoj/pregled/:id',
                name: 'RazvojPregled',
                props:true ,
                component: () => import( '../views/main/PregledRazvoja.vue')
            },
            {
                path: '/obavestenja',
                name: 'Alerts',
                component: () => import( '../views/main/Alerts.vue')
            },
        ],
        component: () => import( '../views/main/Main.vue')

    },
]

const router = new VueRouter({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
