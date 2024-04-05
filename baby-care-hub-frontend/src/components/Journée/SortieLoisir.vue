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
      data: null,
      stayHome: false,
      NouvelSortie: false,
      inputs: {
        childId: this.route.params.id,
        leisureId: "",
        commentaire: "",
        date: new Date().toISOString().slice(0, 10),
      },
      childLeisures: [],
      leisures: [],
    };
  },

  mounted() {
    this.initLeisures();
    this.getLeisure();
  },
  computed: {
    homeLeisures() {
      return this.leisures.slice(0, 3);
    },
    outSideLeisures() {
      return this.leisures.slice(3);
    },
  },
  methods: {
    close() {
      (this.NouvelSortie = false),
        (this.inputs.leisureId = ""),
        (this.inputs.commentaire = "");
    },
    async getLeisure() {
      {
        const response = await this.$axios.get(
          `/leisures/${this.inputs.date}/${this.inputs.childId}`
        );
        this.childLeisures = response.body;
      }
    },
    showSelect() {
      this.stayHome = true;
    },
    AjouterSortie() {
      this.NouvelSortie = true;
    },
    async submit() {
      console.log("inputs", this.inputs);
      const response = await this.$axios.post(`/leisures`, this.inputs);
      if (response) {
        this.close();
        this.getLeisure(this.inputs.date, this.inputs.childId);
      }
    },
    async initLeisures() {
      const response = await this.$axios.get(`/leisures`);
      this.leisures = response.body;
    },
  },
};
</script>

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

        <form class="form mb-2 mt-4" @submit.prevent="submit">
          <div class="row mx-3">
            <div class="col mb-2">
              <label for="appt1" class="mr-2 mb-4"
                >Ou est ce qu'on est allé?</label
              >
              <select
                class="form-select mt-2"
                aria-label="Default select example"
                v-model.number="inputs.leisureId"
              >
                <option selected>Choisir un lieu</option>
                <option v-for="leisure in outSideLeisures" :value="leisure.id">
                  {{ leisure.name }}
                </option>
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
                v-model.number="inputs.leisureId"
              >
                <option selected>Choisir une activité</option>
                <option v-for="leisure in homeLeisures" :value="leisure.id">
                  {{ leisure.name }}
                </option>
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
                  v-model="inputs.commentaire"
                ></textarea>
                <label for="floatingTextarea2">Comments</label>
              </div>
            </div>
          </div>

          <div class="d-flex justify-content-center mt-3 div-button">
            <button class="btn btn1 mb-4 ms-3" type="submit">Confirmer</button>
            <button class="btn mb-4 mx-3" @click="close">Annuler</button>
          </div>
        </form>
      </div>
    </div>

    <template v-if="childLeisures && childLeisures.length > 0">
      <div
        class="d-flex justify-content-center mb-3 mt-5"
        v-for="leisure in childLeisures"
      >
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
                <h5 class="card-title m-0">
                  {{ leisure.leisureId.name }}
                </h5>
                <p class="card-text m-0 mt-3" style="max-width: 500px">
                  {{ leisure.commentaire }}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </template>
    <template v-else>
      <div class="d-flex justify-content-center mb-3 mt-5">
        <div class="card bg-light col-12 col-md-6 mb-2">
          <div class="row g-0">
            <div class="col-md-12">
              <div class="card-body">
                Pas encore de sortie ou de loisir prévu pour l'enfant
                aujourd'hui ! 🏞️💃📚
              </div>
            </div>
          </div>
        </div>
      </div>
    </template>
  </div>
</template>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");
h1 {
  font-family: "Pacifico", cursive;
  color: rgba(180, 95, 146, 0.674);
}
h3 {
  font-family: "Pacifico", cursive;
}

.btn {
  background-color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  color: white;
}
</style>
