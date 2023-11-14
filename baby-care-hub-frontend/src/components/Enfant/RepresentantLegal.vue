<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">Qui sont mes parents ?</h1>

    <div class="d-flex justify-content-center mb-3 mt-5">
      <div class="card col-12 col-md-6 mb-2">
        <div class="card-body">
          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Ma mère s'appelle :</p>
              <span>Marie</span>
            </div>
          </div>

          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Mon père s'appelle :</p>
              <span>Guillaume</span>
            </div>
          </div>
          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Notre nom de famille est :</p>
              <span>David</span>
            </div>
          </div>

          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Vous pouvez appeler ma mère au :</p>
              <span>0766589554</span>
            </div>
          </div>

          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Ou lui envoyer un email sur :</p>
              <span>Marie.david@gmail.com</span>
            </div>
          </div>

          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Vous pouvez appeler mon père au :</p>
              <span>0644758996</span>
            </div>
          </div>

          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Ou lui envoyer un email sur :</p>
              <span>Guillaume.david@gmail.com</span>
            </div>
          </div>
          <hr class="my-2" />

          <div class="row mb-2">
            <div class="d-flex justify-content-start">
              <p class="title me-3">Nous habitons à :</p>
              <span>30 rue Jean Rey, Viroflay</span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="text-center mt-3 mb-5">
      <div class="justify-content-center">
        <RouterLink
          :to="{ name: 'fiche-enfant' }"
          class="btn btn-secondary mb-2 ms-md-3 me-3"
          id="button"
          >Retour</RouterLink
        >
      </div>
    </div>
  </div>
</template>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");

.title {
  color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  font-size: 18px;
}
</style>
