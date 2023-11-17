<script>
export default {
  data() {
    return {
      toDayDate: null,
      showDate: false,
      showDatePicker: false,
      signedUser: false,
    };
  },
  mounted() {
    this.afficheDate();
  },
  methods: {
    afficheDate() {
      const today = new Date().toISOString().slice(0, 10);
      this.toDayDate = today;
    },
    toggleDatePicker() {
      this.showDatePicker = !this.showDatePicker;
    },
    toggleSignedUser() {
      this.signedUser = !this.signedUser;
    },
    cleanLocalStorage() {
      localStorage.clear();
    },
  },
  watch: {
    $route(to) {
      this.showDate = to.path.startsWith("/ma-journee");
    },
  },
};
</script>
<template>
  <nav class="navbar navbar-expand-md bg-light">
    <div class="container-fluid">
      <h1 class="col-4 col-md-2">
        <div :class="['d-flex flex-column', { 'flex-md-row': !isSmallScreen }]">
          <span class="baby">Baby</span>
          <span class="care">Care</span>
          <span class="hub">Hub</span>
        </div>
      </h1>

      <div class="col-5 col-md-2">
        <div class="row" v-if="showDate">
          <h5
            @click="toggleDatePicker"
            class="clickable text-nowrap text-center"
          >
            {{ showDatePicker ? "" : toDayDate }}
          </h5>
          <input
            type="date"
            class="form-control"
            id="datepicker"
            v-model="toDayDate"
            @change="toggleDatePicker"
            v-if="showDatePicker"
          />
        </div>
        <div class="container">
          <div class="row">
            <div
              class="collapse navbar-collapse justify-content-center"
              id="navbar"
            >
              <ul class="navbar-nav d-flex justify-content-center">
                <li class="nav-item mx-2">
                  <!--<RouterLink
                    :to="{ name: 'acceuil', params: { id: 2 } }"
                    class="dropdown-item"
                  >
                    <h6 class="text-decoration-underline">Acceuil</h6>
                  </RouterLink>-->
                </li>
                <li class="nav-item mx-2">
                  <RouterLink
                    :to="{ name: 'edit-user-account' }"
                    class="dropdown-item"
                  >
                    <h6 class="text-decoration-underline">Profile</h6>
                  </RouterLink>
                </li>
                <li class="nav-item mx-2">
                  <RouterLink
                    :to="{ name: 'mes-enfants', params: { id: 2 } }"
                    class="dropdown-item"
                  >
                    <h6 class="text-decoration-underline">Mes enfants</h6>
                  </RouterLink>
                </li>
                <li class="nav-item mx-2">
                  <RouterLink
                    :to="{ name: 'actualitees', params: { id: 2 } }"
                    class="dropdown-item"
                  >
                    <h6 class="text-decoration-underline">Actualités</h6>
                  </RouterLink>
                </li>
                <li
                  class="nav-item mx-2"
                  @click="toggleSignedUser"
                  v-if="!signedUser"
                >
                  <RouterLink :to="{ name: 'signin' }" class="dropdown-item">
                    <h6 class="text-decoration-underline">Sign In</h6>
                  </RouterLink>
                </li>
                <li
                  class="nav-item mx-2"
                  @click="toggleSignedUser"
                  v-if="signedUser"
                >
                  <RouterLink
                    :to="{ name: 'signin' }"
                    class="dropdown-item"
                    @click="cleanLocalStorage"
                  >
                    <h6 class="text-decoration-underline">Sign Out</h6>
                  </RouterLink>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>

      <RouterLink :to="{ name: 'fiche-enfant', params: { id: 2 } }">
        <div class="image-container rounded-circle">
          <img src="../../assets/Noah.jpg" class="bebe-image" />
        </div>
      </RouterLink>
    </div>
  </nav>
</template>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");

@import url("https://fonts.googleapis.com/css2?family=Pacifico&family=Satisfy&display=swap");

.image-container {
  border: 2px solid rgba(180, 95, 146, 0.674);
  border-radius: 50%;
  width: 70px;
  height: 70px;
  overflow: hidden;
}

.bebe-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.baby {
  color: rgba(180, 95, 146, 0.674);
  font-family: "Pacifico", cursive;
  text-decoration: none;
}

.care {
  color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  text-decoration: none;
}

.hub {
  color: rgba(180, 95, 146, 0.674);
  font-family: "Pacifico", cursive;
  text-decoration: none;
}

p {
  color: rgba(180, 95, 146, 0.674);
  font-family: "Satisfy", cursive;
}
.routerLink {
  text-decoration: none;
}
.clickable {
  cursor: pointer;
}
h5 {
  font-family: "Satisfy", cursive;
}
h6 {
  font-family: "Satisfy", cursive;
  color: rgb(160, 197, 237);
}
</style>
