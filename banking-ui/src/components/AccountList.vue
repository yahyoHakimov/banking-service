<template>
  <div>
    <h2>Accounts</h2>
    <div class="card" v-for="acc in accounts" :key="acc.id" style="margin-bottom: 1rem;">
      <div class="card-body">
        <h5 class="card-title">{{ acc.ownerName }}</h5>
        <p class="card-text">
          💰 Balance: {{ acc.balance }} {{ acc.currency }}
        </p>
        <router-link :to="`/transactions/${acc.id}`" class="btn btn-sm btn-outline-primary">
          View Transactions
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../services/api';
import {ref, onMounted, watch} from 'vue';
import {useRoute} from "vue-router";

export default {
  setup() {
    const accounts = ref([]);

    // that helps me to on mount, I mean i don't need to refresh manually
    const route = useRoute();

    const fetchAccounts = () => {
      api.get().then((res) => (accounts.value = res.data));
    };

    onMounted(fetchAccounts);

    watch(
      () => route.fullPath,
      () => {
        fetchAccounts();
      }
    );


    console.log(accounts)

    return { accounts };
  },
};
</script>
