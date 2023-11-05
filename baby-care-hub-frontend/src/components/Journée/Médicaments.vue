<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 title text-center">Médicaments</h1>

    <div class="row d-flex justify-content-center">
      <div class="col-12 col-md-6">
        <form class="form mb-2 mt-4">
          <div class="row mx-2">
            <div class="col mb-2">
              <label class="mt-4 mb-3">Quelle maladie ?</label>
              <select
                class="form-select mt-2 align-items-center"
                aria-label="Default select example"
                v-model="userType"
              >
                <option selected>Choisir</option>
                <option value="fièvre">Fièvre</option>
                <option value="otite">Otite</option>
                <option value="asthme">Asthme</option>
              </select>
            </div>
          </div>
        </form>
      </div>
    </div>
    <div class="row d-flex justify-content-center">
      <div class="col-12 col-md-6">
        <div v-if="userType === 'fièvre'">
          <div class="container mt-4">
            <h5 class="mb-3">Doliprane</h5>

            <table class="table table-bordered table-responsive-sm">
              <tbody>
                <tr>
                  <th scope="row">Temps</th>
                  <td>
                    <input
                      type="time"
                      id="appt1"
                      name="appt1"
                      class="form-control"
                    />
                  </td>
                </tr>
                <tr>
                  <th scope="row">Volume (ml)</th>
                  <td>
                    <input
                      type="number"
                      id="volume1"
                      name="volume1"
                      class="form-control"
                    />
                  </td>
                </tr>
                <tr>
                  <th scope="row">Administration réussie</th>
                  <td class="text-center">
                    <input
                      type="checkbox"
                      id="admin1"
                      name="admin1"
                      class="form-check-input mt-2"
                    />
                  </td>
                </tr>
                <tr>
                  <th scope="row">Conseils</th>
                  <td>
                    Il ne faut pas donner une autre dose de Doliprane avant 4h,
                    Merci &#128512;
                  </td>
                </tr>
              </tbody>
            </table>
            <div class="text-center">
              <button type="submit" class="btn col-md-6 col-6 mt-4">
                Ajouter
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row d-flex justify-content-center">
      <div class="col-12 col-md-6">
        <div v-if="userType === 'asthme'">
          <div class="container mt-4">
            <h5 class="mb-3">Ventoline/Flixotide</h5>

            <table class="table table-bordered table-responsive-sm">
              <tbody>
                <tr>
                  <th scope="row">Temps</th>
                  <td>
                    <input
                      type="time"
                      id="appt1"
                      name="appt1"
                      class="form-control"
                    />
                  </td>
                </tr>

                <tr>
                  <th scope="row">Administration réussie</th>
                  <td>
                    <div class="form-check">
                      <input
                        type="checkbox"
                        id="admin1"
                        name="admin1"
                        class="form-check-input"
                      />
                      <label class="form-check-label" for="admin1"
                        >2 bouffées ventoline</label
                      >
                    </div>
                    <div class="form-check">
                      <input
                        type="checkbox"
                        id="admin2"
                        name="admin2"
                        class="form-check-input"
                      />
                      <label class="form-check-label" for="admin2"
                        >2 bouffées flixotides</label
                      >
                    </div>
                  </td>
                </tr>
                <tr>
                  <th scope="row">Conseils</th>
                  <td>
                    Bouffées de flixotides à refaire chaque 2h, Merci &#128512;
                  </td>
                </tr>
              </tbody>
            </table>

            <div class="text-center">
              <button type="submit" class="btn col-md-6 col-6 mt-4">
                Ajouter
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row d-flex justify-content-center">
      <div class="col-12 col-md-6">
        <div v-if="userType === 'otite'">
          <div class="container mt-4">
            <h5 class="mb-3">Antibiotique</h5>
            <p>
              Il faut donner une seule dose à Noah aprés le repas, Merci
              &#128512;
            </p>
            <div class="table-responsive">
              <table class="table table-bordered">
                <tbody>
                  <tr>
                    <th scoped="row">Temps</th>
                    <td>
                      <input
                        type="time"
                        id="appt1"
                        name="appt1"
                        class="form-control"
                      />
                    </td>
                  </tr>

                  <tr>
                    <th scoped="row">Administration réussie</th>
                    <td class="text-center">
                      <input
                        type="checkbox"
                        id="admin1"
                        name="admin1"
                        class="form-check-input mt-2"
                      />
                    </td>
                  </tr>
                </tbody>
              </table>
              <div class="text-center">
                <button type="submit" class="btn col-md-6 col-6 mt-4">
                  Ajouter
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      userType: "",
    };
  },
};
</script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");
h5 {
  font-family: "Pacifico", cursive;
}
th {
  font-family: "Pacifico", cursive;
  color: black;
}
</style>
