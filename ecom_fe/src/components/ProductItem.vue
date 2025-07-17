<template>
  <div class="product-item">
    <h3>{{ product.title }}</h3>
    <h3>{{ product.price }} $</h3>
    <h3>{{ product.category }}</h3>
    <button>Add to cart</button>
  </div>
</template>

<script setup>
defineProps({
  product: {
    type: Object,
    required: true
  }
})
</script>

<style scoped>
.product-item {
  border: 1px solid #444;
  padding: 1rem;
  width: 220px;
  margin: 1rem;
  background-color: #1e1e1e;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
  color: #f5f5f5;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.product-item:hover {
  transform: translateY(-4px);
  box-shadow: 0 6px 18px rgba(255, 255, 255, 0.1);
}

h3 {
  margin: 0.5rem 0;
}

button {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  cursor: pointer;
  border-radius: 4px;
  font-weight: bold;
  margin-top: 1rem;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #45a049;
}
</style>
