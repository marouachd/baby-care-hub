import { createApp } from "vue";
import { createPinia } from "pinia";
import App from "./App.vue";
import router from "./router";
import axios from "./plugins/axios";
import toast from "./plugins/toast";
import i18n from "./plugins/i18n";

const app = createApp(App);

const pinia = createPinia();
console.log("pinia", pinia);
app.use(pinia);
app.use(toast);
app.use(router);
app.use(axios);
app.use(i18n);
app.mount("#app");
