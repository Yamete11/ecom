<script setup lang="ts">
import { ref } from 'vue'

const login = ref('')
const password = ref('')
const error = ref('')
const loading = ref(false)

const handleLogin = async () => {
  if (!login.value || !password.value) {
    error.value = 'Enter login and password'
    return
  }

  error.value = ''
  loading.value = true

  try {
    const response = await fetch('http://localhost:8085/auth/signin', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username: login.value,
        password: password.value,
      }),
    })

    if (!response.ok) {
      throw new Error('Auth error')
    }
    const data = await response.json()
    console.log("JWT Token:", data.token);


    localStorage.setItem('token', data.token)

  } catch (err: any) {
    error.value = err.message
  } finally {
    loading.value = false
  }
}
</script>


<template>
  <div class="login-container">
    <h2>Вход</h2>

    <div class="form-group">
      <label for="login">Login</label>
      <input id="login" v-model="login" type="text" placeholder="Enter login" />
    </div>

    <div class="form-group">
      <label for="password">Password</label>
      <input id="password" v-model="password" type="password" placeholder="Enter password" />
    </div>

    <p v-if="error" class="error">{{ error }}</p>

    <button @click="handleLogin">Confirm</button>
  </div>
</template>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  background: #1e1e1e;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(255, 255, 255, 0.05);
  color: white;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding-top: 10px;
  padding-bottom: 10px;
  background: #2c2c2c;
  border: 1px solid #444;
  border-radius: 5px;
  color: white;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 5px;
  font-weight: bold;
  cursor: pointer;
}

button:hover {
  background-color: #36946d;
}

.error {
  color: #ff4d4f;
  margin-bottom: 10px;
}
</style>
