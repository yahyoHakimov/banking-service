<template>
  <nav class="navbar navbar-expand navbar-light bg-light mb-4">
    <div class="container">
      <router-link class="navbar-brand" to="/">🏦 BankApp</router-link>
      <div class="navbar-nav">
        <router-link class="nav-link" to="/accounts">Accounts</router-link>
        <router-link class="nav-link" to="/transfer">Transfer</router-link>
      </div>
      <button class="btn btn-outline-danger btn-sm ms-auto" @click="logout">Logout</button>
    </div>
  </nav>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

const logout = () => {
  localStorage.removeItem('username');
  localStorage.removeItem('password');
  router.push('/login');
};
</script>
