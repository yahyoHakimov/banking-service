import { createRouter, createWebHistory } from 'vue-router';
import AccountList from '../components/AccountList.vue';
import TransferForm from '../components/TransferForm.vue';
import TransactionHistory from '../components/TransactionHistory.vue';
import LoginView from "@/views/LoginView.vue";

const routes = [
  { path: '/', redirect: '/accounts' },
  { path: '/login', component: LoginView },
  { path: '/accounts', component: AccountList },
  { path: '/transfer', component: TransferForm },
  { path: '/transactions/:accountId', component: TransactionHistory },
];

export default createRouter({
  history: createWebHistory(),
  routes,
});
