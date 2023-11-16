<script>
import { useRoute, RouterLink } from "vue-router";
import { useVuelidate } from "@vuelidate/core";
import { required, email } from "@vuelidate/validators";
import axios from "axios";
import router from "../../router";

export default {
  setup() {
    return { v$: useVuelidate(), route: useRoute() };
  },
  data() {
    return {
      inputs: {
        roleId: "",
        userId: "",
        mailAdress: null,
        password: null,
      },
    };
  },

  validations() {
    return {
      inputs: {
        mailAdress: { required, email },
        password: { required },
      },
    };
  },
  created() {
    this.$http = axios;
  },

  methods: {
    async submitForm() {
      const result = await this.v$.$validate();
      if (result) {
        try {
          const resp = await this.$http.post(
            `${import.meta.env.VITE_API_BASE_URL}/sign-in`,
            this.inputs
          );
          this.userId = resp.data.userId;
          this.roleId = resp.data.roleId;
          console.log("data", resp.data.userId);
          console.log("token", resp.data.token);
          localStorage.setItem("token", resp.data.token);

          if (resp.status === 200) {
            if (this.roleId == 2) {
              router.push({
                name: "mes-enfants",
                params: { id: this.userId },
              });
            } else {
              router.push({
                name: "acceuil",
                params: { id: this.userId.toString() },
              });
            }
          }
        } catch (error) {
          console.error(
            "Une erreur s'est produite lors de la soumission du formulaire:",
            error
          );
        }
      }
    },
  },
};
</script>
<template>
  <section class="container-xl text-center mb-5">
    <h1 class="mt-4 mb-5">Connectez vous &#128512;</h1>
    <div class="row justify-content-center mb-5">
      <div class="col-md-6 col-12 mb-5">
        <form class="my-4 mb-5" @submit.prevent="submitForm">
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1"
              >Adresse email:</span
            >
            <input
              type="text"
              class="form-control"
              placeholder="Adresse email"
              v-model="inputs.mailAdress"
              :class="{ 'is-invalid': v$.inputs.mailAdress.$error }"
            />
          </div>
          <div class="input-group mb-2">
            <span class="input-group-text" id="basic-addon1"
              >Mot de Passe:</span
            >
            <input
              type="text"
              class="form-control"
              placeholder="*********"
              v-model="inputs.password"
              :class="{ 'is-invalid': v$.inputs.password.$error }"
            />
          </div>
          <div class="d-flex justify-content-between mb-5">
            <div>
              <RouterLink
                :to="{ name: 'create-user-account' }"
                class="link mx-2"
                id="button"
                >Inscrivez-vous</RouterLink
              >
            </div>
            <div>
              <RouterLink
                :to="{ name: 'forgot-password', params: { id: this.userId } }"
                class="link mx-2"
                >Mot de passe oublié ?
              </RouterLink>
            </div>
          </div>
          <div class="d-flex justify-content-center mt-5 mb-5">
            <button type="submit" class="btn col-md-3 col-12 mb-5 mx-2">
              Se connecter
            </button>
          </div>
        </form>
      </div>
    </div>
  </section>
</template>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");

h1 {
  font-family: "Pacifico", cursive;
  color: rgba(180, 95, 146, 0.674);
}
.link {
  color: rgba(180, 95, 146, 0.674);
}
</style>
