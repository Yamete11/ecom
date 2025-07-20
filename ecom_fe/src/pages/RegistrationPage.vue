<script setup lang="ts">
import { ref } from 'vue'

const username = ref('')
const email = ref('')
const password = ref('')
const confirmPassword = ref('')
const error = ref('')
const success = ref('')
const loading = ref(false)

const handleRegister = async () => {
  if (!username.value || !email.value || !password.value || !confirmPassword.value) {
    error.value = 'Please fill in all fields'
    return
  }

  if (password.value !== confirmPassword.value) {
    error.value = 'Passwords do not match'
    return
  }

  error.value = ''
  success.value = ''
  loading.value = true

  try {
    const response = await fetch('http://localhost:8085/auth/signup', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username: username.value,
        email: email.value,
        password: password.value,
      }),
    })

    if (!response.ok) {
      const err = await response.json()
      throw new Error(err.message || 'Registration error')
    }

    success.value = 'Registration successful!'
    username.value = ''
    email.value = ''
    password.value = ''
    confirmPassword.value = ''
  } catch (err: any) {
    error.value = err.message || 'An error occurred'
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="register-container">
    <h2>Register</h2>

    <div class="form-group">
      <label for="username">Username</label>
      <input id="username" v-model="username" type="text" placeholder="Enter username" />
    </div>

    <div class="form-group">
      <label for="email">Email</label>
      <input id="email" v-model="email" type="email" placeholder="Enter email" />
    </div>

    <div class="form-group">
      <label for="password">Password</label>
      <input id="password" v-model="password" type="password" placeholder="Enter password" />
    </div>

    <div class="form-group">
      <label for="confirmPassword">Confirm Password</label>
      <input id="confirmPassword" v-model="confirmPassword" type="password" placeholder="Repeat password" />
    </div>

    <p v-if="error" class="error">{{ error }}</p>
    <p v-if="success" class="success">{{ success }}</p>

    <button :disabled="loading" @click="handleRegister">
      {{ loading ? 'Registering...' : 'Register' }}
    </button>
  </div>
</template>

<style scoped>
.register-container {
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

button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.error {
  color: #ff4d4f;
  margin-bottom: 10px;
}

.success {
  color: #4caf50;
  margin-bottom: 10px;
}
</style>
