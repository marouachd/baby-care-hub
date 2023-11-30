<template>
  <div id="app" class="container mt-5 mb-5 mx-md-5">
    <h1 class="mb-3 text-center">Gouter</h1>

    <form>
      <div class="col-md-12">
        <label class="mb-3 mt-3 ms-3">Qu'est ce qu'il a mangé ?</label>
      </div>
      <div class="row d-flex justify-content-between ms-5 mb-4">
        <div class="col-md-3">
          <span>
            <img
              src="../../assets/fruit.jpg"
              class="img-fluid rounded-start ms-4"
              alt="..."
              width="50"
              height="50"
            />
          </span>
          <div class="form-check">
            <input
              class="form-check-input mt-3"
              type="checkbox"
              value=""
              id="flexCheckChecked"
            />
            <label class="form-check-label mt-2" for="flexCheckChecked">
              Fruit
            </label>
          </div>
        </div>
        <div class="col-md-3">
          <span>
            <img
              src="../../assets/yaourt.jpg"
              class="img-fluid rounded-start ms-4"
              alt="..."
              width="50"
              height="50"
            />
          </span>
          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              value=""
              id="flexCheckChecked"
            />
            <label
              class="form-check-label align-item-center"
              for="flexCheckChecked"
            >
              Yaourt/Compote
            </label>
          </div>
        </div>
        <div class="col-md-3">
          <span>
            <img
              src="../../assets/tartine.png"
              class="img-fluid rounded-start ms-4"
              alt="..."
              width="50"
              height="50"
            />
          </span>
          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              value=""
              id="flexCheckChecked"
            />
            <label class="form-check-label" for="flexCheckChecked">
              Tartine
            </label>
          </div>
        </div>
        <div class="col-md-3">
          <span>
            <img
              src="../../assets/gateau.jpg"
              class="img-fluid rounded-start ms-4"
              alt="..."
              width="50"
              height="50"
            />
          </span>
          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              value=""
              id="flexCheckChecked"
            />
            <label class="form-check-label" for="flexCheckChecked">
              Gateau
            </label>
          </div>
        </div>
      </div>
    </form>
    <Satisfaction />
  </div>
</template>
<script>
import Satisfaction from "../journée/Satisfaction.vue";
export default {
  components: { Satisfaction },
};
</script>
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
</style>
