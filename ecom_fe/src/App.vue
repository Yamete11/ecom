<script setup>
import Navbar from './components/Navbar.vue'
import Sidebar from "@/components/Sidebar.vue";
</script>

<template>
  <div class="layout">
    <Navbar />
    <Sidebar />
    <main class="main">
      <RouterView />
    </main>
  </div>
</template>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: sans-serif;
  background: black;
  color: white;
}

.layout {
  display: flex;
  height: 100vh;        /* Вся высота окна браузера */
  overflow: hidden;     /* Чтобы не было глобального скролла */
}

.main {
  margin-left: 240px;               /* ширина сайдбара */
  padding: 60px 1rem 1rem;          /* отступ сверху под навбар, сбоку padding */
  width: calc(100% - 240px);       /* чтобы не вылезать за сайдбар */
  height: calc(100vh - 64px);      /* вся высота минус навбар */
  overflow-y: auto;                /* прокрутка именно внутри main */
  box-sizing: border-box;
  background-color: #000;          /* если хочешь черный фон под содержимым */
}

</style>
