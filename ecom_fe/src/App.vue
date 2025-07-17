<script setup>
import Navbar from './components/Navbar.vue'
</script>

<template>
  <Navbar></Navbar>
  <main class="main">
    <RouterView />
  </main>
</template>

<style scoped>

body, html {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
}

.main {
  position: fixed;
  left: 0;
  top: 0;
  margin-top: 64px;
  height: 100%;
  width: 100%;
  padding: 1rem;
  box-sizing: border-box;
  background-color: dimgray;
}
</style>
