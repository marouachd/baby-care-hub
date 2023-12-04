<script>
import Navbar from "../src/components/Pages/Navbar.vue";
import Footer from "../src/Components/Pages/Footer.vue";
import Sidebar from "../src/Components/Commons/Sidebar.vue";
import Toast from "./Components/Commons/Toast.vue";
import i18n from "./plugins/i18n";

export default {
  name: "App",

  components: {
    Navbar,
    Footer,
    Sidebar,
    Toast,
    i18n,
  },
  data() {
    return {
      showSidebar: false,
    };
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
        <Navbar></Navbar>
      </div>
    </header>

    <div v-if="showSidebar">
      <div class="app">
        <Sidebar></Sidebar>

        <RouterView />
      </div>
    </div>
    <div v-else>
      <div class="main-content pb-5">
        <RouterView />
      </div>
      <div><Toast id="toast-global" /></div>
    </div>

    <Footer class="fixed-bottom"></Footer>
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
