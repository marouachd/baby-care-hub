<script>
import axios from "axios";
export default {
  data() {
    return {
      imageUrl:
        "https://mdbootstrap.com/img/Photos/Others/placeholder-avatar.jpg",
      gardeModes: [],
      genders: [],
      inputs: {
        childId: "",
        firstName: "",
        birthdayDate: "",
        genderId: 0,
        guardId: 0,
        personalPicture: "",
      },
    };
  },
  created() {
    this.$http = axios;
  },
  methods: {
    async getProfile() {
      const response = await this.$http.get(
        `${import.meta.env.VITE_API_BASE_URL}/child/15/detail`
      );
      const data = response.data;
      console.log("data", data);
      this.inputs.lastName = data.personId.lastName;
      this.inputs.firstName = data.personId.firstName;
      this.inputs.birthdayDate = data.birthdayDate;
      this.inputs.genderId = data.genderId.id;
      this.inputs.guardId = data.guardId.id;
      if (data.personId.identityPhotoName) {
        const imageUrl = `/personal-pictures/${data.personId.identityPhotoName}`;
        this.imageUrl = imageUrl;
        this.inputs.personalPicture = data.personId.identityPhotoName;
      }
    },
    async remove(id) {
      const resp = await this.$http.delete(
        `${import.meta.env.VITE_API_BASE_URL}/child/${id}`
      );
      if (resp.status === 204) {
        console.log("ok");
      } else {
        console.log("no");
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
      const resp = await this.$http.put(
        `${import.meta.env.VITE_API_BASE_URL}/child/15`,
        formData
      );
      if (resp.status === 204) {
        console.log("ok");
      } else {
        console.log("No");
      }
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
          <button class="btn btn-danger mb-2 ms-md-3" @click="remove(14)">
            Quitter
          </button>
        </div>
      </div>
    </form>
  </section>
</template>
<style>
img {
  cursor: pointer;
}
.circle-image {
  width: 150px;
  height: 150px;
  object-fit: cover;
}
</style>
