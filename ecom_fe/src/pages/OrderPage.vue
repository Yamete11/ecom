<script setup lang="ts">
import { onMounted, ref } from "vue"
import axios from "axios"
import api from '../api/axios'
import OrderList from "@/components/OrderList.vue";

interface OrderItem {
  productId: number
  quantity: number
  price: number
}

interface OrderDTO {
  userId: number
  createdAt: string
  orderStatus: string
  totalPrice: number
  items: OrderItem[]
}

interface Product {
  id: number
  title: string
  price: number
  category: string
}

const orders = ref<OrderDTO[]>([])
const products = ref<Product[]>([])


async function loadOrders() {
  try {
    const response = await axios.get('http://localhost:8083/orders')
    orders.value = response.data
  } catch (error) {
    console.error('Ошибка при загрузке заказов:', error)
  }
}

const loadProducts = async () => {
  const response = await api.get('/products')
  products.value = response.data
  console.log(products)
}

onMounted(() => {
  loadOrders(); loadProducts()
})
</script>

<template>
  <div class="order-wrapper">
    <div class="order-container" v-for="order in orders" :key="order.createdAt">
      <p><strong>Date:</strong> {{ new Date(order.createdAt).toLocaleString() }}</p>
      <p><strong>Order status:</strong> {{ order.orderStatus }}</p>
      <ul class="order-items">
        <OrderList :order="order.items" :products="products" />
      </ul>
      <p><strong>Total price:</strong> {{ order.totalPrice }} $</p>
    </div>
  </div>
</template>

<style scoped>
.order-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 2rem;
}

.order-container {
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  padding: 1.5rem;
  width: 600px;
  margin-bottom: 2rem;
}

.order-items {
  margin-top: 1rem;
  list-style: none;
  padding: 0;
}
</style>
