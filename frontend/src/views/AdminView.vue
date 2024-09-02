<template>
  <div class="admin-view">
    <h2 class="text-3xl font-bold mb-8 text-center text-gray-800">管理投票項目</h2>
    
    <AddItemForm @add-item="addVotingItem" />
    
    <VotingTable :items="votingItems">
      <template #action="{ item }">
        <button @click="editVotingItem(item)" 
                class="bg-blue-500 hover:bg-blue-600 text-white font-medium py-1 px-3 rounded-lg mr-2 transition duration-300">
          編輯
        </button>
      </template>
    </VotingTable>
  </div>
</template>

<script>
import axios from 'axios';
import VotingTable from '../components/VotingTable.vue';
import AddItemForm from '../components/AddItemForm.vue';

export default {
  name: 'AdminView',
  components: {
    VotingTable,
    AddItemForm
  },
  data() {
    return {
      votingItems: []
    }
  },
  mounted() {
    this.getVotingItems();
  },
  methods: {
    async getVotingItems() {
      try {
        const response = await axios.get('/api/voting-items');
        this.votingItems = response.data;
      } catch (error) {
        console.error('Error getting voting items:', error);
      }
    },
    async addVotingItem(name) {
      try {
        await axios.post('/api/voting-items', { name });
        this.getVotingItems();
      } catch (error) {
        console.error('Error adding voting item:', error);
      }
    },
    async editVotingItem(item) {
      const newName = prompt('輸入新的項目名稱:', item.name);
      if (newName && newName !== item.name) {
        try {
          await axios.put(`/api/voting-items/${item.id}`, { name: newName });
          this.getVotingItems();
        } catch (error) {
          console.error('Error updating voting item:', error);
        }
      }
    }
  }
}
</script>