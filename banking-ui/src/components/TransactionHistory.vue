<template>
  <div>
    <h2>Transaction History for Account {{ accountId }}</h2>
    <ul>
      <li v-for="tx in transactions" :key="tx.id">
        From: {{ tx.fromAccountId }} → To: {{ tx.toAccountId }} |
        Amount: {{ tx.amount }} |
        Time: {{ tx.timestamp }}
      </li>
    </ul>
  </div>
</template>

<script>
import api from '../services/api';
import { useRoute } from 'vue-router';

export default {
  name: 'TransactionHistory',
  setup() {
    const route = useRoute();
    const accountId = route.params.accountId;
    const transactions = ref([]);

    onMounted(() => {
      api.get(`/transactions/${accountId}`).then((res) => {
        transactions.value = res.data;
      });
    });

    return { accountId, transactions };
  },
};
</script>
