<script>
import { RouterLink, useRoute } from "vue-router";
import axios from "axios";
export default {
  setup() {
    return {
      route: useRoute(),
    };
  },
  data() {
    return {
      baseUrl: import.meta.env.VITE_IMG_BASE_URL,
      id: this.route.params.id,
      childs: [],
    };
  },

  methods: {
    async getChilds() {
      const response = await this.$axios.get(`/child/childminder/${this.id}`);
      this.childs = response.body;
      console.log("child", this.childs);
    },

    calculateAge(birthdayDate) {
      const birthDate = new Date(birthdayDate);
      const currentDate = new Date();
      const age = currentDate.getFullYear() - birthDate.getFullYear();
      return age;
    },

    getChildImage(child) {
      if (child.personId.identityPhotoName) {
        return "/personal-pictures/" + child.personId.identityPhotoName;
      } else {
        return "/personal-pictures/placeholder-avatar.jpg";
      }
    },
  },
  beforeMount() {
    this.getChilds();
    this.id = localStorage.getItem("userId");
  },
};
</script>
<template>
  <div class="countainer mx-3 my-3">
    <h1 class="mt-4 mb-4 text-center">Mes bouts de choux</h1>

    <div class="row row-cols-2 row-cols-md-3 g-4 mt-5 mx-5 position-relative">
      <div class="col-12 col-md-2" v-for="child in childs">
        <div
          v-if="!child.accepted"
          class="text-danger position-absolute"
          style="top: 0; left: 10; z-index: 1"
        >
          Nouvelle demande
        </div>
        <div
          class="card h-100 bg-light"
          :class="{ disabledCard: !child.active || !child.accepted }"
        >
          <img :src="getChildImage(child)" class="card-img-top" alt="..." />
          <div class="card-body d-flex flex-column justify-content-end">
            <h5 class="card-title">
              {{ child.personId.firstName }}&ensp;{{ child.personId.lastName }}
            </h5>
            <p class="card-text">
              Age : {{ calculateAge(child.birthdayDate) }} ans
            </p>
            <div class="d-flex justify-content-between">
              <RouterLink
                :to="{ name: 'general', params: { id: child.id } }"
                class="btn"
                :class="('btn', { disabled: !child.active })"
              >
                <i class="fas fa-pen"></i>
              </RouterLink>

              <RouterLink
                :to="{ name: 'fiche-enfant', params: { id: child.id } }"
                class="btn"
                :class="('btn', { disabled: !child.active })"
              >
                <i class="fa-solid fa-magnifying-glass"></i>
              </RouterLink>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");

h1 {
  font-family: "Pacifico", cursive;
  color: rgb(160, 197, 237);
}
style pour bouton {
  background-color: rgba(180, 95, 146, 0.674);
  font-family: "Pacifico", cursive;
  color: white;
}
a {
  background-color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  color: white;
}

h5 {
  font-family: "Pacifico", cursive;
}
p {
  font-family: "Satisfy", cursive;
}
.disabledCard {
  opacity: 0.3;
  border: 2px solid red;
}
</style>
