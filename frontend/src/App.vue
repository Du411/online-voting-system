<template>
  <div id="app" class="min-h-screen bg-gray-50">
    <header class="bg-white shadow-sm">
      <nav class="container mx-auto px-4 sm:px-6 lg:px-8 py-4">
        <div class="flex justify-between items-center">
          <div class="text-xl font-semibold text-gray-800">
            線上投票系統
          </div>
          <div class="flex space-x-4">
            <router-link to="/" class="text-gray-600 hover:text-indigo-600 transition duration-300">首頁</router-link>
            <router-link to="/admin" class="text-gray-600 hover:text-indigo-600 transition duration-300">管理</router-link>
          </div>
        </div>
      </nav>
    </header>
    <main class="container mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <router-view v-slot="{ Component }">
        <transition name="fade" mode="out-in">
          <component :is="Component" />
        </transition>
      </router-view>
    </main>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>