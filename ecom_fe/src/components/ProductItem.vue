<script setup>
defineProps({
  product: {
    type: Object,
    required: true
  }
})
</script>

<template>
  <div class="product-item">
    <h3>{{ product.title }}</h3>
    <h3>{{ product.price }} $</h3>
    <button>Add to cart</button>
  </div>
</template>

<style scoped>
.product-item {
  border: 1px solid #ccc;
  padding: 1rem;
  width: 200px;
  margin-bottom: 1rem;
}
</style>
