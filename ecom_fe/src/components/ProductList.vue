<script setup lang="ts">
import ProductItem from "@/components/ProductItem.vue";
import { computed } from 'vue'

interface Product {
  id: number
  title: string
  price: number
  category: string
}

const props = defineProps<{
  products: Product[],
  searchQuery: string
}>()

const searchQuery = props.searchQuery ?? ''

const filteredProducts = computed(() => {
  return props.products.filter(p =>
      p.title.toLowerCase().includes(props.searchQuery.toLowerCase())
  )
})
</script>

<template>
  <div class="product-list">
    <div v-if="!filteredProducts.length">No products found.</div>

    <div class="products">
      <ProductItem
          v-for="product in filteredProducts"
          :key="product.id"
          :product="product"
      />
    </div>
  </div>
</template>


<style scoped>
.product-list {
  padding: 1rem;
}
.products {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
}
.product img {
  width: 100%;
  height: auto;
}
</style>
