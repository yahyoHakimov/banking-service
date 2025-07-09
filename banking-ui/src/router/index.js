import { createRouter, createWebHistory } from 'vue-router';
import AccountList from '../components/AccountList.vue';
import TransferForm from '../components/TransferForm.vue';
import TransactionHistory from '../components/TransactionHistory.vue';

const routes = [
  { path: '/accounts', component: AccountList },
  { path: '/transfer', component: TransferForm },
  { path: '/transactions/:accountId', component: TransactionHistory },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
