<script setup lang="ts">
import OrderItem from "@/components/OrderItem.vue";

interface OrderItem {
  productId: number
  quantity: number
  price: number
}

interface Product {
  id: number
  title: string
  price: number
  category: string
}

const props = defineProps<{
  order: OrderItem[]
  products: Product[]
}>()

function getProductTitle(productId: number): string {
  return props.products.find(p => p.id === productId)?.title ?? 'Unknown Product'
}
</script>

<template>
  <div class="products">
    <div v-for="item in order" :key="item.productId">
      <OrderItem :order="item" :title="getProductTitle(item.productId)" />
    </div>
  </div>
</template>
