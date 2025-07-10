<template>
  <div>
    <h2>Transaction History for Account ID: {{ accountId }}</h2>

    <div v-if="transactions.length === 0" class="alert alert-info">
      No transactions found.
    </div>

    <table v-else class="table table-striped">
      <thead>
      <tr>
        <th>ID</th>
        <th>From</th>
        <th>To</th>
        <th>Amount</th>
        <th>Time</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="tx in transactions" :key="tx.id">
        <td>{{ tx.id }}</td>
        <td>{{ tx.fromAccountId }}</td>
        <td>{{ tx.toAccountId }}</td>
        <td>{{ tx.amount }}</td>
        <td>{{ new Date(tx.timestamp).toLocaleString() }}</td>
      </tr>
      </tbody>
    </table>

    <router-link to="/accounts" class="btn btn-secondary mt-3">← Back to Accounts</router-link>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import api from '../services/api';

export default {
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
