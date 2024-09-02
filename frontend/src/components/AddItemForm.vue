<template>
    <form @submit.prevent="submitForm" class="mb-8 bg-white rounded-lg shadow-md p-6">
      <input v-model="itemName" placeholder="輸入新的投票項目" required
             class="w-full px-3 py-2 placeholder-gray-400 text-gray-700 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 mb-4">
      <button type="submit" 
              class="w-full bg-blue-500 hover:bg-blue-600 text-white font-medium py-2 px-4 rounded-lg transition duration-300">
        新增項目
      </button>
    </form>
  </template>
  
  <script>
  export default {
    name: 'AddItemForm',
    data() {
      return {
        itemName: ''
      }
    },
    methods: {
      submitForm() {
        this.$emit('add-item', this.itemName);
        this.itemName = '';
      }
    }
  }
  </script>