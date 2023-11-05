<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">Sieste</h1>

    <div class="d-flex justify-content-end">
      <button class="btn" @click="AjouterSieste()">Ajouter</button>
    </div>

    <div class="d-flex justify-content-center">
      <div v-if="NouvelSieste" class="shadow-sm col-12 col-md-6 mb-2">
        <div class="text-center">
          <h3 class="d-inline mb-4">Ajouter une sieste</h3>
        </div>
        <form class="form mb-2 mt-4">
          <div class="row mx-2">
            <div class="col mb-2">
              <label for="appt1" class="mr-2 mb-2">Heure de sommeil:</label>
              <input
                type="time"
                id="appt1"
                name="appt1"
                class="form-control"
                v-model="heureArrivee"
              />
            </div>
          </div>
          <div class="row mx-2">
            <div class="col mb-2">
              <label for="appt2" class="mr-2 mb-2">Heure de réveil:</label>
              <input
                type="time"
                id="appt2"
                name="appt2"
                class="form-control"
                v-model="heureSortie"
              />
            </div>
          </div>
          <div class="row mx-2">
            <div class="col mb-2">
              <div class="form-floating mt-2">
                <textarea
                  class="form-control mt-2"
                  placeholder="Leave a comment here"
                  id="floatingTextarea2"
                ></textarea>
                <label for="floatingTextarea2">Commentaires</label>
              </div>
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
              src="../../assets/sieste.jpg"
              class="img-fluid rounded-start mt-4 ms-4"
              alt="..."
              width="50"
              height="50"
            />
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <h5 class="card-title m-0">Sieste</h5>
              <p class="mt-2">Heure de sommeil:</p>
              <p>Heure de réveil:</p>
              <p class="card-text m-0 mt-2">
                Noah a fait une sieste, il a bien dormi
              </p>
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
      NouvelSieste: false,
    };
  },
  methods: {
    AjouterSieste() {
      this.NouvelSieste = true;
    },
  },
};
</script>
