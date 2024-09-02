<template>
  <div class="vote-view">
    <h2 class="text-3xl font-bold mb-8 text-center text-gray-800">投票</h2>
    
    <VotingTable :items="votingItems">
      <template #action="{ item }">
        <button @click="vote(item.id)" 
                :disabled="item.hasVoted"
                :class="['font-medium py-1 px-3 rounded-lg transition duration-300', 
                         item.hasVoted ? 'bg-gray-300 text-gray-500 cursor-not-allowed' : 'bg-blue-500 hover:bg-blue-600 text-white']">
          {{ item.hasVoted ? '已投票' : '投票' }}
        </button>
      </template>
    </VotingTable>
  </div>
</template>

<script>
import axios from 'axios';
import VotingTable from '../components/VotingTable.vue';

export default {
  name: 'VoteView',
  components: {
    VotingTable
  },
  data() {
    return {
      votingItems: []
    }
  },
  mounted() {
    this.checkUserAndLoadItems();
  },
  methods: {
    checkUserAndLoadItems() {
      const userId = localStorage.getItem('userId');
      if (!userId) {
        alert('請先登記您的名字');
        this.$router.push('/');
        return;
      }
      this.getVotingItems();
    },
    async getVotingItems() {
      try {
        const response = await axios.get('/api/voting-items');
        const userId = localStorage.getItem('userId');
        this.votingItems = await this.addVotingStatus(response.data, userId);
      } catch (error) {
        console.error('Error getting voting items:', error);
      }
    },
    async addVotingStatus(items, userId) {
      const updatedItems = [];
      
      for (let item of items) {
        const hasVoted = await this.checkVotingStatus(userId, item.id);
        item.hasVoted = hasVoted;
        updatedItems.push(item);
      }
      return updatedItems;
    },
    async checkVotingStatus(userId, itemId) {
      try {
        const response = await axios.get(`/api/voting-records/has-voted/${userId}/${itemId}`);
        return response.data;
      } catch (error) {
        console.error('Error checking voting status:', error);
        return false;
      }
    },
    async vote(itemId) {
      const userId = localStorage.getItem('userId');
      try {
        await axios.post('/api/voting-records', null, {
          params: { voterId: userId, votingItemId: itemId }
        });
        alert('投票成功！');
        this.getVotingItems();
      } catch (error) {
        this.handleVoteError(error);
      }
    },
    handleVoteError(error) {
      console.error('Error voting:', error);
      if (error.response && error.response.status === 403) {
        alert(error.response.data);
      } else {
        alert('投票失敗，請稍後再試');
      }
    }
  }
}
</script>