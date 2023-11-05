<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">Prise de température</h1>

    <div class="d-flex justify-content-end">
      <button class="btn" @click="AjouterTempérature()">Ajouter</button>
    </div>

    <div class="d-flex justify-content-center">
      <div v-if="NouveauTempérature" class="shadow-sm col-12 col-md-6 mb-2">
        <div class="text-center">
          <h3 class="d-inline mb-4 ms-5">Prise de température</h3>
        </div>
        <form class="form mb-2 mt-4">
          <div class="row">
            <div class="col mb-2">
              <label for="appt1" class="mr-2 mb-4 ms-3"
                >Temps de prise de température:</label
              >
              <input
                type="time"
                id="appt1"
                name="appt1"
                class="form-control mt-4 ms-3"
              />
            </div>
            <div class="col mb-2 me-3">
              <label class="mr-2 mb-4">Entrez la température en degré :</label>
              <input type="number" class="form-control mt-4" />
            </div>
          </div>

          <div class="d-flex justify-content-center mt-3">
            <button class="btn btn1 mb-4 ms-3">Confirmer</button>
            <button class="btn mb-4 mx-3">Annuler</button>
          </div>
        </form>
      </div>
    </div>

    <div class="d-flex justify-content-center mb-3 mt-5">
      <div class="card bg-light col-12 col-md-6 mb-2">
        <div class="row g-0">
          <div class="col-md-4">
            <img
              src="../../assets/Thermometre.jpg"
              class="img-fluid rounded-start mt-4 ms-4"
              alt="..."
              width="50"
              height="50"
            />
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <h5 class="card-title m-0">Température</h5>
              <p class="card-text m-0 mt-2">
                Température de Noah a augmenté à : 39°
              </p>
              <p class="card-text m-0">à 16h:00</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");

h3 {
  font-family: "Pacifico", cursive;
}
</style>
<script>
export default {
  data() {
    return {
      NouveauTempérature: false,
    };
  },
  methods: {
    AjouterTempérature() {
      this.NouveauTempérature = true;
    },
  },
};
</script>
