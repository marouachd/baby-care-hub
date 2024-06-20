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
    this.userId = localStorage.getItem("userId");
  },
  methods: {
    async getUser() {
      const resp = await this.$axios.get(`/user/${this.id}/detail`);
    },
    async getChilds() {
      const response = await this.$axios.get(`/child/parent/${this.id}`);
      this.childs = response.body;
      console.log(this.childs, "mes enfants");
    },
    AjouterEnfant() {
      this.$router.push({
        name: "id-nounou",
        params: { id: this.id },
      });
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
    this.getChilds();
    this.childminderCode = localStorage.getItem("childminderCode");
  },
};
</script>
<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">{{ $t("myChildren.title") }}</h1>

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

          <h5 class="title mt-3 ms-2 me-5 text-nowrap">
            {{ $t("myChildren.buttons") }}
          </h5>

          <button class="mb-2 ms-md-3 mt-2">
            <i class="fa fa-plus"></i>
          </button>
        </div>
      </div>
    </div>

    <div
      class="row d-flex justify-content-center mb-3 mt-5 mx-2"
      v-for="child in childs"
    >
      <div
        class="col-md-6 col-12 mb-2 mb-2 align-items-center text-center text-nowrap"
        :class="{ childDetail: !child.active, childRow: child.active }"
      >
        <div class="d-flex justify-content-center align-items-center">
          <div class="col-3 d-flex justify-content-center">
            <img
              :src="getChildImage(child)"
              class="img-fluid rounded-circle w-50"
              alt="..."
            />
          </div>

          <div class="col-6 d-flex flex-column text-center align-items-center">
            <h5 class="title mt-3">
              <span>{{ child.personId.firstName }}</span
              >&ensp;
              <span>{{ child.personId.lastName }}</span>
            </h5>
          </div>

          <div class="col-3 d-flex justify-content-center">
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
                    <li
                      :class="{
                        disabledChangeChildminder: child.childminderCode,
                      }"
                    >
                      <RouterLink
                        :to="{
                          name: 'id-nounou',
                          params: { id: child.id },
                        }"
                        class="dropdown-item"
                      >
                        <i
                          class="fa fa-exchange"
                          aria-hidden="true"
                          style="color: red"
                        ></i
                        >&ensp;Changer la nounou</RouterLink
                      >
                    </li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div
        class="col-9 d-flex flex-column text-center align-items-center"
        v-if="!child.active && child.accepted"
      >
        <span class="ms-3 col-12 text-center align-items-center">
          Votre enfant n'est plus gardé par sa nounou son compte est donc
          désactiver pour le réactiver
          <RouterLink
            class=""
            :to="{ name: 'id-nounou', params: { id: child.id } }"
            >cliquer ici!</RouterLink
          >
        </span>
      </div>
      <div
        class="text-danger d-flex row justify-content-center align-items-center"
        :class="{ disabled: !child.accepted }"
        v-if="!child.accepted && child.active"
      >
        <div
          class="col-md-9 col-12 d-flex flex-column text-center align-items-center"
        >
          <span
            class="col-md-6 col-12 text-center align-items-center"
            v-if="
              child && child.childminderCode && child.childminderCode.personId
            "
          >
            Votre enfant sera bientôt pris en charge par
            {{ child.childminderCode.personId.firstName }}
            {{ child.childminderCode.personId.lastName }}.</span
          >
          <span class="col-md-6 col-12 text-center align-items-center">
            Veuillez patienter pendant que la nounou valide la garde et accepte
            votre enfant dans sa liste. 🤗
          </span>
        </div>
      </div>
      <div
        class="form-text text-danger row d-flex justify-content-center"
        v-if="!child.accepted && !child.active"
      >
        <span class="col-9 ms-5">
          La nounou n'a pas accepté de garder votre enfant, veuillez contacter
          une autre nounou disponible ! Chercher dans la liste &nbsp;
          <RouterLink
            class=""
            :to="{ name: 'id-nounou', params: { id: child.id } }"
            >Liste des nounous!</RouterLink
          >
        </span>
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
.childDetail {
  border: 2px solid red;
  border-radius: 10px;
  opacity: 0.3;
}
.childRow {
  border: 0.5px solid rgb(183, 176, 176);
  border-radius: 10px;
}
.disabled {
  pointer-events: none;
}
.disabledChangeChildminder {
  opacity: 0.5;
  pointer-events: none;
}
</style>
