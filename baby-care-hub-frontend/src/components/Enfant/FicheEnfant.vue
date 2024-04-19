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
      userId: "",
    };
  },
  created() {
    this.$http = axios;
    console.log("role", this.roleId);
  },
  beforeUpdate() {
    this.roleId = localStorage.getItem("roleId");
    this.userId = localStorage.getItem("userId");
    console.log(this.userId, "userId");
  },
  methods: {
    async getChildProfile() {
      const response = await this.$axios.get(`/child/${this.id}/detail`);
      this.data = response.body;
      console.log(this.data);
      return this.data;
    },
    async suppress(id) {
      const resp = await this.$axios.delete(`/child/${id}`);
      if (resp.status === 204) {
        this.$toast.success("toast-global", "Le profile a été supprimé.");
        this.$router.push({
          name: "mes-enfants",
          params: { id: this.userId },
        });
      } else {
        this.$toast.error("toast-global", "Un problème est survenu.");
      }
    },

    async desactive(id) {
      const childToGuard = await this.getChildProfile();
      if (childToGuard.accepted && childToGuard.active) {
        console.log(childToGuard, "childToGuard");
        const refuseGuard = await this.$axios.post(`/child/desactive/${id}`);
        if (refuseGuard) {
          this.$toast.success(
            "toast-global",
            "Vous avez choisi de ne plus garder cet enfant."
          );
        }
      } else if (!childToGuard.accept && childToGuard.active) {
        const desactivateGuard = await this.$axios.post(
          `/child/refuse-guard/${this.id}`
        );
        if (desactivateGuard) {
          this.$toast.success(
            "toast-global",
            "Vous avez refuser la garde de cet enfant."
          );
        }
      }
      this.$router.push({
        name: "home",
        params: { id: this.userId },
      });
    },

    async accepte(id) {
      const acceptGuard = await this.$axios.patch(`/child/accepte/${id}`);
      if (acceptGuard.status === 200) {
        this.$toast.success(
          "toast-global",
          "Vous avez accepté de garder cet enfant."
        );
        this.$router.push({
          name: "home",
          params: { id: this.userId },
        });
      } else {
        this.$toast.error("toast-global", "Un problème est survenu.");
      }
    },
    calculateAge(birthdayDate) {
      const birthDate = new Date(birthdayDate);
      const currentDate = new Date();
      const age = currentDate.getFullYear() - birthDate.getFullYear();
      return age;
    },
  },

  mounted() {
    this.getChildProfile();
    //this.id = this.route.params.id;
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
                >{{ data.personId ? data.parentId.personId.firstName : "" }} et
                ...
                <RouterLink
                  :to="{ name: 'représentant-légal', params: { id: this.id } }"
                  class="btn mb-2 me-md-3 ms-1"
                  id="button"
                >
                  <i class="fa fa-eye" v-if="this.roleId == 1"></i
                ></RouterLink>
              </span>
            </div>
          </div>

          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-center">
              <p class="title me-3">J'habite à :</p>
              <span>...</span>
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
        <!--<RouterLink
          :to="{ name: 'accueil', params: { id: 2 } }"
          class="btn btn-secondary mb-2 ms-md-3 me-3"
          id="button"
          >Retour</RouterLink
        >-->
        <button
          class="btn btn-quitter mb-2 ms-md-3"
          @click="desactive(id)"
          v-if="this.roleId == 1"
        >
          Annuler la garde
        </button>
        <button
          class="btn btn-confirmer mb-2 ms-md-3"
          @click="accepte(id)"
          v-if="this.roleId == 1"
          :class="{ disabled: data.accepted }"
        >
          Confirmer la garder
        </button>
        <button
          class="btn btn-quitter mb-2 ms-md-3"
          @click="suppress(id)"
          v-if="this.roleId == 2"
        >
          Supprimer ce profile
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
.btn-quitter {
  background-color: rgba(180, 95, 146, 0.674);
  font-family: "Pacifico", cursive;
  color: white;
}
.btn-confirmer {
  background-color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  color: white;
}
.disabled {
  opacity: 0.5;
  pointer-events: none;
}
</style>
