<script setup lang="ts">
import { defineEmits } from 'vue'

interface Product {
  id: number
  title: string
  price: number
  category: string
}

const props = defineProps<{
  product: Product
}>()

const emit = defineEmits<{
  (e: 'remove', id: number): void
}>()

const handleRemove = () => {
  emit('remove', props.product.id)
}
</script>

<template>
  <div class="product-item">
    <div class="info">
      <h3 class="title">{{ product.title }}</h3>
      <p class="category">{{ product.category }}</p>
    </div>
    <div class="actions">
      <div class="price">{{ product.price }} $</div>
      <button class="remove-btn" @click="handleRemove">🗑</button>
    </div>
  </div>
</template>

<style scoped>
.product-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: 1px solid #eee;
  padding: 1rem;
  border-radius: 8px;
  background-color: #fafafa;
}

.info {
  flex: 1;
}

.actions {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.title {
  margin: 0;
  font-size: 18px;
  font-weight: 600;
}

.category {
  margin: 0;
  font-size: 14px;
  color: #888;
}

.price {
  font-weight: bold;
  font-size: 18px;
}

.remove-btn {
  background: none;
  border: none;
  color: red;
  font-size: 18px;
  cursor: pointer;
}
</style>
