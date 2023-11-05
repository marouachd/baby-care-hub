<template>
  <div class="satisfaction-scale mt-3">
    <label for="satisfaction">Est-ce qu'il a bien mangé ?</label>
  </div>
  <form>
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
          ></textarea>
          <label for="floatingTextarea2">Comments</label>
        </div>
        <div class="d-flex justify-content-center mt-3">
          <button class="btn">Confirmer</button>
        </div>
      </div>
    </div>
  </form>
</template>
<script>
export default {
  data() {
    return {
      selectedLevel: 1,
      thumbPosition: "50%",
      satisfactionLevels: ["Pas mangé", "Peu mangé", "Bien mangé"],
    };
  },
  methods: {
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
    },
  },
  mounted() {
    const slider = this.$refs.slider;
    slider.addEventListener("mousedown", (event) => {
      this.updateThumbPosition(event);

      document.addEventListener("mousemove", this.updateThumbPosition);
      document.addEventListener("mouseup", () => {
        document.removeEventListener("mousemove", this.updateThumbPosition);
      });
    });
  },
};
</script>
<style>
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
