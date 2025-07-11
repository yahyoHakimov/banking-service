import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api/accounts',
});

api.interceptors.request.use((config) => {
  const username = localStorage.getItem('username');
  const password = localStorage.getItem('password');

  if (username && password) {
    config.auth = { username, password };
  }

  return config;
});

export default api;
