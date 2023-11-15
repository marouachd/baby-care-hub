<script>
export default {
  data() {
    return {
      inputs: {
        childminderCode: "",
      },
    };
  },
  methods: {
    Submit() {
      this.$router.push("/mes-enfants");
      localStorage.setItem("childminderCode", this.inputs.childminderCode);
    },
  },
};
</script>
<template>
  <section class="container-xl text-center mb-5">
    <h1 class="mt-5 mb-5">Liaison avec la nounou de votre enfant</h1>
    <div class="row justify-content-center mb-5">
      <div class="col-md-6 col-12 mb-5">
        <form class="my-4 mb-5" @submit.prevent="Submit()">
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1"
              >Identifiant nounou:</span
            >
            <input
              type="text"
              class="form-control"
              placeholder="MAS241278"
              id="childminderCode"
              name="childminderCode"
              v-model="inputs.childminderCode"
            />
          </div>
          <div class="d-flex justify-content-center mt-5 mb-5">
            <button
              @click="Submit"
              type="submit"
              class="btn col-md-3 col-12 mx-2 mt-5 mb-4"
            >
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
</style>
