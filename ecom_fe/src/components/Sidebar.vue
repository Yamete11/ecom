<script setup lang="ts">
import { RouterLink } from 'vue-router'
</script>

<template>
  <aside class="sidebar">
    <nav class="nav">
      <RouterLink to="/products" class="nav-link">Products</RouterLink>
      <RouterLink to="/categories" class="nav-link">Categories</RouterLink>
      <RouterLink to="/orders" class="nav-link">Orders</RouterLink>
    </nav>
  </aside>
</template>

<style scoped>
.sidebar {
  position: fixed;
  top: 64px;
  left: 0;
  width: 240px;
  height: calc(100vh - 64px);
  background-color: #111;
  padding: 1rem;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.2);
  color: white;
  overflow-y: auto;
}

.nav {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.nav-link {
  color: #ccc;
  text-decoration: none;
  font-size: 0.95rem;
  padding: 0.5rem;
  border-radius: 4px;
  transition: background 0.2s, color 0.2s;
}

.nav-link:hover {
  background-color: #222;
  color: #00bfff;
}
</style>
