<template>
  <form @submit.prevent="submitTransfer">
    <input v-model="from" placeholder="From Account ID" />
    <input v-model="to" placeholder="To Account ID" />
    <input v-model="amount" placeholder="Amount" />
    <button>Transfer</button>
  </form>
</template>

<script>
import api from '../services/api';

export default {
  data() {
    return {
      from: '',
      to: '',
      amount: '',
    };
  },
  methods: {
    submitTransfer() {
      api.post(`/transfer`, null, {
        params: {
          fromId: this.from,
          toId: this.to,
          amount: this.amount,
        },
      }).then(() => alert('Success')).catch(err => alert(err));
    },
  },
};
</script>
