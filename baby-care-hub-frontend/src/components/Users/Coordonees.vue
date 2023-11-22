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
      data: {},
    };
  },
  created() {
    this.$http = axios;
  },
  methods: {
    async getUserProfile() {
      const response = await this.$http.get(
        `${import.meta.env.VITE_API_BASE_URL}/user/${this.id}/detail`
      );
      console.log("rep", response);
      this.data = response.data;
      const identifier = response.data.id;
      console.log("id", identifier, this.data.id);
    },
  },
  mounted() {
    this.getUserProfile();
    this.id = this.route.params.id;
  },
};
</script>
<template>
  <section class="container-xl text-center mt-5">
    <h1 class="mt-3 mb-5">Vos Coordonnées</h1>
    <div class="d-flex justify-content-center">
      <div class="card mb-3 mt-5 w-">
        <div class="row g-0">
          <div class="col-md-4">
            <img
              v-if="this.data.personId && this.data.personId.identityPhotoName"
              :src="'/personal-pictures/' + data.personId.identityPhotoName"
              class="w-50"
            />
          </div>
          <div class="col-md-8">
            <div class="card-body d-flex justify-content-start">
              <div class="container text-start">
                <div class="row mb-3">
                  <div class="col">
                    <h4 class="card-title">Nom et prénom :</h4>
                  </div>
                  <div class="col mt-2" v-if="this.data.personId">
                    <h6>
                      {{ this.data.personId.firstName }}&nbsp;{{
                        this.data.personId.lastName
                      }}
                    </h6>
                  </div>

                  <RouterLink
                    :to="{
                      name: 'edit-user-account',
                      params: { id: this.id },
                    }"
                    class="dropdown-item"
                  >
                    <i
                      class="btn fa-solid fa-pen position-absolute top-0 end-0 m-2"
                    ></i>
                  </RouterLink>
                </div>

                <div class="row mb-3">
                  <div class="col">
                    <h4 class="card-title">Adresse email :</h4>
                  </div>
                  <div class="col mt-2" v-if="this.data.mailAdress">
                    <h6>
                      {{ this.data.mailAdress }}
                    </h6>
                  </div>
                </div>

                <div class="row mb-3">
                  <div class="col">
                    <h4 class="card-title">Numéro de téléphone:</h4>
                  </div>
                  <div class="col mt-2" v-if="this.data.phoneNumber">
                    <h6>
                      {{ this.data.phoneNumber }}
                    </h6>
                  </div>
                </div>

                <div class="row mb-3">
                  <div class="col">
                    <h4 class="card-title">Vous êtes :</h4>
                  </div>
                  <div class="col mt-2" v-if="this.data.roleId">
                    <h6>
                      {{ this.data.roleId.name }}
                    </h6>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style>
h1 {
  font-family: "Pacifico", cursive;
  color: rgba(180, 95, 146, 0.674);
}
h6 {
  font-family: "Pacifico", cursive;
}
h4 {
  font-family: "Pacifico", cursive;
  color: rgb(166, 161, 161);
}
.btn {
  color: white;
  background-color: rgb(160, 197, 237);
}
.card-title {
  white-space: nowrap;
}

.mt-2 {
  white-space: nowrap;
}
</style>
