<script>
import { RouterLink, useRoute } from "vue-router";
import axios from "axios";
export default {
  setup() {
    return {
      route: useRoute(),
    };
  },
  data() {
    return {
      userId: "",
      childminderCode: null,
      id: "",
      showMenu: false,
      childs: [],
    };
  },
  created() {
    this.$http = axios;
    this.userId = localStorage.getItem("userId");
  },
  methods: {
    async getUser() {
      const resp = await this.$http.get(
        `${import.meta.env.VITE_API_BASE_URL}/user/${this.id}/detail`,
        console.log("user", resp)
      );
    },
    async getChilds() {
      const response = await this.$http.get(
        `${import.meta.env.VITE_API_BASE_URL}/child/parent/${this.id}`
      );
      this.childs = response.data;
      console.log("child", this.childs);
    },
    AjouterEnfant() {
      console.log("Méthode AjouterEnfant appelée");
      if (this.childs.length !== 0) {
        console.log("childmindercode", this.childminderCode);
        if (this.childminderCode === null) {
          this.$router.push({
            name: "id-nounou",
            params: { id: this.id },
          });
        } else {
          this.$router.push({
            name: "create-profile-enfant",
            params: { id: this.userId },
          });
        }
      } else {
        this.$router.push({
          name: "id-nounou",
          params: { id: this.userId },
        });
        if (this.childminderCode !== null) {
          this.$router.push({
            name: "create-profile-enfant",
            params: { id: this.id },
          });
        }
      }
    },
    getChildImage(child) {
      if (child.personId.identityPhotoName) {
        return "/personal-pictures/" + child.personId.identityPhotoName;
      } else {
        return "/personal-pictures/placeholder-avatar.jpg";
      }
    },
  },
  beforeMount() {
    this.id = localStorage.getItem("userId");
    console.log("id", this.id);
    this.getChilds();
    this.childminderCode = localStorage.getItem("childminderCode");
  },
};
</script>
<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">Mes enfants</h1>

    <div class="row d-flex justify-content-center mb-3 mt-5 mx-2">
      <div
        class="col-md-6 col-6 border rounded pt-1 pb-1 clickable"
        v-on:click="AjouterEnfant()"
      >
        <div class="d-flex justify-content-center">
          <img
            src="https://mdbootstrap.com/img/Photos/Others/placeholder-avatar.jpg"
            class="img-fluid rounded-circle"
            alt="..."
            width="50"
            height="50"
          />

          <h5 class="title mt-3 ms-2 me-5 text-nowrap">Ajouter un enfant</h5>

          <button class="mb-2 ms-md-3 mt-2">
            <i class="fa fa-plus"></i>
          </button>
        </div>
      </div>
    </div>

    <div
      class="row d-flex justify-content-center mb-3 mt-5 mx-5"
      v-for="child in childs"
    >
      <div class="col-md-6 col-12 mb-2 mb-2 align-items-center text-center">
        <div class="d-flex justify-content-center align-items-center">
          <div class="col d-flex justify-content-center mx-5">
            <img
              :src="getChildImage(child)"
              class="img-fluid rounded-circle w-100"
              alt="..."
            />
          </div>

          <div
            class="col d-flex flex-column mx-5 text-center align-items-center"
          >
            <h5 class="title mt-3">
              <span>{{ child.personId.firstName }}</span
              >&ensp;
              <span>{{ child.personId.lastName }}</span>
            </h5>
          </div>

          <div class="col d-flex justify-content-center mx-5">
            <div
              id="navbar"
              class="col d-flex justify-content-center collapse navbar-collapse"
            >
              <ul class="navbar-nav">
                <li class="nav-item dropdown">
                  <a
                    class="nav-link dropdown-toggle-no-arrow"
                    href="#"
                    role="button"
                    data-bs-toggle="dropdown"
                    style="color: rgb(129, 126, 126); font-size: 24px"
                  >
                    ...
                  </a>
                  <ul class="dropdown-menu">
                    <li>
                      <RouterLink
                        :to="{
                          name: 'edit-profile-enfant',
                          params: { id: child.id },
                        }"
                        class="dropdown-item"
                      >
                        <i class="fa fa-pencil fa-fw mr-2"></i>
                        Edit</RouterLink
                      >
                    </li>
                    <li>
                      <RouterLink
                        :to="{
                          name: 'fiche-enfant',
                          params: { id: child.id },
                        }"
                        class="dropdown-item"
                      >
                        <i class="fa-regular fa-user"></i>
                        &ensp;Détails</RouterLink
                      >
                    </li>
                    <li>
                      <RouterLink
                        :to="{
                          name: 'actualitees',
                          params: { id: child.id },
                        }"
                        class="dropdown-item"
                      >
                        <i class="fa-regular fa-newspaper"></i
                        >&ensp;Actualités</RouterLink
                      >
                    </li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
h1 {
  font-family: "Pacifico", cursive;
  color: rgba(180, 95, 146, 0.674);
}
@media (max-width: 768px) {
  .border.rounded {
    width: 100%;
    box-sizing: border-box;
  }
}
.clickable {
  cursor: pointer;
}

h5 {
  font-family: "Pacifico", cursive;
  color: rgb(129, 126, 126);
}
button {
  background-color: white;
  color: rgb(129, 126, 126);
  border: none;
}
</style>
