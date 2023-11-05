<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">Change</h1>

    <div class="d-flex justify-content-end">
      <button class="btn" @click="AjouterChange()">Ajouter</button>
    </div>

    <div class="d-flex justify-content-center">
      <div v-if="NouveauChange" class="shadow-sm col-12 col-md-6 mb-2">
        <div class="text-center">
          <h3 class="d-inline mb-4">Ajouter un change</h3>
        </div>
        <form class="form mb-2 mt-4">
          <div class="row ms-2">
            <div class="col mb-2">
              <label for="appt1" class="mr-2 mb-4 ms-3">Type de change </label>
              <select
                class="form-select mt-2 w-100"
                aria-label="Default select example"
              >
                <option selected>Choisir</option>
                <option value="1">Pipi</option>
                <option value="2">Selles</option>
                <option value="3">Pipi & selles</option>
              </select>
            </div>

            <div class="col mb-2 me-3">
              <label class="mr-2 mb-4">Type des selles</label>
              <select
                class="form-select mt-2 w-100"
                aria-label="Default select example"
              >
                <option selected>Choisir</option>
                <option value="1">Normales</option>
                <option value="2">Dures</option>
                <option value="3">Liquides</option>
              </select>
            </div>
          </div>
          <div class="row mx-2">
            <div class="form-floating">
              <textarea
                class="form-control mt-2"
                placeholder="Leave a comment here"
                id="floatingTextarea2"
              ></textarea>
              <label for="floatingTextarea2">Commentaires</label>
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
              src="../../assets/change.jpg"
              class="img-fluid rounded-start mt-4 ms-4"
              alt="..."
              width="50"
              height="50"
            />
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <h5 class="card-title m-0">Change</h5>
              <p class="card-text m-0 mt-3">
                Les selles sont normaux toutes la journée.
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
      NouveauChange: false,
    };
  },
  methods: {
    AjouterChange() {
      this.NouveauChange = true;
    },
  },
};
</script>
