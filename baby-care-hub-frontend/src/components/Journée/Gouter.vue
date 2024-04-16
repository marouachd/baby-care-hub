<script>
import { RouterLink, useRoute } from "vue-router";
import axios from "axios";
//import Satisfaction from "../journée/Satisfaction.vue";
export default {
  //components: { Satisfaction },
  setup() {
    return {
      route: useRoute(),
    };
  },
  data() {
    return {
      selectedLevel: 1,
      thumbPosition: "50%",
      satisfactionLevels: ["Pas mangé", "Peu mangé", "Bien mangé"],
      snacks: [],
      inputs: {
        childId: this.route.params.id,
        eval: "",
        commentaire: "",
        type: "snack",
        date: new Date().toISOString().slice(0, 10),
        snackId: "",
      },
    };
  },
  created() {
    this.$http = axios;
  },
  mounted() {
    this.getSnack();
    this.initSnacks();
    const slider = this.$refs.slider;
    slider.addEventListener("mousedown", (event) => {
      this.updateThumbPosition(event);

      document.addEventListener("mousemove", this.updateThumbPosition);
      document.addEventListener("mouseup", () => {
        document.removeEventListener("mousemove", this.updateThumbPosition);
      });
    });
  },
  methods: {
    async getSnack() {
      {
        const response = await this.$axios.get(
          `/meals/${this.inputs.date}/${this.inputs.childId}/${this.inputs.type}`
        );
        this.data = response.body;
        console.log(this.data, "snack");
      }
    },
    updateThumbPosition(event) {
      const slider = this.$refs.slider;
      const maxPosition =
        slider.offsetWidth - slider.querySelector(".slider-thumb").offsetWidth;
      const position = Math.min(
        Math.max(event.clientX - slider.getBoundingClientRect().left, 0),
        maxPosition
      );
      this.thumbPosition = position + "px";

      const level = Math.round((position / maxPosition) * 2);
      this.selectedLevel = level;
      this.inputs.eval = this.satisfactionLevels[this.selectedLevel];
    },
    async initSnacks() {
      const response = await this.$axios.get(`/meals`);
      this.snacks = response.body;
    },
    async submit() {
      const response = await this.$axios.post(`/meals`, this.inputs);
      if (response && response.status === 200) {
        this.$toast.success("toast-global", "Gouter enregistré");
        this.inputs = {};
      } else {
        this.$toast.error("toast-global", "Un problème est survenu.");
      }
    },
  },
};
</script>

<template>
  <div id="app" class="container mt-5 mb-5 mx-md-5">
    <h1 class="mb-3 text-center">Gouter</h1>

    <form @submit.prevent="submit()">
      <div class="col-md-12">
        <label class="mb-3 mt-3 ms-3">Qu'est ce qu'il a mangé ?</label>
      </div>
      <div class="row d-flex justify-content-between ms-5 mb-4">
        <div class="col-md-3" v-for="snack in snacks">
          <span>
            <img
              :src="snack.imagePath"
              class="img-fluid rounded-start ms-4"
              alt="..."
              width="50"
              height="50"
            />
          </span>
          <div class="form-check">
            <input
              class="form-check-input mt-3"
              type="radio"
              :value="snack.id"
              :id="'snack_' + snack.id"
              v-model="inputs.snackId"
            />
            <label
              class="form-check-label mt-2"
              for="flexCheckChecked"
              :for="snack.id"
            >
              {{ snack.name }}
            </label>
          </div>
        </div>
      </div>

      <div class="satisfaction-scale mt-3">
        <label for="satisfaction">Est-ce qu'il a bien mangé ?</label>
      </div>

      <div class="row justify-content-center">
        <div class="col-md-8 col-lg-12">
          <div class="smileys d-flex justify-content-between mt-3">
            <span class="sad"> 😞</span>
            <span class="neutre"> 😐</span>
            <span class="happy"> 😃</span>
          </div>
          <div class="slider mt-2" ref="slider">
            <div class="slider-thumb" :style="{ left: thumbPosition }"></div>
          </div>
          <div class="satisfaction-labels mt-2 d-flex justify-content-between">
            <div
              class="satisfaction-label"
              v-for="(level, index) in satisfactionLevels"
              :key="index"
              :class="{ selected: index === selectedLevel }"
            >
              {{ level }}
            </div>
          </div>
          <div class="form-floating mt-3">
            <textarea
              class="form-control"
              placeholder="Leave a comment here"
              id="floatingTextarea2"
              v-model="inputs.commentaire"
            ></textarea>
            <label for="floatingTextarea2">Comments</label>
          </div>
          <div class="d-flex justify-content-center mt-3">
            <button class="btn mb-5" type="submit">Confirmer</button>
          </div>
        </div>
      </div>
    </form>
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
  color: rgba(180, 95, 146, 0.674);
}

.smileys {
  width: 100%;
  font-size: 36px;
}

.slider {
  width: 100%;
  height: 20px;
  background-color: #ccc;
  position: relative;
}

.slider-thumb {
  width: 20px;
  height: 20px;
  background-color: #007bff;
  position: absolute;
  top: 0;
  left: 0;
  cursor: pointer;
}

.slider-thumb:active {
  cursor: grabbing;
}

.slider-thumb:hover {
  background-color: #0056b3;
}

.selected {
  font-weight: bold;
}

.btn {
  background-color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  color: white;
}
</style>
