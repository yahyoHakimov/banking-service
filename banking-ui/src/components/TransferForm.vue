<template>
  <div>
    <h2>Transfer Funds</h2>
    <form @submit.prevent="transfer">
      <div class="mb-3">
        <label>From Account ID</label>
        <input class="form-control" v-model="fromId" />
      </div>
      <div class="mb-3">
        <label>To Account ID</label>
        <input class="form-control" v-model="toId" />
      </div>
      <div class="mb-3">
        <label>Amount</label>
        <input class="form-control" type="number" v-model="amount" />
      </div>
      <button class="btn btn-primary">Submit Transfer</button>
    </form>
    <p v-if="message" class="mt-3 alert alert-success">{{ message }}</p>
  </div>
</template>

<script>
import api from '../services/api';
import { ref } from 'vue';

export default {
  setup() {
    const fromId = ref('');
    const toId = ref('');
    const amount = ref('');
    const message = ref('');

    const transfer = async () => {
      try {
        await api.post('/transfer', null, {
          params: {
            fromId: fromId.value,
            toId: toId.value,
            amount: amount.value,
          },
        });
        message.value = 'Transfer successful!';
      } catch (err) {
        message.value = 'Transfer failed!';
      }
    };

    return { fromId, toId, amount, message, transfer };
  },
};
</script>
