<script>
import Navbar from "../src/components/Pages/Navbar.vue";
import Footer from "../src/Components/Pages/Footer.vue";
import Sidebar from "../src/Components/Commons/Sidebar.vue";

export default {
  name: "App",

  components: {
    Navbar,
    Footer,
    Sidebar,
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
    <div>
      <RouterView />
    </div>
  </div>

  <Footer></Footer>
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
</style>
