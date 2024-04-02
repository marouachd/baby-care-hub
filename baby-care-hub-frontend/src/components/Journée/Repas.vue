<script>
//import Satisfaction from "../journée/Satisfaction.vue";
import { RouterLink, useRoute } from "vue-router";
import axios from "axios";
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
      inputs: {
        childId: this.route.params.id,
        eval: "1",
        commentaire: "",
        type: "dejeuner",
        date: new Date().toISOString().slice(0, 10),
      },
    };
  },
  created() {
    this.$http = axios;
  },
  mounted() {
    this.getMeal();
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
    async getMeal() {
      {
        const response = await axios.get(
          `http://localhost:8082/meals/${this.inputs.date}/${this.inputs.childId}/${this.inputs.type}`
        );
        this.data = response.data;
        console.log(this.data, "meal");
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
    async submit() {
      console.log("inputs", this.inputs);
      const response = await axios.post(
        "http://localhost:8082/meals",
        this.inputs
      );
      console.log(response);
    },
  },
};
</script>
<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">Repas</h1>

    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-12 col-md-8 col-lg-8">
          <div class="card bg-light mb-2">
            <div class="row gx-0">
              <div class="col-md-4">
                <img
                  src="../../assets/repas.png"
                  class="img-fluid rounded-start mt-5 ms-5"
                  alt="..."
                  width="70"
                  height="70"
                />
              </div>
              <div class="col-md-8">
                <div class="card-body">
                  <div class="satisfaction-scale mt-3">
                    <label for="satisfaction"
                      >Est-ce qu'il a bien mangé ?</label
                    >
                  </div>
                  <form @submit.prevent="submit()">
                    <div class="row justify-content-center">
                      <div class="col-md-8 col-lg-12">
                        <div
                          class="smileys d-flex justify-content-between mt-3"
                        >
                          <span class="sad"> 😞</span>
                          <span class="neutre"> 😐</span>
                          <span class="happy"> 😃</span>
                        </div>
                        <div class="slider mt-2" ref="slider">
                          <div
                            class="slider-thumb"
                            :style="{ left: thumbPosition }"
                          ></div>
                        </div>
                        <div
                          class="satisfaction-labels mt-2 d-flex justify-content-between"
                        >
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
                          <button class="btn mb-5" type="submit">
                            Confirmer
                          </button>
                        </div>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
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
h1 {
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
</style>
