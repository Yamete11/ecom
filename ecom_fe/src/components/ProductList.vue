<script setup>
import ProductItem from "@/components/ProductItem.vue";

defineProps({
  products: {
    type: Array,
    required: true,
  }
})
</script>

<template>
  <div class="product-list">
    <div v-if="!products.length">No products found.</div>

    <div class="products">
      <ProductItem
          v-for="product in products"
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
