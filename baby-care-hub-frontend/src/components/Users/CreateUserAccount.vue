<script>
import axios from "axios";
export default {
  data() {
    return {
      userType: "",
      roles: [],
    };
  },
  created() {
    this.$http = axios;
  },
  methods: {
    ChangerPath() {
      console.log("userType", this.userType);
      if (this.userType === 2) {
        this.$router.push("/id-nounou");
      } else if (this.userType === 1) {
        this.$router.push("/accueil");
      } else {
        alert("Veuillez choisir un rôle valide !");
      }
    },
    async initRoles() {
      const response = await this.$http.get(
        `${import.meta.env.VITE_API_BASE_URL}/role`
      );
      this.roles = response.data;
    },
  },
  watch: {
    "roles[0].id": function (newRoleId) {
      if (newRoleId === 1) {
        this.userType = "assistante";
      } else if (newRoleId === 2) {
        this.userType = "parent";
      } else {
        this.userType = "";
      }
    },
  },
  mounted() {
    this.initRoles();
  },
};
</script>

<template>
  <section class="container-xl text-center mb-4">
    <h1 class="mt-4 mb-5">Créer un compte utilisateur</h1>

    <form class="my-4" @submit.prevent="ChangerPath()">
      <div class="container">
        <div class="row g-2 g-lg-3">
          <div class="col-md-6 col-12">
            <div class="text-center">
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1"
                  >Nom de famille :</span
                >
                <input
                  type="text"
                  class="form-control"
                  placeholder="Nom de famille"
                />
              </div>
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">Prénom :</span>
                <input type="text" class="form-control" placeholder="Prénom" />
              </div>
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1"
                  >Numéro de télephone :</span
                >
                <input
                  type="text"
                  class="form-control"
                  placeholder="téléphone"
                />
              </div>
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1"
                  >Adresse email:</span
                >
                <input
                  type="text"
                  class="form-control"
                  placeholder="Adresse email"
                />
              </div>
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1"
                  >Mot de passe:</span
                >
                <input type="text" class="form-control" placeholder="******" />
              </div>
            </div>
          </div>

          <div class="col-md-6 col-12">
            <div class="col-md-6 input-group mb-3">
              <span class="input-group-text" id="basic-addon1"
                >Rejoindre cette application en tant que :</span
              >
              <select
                class="form-select"
                aria-label="Default select example"
                v-model="userType"
              >
                <option selected>--------</option>
                <option v-for="role in roles" :value="role.id">
                  {{ role.name }}
                </option>
              </select>
            </div>
            <div v-if="userType === 1">
              <div class="input-group mb-3">
                <h5 class="mb-3">Entrez votre identifiant :</h5>
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon1">ID :</span>
                  <input
                    type="text"
                    class="form-control"
                    placeholder="MAS241278"
                  />
                </div>
              </div>
            </div>
            <div v-if="userType === 2">
              <div class="input-group mb-3">
                <h5 class="mb-3">Coordonnées deuxième parent :</h5>
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon1"
                    >Prénom :</span
                  >
                  <input
                    type="text"
                    class="form-control"
                    placeholder="Prénom"
                  />
                </div>
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon1"
                    >Numéro de télephone :</span
                  >
                  <input
                    type="text"
                    class="form-control"
                    placeholder="téléphone"
                  />
                </div>
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon1"
                    >Adresse email:</span
                  >
                  <input
                    type="text"
                    class="form-control"
                    placeholder="Adresse email"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="col-md-6 input-group mb-3">
          <input type="file" class="form-control" />
        </div>
        <div class="col-12 input-group mb-3">
          <span class="input-group-text" id="basic-addon1">Adresse :</span>
          <input type="text" class="form-control" placeholder="Adresse" />
        </div>
      </div>

      <div class="d-flex justify-content-center mt-3 mb-5 mx-2">
        <button
          type="submit"
          class="btn col-md-3 col-12 mb-4"
          @click="ChangerPath()"
        >
          Confirmer
        </button>
      </div>
    </form>
  </section>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");
h5 {
  font-family: "Pacifico", cursive;
  color: rgb(166, 161, 161);
}
</style>
