<script setup lang="ts">
import { ref, onMounted } from 'vue'
import ProductList from '../components/ProductList.vue'
import { Product, ProductAPI } from '@/api/product'

const products = ref<Product[]>([])
const searchQuery = ref('')

const loadProducts = async () => {
  products.value = await ProductAPI.getAll()
  console.log(products.value)
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
