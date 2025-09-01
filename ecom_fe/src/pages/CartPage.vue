<script setup lang="ts">
import CartList from '../components/CartList.vue'
import { onMounted, ref } from "vue";
import { Product, CartAPI } from "@/api/cart";

const cartItems = ref<Product[]>([])

const loadCart = async () => {
  const cart = await CartAPI.getCart(1)
  cartItems.value = cart.cartItems
  console.log(cartItems.value)
}

async function removeProduct(productId: number) {
  await CartAPI.removeProduct(productId)
  cartItems.value = cartItems.value.filter(item => item.productId !== productId)
}

onMounted(async () => {
  await loadCart()
})
</script>


<template>
  <div class="cart-wrapper">
    <div class="cart-container">
      <h1 class="title">Your Cart</h1>
      <CartList
          :product="cartItems ?? []"
          @remove="removeProduct"
      />
    </div>
  </div>
</template>


<style scoped>
.cart-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 75%;
}

.cart-container {
  background: #fff;
  padding: 2rem;
  border-radius: 12px;
  width: 600px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.title {
  text-align: center;
  margin-bottom: 2rem;
}


button {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 0.75rem 1.5rem;
  cursor: pointer;
  border-radius: 6px;
  font-weight: bold;
  font-size: 16px;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #45a049;
}

.pay-section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 1.5rem;
}

.payment-methods {
  display: flex;
  gap: 1rem;
}

.radio-label {
  display: flex;
  align-items: center;
  gap: 0.3rem;
  font-weight: 500;
}


</style>
