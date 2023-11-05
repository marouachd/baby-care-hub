<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">Sortie & Loisir</h1>

    <div class="d-flex justify-content-end">
      <button class="btn" @click="AjouterSortie()">Ajouter</button>
    </div>

    <div class="d-flex justify-content-center">
      <div v-if="NouvelSortie" class="shadow-sm col-12 col-md-6 mb-2">
        <div class="text-center">
          <h3 class="d-inline mb-4 ms-5">
            Qu'est ce qu'on a fait l'aprés midi ?
          </h3>
        </div>

        <form class="form mb-2 mt-4">
          <div class="row mx-3">
            <div class="col mb-2">
              <label for="appt1" class="mr-2 mb-4"
                >Ou est ce qu'on est allé?</label
              >
              <select
                class="form-select mt-2"
                aria-label="Default select example"
              >
                <option selected>Choisir un lieu</option>
                <option value="1">Parc à coté</option>
                <option value="2">Bibliothéque</option>
                <option value="3">Ludothéque</option>
              </select>
            </div>
          </div>

          <div class="form-check mx-3">
            <input
              class="form-check-input"
              type="checkbox"
              value=""
              id="flexCheckChecked"
              @click="showSelect"
            />
            <label class="form-check-label" for="flexCheckChecked">
              Il ne fait pas beau aujourd'hui on a décidé de rester à la maison
            </label>
          </div>

          <div class="row mx-3" v-if="stayHome">
            <div class="col mb-2">
              <label for="appt1" class="mr-2 mb-4 mt-4">On a fait quoi?</label>
              <select
                class="form-select mt-2"
                aria-label="Default select example"
              >
                <option selected>Choisir une activité</option>
                <option value="1">Lire un livre</option>
                <option value="2">Danse</option>
                <option value="3">Regarder des dessins animées</option>
              </select>
            </div>
          </div>

          <div class="row mx-3">
            <div class="col mb-2">
              <div class="form-floating">
                <textarea
                  class="form-control mt-2"
                  placeholder="Leave a comment here"
                  id="floatingTextarea2"
                ></textarea>
                <label for="floatingTextarea2">Comments</label>
              </div>
            </div>
          </div>

          <div class="d-flex justify-content-center mt-3 div-button">
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
              src="../../assets/parc.jpg"
              class="img-fluid rounded-start mt-4 ms-4"
              alt="..."
              width="50"
              height="50"
            />
          </div>
          <div class="col-md-8">
            <div class="card-body w-auto">
              <h5 class="card-title m-0">On est allé au parc</h5>
              <p class="card-text m-0 mt-3" style="max-width: 500px">
                Noah a jouer au balançoire, il etait trés organisé, il fesait la
                queue et attend son role
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
      stayHome: false,
      NouvelSortie: false,
    };
  },
  methods: {
    showSelect() {
      this.stayHome = true;
    },
    AjouterSortie() {
      this.NouvelSortie = true;
    },
  },
};
</script>
