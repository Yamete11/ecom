<script setup lang="ts">
import CartItem from "@/components/CartItem.vue";

interface Product {
  id: number
  title: string
  price: number
  category: string
}

const props = defineProps<{
  product: Product[]
}>()

const emit = defineEmits<{
  (e: 'remove', id: number): void
}>()
</script>

<template>
  <div>
    <div v-if="!product.length" class="empty">The cart is empty</div>
    <div class="products">
      <CartItem
          v-for="p in product"
          :key="p.id"
          :product="p"
          @remove="emit('remove', $event)"
      />
    </div>
  </div>
</template>


<style scoped>
.products {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.empty {
  text-align: center;
  color: #999;
  font-style: italic;
}
</style>
