<template>
    <table class="w-full border-collapse bg-white rounded-lg shadow-md overflow-hidden">
      <thead class="bg-gray-100">
        <tr>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">項目名稱</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">票數</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">操作</th>
        </tr>
      </thead>
      <tbody class="divide-y divide-gray-200">
        <tr v-for="item in items" :key="item.id">
          <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{{ item.name }}</td>
          <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{{ item.votes }}</td>
          <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
            <slot name="action" :item="item"></slot>
          </td>
        </tr>
      </tbody>
    </table>
  </template>
  
  <script>
  export default {
    name: 'VotingTable',
    props: {
      items: Array
    }
  }
  </script>