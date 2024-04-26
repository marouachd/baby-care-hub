<script>
import Navbar from "../src/components/Pages/Navbar.vue";
import Footer from "../src/Components/Pages/Footer.vue";
import Sidebar from "../src/Components/Commons/Sidebar.vue";
import Toast from "./Components/Commons/Toast.vue";
import i18n from "./plugins/i18n";
import { RouterLink, useRoute } from "vue-router";
import { mapWritableState } from "pinia";
import { mapState } from "pinia";
import useStore from "../store/store.js";

export default {
  name: "App",
  setup() {
    return {
      route: useRoute(),
      actualitiesData: null,
    };
  },

  components: {
    Navbar,
    Footer,
    Sidebar,
    Toast,
    i18n,
  },
  data() {
    return {
      id: this.route.params.id,
      showSidebar: false,
      actualitiesData: null,
    };
  },

  methods: {
    async fetchActualities(date) {
      const response = await this.$axios.get(
        `/actualities/${date}/${this.childId}`
      );
      this.actualitiesData = response.body;
      console.log(this.actualitiesData, "actualities");
      this.actualities = this.actualitiesData;
      console.log("this app", this.actualities);
    },
  },
  computed: {
    ...mapWritableState(useStore, ["actualities"]),
    ...mapState(useStore, ["childId"]),
  },

  watch: {
    $route(to) {
      this.showSidebar = to.path.startsWith("/ma-journee");
    },
  },
};
</script>

<template>
  <div class="global">
    <header>
      <div class="wrapper">
        <Navbar @date-changed="fetchActualities"></Navbar>
      </div>
    </header>

    <div v-if="showSidebar">
      <div class="app">
        <Sidebar></Sidebar>
        <div><Toast id="toast-global" /></div>
        <RouterView />
      </div>
    </div>
    <div v-else>
      <div class="main-content pb-5">
        <RouterView />
      </div>
      <div><Toast id="toast-global" /></div>
    </div>

    <Footer></Footer>
    <!--<Footer class="fixed-bottom"></Footer>-->
  </div>
</template>

<style>
.app {
  display: flex;
}
.app main {
  flex: 1 1 0;
  padding: 2rem;
  @media (max-width: 1024px) {
    padding-left: 6rem;
  }
}
.global {
  height: 100%;
}
</style>
