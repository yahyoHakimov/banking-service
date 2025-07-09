<template>
  <div>
    <h2>Accounts</h2>
    <ul>
      <li v-for="acc in accounts" :key="acc.id">
        {{ acc.ownerName }} - {{ acc.balance }} {{ acc.currency }}
      </li>
    </ul>
  </div>
</template>

<script>
import api from '../services/api';

export default {
  name: 'AccountList',
  data() {
    return {
      accounts: [],
    };
  },
  mounted() {
    api.get('/').then(res => {
      this.accounts = res.data;
    });
  },
};
</script>
