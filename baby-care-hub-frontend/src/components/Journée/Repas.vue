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
                  <Satisfaction />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
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

h3 {
  font-family: "Pacifico", cursive;
}
h1 {
  font-family: "Pacifico", cursive;
  color: rgba(180, 95, 146, 0.674);
}
</style>
