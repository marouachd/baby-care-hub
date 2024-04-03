<script>
import { useRoute, RouterLink } from "vue-router";
import { useVuelidate } from "@vuelidate/core";
import { required, email } from "@vuelidate/validators";
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

  methods: {
    async submitForm() {
      const result = await this.v$.$validate();
      if (result) {
        try {
          console.log("inputs", this.inputs);
          const resp = await this.$axios.post(`/sign-in`, this.inputs);
          console.log("resp", resp);
          if (resp.status === 200) {
            this.userId = resp.body.userId;
            this.roleId = resp.body.roleId;
            //localStorage.setItem("token", resp.body.token);
            localStorage.setItem("userId", this.userId);
            localStorage.setItem("roleId", this.roleId);
            if (this.roleId == 2) {
              router.push({
                name: "mes-enfants",
                params: { id: this.userId },
              });
              console.log("mes enfants", this.userId);
            } else {
              router.push({
                name: "home",
                params: { id: this.userId },
              });
              console.log("acceuil", this.userId.toString());
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
    <h1 class="mt-4 mt-5">{{ $t("signin.title") }} &#128512;</h1>
    <div class="row justify-content-center mb-5">
      <div class="col-md-6 col-12 mb-5">
        <form class="my-4 mb-5" @submit.prevent="submitForm" novalidate>
          <div :class="{ 'mb-3': v$.inputs.mailAdress.$error }">
            <div class="input-group">
              <span class="input-group-text" id="basic-addon1"
                >{{ $t("signin.email") }}:</span
              >
              <input
                id="email"
                type="text"
                class="form-control"
                placeholder="john.dupont@email.com"
                v-model="inputs.mailAdress"
                :class="{ 'is-invalid': v$.inputs.mailAdress.$error }"
              />
            </div>

            <span class="text-danger" v-if="v$.inputs.mailAdress.$error">
              Adresse mail invalide.
            </span>
          </div>
          <div class="mt-3">
            <div class="input-group">
              <span class="input-group-text" id="basic-addon1"
                >{{ $t("signin.password") }}:</span
              >
              <input
                id="password"
                type="password"
                class="form-control"
                placeholder="*********"
                v-model="inputs.password"
                :class="{ 'is-invalid': v$.inputs.password.$error }"
              />
            </div>
            <span class="text-danger" v-if="v$.inputs.password.$error">
              Ce champs est requis.
            </span>
          </div>
          <div class="d-flex justify-content-between mb-5">
            <div>
              <RouterLink
                :to="{ name: 'create-user-account' }"
                class="link mx-2"
                id="button"
                >{{ $t("signin.link1") }}</RouterLink
              >
            </div>
            <div>
              <RouterLink :to="{ name: 'forgot-password' }" class="link mx-2"
                >{{ $t("signin.link2") }} ?
              </RouterLink>
            </div>
          </div>
          <div class="d-flex justify-content-center mt-5 mb-5">
            <button type="submit" class="btn col-md-3 col-12 mb-5 mx-2">
              {{ $t("signin.buttons.login") }}
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
button {
  background-color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  color: white;
}
#email:focus,
#password:focus {
  border-color: rgba(202, 160, 185, 0.674);
  box-shadow: 0 0 0 0.2rem rgba(202, 160, 185, 0.674);
}
.favicon {
  height: 80px;
}
</style>
