import axios from "axios";

const ACCEPTED_STATUS = [200, 201, 202, 204, 400];

export default {
  install: (app) => {
    // Création et configuration d'une instance axios
    const axiosInstance = axios.create({
      baseURL: import.meta.env.VITE_API_BASE_URL,
      validateStatus: (status) => {
        return ACCEPTED_STATUS.includes(status);
      },
    });
    axiosInstance.interceptors.response.use(
      (response) => {
        const status = response.status;
        const data = response.data;
        const body = data != "" ? data : null;
        if (response.data.token) {
          const token = data.token;
          console.log("Le Token !!", token);
          console.log("1");
          //localStorage.clear();
          localStorage.setItem("token", token);
          localStorage.setItem("isAuthenticated", true);
          //  const role = data.role;
          //  const userName = data.firstName;
          // localStorage.setItem("role", role);
          // localStorage.setItem("userName", userName);
        }
        return { status: status, body: body };
      },
      (error) => {
        return Promise.reject(error);
      }
    );
    axiosInstance.interceptors.request.use(
      (config) => {
        console.log("2");
        const token = localStorage.getItem("token");
        const bearerToken = token ? `Bearer ${token}` : null;
        config.headers["Authorization"] = bearerToken;
        return config;
      },
      (error) => {
        return Promise.reject(error);
      }
    );
    // Utilasation des global properties pour accéder à axios avec sa configuration dans toute l'app
    // Via $axios
    app.config.globalProperties.$axios = axiosInstance;
  },
};
