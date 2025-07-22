<script setup lang="ts">
interface OrderItem {
  productId: number
  quantity: number
  price: number
}

const props = defineProps<{
  order: OrderItem
  title: string
}>()
</script>

<template>
  <div class="order-item">
    <strong>Product:</strong> {{ title }}<br>
    <strong>Quantity:</strong> {{ order.quantity }}<br>
    <strong>Price per item:</strong> {{ order.price }}$
  </div>
</template>

<style scoped>
.order-item {
  padding: 1rem 0;
  border-bottom: 1px solid #ddd;
}
.order-item:last-child {
  border-bottom: none;
}
</style>
