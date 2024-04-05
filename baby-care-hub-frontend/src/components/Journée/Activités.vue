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
      NouvelActivité: false,
      data: [],
      inputs: {
        activityId: "",
        childId: this.route.params.id,
        commentaire: "",
        date: new Date().toISOString().slice(0, 10),
      },
      activities: [],
    };
  },
  mounted() {
    this.initActivities();
    this.getActivities();
  },
  methods: {
    async getActivities() {
      {
        const response = await this.$axios.get(
          `/activities/${this.inputs.date}/${this.inputs.childId}`
        );
        this.data = response.body;

        console.log(this.data, "activity");
      }
    },
    async initActivities() {
      const response = await this.$axios.get(`/activities`);
      this.activities = response.body;
    },
    AjouterActivité() {
      this.NouvelActivité = true;
    },
    close() {
      this.NouvelActivité = false;
      this.inputs.commentaire = "";
      this.inputs.activityId = "";
    },
    async submit() {
      console.log("inputs", this.inputs);
      const response = await this.$axios.post(`/activities`, this.inputs);
      if (response) {
        this.close();
        this.getActivities(this.inputs.date, this.inputs.childId);
      }
    },
  },
};
</script>
<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">Activités</h1>

    <div class="d-flex justify-content-end">
      <button class="btn" @click="AjouterActivité()">Ajouter</button>
    </div>
    <div class="d-flex justify-content-center">
      <div v-if="NouvelActivité" class="shadow-sm col-12 col-md-6 mb-2">
        <div class="text-center">
          <h3 class="d-inline mb-4 ms-5">Ajouter une activité</h3>
        </div>

        <form class="form mb-2 mt-4" @submit.prevent="submit">
          <div class="row mx-2">
            <div class="col mb-2">
              <label for="appt1" class="mr-2 mb-4"
                >Entrez le type d'activité:</label
              >
              <select
                class="form-select mt-2"
                aria-label="Default select example"
                v-model.number="inputs.activityId"
              >
                <option selected>Choisir une activité</option>
                <option v-for="activity in activities" :value="activity.id">
                  {{ activity.name }}
                </option>
              </select>
            </div>
          </div>
          <div class="row mx-2">
            <div class="col mb-2">
              <div class="form-floating">
                <textarea
                  class="form-control mt-2"
                  v-model="inputs.commentaire"
                  id="commentaire"
                ></textarea>
                <label for="commentaire">Commentaires</label>
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-center mt-3">
            <button class="btn btn1 mb-4 ms-3" type="submit">Confirmer</button>
            <button class="btn mb-4 mx-3" @click="close">Annuler</button>
          </div>
        </form>
      </div>
    </div>
    <template v-if="data && data.length > 0">
      <div
        class="d-flex justify-content-center mb-3 mt-5"
        v-for="activity in data"
        :key="activity.id"
      >
        <div class="card bg-light col-12 col-md-6 mb-2">
          <div class="row g-0">
            <div class="col-md-4">
              <img
                src="../../assets/activité.jpg"
                class="img-fluid rounded-start mt-4 ms-4"
                alt="..."
                width="50"
                height="50"
              />
            </div>
            <div class="col-md-8">
              <div class="card-body">
                <h5 class="card-title m-0">
                  {{ activity.activityId.activityName }}
                </h5>
                <p class="card-text m-0 mt-2">
                  On a joué aux {{ activity.activityId.activityName }},
                  {{ activity.commentaire }}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </template>
    <template v-if="(!data || data.length == 0) && !NouvelActivité">
      <div class="d-flex justify-content-center mb-3 mt-5">
        <div class="card bg-light col-12 col-md-6 mb-2">
          <div class="row g-0">
            <div class="col-md-12">
              <div class="card-body">
                Pas encore d'activités enregistré 🧸 C'est peut-être l'heure de
                lui proposer quelque chose ! 🎨🧩
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
