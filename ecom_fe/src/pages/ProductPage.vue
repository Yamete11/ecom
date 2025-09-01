<script setup lang="ts">
import { ref, onMounted, watch } from 'vue'
import {api} from '../api/axios'
import ProductList from '../components/ProductList.vue'

const products = ref([])
const searchQuery = ref('')

const loadProducts = async () => {
  const response = await api.get('/products')
  products.value = response.data
  console.log(products)
}

onMounted(async () => {
  await loadProducts()
})
</script>

<template>
  <input
      v-model="searchQuery"
      type="text"
      class="search"
      placeholder="Search products..."
  />
  <div>
    <ProductList :products="products" :search-query="searchQuery" />
  </div>
</template>

<style scoped>
.search {
  width: 100%;
  max-width: 400px;
  margin-bottom: 1rem;
  margin-left: 2rem;
  padding: 0.5rem;
  border-radius: 4px;
  border: 1px solid #444;
  background-color: #222;
  color: white;
}
</style>
