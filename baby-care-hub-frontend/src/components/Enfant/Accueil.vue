<script>
import { RouterLink } from "vue-router";
import axios from "axios";
export default {
  data() {
    return {
      baseUrl: import.meta.env.VITE_IMG_BASE_URL,

      childs: [],
    };
  },
  created() {
    this.$http = axios;
  },
  methods: {
    async getChilds() {
      const response = await this.$http.get(
        `${import.meta.env.VITE_API_BASE_URL}/child`
      );
      this.childs = response.data;
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
  },
};
</script>
<template>
  <div class="countainer mx-3 my-3">
    <h1 class="mt-4 mb-4 text-center">Mes bouts de choux</h1>

    <div class="row row-cols-2 row-cols-md-3 g-4 mt-4">
      <div class="col-12 col-md-2" v-for="child in childs">
        <div class="card h-100 bg-light">
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
              >
                <i class="fas fa-pen"></i>
              </RouterLink>

              <RouterLink
                :to="{ name: 'fiche-enfant', params: { id: child.id } }"
                class="btn"
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
#button {
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
</style>
