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
      inputs: {
        mailAdress: "",
      },
    };
  },

  methods: {
    async submit() {
      console.log(this.inputs.mailAdress);
      const encodedEmail = encodeURIComponent(this.inputs.mailAdress);
      console.log(encodedEmail);
      // Send the encoded email address in the request
      const response = await this.$axios.post(
        `/password/reset-password`,
        encodedEmail
      );

      if (response && response.status == 200) {
        this.inputs.mailAdress = "";
        this.$toast.success(
          "toast-global",
          "Un email est envoyé à votre adresse"
        );
      } else {
        this.$toast.error("toast-global", "Un problème est survenu.");
      }
      console.log(response, "response");
    },
  },
};
</script>
<template>
  <section class="container-xl text-center mb-5">
    <h1 class="mt-5 mb-5">Entrez votre adresse email !</h1>
    <div class="row justify-content-center mb-5">
      <div class="col-md-6 col-12 mb-5">
        <form class="my-4 mb-5" @submit.prevent="submit">
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1"
              >Adresse email:</span
            >
            <input
              type="email"
              class="form-control"
              placeholder="Adresse email"
              id="mailAdress"
              name="mailAdress"
              v-model="inputs.mailAdress"
            />
          </div>
          <div class="d-flex justify-content-center mt-5 mb-5">
            <button type="submit" class="btn col-md-3 col-12 mx-2 mt-5 mb-4">
              Confirmer
            </button>
          </div>
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
