<template>
  <div class="d-flex justify-content-center align-items-center vh-100 bg-light">
    <div class="card p-4 shadow-sm" style="width: 350px;">
      <h3 class="text-center mb-3">🔐 Login</h3>
      <form @submit.prevent="login">
        <div class="mb-3">
          <input class="form-control" v-model="username" placeholder="Username" required />
        </div>
        <div class="mb-3">
          <input class="form-control" type="password" v-model="password" placeholder="Password" required />
        </div>
        <button class="btn btn-primary w-100">Login</button>
      </form>
      <div v-if="error" class="alert alert-danger mt-3">{{ error }}</div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const username = ref('');
    const password = ref('');
    const error = ref('');
    const router = useRouter();

    const login = async () => {
      try {
        const testApi = axios.create({
          baseURL: 'http://localhost:8080/api/accounts',
          auth: {
            username: username.value,
            password: password.value,
          },
        });

        // Try a public + protected API to test credentials
        await testApi.get('/transactions/1');

        // Save credentials
        localStorage.setItem('username', username.value);
        localStorage.setItem('password', password.value);

        router.push('/accounts');
      } catch (err) {
        error.value = 'Invalid username or password.';
      }
    };

    return { username, password, login, error };
  },
};
</script>
