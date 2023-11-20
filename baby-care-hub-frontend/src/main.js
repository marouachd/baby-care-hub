import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "./plugins/axios";
import toast from "./plugins/toast";

const app = createApp(App);
app.use(toast);
app.use(router);
app.use(axios);
app.mount("#app");
