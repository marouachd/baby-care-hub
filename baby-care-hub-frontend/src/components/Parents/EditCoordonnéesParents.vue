<template>
  <section class="container-xl text-center mb-4">
    <h1 class="mt-4 mb-5">Votre profile</h1>
    <form class="my-4" @submit.prevent="">
      <div class="container">
        <div class="row g-2 g-lg-3">
          <div class="col-md-6 col-12">
            <div class="text-center">
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1"
                  >Nom de famille :</span
                >
                <input
                  type="text"
                  class="form-control"
                  placeholder="Nom de famille"
                />
              </div>
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">Prénom :</span>
                <input type="text" class="form-control" placeholder="Prénom" />
              </div>
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1"
                  >Numéro de télephone :</span
                >
                <input
                  type="text"
                  class="form-control"
                  placeholder="téléphone"
                />
              </div>
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1"
                  >Adresse email:</span
                >
                <input
                  type="text"
                  class="form-control"
                  placeholder="Adresse email"
                />
              </div>
            </div>
          </div>
          <div class="col-md-6 col-12">
            <div>
              <div class="input-group mb-3">
                <h5 class="mb-3">Coordonnées deuxième parent :</h5>
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon1"
                    >Prénom :</span
                  >
                  <input
                    type="text"
                    class="form-control"
                    placeholder="Prénom"
                  />
                </div>
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon1"
                    >Numéro de télephone :</span
                  >
                  <input
                    type="text"
                    class="form-control"
                    placeholder="téléphone"
                  />
                </div>
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon1"
                    >Adresse email:</span
                  >
                  <input
                    type="text"
                    class="form-control"
                    placeholder="Adresse email"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="input-group mb-5">
          <span class="input-group-text" id="basic-addon1">Adresse :</span>
          <input type="text" class="form-control" placeholder="Adresse" />
        </div>
      </div>

      <div class="d-flex justify-content-center mt-3 mb-5">
        <button type="submit" class="btn col-md-3 col-12 mb-5 mx-2">
          Confirmer
        </button>
      </div>
    </form>
  </section>
</template>
<style>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");
h1 {
  color: rgba(180, 95, 146, 0.674);
  font-family: "Pacifico", cursive;
}

h5 {
  font-family: "Pacifico", cursive;
  color: rgb(166, 161, 161);
}
.btn {
  background-color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  color: white;
}
</style>
