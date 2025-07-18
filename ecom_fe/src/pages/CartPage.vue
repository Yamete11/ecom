<script setup lang="ts">
import CartList from '../components/CartList.vue'
import { onMounted, ref } from "vue";
import axios from 'axios'

interface Product {
  id: number
  title: string
  price: number
  category: string
}

interface CartDTO {
  id: number
  userId: number
  createdAt: string
  cartItems: Product[]
}

interface PaymentMethods {
  id: number
  title: string
}

const paymentMethods = ref<PaymentMethods[]>([])
const selectedMethod = ref<number | null>(null)

async function loadPaymentMethods(){
  try {
    const response = await axios.get('http://localhost:8082/payment-methods')
    paymentMethods.value = response.data
    selectedMethod.value = paymentMethods.value[0]?.id ?? null
  } catch (error) {
    console.error('Ошибка при загрузке методов оплаты:', error)
  }
}

const cart = ref<CartDTO | null>(null)

const loadCart = async () => {
  const response = await axios.get('http://localhost:8081/carts/1')
  cart.value = response.data
}

async function removeProduct(productId: number) {
  if (!cart.value) return

  try {
    await axios.delete(`http://localhost:8081/cart-items/${productId}`)
    cart.value.cartItems = cart.value.cartItems.filter(p => p.id !== productId)
  } catch (error) {
    console.error('Ошибка при удалении товара из корзины:', error)
  }
}


function pay() {
  alert('Payment functionality coming soon!')
}

onMounted(async () =>{
  await loadCart();
  await loadPaymentMethods()

})
</script>

<template>
  <div class="cart-wrapper">
    <div class="cart-container">
      <h1 class="title">Your Cart</h1>
      <CartList
          :product="cart?.cartItems ?? []"
          @remove="removeProduct"
      />
      <div class="pay-section">
        <div class="payment-methods">
          <label
              v-for="method in paymentMethods"
              :key="method.id"
              class="radio-label"
          >
            <input
                type="radio"
                :value="method.id"
                v-model="selectedMethod"
            />
            {{ method.title }}
          </label>
        </div>

        <button @click="pay">Pay</button>
      </div>
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
