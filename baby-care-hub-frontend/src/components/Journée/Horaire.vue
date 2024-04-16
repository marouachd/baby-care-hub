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
      heureArrivee: "",
      heureSortie: "",
      time: "",
      type: "presence",
      inputs: {
        childId: this.route.params.id,
        date: new Date().toISOString().slice(0, 10),
        startTime: "",
        endTime: "",
        commentaire: "",
      },
      presences: [],
    };
  },

  mounted() {
    this.getPresenceTime();
  },

  methods: {
    async getPresenceTime() {
      const presence = await this.$axios.get(
        `/naps/${this.inputs.date}/${this.inputs.childId}/${this.type}`
      );
      this.presences = presence.body;
      console.log(this.presences, "presenceeee");
    },

    async submit() {
      try {
        const response = await this.$axios.post(
          `/naps/${this.type}`,
          this.inputs
        );
        if (response) {
          this.getPresenceTime(
            this.inputs.date,
            this.inputs.childId,
            this.type
          );
          this.startTime = "";
          this.endTime = "";
          this.commentaire = "";
        }
        //this.calculeHoraireGarde();
      } catch (error) {
        console.error("Erreur lors de la soumission :", error);
      }
      //this.calculeHoraireGarde();
    },
    calculeHoraireGarde() {
      const appt1 = this.inputs.startTime;
      const appt2 = this.inputs.endTime;

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
<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">Horaire</h1>
    <div class="d-flex justify-content-center">
      <form class="mt-5 col-6 col-md-6 mb-2" @submit.prevent="submit">
        <label for="appt1" class="mb-2 text-center">Heure d'arrivée:</label>
        <input
          type="time"
          id="appt1"
          name="appt1"
          class="form-control"
          v-model="inputs.startTime"
        />

        <label for="appt2" class="mr-2 mb-2">Heure de sortie:</label>
        <input
          type="time"
          id="appt2"
          name="appt2"
          class="form-control"
          v-model="inputs.endTime"
        />
        <div class="text-center">
          <button type="submit" class="mt-3 btn mx-5">Enregistrer</button>
        </div>
      </form>
    </div>
    <!--<div class="d-flex justify-content-center">
      <div class="mt-3 col-12 col-md-6 col-lg-4 mb-3">
        <span class="d-inline span">
          Le temps de garde cumulé pour aujourd'hui est :
          {{ time }}
        </span>
      </div>
    </div>-->
    <div
      class="d-flex justify-content-center"
      v-if="this.presences"
      v-for="presence in presences"
    >
      <div class="mt-5 col-6 col-md-6 mb-2">
        <div class="d-flex align-items-center">
          <img
            src="../../assets/horloge.jpg"
            class="img-fluid rounded-circle me-5"
            alt="Icône Horaire"
            style="width: 100px"
          />
          <div class="d-flex flex-column">
            <div class="col">
              <div class="row" v-if="presence.startTime">
                Heure d'arrivée : {{ presence.startTime }}
              </div>
            </div>
            <div class="col">
              <div class="row mt-2" v-if="presence.endTime">
                Heure de départ : {{ presence.endTime }}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

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
h1 {
  font-family: "Pacifico", cursive;
  color: rgba(180, 95, 146, 0.674);
}
.btn {
  background-color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  color: white;
}
</style>
