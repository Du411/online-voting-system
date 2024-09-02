<template>
  <div class="home-view">
    <h2 class="text-3xl font-bold mb-8 text-center text-gray-800">歡迎來到線上投票系統</h2>
    <form @submit.prevent="registerUser" class="max-w-md mx-auto">
      <input v-model="userName" placeholder="請輸入您的名字" required
             class="w-full px-3 py-2 placeholder-gray-400 text-gray-700 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 mb-4">
      <button type="submit" 
              class="w-full bg-blue-500 hover:bg-blue-600 text-white font-medium py-2 px-4 rounded-lg transition duration-300">
        進入投票
      </button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'HomeView',
  data() {
    return {
      userName: ''
    }
  },
  methods: {
    async registerUser() {
      try {
        const response = await axios.post('/api/voters', { name: this.userName });
        localStorage.setItem('userId', response.data.id);
        this.$router.push('/vote');
      } catch (error) {
        console.error('Error registering user:', error);
        alert('已有相同的名字被註冊');
      }
    }
  }
}
</script>