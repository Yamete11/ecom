import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../pages/HomePage.vue'
import CartPage from '../pages/CartPage.vue'
import ProfilePage from '../pages/ProfilePage.vue'

const routes = [
    { path: '/', component: HomePage },
    { path: '/cart', component: CartPage },
    { path: '/profile', component: ProfilePage }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router
