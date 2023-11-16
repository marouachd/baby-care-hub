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
      id: this.route.params.id,
      data: "",
    };
  },
  created() {
    this.$http = axios;
  },
  methods: {
    async getProfile() {
      const response = await this.$http.get(
        `${import.meta.env.VITE_API_BASE_URL}/child/${this.id}/detail`
      );
      this.data = response.data;
      console.log(this.data);
    },

    async remove(id) {
      const resp = await this.$http.delete(
        `${import.meta.env.VITE_API_BASE_URL}/child/${id}`
      );
      if (resp.status === 204) {
        console.log("ok");
      } else {
        console.log("no");
      }
    },
    calculateAge(birthdayDate) {
      const birthDate = new Date(birthdayDate);
      const currentDate = new Date();
      const age = currentDate.getFullYear() - birthDate.getFullYear();
      return age;
    },
  },

  beforeMount() {
    this.getProfile();
    this.id = this.route.params.id;
    console.log("id", this.route.params.id);
  },
};
</script>
<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">Qui suis je ?</h1>

    <div class="d-flex justify-content-center mb-3 mt-5">
      <div class="card col-12 col-md-6 mb-2">
        <div class="card-body">
          <div class="row mb-2">
            <div class="d-flex justify-content-center">
              <p class="title me-3">Je m'appelle :</p>
              <span>{{ data.personId ? data.personId.firstName : "" }}</span>
            </div>
          </div>

          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-center">
              <p class="title me-3">Mon nom de famille c'est :</p>

              <span>{{ data.personId ? data.personId.lastName : "" }}</span>
            </div>
          </div>

          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-center">
              <p class="title me-3">J'ai :</p>

              <span>{{ calculateAge(data.birthdayDate) }} ans</span>
            </div>
          </div>

          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-center">
              <p class="title mt-1 me-2 text-nowrap">Mes parents sont:</p>
              <span class="text-nowrap"
                >Marie et Guillaume
                <RouterLink
                  :to="{ name: 'représentant-légal' }"
                  class="btn mb-2 me-md-3 ms-1"
                  id="button"
                >
                  <i class="fa fa-eye"></i
                ></RouterLink>
              </span>
            </div>
          </div>

          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-center">
              <p class="title me-3">J'habite à :</p>
              <span>Viroflay</span>
            </div>
          </div>
          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-center">
              <p class="title me-3 text-nowrap">Je resterai avec vous :</p>

              <span class="text-nowrap"
                >{{ data.guardId ? data.guardId.name : "" }} &#128512;</span
              >
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="text-center mt-3 mb-5">
      <div class="justify-content-center">
        <RouterLink
          :to="{ name: 'accueil', params: { id: 2 } }"
          class="btn btn-secondary mb-2 ms-md-3 me-3"
          id="button"
          >Retour</RouterLink
        >
        <button class="btn btn-danger mb-2 ms-md-3" @click="remove(id)">
          Supprimer
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");

.title {
  color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  font-size: 18px;
}
h1 {
  font-family: "Pacifico", cursive;
  color: rgba(180, 95, 146, 0.674);
}

.card {
  border-radius: 15px;
}

.card-body {
  padding: 1rem;
}
i {
  color: black;
}
</style>
