<script setup lang="ts">
import { ref } from 'vue'

const username = ref('john_doe')
const email = ref('john@example.com')
const password = ref('')
</script>

<template>
  <div class="profile-container">
    <h1 class="title">Profile</h1>
    <form class="profile-form">
      <label>
        Username:
        <input type="text" v-model="username" />
      </label>

      <label>
        Email:
        <input type="email" v-model="email" />
      </label>

      <label>
        Password:
        <input type="password" v-model="password" />
      </label>

      <label>
        Role:
        <label></label>
      </label>

      <button type="submit">Save</button>
    </form>
  </div>
</template>

<style scoped>
.profile-container {
  width: 1000px;
  margin: 0 auto;
  padding: 2rem;
  border-radius: 8px;
  height: 100%;
  box-sizing: border-box;
  color: white;
}


.title {
  font-size: 1.5rem;
  margin-bottom: 1.5rem;
  text-align: center;
  color: #ffffff;
}

.profile-form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

label {
  display: flex;
  flex-direction: column;
  font-size: 0.95rem;
  color: #ccc;
}

input {
  padding: 0.5rem;
  border-radius: 4px;
  border: 1px solid #444;
  background-color: #2a2a2a;
  color: white;
}

button {
  padding: 0.6rem 1rem;
  border: none;
  background-color: #00bfff;
  color: white;
  font-weight: bold;
  border-radius: 4px;
  cursor: pointer;
  align-self: flex-end;
  transition: background-color 0.2s ease;
}

button:hover {
  background-color: #009acd;
}
</style>
