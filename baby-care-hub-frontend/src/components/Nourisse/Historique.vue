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
      baseUrl: import.meta.env.VITE_IMG_BASE_URL,
      id: this.route.params.id,
      histories: [],
    };
  },

  methods: {
    async gethistories() {
      const response = await this.$axios.get(`/history/${this.id}`);
      this.histories = response.body;
      console.log(this.histories, "histories");
    },
  },
  mounted() {
    this.gethistories();
  },
  beforeMount() {
    this.id = localStorage.getItem("userId");
  },
};
</script>
<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">Historique</h1>

    <div class="row d-flex justify-content-center mb-3 mt-5 mx-2">
      <div class="table-responsive">
        <table class="table table-striped">
          <thead>
            <tr>
              <th>#</th>
              <th>Nom</th>
              <th>Prénom</th>
              <th>Mode de Garde</th>
              <th>Date de Début</th>
              <th>Date de Fin</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(history, index) in histories" :key="index">
              <td>{{ index + 1 }}</td>
              <td>{{ history.childId.personId.lastName }}</td>
              <td>{{ history.childId.personId.firstName }}</td>
              <td>{{ history.childId.guardId.name }}</td>
              <td>{{ history.startDate }}</td>
              <td>{{ history.endDate }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<style>
h1 {
  font-family: "Pacifico", cursive;
  color: rgba(180, 95, 146, 0.674);
}
@media (max-width: 768px) {
  .border.rounded {
    width: 100%;
    box-sizing: border-box;
  }
}
</style>
