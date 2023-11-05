<script>
export default {
  data() {
    return {
      imageUrl:
        "https://mdbootstrap.com/img/Photos/Others/placeholder-avatar.jpg",
    };
  },
  methods: {
    updateImage(event) {
      const file = event.target.files[0];
      if (file) {
        const imageUrl = URL.createObjectURL(file);
        this.imageUrl = imageUrl;
      }
    },
    triggerFileInput() {
      this.$refs.fileInput.click();
    },
  },
};
</script>
<template>
  <section class="container-xl text-center mb-4">
    <h1 class="mt-4 mb-5">Modifier détails de l'enfant</h1>

    <form class="my-4">
      <div class="container">
        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-3">
          <div class="col-md-4">
            <div class="text-center">
              <img
                ref="profileImage"
                :src="imageUrl"
                class="img-fluid rounded-circle circle-image"
                alt="example placeholder"
                @click="triggerFileInput"
              />

              <input
                type="file"
                ref="fileInput"
                style="display: none"
                @change="updateImage"
              />
            </div>
          </div>

          <div class="col-md-4">
            <div class="mb-3">
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">Nom :</span>
                <input type="text" class="form-control" placeholder="Nom" />
              </div>
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon2">Prénom :</span>
                <input type="text" class="form-control" placeholder="Prénom" />
              </div>

              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon3"
                  >Date de naissance :</span
                >
                <input
                  type="date"
                  class="form-control datepicker"
                  id="datepicker"
                />
              </div>
            </div>
          </div>
        </div>

        <div class="col-md-6 input-group mb-3 mt-3">
          <span class="input-group-text" id="basic-addon4"
            >Représentant légal :</span
          >
          <input type="text" class="form-control" placeholder="Nom&Prénom" />
          <RouterLink
            :to="{ name: 'create-parent-account' }"
            class="btn mb-2 me-md-3 ms-2 mt-1"
            id="button"
          >
            <i class="fa fa-save"></i
          ></RouterLink>
        </div>
        <div class="col-md-6 input-group mb-3">
          <span class="input-group-text" id="basic-addon5">Sexe:</span>
          <select class="form-select" aria-label="Default select example">
            <option selected>Choisir le sexe de l'enfant</option>
            <option value="1">Une petite fille</option>
            <option value="2">Un petit garçon</option>
          </select>
        </div>
        <div class="col-md-6 input-group mb-3">
          <span class="input-group-text" id="basic-addon6"
            >Mode de garde :</span
          >
          <select class="form-select" aria-label="Default select example">
            <option selected>Choisir un mode de garde</option>
            <option value="1">Temps plein</option>
            <option value="2">Les mercredis</option>
            <option value="3">Vacances scolaires</option>
            <option value="4">Sortie d'école</option>
          </select>
        </div>
      </div>
      <div class="text-center mt-5 mb-5">
        <div class="justify-content-center">
          <button class="btn btn-secondary mb-2 ms-md-3 me-3">Confirmer</button>
          <button class="btn btn-danger mb-2 ms-md-3">Quitter</button>
        </div>
      </div>
    </form>
  </section>
</template>
<style>
img {
  cursor: pointer;
}
.circle-image {
  width: 150px;
  height: 150px;
  object-fit: cover;
}
</style>
