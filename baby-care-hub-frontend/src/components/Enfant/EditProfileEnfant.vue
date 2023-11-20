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
      userId: "",
      id: this.route.params.id,
      imageUrl:
        "https://mdbootstrap.com/img/Photos/Others/placeholder-avatar.jpg",
      gardeModes: [],
      genders: [],
      inputs: {
        childId: "",
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
    this.userId = localStorage.getItem("userId");
  },
  methods: {
    async getProfile() {
      const response = await this.$http.get(
        `${import.meta.env.VITE_API_BASE_URL}/child/${this.id}/detail`
      );
      const data = response.data;
      console.log("data", data);
      this.inputs.lastName = data.personId.lastName;
      this.inputs.firstName = data.personId.firstName;
      this.inputs.birthdayDate = data.birthdayDate;
      this.inputs.genderId = data.genderId.id;
      this.inputs.guardId = data.guardId.id;
      this.inputs.pseudoName = data.personId.pseudoName;
      if (data.personId.identityPhotoName) {
        const imageUrl = `/personal-pictures/${data.personId.identityPhotoName}`;
        this.imageUrl = imageUrl;
        this.inputs.personalPicture = data.personId.identityPhotoName;
      }
    },

    async submit() {
      const formData = new FormData();
      formData.append("personalPicture", this.inputs.personalPicture);
      formData.append("lastName", this.inputs.lastName);
      formData.append("firstName", this.inputs.firstName);
      formData.append("birthdayDate", this.inputs.birthdayDate);
      formData.append("genderId", this.inputs.genderId);
      formData.append("guardId", this.inputs.guardId);
      formData.append("pseudoName", this.inputs.pseudoName);
      const resp = await this.$http.put(
        `${import.meta.env.VITE_API_BASE_URL}/child/${this.id}`,
        formData
      );
      if (resp.status === 204) {
        this.$toast.success(
          "toast-global",
          "Le profile de votre enfant a été modifié."
        );
      } else {
        this.$toast.error("toast-global", "Un problème est survenu.");
      }

      this.$router.push({
        name: "mes-enfants",
        params: { id: this.userId },
      });
    },
    updateImage(event) {
      const file = event.target.files[0];
      if (file) {
        const imageUrl = URL.createObjectURL(file);
        this.imageUrl = imageUrl;
        this.inputs.personalPicture = file;
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
      this.$router.push({
        name: "mes-enfants",
        params: { id: this.userId },
      });
    },
  },
  mounted() {
    this.initGardeModes();
    this.initGenders();
    this.getProfile();
  },
};
</script>
<template>
  <section class="container-xl text-center mb-4">
    <h1 class="mt-4 mb-5">Editer un profil enfant</h1>

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
        </div>

        <div class="col-md-6 input-group mb-3">
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
img {
  cursor: pointer;
}
.circle-image {
  width: 150px;
  height: 150px;
  object-fit: cover;
}
</style>
