<script>
import { mapWritableState } from "pinia";
import { mapState } from "pinia";
import useStore from "../../../store/store.js";
import { RouterLink, useRoute } from "vue-router";
import axios from "axios";
import { inject } from "vue";
export default {
  inject: ["actusData"],
  setup() {
    return {
      route: useRoute(),
    };
  },

  data() {
    return {
      toDayDate: new Date().toISOString().slice(0, 10),
      rawArray: "",
      data: null,
      showDate: false,
      id: this.route.params.id,
      //date: new Date().toISOString().slice(0, 10),
      selectedDate: "",
      naps: [],
      presences: [],
      showDatePicker: false,
    };
  },

  watch: {
    async "$data.selectedDate"(newDate) {
      if (newDate) {
        this.toDayDate = newDate;
      }
    },
    async selectedDate(newDate) {
      if (newDate) {
        await this.fetchActualities(newDate);
      }
    },
  },

  mounted() {
    this.afficheDate();
    this.fetchActualities(this.toDayDate);
    this.selectedDate = localStorage.getItem("selectedDate");

    this.childId = this.route.params.id;
    console.log(this.childId, "id");
    this.$emit("date-changed", this.toDayDate);
  },
  computed: {
    ...mapState(useStore, ["actualities"]),
    ...mapWritableState(useStore, ["childId"]),
  },

  methods: {
    handleChangeDate() {
      this.fetchActualities(this.selectedDate); //à refacto !!! n'est pas necessaire ici
      this.toggleDatePicker();
    },
    afficheDate() {
      const storedDate = localStorage.getItem("selectedDate");
      if (storedDate) {
        this.toDayDate = storedDate;
      } else {
        const today = new Date().toISOString().slice(0, 10);
        this.toDayDate = today;
      }
    },

    toggleDatePicker() {
      this.showDatePicker = !this.showDatePicker;
      if (!this.showDatePicker) {
        localStorage.setItem("selectedDate", this.toDayDate);
        this.$emit("date-changed", localStorage.getItem("selectedDate"));
      }
    },
    async fetchActualities(date) {
      const response = await this.$axios.get(`/actualities/${date}/${this.id}`);
      this.data = response.body;
      console.log(this.data, "yes!");
      if (this.data) {
        this.naps = this.data.nap;
        this.presences = this.naps.filter((item) => item.type === "presence");
        this.naps = this.naps.filter((item) => item.type === "sieste");
      }
      console.log(this.data, "actualité selected date");
    },
  },
};
</script>

<template>
  <div class="container mt-4 mb-4">
    <div class="row justify-content-center mb-4">
      <div class="col-auto">
        <div class="d-flex align-items-center">
          <h4
            @click="toggleDatePicker"
            class="clickable text-nowrap text-center"
          >
            {{ showDatePicker ? "" : toDayDate }}
          </h4>
          <input
            type="date"
            class="form-control"
            id="datepicker"
            v-model="toDayDate"
            @change="handleChangeDate"
            v-if="showDatePicker"
          />
        </div>
      </div>
    </div>
    <h1 class="mt-4 mb-4 text-center">
      Fiche Récapitulative de la Journée de l'Enfant
    </h1>
    <div class="card bg-light rounded">
      <div
        class="comment-section mt-2"
        v-if="this.actualities && this.presences"
        v-for="presence in presences"
      >
        <div class="card mx-2 mt-2">
          <div class="card-body">
            <div class="col-md-6">
              <h4>Arrivée</h4>
              <div class="d-flex align-items-center">
                <div class="img-container me-5">
                  <img
                    src="../../../public/images/horloge.jpg"
                    class="img-fluid rounded-circle"
                    alt="Icône Horaire"
                  />
                </div>
                <div>Heure d'arrivée : {{ presence.startTime }}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div
        class="comment-section mt-2"
        v-if="this.actualities && this.presences"
        v-for="presence in presences"
      >
        <span class="comment">⭐</span>
        <span class="comment">❤️</span>
        <span class="comment">👏</span>
        <span class="comment">😞</span>
      </div>
      <div
        v-if="this.actualities && this.actualities.babyBottels"
        v-for="bottle in this.actualities.babyBottels"
        :key="bottle.id"
      >
        <div class="card mx-2 mt-4">
          <div class="card-body">
            <div class="col-md-6">
              <h4>Biberon</h4>
              <div class="d-flex align-items-center">
                <div class="img-container me-5">
                  <img
                    src="../../../public/images/biberon.jpg"
                    class="img-fluid rounded-circle"
                    alt="Icône Horaire"
                  />
                </div>
                <div>Biberon de {{ bottle.volume }} mL</div>

                <div class="ms-1">à {{ bottle.time }}</div>
              </div>
            </div>
          </div>
        </div>

        <div
          class="comment-section mt-2"
          v-if="this.actualities && this.actualities.babyBottels"
        >
          <span class="comment">⭐</span>
          <span class="comment">❤️</span>
          <span class="comment">👏</span>
          <span class="comment">😞</span>
        </div>
      </div>

      <div
        class="card mx-2 mt-4"
        v-if="this.actualities && this.actualities.childActivity"
      >
        <div class="card-body">
          <div class="col-md-6">
            <h4>Activités</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../../public/images/activité.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div class="d-flex flex-column">
                <div>
                  {{ this.actualities.childActivity.activityId.activityName }} :
                </div>

                <div class="ms-1">
                  {{ this.actualities.childActivity.commentaire }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div
        class="comment-section mt-2"
        v-if="this.actualities && this.actualities.childActivity"
      >
        <span class="comment">⭐</span>
        <span class="comment">❤️</span>
        <span class="comment">👏</span>
        <span class="comment">😞</span>
      </div>

      <div class="card mx-2" v-if="this.actualities && this.actualities.meal">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Repas</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../../public/images/repas.png"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div class="d-flex flex-column">
                <div>{{ this.actualities.meal.eval }} :</div>

                <div class="ms-1">
                  {{ this.actualities.commentaire }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div
        class="comment-section mt-2"
        v-if="this.actualities && this.actualities.meal"
      >
        <span class="comment">⭐</span>
        <span class="comment">❤️</span>
        <span class="comment">👏</span>
        <span class="comment">😞</span>
      </div>

      <div v-if="this.actualities && this.actualities.nap" v-for="nap in naps">
        <div class="card mx-2 mt-2">
          <div class="card-body">
            <div class="col-md-6">
              <h4>Sieste</h4>

              <div class="d-flex align-items-center">
                <div class="img-container me-5">
                  <img
                    src="../../../public/images/sieste.jpg"
                    class="img-fluid rounded-circle"
                    alt="Icône Horaire"
                  />
                </div>
                <div class="d-flex flex-column">
                  <div class="mt-1">Heure de sommeil: {{ nap.startTime }}</div>
                  <div class="mt-1" v-if="nap.endTime">
                    Heure de réveil: {{ nap.endTime }}
                  </div>
                  <div class="mt-1" v-if="nap.endTime">
                    Commentaire: {{ nap.commentaire }}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div
          class="comment-section mt-2"
          v-if="this.actualities && this.actualities.nap"
        >
          <span class="comment">⭐</span>
          <span class="comment">❤️</span>
          <span class="comment">👏</span>
          <span class="comment">😞</span>
        </div>
      </div>

      <div class="card mx-2" v-if="this.actualities && this.actualities.snack">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Gouter</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../../public/images/gouter.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div class="d-flex flex-column">
                <div>{{ this.actualities.snack.eval }} :</div>
                <div>
                  Le gouter pour aujourd'hui est
                  {{ this.actualities.snack.snackId.name }}
                </div>

                <div class="ms-1">
                  {{ this.actualities.snack.commentaire }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div
        class="comment-section mt-2"
        v-if="this.actualities && this.actualities.snack"
      >
        <span class="comment">⭐</span>
        <span class="comment">❤️</span>
        <span class="comment">👏</span>
        <span class="comment">😞</span>
      </div>
      <div
        class="card mx-2"
        v-if="this.actualities && this.actualities.leisure"
      >
        <div class="card-body">
          <div class="col-md-6">
            <h4>Sortie&Loisir</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../../public/images/parc.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>
                <p
                  v-if="
                    this.actualities &&
                    this.actualities.leisure &&
                    this.actualities.leisure.leisureId.id < 4
                  "
                >
                  Il ne fait pas beau aujourd'hui on decidé de rester à la
                  maison
                </p>

                <p>
                  On
                  {{
                    this.actualities &&
                    this.actualities.leisure &&
                    this.actualities.leisure.leisureId.id < 4
                      ? "a choisi de"
                      : "est allé : "
                  }}
                  {{ this.actualities.leisure.leisureId.name }}
                </p>

                <p>{{ this.actualities.leisure.commentaire }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div
        class="comment-section mt-2"
        v-if="this.actualities && this.actualities.leisure"
      >
        <span class="comment">⭐</span>
        <span class="comment">❤️</span>
        <span class="comment">👏</span>
        <span class="comment">😞</span>
      </div>
      <div
        class="comment-section mt-2"
        v-if="this.actualities && this.presences"
        v-for="presence in presences"
      >
        <div class="card mx-2 mt-2" v-if="presence.endTime">
          <div class="card-body">
            <div class="col-md-6">
              <h4>Départ</h4>
              <div class="d-flex align-items-center">
                <div class="img-container me-5">
                  <img
                    src="../../../public/images/horloge.jpg"
                    class="img-fluid rounded-circle"
                    alt="Icône Horaire"
                  />
                </div>
                <div>Heure de sortie : {{ presence.endTime }}</div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div
        class="comment-section mt-2"
        v-if="this.actualities && this.presences"
        v-for="presence in presences"
      >
        <div v-if="presence.endTime">
          <span class="comment">⭐</span>
          <span class="comment">❤️</span>
          <span class="comment">👏</span>
          <span class="comment">😞</span>
        </div>
      </div>
      <div class="card mx-2 mt-2 mb-2" v-if="!this.actualities">
        <div class="card-body">
          <p>Aucune donnée disponible pour cette date.</p>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");
h1 {
  color: rgba(180, 95, 146, 0.674);
  font-family: "Pacifico", cursive;
  text-decoration: none;
}
h4 {
  color: rgb(166, 161, 161);
  font-family: "Pacifico", cursive;
}
.img-container {
  width: 80px;
  height: 80px;
  overflow: hidden;
}

.img-container img {
  max-width: 100%;
  max-height: 100%;
  object-fit: cover;
}

#datepicker {
  margin-left: 10px;
}
</style>
