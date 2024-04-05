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
      rawArray: "",
      data: null,
      id: this.route.params.id,
      date: new Date().toISOString().slice(0, 10),
      //date: "2024-03-32",
    };
  },

  mounted() {
    this.getActuality();
  },

  methods: {
    async getActuality() {
      {
        const response = await this.$axios.get(
          `/actualities/${this.date}/${this.id}`
        );
        this.data = response.body;
        console.log(this.data, "actualités");
        console.log(this.data.babyBottels, "bottels");
        console.log(this.data.nap, "nap");
      }
    },
  },
};
</script>

<template>
  <div class="container mt-4 mb-4">
    <h1 class="mt-4 mb-4 text-center">
      Fiche Récapitulative de la Journée de l'Enfant
    </h1>
    <div class="card bg-light rounded">
      <div
        class="card mx-2 mt-4"
        v-if="this.data && this.data.presence && this.data.presence.startTime"
      >
        <div class="card-body">
          <div class="col-md-6">
            <h4>Arrivée</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/horloge.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>Heure d'arrivée : {{ this.data.presence.startTime }}</div>
            </div>
          </div>
        </div>
      </div>
      <div
        class="comment-section mt-2"
        v-if="this.data && this.data.presence && this.data.presence.startTime"
      >
        <span class="comment">⭐</span>
        <span class="comment">❤️</span>
        <span class="comment">👏</span>
        <span class="comment">😞</span>
      </div>
      <div
        v-if="this.data && this.data.babyBottels"
        v-for="bottle in this.data.babyBottels"
        :key="bottle.id"
      >
        <div class="card mx-2 mt-4">
          <div class="card-body">
            <div class="col-md-6">
              <h4>Biberon</h4>
              <div class="d-flex align-items-center">
                <div class="img-container me-5">
                  <img
                    src="../../assets/biberon.jpg"
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
          v-if="this.data && this.data.babyBottels"
        >
          <span class="comment">⭐</span>
          <span class="comment">❤️</span>
          <span class="comment">👏</span>
          <span class="comment">😞</span>
        </div>
      </div>

      <div class="card mx-2 mt-4" v-if="this.data && this.data.childActivity">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Activités</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/activité.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>{{ this.data.childActivity.activityId.activityName }} :</div>

              <div class="ms-1">
                {{ this.data.childActivity.commentaire }}
              </div>
            </div>
          </div>
        </div>
      </div>

      <div
        class="comment-section mt-2"
        v-if="this.data && this.data.childActivity"
      >
        <span class="comment">⭐</span>
        <span class="comment">❤️</span>
        <span class="comment">👏</span>
        <span class="comment">😞</span>
      </div>

      <div class="card mx-2" v-if="this.data && this.data.meal">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Repas</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/repas.png"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>{{ this.data.meal.eval }} :</div>

              <div class="ms-1">
                {{ this.data.meal.commentaire }}
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="comment-section mt-2" v-if="this.data && this.data.meal">
        <span class="comment">⭐</span>
        <span class="comment">❤️</span>
        <span class="comment">👏</span>
        <span class="comment">😞</span>
      </div>

      <div v-if="this.data && this.data.nap" v-for="nap in this.data.nap">
        <div class="card mx-2 mt-2">
          <div class="card-body">
            <div class="col-md-6">
              <h4>Sieste</h4>

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
                    <p>Heure de sommeil: {{ nap.startTime }}</p>
                    <p>Heure de réveil: {{ nap.endTime }}</p>
                    <p class="card-text m-0 mt-2">
                      {{ nap.commentaire }}
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="comment-section mt-2" v-if="this.data && this.data.nap">
          <span class="comment">⭐</span>
          <span class="comment">❤️</span>
          <span class="comment">👏</span>
          <span class="comment">😞</span>
        </div>
      </div>

      <div class="card mx-2" v-if="this.data && this.data.snack">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Gouter</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/gouter.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>
                Le gouter pour aujourd'hui est
                {{ this.data.snack.snackId.name }}
              </div>

              <div class="ms-1">
                {{ this.data.snack.commentaire }}
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="comment-section mt-2" v-if="this.data && this.data.snack">
        <span class="comment">⭐</span>
        <span class="comment">❤️</span>
        <span class="comment">👏</span>
        <span class="comment">😞</span>
      </div>
      <div class="card mx-2" v-if="this.data && this.data.leisure">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Sortie&Loisir</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/parc.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>
                <p
                  v-if="
                    this.data &&
                    this.data.leisure &&
                    this.data.leisure.leisureId.id < 4
                  "
                >
                  Il ne fait pas beau aujourd'hui on decidé de rester à la
                  maison
                </p>

                <p>
                  On
                  {{
                    this.data &&
                    this.data.leisure &&
                    this.data.leisure.leisureId.id < 4
                      ? "a choisi de"
                      : "est allé : "
                  }}
                  {{ this.data.leisure.leisureId.name }}
                </p>

                <p>{{ this.data.leisure.commentaire }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="comment-section mt-2" v-if="this.data && this.data.leisure">
        <span class="comment">⭐</span>
        <span class="comment">❤️</span>
        <span class="comment">👏</span>
        <span class="comment">😞</span>
      </div>
      <div
        class="card mx-2 mt-2"
        v-if="this.data && this.data.presence && this.data.presence.endTime"
      >
        <div class="card-body">
          <div class="col-md-6">
            <h4>Départ</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/horloge.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>Heure de sortie : {{ this.data.presence.endTime }}</div>
            </div>
          </div>
        </div>
      </div>

      <div
        class="comment-section mt-2"
        v-if="this.data && this.data.presence && this.data.presence.endTime"
      >
        <span class="comment">⭐</span>
        <span class="comment">❤️</span>
        <span class="comment">👏</span>
        <span class="comment">😞</span>
      </div>
      <div class="card mx-2 mt-2 mb-2" v-if="!data">
        <div class="card-body">
          <p>Aucune donnée disponible pour le moment.</p>
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
</style>
