<script setup>
import { RouterLink } from 'vue-router'
</script>

<template>
  <header class="navbar">
    <RouterLink to="/" class="logo">durden</RouterLink>

    <nav class="nav-links">
      <input type="text" placeholder="Search..." class="search" />
      <RouterLink to="/cart" class="nav-link">Cart</RouterLink>
      <RouterLink to="/profile" class="nav-link">Profile</RouterLink>
      <RouterLink to="/settings" class="nav-link">Settings</RouterLink>
      <RouterLink to="/logout" class="logout nav-link">Sign out</RouterLink>
    </nav>
  </header>
</template>

<style scoped>
.navbar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  height: 64px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 2rem;
  background-color: #1a1a1a;
  color: white;
  z-index: 1000;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3);
}

.logo {
  font-size: 2.5rem;
  font-weight: bold;
  text-decoration: none;
  color: white;
}

.nav-links {
  display: flex;
  align-items: center;
  gap: 1.5rem;
}

.nav-link {
  text-decoration: none;
  color: #ccc;
  font-size: 0.95rem;
  padding: 0.5rem;
  border-radius: 4px;
  transition: background 0.2s, color 0.2s;
}

.nav-link:hover {
  background-color: #222;
  color: #00bfff;
}

.search {
  padding: 6px 10px;
  border-radius: 4px;
  border: 1px solid #444;
  background: #333;
  color: white;
  font-size: 0.9rem;
}

.search::placeholder {
  color: #aaa;
}

.logout {
  color: #f87171;
}

.logout:hover {
  color: #ef4444;
}
</style>
