<script setup>
import { ref, onMounted, watch } from 'vue'
import api from '../api/axios'
import ProductList from '../components/ProductList.vue'

const categories = ref([])
const selectedCategory = ref(null)
const products = ref([])

const loadCategories = async () => {
  const response = await api.get('/categories')
  categories.value = response.data
}

const loadProducts = async () => {
  const params = selectedCategory.value ? { categoryId: selectedCategory.value } : {}
  const response = await api.get('/products', { params })
  products.value = response.data
}

onMounted(async () => {
  await loadCategories()
  await loadProducts()
})

watch(selectedCategory, loadProducts)
</script>

<template>
  <div class="home-page">
    <h1>Welcome to E-Shop!</h1>
    <div class="filters">
      <label>Filter by category:</label>
      <select v-model="selectedCategory">
        <option :value="null">All categories</option>
        <option v-for="cat in categories" :key="cat.id" :value="cat.id">
          {{ cat.title }}
        </option>
      </select>
    </div>
    <div>
      <ProductList :products="products" />
    </div>
  </div>
</template>

<style scoped>
.home-page {
  padding: 1rem;
}
.filters {
  margin-bottom: 1rem;
}
</style>
