<script>
import { RouterLink, useRoute } from "vue-router";
export default {
  setup() {
    return {
      route: useRoute(),
    };
  },
  data() {
    return {
      token: this.route.params.token,
      inputs: {
        mailAdress: "",
        password: "",
      },
      confirmPassword: "",
    };
  },
  mounted() {
    console.log("token", this.token);
  },

  methods: {
    async submit() {
      //this.inputs.mailAdress = localStorage.getItem("mailAdress");
      const response = await this.$axios.get(
        `/password/reset-password/${this.token}`,
        {
          params: { password: this.inputs.password },
        }
      );
      console.log("inputs", this.inputs);
      if (response) {
        this.inputs = {};
        this.confirmPassword = "";
        this.$router.push({
          name: "signin",
        });
      }
      console.log(response, "response");
    },
  },
};
</script>

<template>
  <section class="container-xl text-center mb-5">
    <h1 class="mt-5 mb-5">Choisissez une nouvelle mot de passe</h1>
    <div class="row justify-content-center mb-5">
      <div class="col-md-6 col-12 mb-5">
        <form class="my-4 mb-5" @submit.prevent="submit">
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1"
              >Mot de passe :</span
            >
            <input
              type="password"
              class="form-control"
              placeholder="mot de passe"
              id="password"
              name="password"
              v-model="inputs.password"
            />
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1"
              >Confirmer mot de passe :</span
            >
            <input
              type="password"
              class="form-control"
              placeholder="mot de passe"
              id="confirm-password"
              name="confirm-password"
              v-model="confirmPassword"
            />
          </div>
          <div class="d-flex justify-content-center mt-5 mb-5">
            <button type="submit" class="btn col-md-3 col-12 mx-2 mt-5 mb-4">
              Confirmer
            </button>
          </div>
          <div class="input-group mb-5"></div>
        </form>
      </div>
    </div>
  </section>
</template>

<style>
h1 {
  font-family: "Pacifico", cursive;
  color: rgba(180, 95, 146, 0.674);
}
.btn {
  background-color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  color: white;
}
</style>
