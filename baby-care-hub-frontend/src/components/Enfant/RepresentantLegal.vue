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
    async getChildProfile() {
      const response = await this.$http.get(
        `${import.meta.env.VITE_API_BASE_URL}/child/${this.id}/detail`
      );
      this.data = response.data;
      console.log("child profile", this.data);
    },
  },
  mounted() {
    this.getChildProfile();
  },
};
</script>
<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">Qui sont mes parents ?</h1>

    <div
      class="d-flex justify-content-center mb-3 mt-5"
      v-if="this.data && this.data.parentId && this.data.parentId.personId"
    >
      <div class="card col-12 col-md-6 mb-2">
        <div class="card-body">
          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Ma mère s'appelle :</p>
              <span>{{ data.parentId.personId.firstName }}</span>
            </div>
          </div>

          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Mon père s'appelle :</p>
              <span>...</span>
            </div>
          </div>
          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Notre nom de famille est :</p>
              <span>{{ data.parentId.personId.lastName }}</span>
            </div>
          </div>

          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Vous pouvez appeler ma mère au :</p>
              <span>{{ data.parentId.phoneNumber }}</span>
            </div>
          </div>

          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Ou lui envoyer un email sur :</p>
              <span>{{ data.parentId.mailAdress }}</span>
            </div>
          </div>

          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Vous pouvez appeler mon père au :</p>
              <span>...</span>
            </div>
          </div>

          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Ou lui envoyer un email sur :</p>
              <span>...</span>
            </div>
          </div>
          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Nous habitons à :</p>
              <span>...</span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="text-center mt-3 mb-5">
      <div class="justify-content-center">
        <!--<RouterLink
          :to="{ name: 'fiche-enfant', params: { id: 2 } }"
          class="btn btn-secondary mb-2 ms-md-3 me-3"
          id="button"
          >Retour</RouterLink
        >-->
      </div>
    </div>
  </div>
</template>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");
h1 {
  font-family: "Pacifico", cursive;
  color: rgba(180, 95, 146, 0.674);
}
.title {
  color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  font-size: 18px;
}
</style>
