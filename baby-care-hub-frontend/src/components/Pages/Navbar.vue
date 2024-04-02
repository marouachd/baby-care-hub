<script>
import { RouterLink, useRoute } from "vue-router";
export default {
  setup() {
    return {
      route: useRoute(),
    };
  },
  data() {
    return {
      roleId: "",
      id: this.route.params.id,
      toDayDate: null,
      showDate: false,
      showDatePicker: false,
      isLoggedIn: false,
      userId: "",
    };
  },

  mounted() {
    this.id = this.route.params.id;
    this.afficheDate();
    localStorage.setItem("date", this.toDayDate);
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
      this.isLoggedIn = false;
    },
  },
  watch: {
    $route(to) {
      this.showDate = to.path.startsWith("/ma-journee");
      this.isLoggedIn = Boolean(
        localStorage.getItem("userId") && localStorage.getItem("roleId")
      );
    },
    deep: true,
  },
  beforeUpdate() {
    this.roleId = localStorage.getItem("roleId");
    this.userId = localStorage.getItem("userId");
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
          <h4
            @click="toggleDatePicker"
            class="clickable text-nowrap text-center"
          >
            {{ showDatePicker ? "" : toDayDate }}
          </h4>
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
                  <RouterLink
                    :to="{ name: 'home', params: { id: this.userId } }"
                    class="dropdown-item"
                    v-if="isLoggedIn && this.roleId == 1"
                  >
                    <h5 class="text-decoration-underline">
                      <i class="fa fa-home" aria-hidden="true">{{
                        $t("navbar.accueil")
                      }}</i>
                    </h5>
                  </RouterLink>
                </li>

                <li class="nav-item mx-2">
                  <RouterLink
                    :to="{ name: 'coordonees', params: { id: this.userId } }"
                    class="dropdown-item"
                    v-if="isLoggedIn"
                  >
                    <h5 class="text-decoration-underline">
                      <i class="fa fa-user ms-3" aria-hidden="true">{{
                        $t("navbar.profile")
                      }}</i>
                    </h5>
                  </RouterLink>
                </li>

                <li class="nav-item mx-2">
                  <RouterLink
                    :to="{ name: 'mes-enfants', params: { id: this.id } }"
                    class="dropdown-item"
                    v-if="isLoggedIn && roleId == 2"
                  >
                    <h5 class="text-decoration-underline">
                      <i class="fa fa-child" aria-hidden="true">{{
                        $t("navbar.mesEnfants")
                      }}</i>
                    </h5>
                  </RouterLink>
                </li>

                <li class="nav-item mx-2">
                  <RouterLink
                    :to="{ name: 'signin' }"
                    class="dropdown-item"
                    @click="cleanLocalStorage"
                    v-if="isLoggedIn"
                  >
                    <h5 class="text-decoration-underline">
                      <i class="fas fa-sign-out-alt">{{
                        $t("navbar.signout")
                      }}</i>
                    </h5>
                  </RouterLink>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>

      <!-- <div class="locale-changer">
        <select v-model="$i18n.locale" class="rounded">
          <option
            v-for="locale in $i18n.availableLocales"
            :key="`locale-${locale}`"
            :value="locale"
          >
            {{ locale }}
          </option>
        </select>
      </div>-->

      <div class="image-container rounded-circle">
        <img src="../../../public/favicon.jpg" class="bebe-image" />
      </div>
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
h4 {
  font-family: "Satisfy", cursive;
}
h5 {
  font-family: "Satisfy", cursive;
  color: rgb(160, 197, 237);
}
</style>
