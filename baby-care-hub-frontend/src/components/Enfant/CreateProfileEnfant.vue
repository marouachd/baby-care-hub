<script>
import axios from "axios";
export default {
  data() {
    return {
      id: "",
      token: "",
      childminderCode: "",
      imageUrl:
        "https://mdbootstrap.com/img/Photos/Others/placeholder-avatar.jpg",

      gardeModes: [],
      genders: [],
      inputs: {
        lastName: "",
        firstName: "",
        pseudoName: "",
        birthdayDate: "",
        genderId: 0,
        guardId: 0,
        personalPicture: new File(
          [
            /* personal-pictures */
          ],
          "placeholder-avatar.jpg",
          {
            type: "image/jpeg",
          }
        ),
      },
    };
  },
  created() {
    this.$http = axios;
    this.id = localStorage.getItem("userId");
  },
  methods: {
    async submit() {
      const formData = new FormData();
      if (this.inputs.personalPicture) {
        formData.append("personalPicture", this.inputs.personalPicture);
      } else {
        formData.append("personalPicture", this.imageUrl);
      }
      formData.append("lastName", this.inputs.lastName);
      formData.append("firstName", this.inputs.firstName);
      formData.append("pseudoName", this.inputs.pseudoName);
      formData.append("birthdayDate", this.inputs.birthdayDate);
      formData.append("genderId", this.inputs.genderId);
      formData.append("guardId", this.inputs.guardId);
      formData.append("token", this.token);
      formData.append("childminderCode", this.childminderCode);
      const resp = await this.$http.post(
        `${import.meta.env.VITE_API_BASE_URL}/child`,
        formData
      );
      if (resp.status === 204) {
        this.$toast.success(
          "toast-global",
          "Le profile de votre enfant a été crée."
        );
      } else {
        this.$toast.error("toast-global", "Un problème est survenu.");
      }
      this.$router.push({
        name: "mes-enfants",
        params: { id: this.id },
      });
    },
    updateImage(event) {
      const file = event.target.files[0];
      if (file) {
        const imageUrl = URL.createObjectURL(file);
        this.imageUrl = imageUrl;
        this.inputs.personalPicture = event.target.files[0];
      }
    },
    triggerFileInput() {
      this.$refs.fileInput.click();
    },
    async initGardeModes() {
      const response = await this.$http.get(
        `${import.meta.env.VITE_API_BASE_URL}/guard-mode`
      );
      this.gardeModes = response.data;
    },
    async initGenders() {
      const response = await this.$http.get(
        `${import.meta.env.VITE_API_BASE_URL}/gender`
      );
      this.genders = response.data;
    },
    back() {
      console.log("back");
      this.$router.push({
        name: "mes-enfants",
        params: { id: this.id },
      });
    },
  },

  mounted() {
    this.initGardeModes();
    this.initGenders();
  },
  beforeMount() {
    this.token = localStorage.getItem("token");
    this.childminderCode = localStorage.getItem("childminderCode");
    console.log("token", this.token);
    console.log("childminderCode", this.childminderCode);
  },
};
</script>
<template>
  <section class="container-xl text-center mb-4">
    <h1 class="mt-4 mb-5">Créer un profil enfant</h1>

    <form class="my-4" @submit.prevent="submit">
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
                id="personalPicture"
                name="personalPicture"
              />
            </div>
          </div>

          <div class="col-md-4">
            <div class="mb-3">
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">Nom :</span>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Nom"
                  id="lastName"
                  name="lastName"
                  v-model="inputs.lastName"
                />
              </div>
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon2">Prénom :</span>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Prénom"
                  id="firstName"
                  name="firstName"
                  v-model="inputs.firstName"
                />
              </div>

              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon3"
                  >Date de naissance :</span
                >
                <input
                  type="date"
                  class="form-control datepicker"
                  id="birthdayDate"
                  name="birthdayDate"
                  v-model="inputs.birthdayDate"
                />
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6 input-group mb-3 mt-3">
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon2">Pseudo:</span>
            <input
              type="text"
              class="form-control"
              placeholder="Little girl"
              id="pseudoName"
              name="pseudoName"
              v-model="inputs.pseudoName"
            />
          </div>
          <div class="col-md-6 input-group">
            <span class="input-group-text" id="basic-addon5">Sexe:</span>
            <select
              v-model.number="inputs.genderId"
              id="genderId"
              name="genderId"
              class="form-select"
            >
              <option selected disabled value="0">Choisir le sexe</option>
              <option v-for="gender in genders" :value="gender.id">
                {{ gender.name }}
              </option>
            </select>
          </div>
        </div>
        <div class="col-md-6 input-group mb-3">
          <span class="input-group-text" id="basic-addon5">Mode de garde</span>
          <select
            v-model.number="inputs.guardId"
            id="guardId"
            name="guardId"
            class="form-select"
          >
            <option selected disabled value="0">
              Choisir un mode de garde
            </option>
            <option v-for="gardeMode in gardeModes" :value="gardeMode.id">
              {{ gardeMode.name }}
            </option>
          </select>
        </div>
      </div>
      <div class="text-center mt-5 mb-5">
        <div class="justify-content-center">
          <button class="btn btn-secondary mb-2 ms-md-3 me-3" type="submit">
            Confirmer
          </button>
          <button class="btn btn-danger mb-2 ms-md-3" @click="back">
            Quitter
          </button>
        </div>
      </div>
    </form>
  </section>
</template>
<style>
h1 {
  font-family: "Pacifico", cursive;
  color: rgba(180, 95, 146, 0.674);
}
#button {
  background-color: rgba(180, 95, 146, 0.674);
  font-family: "Pacifico", cursive;
  color: white;
}
img {
  cursor: pointer;
}
.circle-image {
  width: 150px;
  height: 150px;
  object-fit: cover;
}
</style>
