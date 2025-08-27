<script setup lang="ts">
interface Product {
  productId: number;
  title: string;
  quantity: number;
  price: number;
  category: string;
}

const props = defineProps<{
  product: Product;
}>();

const emit = defineEmits<{
  (e: "remove", id: number): void;
}>();

const handleRemove = () => {
  emit("remove", props.product.productId);
};
</script>

<template>
  <div class="cart-item">
    <div>{{ product.title }} - {{ product.quantity }} x ${{ product.price }}</div>
    <button @click="handleRemove">Remove</button>
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
