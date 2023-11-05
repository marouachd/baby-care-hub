<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">Horaire</h1>
    <div class="d-flex justify-content-center">
      <form
        class="mt-5 col-6 col-md-6 mb-2"
        @submit.prevent="calculeHoraireGarde"
      >
        <label for="appt1" class="mb-2 text-center">Heure d'arrivée:</label>
        <input
          type="time"
          id="appt1"
          name="appt1"
          class="form-control"
          v-model="heureArrivee"
        />
      </form>
    </div>
    <div class="d-flex justify-content-center">
      <form
        class="mt-3 col-6 col-md-6 mb-2"
        @submit.prevent="calculeHoraireGarde"
      >
        <label for="appt2" class="mr-2 mb-2">Heure de sortie:</label>
        <input
          type="time"
          id="appt2"
          name="appt2"
          class="form-control"
          v-model="heureSortie"
        />
        <div class="text-center">
          <button type="submit" class="mt-3 btn mx-5">Calculer</button>
        </div>
      </form>
    </div>
    <div class="d-flex justify-content-center">
      <div class="mt-3 col-12 col-md-6 col-lg-4 mb-3">
        <span class="d-inline span">
          Le temps de garde cumulé pour aujourd'hui est :
          {{ time }}
        </span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      heureArrivee: "",
      heureSortie: "",
      time: "",
    };
  },

  methods: {
    calculeHoraireGarde() {
      const appt1 = this.heureArrivee;
      const appt2 = this.heureSortie;

      if (appt1 && appt2) {
        const [hours1, minutes1] = appt1.split(":").map(Number);
        const [hours2, minutes2] = appt2.split(":").map(Number);

        const time1 = hours1 * 60 + minutes1;
        const time2 = hours2 * 60 + minutes2;

        const difference = time2 - time1;

        if (difference >= 0) {
          const hours = Math.floor(difference / 60);
          const minutes = difference % 60;

          this.time = hours + " h " + minutes + " min";
        } else {
          this.time = "Erreur: Heure de sortie antérieure à l'heure d'arrivée";
        }
      } else {
        this.time = "";
      }
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");
label {
  font-family: "Pacifico", cursive;
  color: rgb(166, 161, 161);
  font-size: 18px;
}
.span {
  font-family: "Pacifico", cursive;
  color: black;
  font-size: 14px;
}
</style>
