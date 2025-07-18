import { createRouter, createWebHistory } from 'vue-router'
import ProductPage from '../pages/ProductPage.vue'
import CartPage from '../pages/CartPage.vue'
import ProfilePage from '../pages/ProfilePage.vue'
import OrderPage from '../pages/OrderPage.vue'

const routes = [
    { path: '/', redirect: '/products' },
    { path: '/products', component: ProductPage },
    { path: '/cart', component: CartPage },
    { path: '/profile', component: ProfilePage },
    { path: '/orders', component: OrderPage }
]


const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router