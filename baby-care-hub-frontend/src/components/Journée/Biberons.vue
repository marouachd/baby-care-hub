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
      NouveauBiberon: false,
      babyBottels: [],
      inputs: {
        time: "",
        volume: "",
        childId: this.route.params.id,
        date: new Date().toISOString().slice(0, 10),
      },
    };
  },

  mounted() {
    this.getBabyBottel();
  },
  methods: {
    close() {
      this.NouveauBiberon = false;
      this.inputs.volume = "";
      this.inputs.time = "";
    },
    async getBabyBottel() {
      const response = await this.$axios.get(
        `/bottels/${this.inputs.date}/${this.inputs.childId}`
      );
      this.babyBottels = response.body;
    },

    AjouterBiberon() {
      this.NouveauBiberon = true;
    },
    async submit() {
      const response = await this.$axios.post(`/bottels`, this.inputs);
      if (response) {
        this.close();
        this.getBabyBottel(this.inputs.date, this.inputs.childId);
      }
    },
  },
};
</script>
<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">Biberon</h1>

    <div class="d-flex justify-content-end">
      <button class="btn" @click="AjouterBiberon">Ajouter</button>
    </div>

    <div class="d-flex justify-content-center">
      <div v-if="NouveauBiberon" class="shadow-sm col-12 col-md-6 mb-2">
        <div class="text-center">
          <h3 class="d-inline mb-4 ms-5">Ajouter un biberon</h3>
        </div>
        <form class="form mb-2 mt-4" @submit.prevent="submit">
          <div class="row">
            <div class="col mb-2">
              <label for="appt1" class="mr-2 mb-4 ms-3"
                >Temps de prise de biberon:</label
              >
              <input
                type="time"
                id="time"
                name="time"
                class="form-control mt-4 ms-3"
                v-model="inputs.time"
              />
            </div>
            <div class="col mb-2 me-3">
              <label class="mr-2 mb-4"
                >Entrez la quantité de lait en mL :</label
              >
              <input
                type="number"
                class="form-control mt-4"
                id="volume"
                name="volume"
                v-model="inputs.volume"
              />
            </div>
          </div>

          <div class="d-flex justify-content-center mt-3">
            <button class="btn btn1 mb-4 ms-3" type="submit">Confirmer</button>
            <button class="btn mb-4 mx-3" @click="close">Annuler</button>
          </div>
        </form>
      </div>
    </div>
    <template v-if="babyBottels && babyBottels.length > 0">
      <div
        class="d-flex justify-content-center mb-3 mt-5"
        v-for="babyBottel in babyBottels"
      >
        <div class="card bg-light col-12 col-md-6 mb-2">
          <div class="row g-0">
            <div class="col-md-4">
              <img
                src="../../../public/images/biberon.jpg"
                class="img-fluid rounded-start mt-4 ms-4"
                alt="..."
                width="50"
                height="50"
              />
            </div>
            <div class="col-md-8">
              <div class="card-body">
                <h5 class="card-title m-0">Biberon</h5>
                <p class="card-text m-0 mt-2">
                  Prise de biberon de {{ babyBottel.volume }} ml
                </p>
                <p class="card-text m-0">à {{ babyBottel.time }}</p>
              </div>
            </div>
          </div>
        </div>
      </div></template
    >
    <template v-else>
      <div class="d-flex justify-content-center mb-3 mt-5">
        <div class="card bg-light col-12 col-md-6 mb-2">
          <div class="row g-0">
            <div class="col-md-12">
              <div class="card-body">
                Oups ! Pas de biberon enregistré pour le moment, attendons
                patiemment 🍼🐻
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
  white-space: nowrap;
}
.btn {
  background-color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  color: white;
}
</style>
