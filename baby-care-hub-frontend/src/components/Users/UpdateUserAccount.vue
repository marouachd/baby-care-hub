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
      id: this.route.params.id,
      imageUrl:
        "https://mdbootstrap.com/img/Photos/Others/placeholder-avatar.jpg",

      userType: "",
      roles: [],
      inputs: {
        mailAdress: "",
        phoneNumber: "",
        password: "",
        firstName: "",
        lastName: "",
        pseudoName: "",
        personalPicture: new File(
          [
            /* personal-pictures */
          ],
          "placeholder-avatar.jpg",
          {
            type: "image/jpeg",
          }
        ),
        roleId: "",
      },
    };
  },
  created() {
    this.$http = axios;
  },
  methods: {
    async getUserProfile() {
      const response = await this.$http.get(
        `${import.meta.env.VITE_API_BASE_URL}/user/${this.id}/detail`
      );
      const data = response.data;
      console.log("data", data);
      this.inputs.lastName = data.personId.lastName;
      this.inputs.firstName = data.personId.firstName;
      this.inputs.password = data.password;
      this.inputs.roleId = data.roleId.id;
      this.inputs.phoneNumber = data.phoneNumber;
      this.inputs.mailAdress = data.mailAdress;
      this.inputs.pseudoName = data.personId.pseudoName;
      if (data.personId.identityPhotoName) {
        const imageUrl = `/personal-pictures/${data.personId.identityPhotoName}`;
        this.imageUrl = imageUrl;
        this.inputs.personalPicture = new File(
          [],
          data.personId.identityPhotoName
        );
      }
    },
    async submit() {
      const formData = new FormData();
      if (this.inputs.personalPicture && this.inputs.personalPicture.size > 0) {
        formData.append("personalPicture", this.inputs.personalPicture);
      }
      formData.append("lastName", this.inputs.lastName);
      formData.append("firstName", this.inputs.firstName);
      formData.append("password", this.inputs.password);
      formData.append("phoneNumber", this.inputs.phoneNumber);
      formData.append("roleId", this.inputs.roleId);
      formData.append("pseudoName", this.inputs.pseudoName);
      formData.append("mailAdress", this.inputs.mailAdress);
      const resp = await this.$http.patch(
        `${import.meta.env.VITE_API_BASE_URL}/user/${this.id}`,
        formData
      );
      console.log("Status de la réponse:", resp.status);

      if (resp.status === 204) {
        this.$toast.success(
          "toast-global",
          "Votre compte a été modifier avec succées."
        );

        this.$router.push({
          name: "coordonees",
          params: { id: this.id },
        });
      } else {
        this.$toast.error("toast-global", "Un problème est survenu.");
      }
    },
    async initRoles() {
      const response = await this.$http.get(
        `${import.meta.env.VITE_API_BASE_URL}/role`
      );
      this.roles = response.data;
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
  },

  mounted() {
    this.initRoles();
    this.getUserProfile();
  },
  beforeMount() {
    this.id = localStorage.getItem("userId");
    this.roleId = localStorage.getItem("roleId");
  },
};
</script>

<template>
  <section class="container-xl text-center mb-4">
    <h1 class="mt-4 mb-5">{{ $t("userAccount.titleUpdate") }}</h1>

    <form class="my-4" @submit.prevent="submit">
      <div class="container mt-5">
        <div class="row g-2 g-lg-3 mt-5">
          <div class="col-md-6 col-12">
            <div class="text-center">
              <div class="row row-cols-2">
                <div class="col-4 col-md-4">
                  <div class="text-center">
                    <img
                      ref="profileImage"
                      :src="imageUrl"
                      class="img-fluid h-50 w-50 mb-4"
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

                <div class="col-8 col-md-8">
                  <div class="mb-5">
                    <div class="input-group mb-3">
                      <span class="input-group-text" id="basic-addon1"
                        >{{ $t("userAccount.nom") }} :</span
                      >
                      <input
                        type="text"
                        class="form-control"
                        placeholder="Nom"
                        id="lastName"
                        name="lastName"
                        v-model="inputs.lastName"
                      />
                    </div>
                    <div class="input-group mb-5">
                      <span class="input-group-text" id="basic-addon2"
                        >{{ $t("userAccount.prenom") }} :</span
                      >
                      <input
                        type="text"
                        class="form-control"
                        placeholder="Prénom"
                        id="firstName"
                        name="firstName"
                        v-model="inputs.firstName"
                      />
                    </div>
                  </div>
                </div>
              </div>
              <div class="input-group mb-3 mt-2">
                <span class="input-group-text" id="basic-addon1"
                  >{{ $t("userAccount.tel") }} :</span
                >
                <input
                  type="text"
                  class="form-control"
                  placeholder="téléphone"
                  id="phoneNumber"
                  name="phoneNumber"
                  v-model="inputs.phoneNumber"
                />
              </div>
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1"
                  >{{ $t("userAccount.email") }}:</span
                >
                <input
                  type="email"
                  class="form-control"
                  placeholder="Adresse email"
                  id="mailAdress"
                  name="mailAdress"
                  v-model="inputs.mailAdress"
                />
              </div>
              <div class="col-12 input-group mb-5">
                <span class="input-group-text" id="basic-addon1"
                  >{{ $t("userAccount.adress") }} :</span
                >
                <input type="text" class="form-control" placeholder="Adresse" />
              </div>
            </div>
          </div>

          <div class="col-md-6 col-12">
            <div class="col-md-6 input-group mb-3">
              <span class="input-group-text" id="basic-addon1"
                >{{ $t("userAccount.role") }} :</span
              >
              <select
                class="form-select"
                aria-label="Default select example"
                v-model.number="inputs.roleId"
                id="roleId"
                name="roleId"
              >
                <option selected>--------</option>
                <option v-for="role in roles" :value="role.id">
                  {{ role.name }}
                </option>
              </select>
            </div>
            <div v-if="inputs.roleId === 1">
              <div class="input-group mb-3">
                <h5 class="mb-3">{{ $t("userAccount.id") }} :</h5>
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon1">ID :</span>
                  <input
                    type="text"
                    class="form-control"
                    placeholder="MAS241278"
                    v-model="inputs.pseudoName"
                    id="pseudoName"
                    name="pseudoName"
                  />
                </div>
              </div>
            </div>
            <div v-if="inputs.roleId === 2">
              <div class="input-group mb-3">
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon1"
                    >Pseudo:</span
                  >
                  <input
                    type="text"
                    class="form-control"
                    placeholder="Best mom"
                    v-model="inputs.pseudoName"
                    id="pseudoName"
                    name="pseudoName"
                  />
                </div>
                <h5 class="mb-3">{{ $t("userAccount.secondParent") }} :</h5>
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon1"
                    >{{ $t("userAccount.prenom") }} :</span
                  >
                  <input
                    type="text"
                    class="form-control"
                    placeholder="Prénom"
                  />
                </div>
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon1"
                    >{{ $t("userAccount.tel") }} :</span
                  >
                  <input
                    type="text"
                    class="form-control"
                    placeholder="téléphone"
                  />
                </div>
                <div class="input-group">
                  <span class="input-group-text" id="basic-addon1"
                    >{{ $t("userAccount.email") }}:</span
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
      </div>

      <div class="d-flex justify-content-center mt-5 mb-5 mx-2">
        <button class="btn btn-confirmer mb-2 ms-md-3 me-3" type="submit">
          {{ $t("userAccount.buttons.confirm") }}
        </button>
      </div>
    </form>
  </section>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");
h1 {
  font-family: "Pacifico", cursive;
  color: rgba(180, 95, 146, 0.674);
}
h5 {
  font-family: "Pacifico", cursive;
  color: rgb(166, 161, 161);
}

.btn-confirmer {
  background-color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  color: white;
}
</style>
