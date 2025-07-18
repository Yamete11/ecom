<script setup lang="ts">
import { ref } from 'vue'
import api from '../api/axios'


interface Product {
  id: number
  title: string
  price: number
  category: string
}

const props = defineProps<{
  product: Product
}>()

const isAdding = ref(false)

async function addToCart() {
  if (isAdding.value) return
  isAdding.value = true

  try {
    const response = await fetch('http://localhost:8080/products/add-to-cart', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        userId: 1,
        productId: props.product.id,
        quantity: 1
      }),
    })

    if (!response.ok) {
      throw new Error('Failed to add to cart')
    }

  } catch (error) {
    console.error(error)
    alert('Error adding product to cart')
  } finally {
    isAdding.value = false
  }
}
</script>

<template>
  <div class="product-item">
    <h3>{{ product.id }}</h3>
    <h3>{{ product.title }}</h3>
    <h3>{{ product.price }} $</h3>
    <h3>{{ product.category }}</h3>
    <button @click="addToCart">Add to cart</button>
  </div>
</template>

<style scoped>
.product-item {
  border: 1px solid #444;
  padding: 1rem;
  width: 220px;
  margin: 1rem;
  background-color: #1e1e1e;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
  color: #f5f5f5;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.product-item:hover {
  transform: translateY(-4px);
  box-shadow: 0 6px 18px rgba(255, 255, 255, 0.1);
}

h3 {
  margin: 0.5rem 0;
}

button {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  cursor: pointer;
  border-radius: 4px;
  font-weight: bold;
  margin-top: 1rem;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #45a049;
}
</style>
