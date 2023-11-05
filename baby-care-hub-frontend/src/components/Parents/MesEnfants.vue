<template>
  <div id="app" class="container mt-5 mb-5">
    <h1 class="mb-3 text-center">Mes enfants</h1>

    <div class="row d-flex justify-content-center mb-3 mt-5 mx-2">
      <div
        class="col-md-6 col-6 border rounded pt-1 pb-1 clickable"
        v-on:click="AjouterEnfant()"
      >
        <div class="d-flex justify-content-center">
          <img
            src="https://mdbootstrap.com/img/Photos/Others/placeholder-avatar.jpg"
            class="img-fluid rounded-circle"
            alt="..."
            width="50"
            height="50"
          />

          <h5 class="title mt-3 ms-2 me-5 text-nowrap">Ajouter un enfant</h5>

          <button class="mb-2 ms-md-3 mt-2">
            <i class="fa fa-plus"></i>
          </button>
        </div>
      </div>
    </div>

    <div class="row d-flex justify-content-center mb-3 mt-5 mx-5">
      <div class="col-md-6 col-12 mb-2 mb-2 align-items-center text-center">
        <div class="d-flex justify-content-center">
          <div class="row">
            <div class="col d-flex justify-content-center mx-5">
              <img
                src="../../assets/activité.jpg"
                class="img-fluid rounded-start me-3"
                alt="..."
                width="50"
                height="50"
              />

              <h5 class="title mt-3 ms-2 text-nowrap">Eyad Chroud</h5>

              <div
                id="navbar"
                class="col d-flex justify-content-center collapse navbar-collapse"
              >
                <ul class="navbar-nav">
                  <li class="nav-item dropdown">
                    <a
                      class="nav-link dropdown-toggle-no-arrow"
                      href="#"
                      role="button"
                      data-bs-toggle="dropdown"
                      style="color: rgb(129, 126, 126); font-size: 24px"
                    >
                      ...
                    </a>
                    <ul class="dropdown-menu">
                      <li>
                        <RouterLink
                          :to="{ name: 'edit-profile-enfant' }"
                          class="dropdown-item"
                        >
                          Modifier</RouterLink
                        >
                      </li>
                      <li>
                        <RouterLink
                          :to="{ name: 'fiche-enfant' }"
                          class="dropdown-item"
                        >
                          Détails</RouterLink
                        >
                      </li>
                      <li>
                        <RouterLink
                          :to="{ name: 'actualitees' }"
                          class="dropdown-item"
                        >
                          Actualités</RouterLink
                        >
                      </li>
                    </ul>
                  </li>
                </ul>
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
      showMenu: false,
    };
  },
  methods: {
    AjouterEnfant() {
      this.$router.push("/create-profile-enfant");
    },
  },
};
</script>
<style>
@media (max-width: 768px) {
  .border.rounded {
    width: 100%;
    box-sizing: border-box;
  }
}
.clickable {
  cursor: pointer;
}

button {
  background-color: white;
  color: rgb(129, 126, 126);
  border: none;
}
</style>
