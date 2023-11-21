<script>
import axios from "axios";
export default {
  data() {
    return {
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
        roleId: 2,
      },
    };
  },
  created() {
    this.$http = axios;
  },
  methods: {
    back() {
      this.$router.push({
        name: "signin",
      });
    },
    async submit() {
      const formData = new FormData();
      if (this.inputs.personalPicture) {
        formData.append("personalPicture", this.inputs.personalPicture);
      } else {
        formData.append("personalPicture", this.imageUrl);
      }
      formData.append("lastName", this.inputs.lastName);
      formData.append("firstName", this.inputs.firstName);
      formData.append("password", this.inputs.password);
      formData.append("phoneNumber", this.inputs.phoneNumber);
      formData.append("roleId", this.inputs.roleId);
      formData.append("pseudoName", this.inputs.pseudoName);
      formData.append("mailAdress", this.inputs.mailAdress);

      console.log("pseudoName:", this.inputs.pseudoName);
      const resp = await this.$http.post(
        `${import.meta.env.VITE_API_BASE_URL}/user`,
        formData
      );
      console.log("Status de la réponse:", resp.status);

      if (resp.status === 200) {
        this.$toast.success(
          "toast-global",
          "Votre compte a été créer avec succées."
        );
      } else {
        this.$toast.error("toast-global", "Un problème est survenu.");
      }
      this.$router.push({
        name: "signin",
      });
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
        this.inputs.personalPicture = event.target.files[0];
      }
    },
    triggerFileInput() {
      this.$refs.fileInput.click();
    },
  },
  mounted() {
    this.initRoles();
  },
};
</script>

<template>
  <section class="container-xl text-center mb-4">
    <h1 class="mt-4 mb-5">Créer un compte utilisateur</h1>

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
                        >Nom :</span
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
                        >Prénom :</span
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
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1"
                  >Numéro de télephone :</span
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
                  >Adresse email:</span
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
              <div class="input-group mb-4">
                <span class="input-group-text" id="basic-addon1"
                  >Mot de passe:</span
                >
                <input
                  type="password"
                  class="form-control"
                  placeholder="******"
                  id="password"
                  name="password"
                  v-model="inputs.password"
                />
              </div>
            </div>
          </div>

          <div class="col-md-6 col-12">
            <div class="col-md-6 input-group mb-3">
              <span class="input-group-text" id="basic-addon1"
                >Rejoindre cette application en tant que :</span
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
                <h5 class="mb-3">Entrez votre identifiant :</h5>
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
                <h5 class="mb-3">Coordonnées deuxième parent :</h5>
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon1"
                    >Prénom :</span
                  >
                  <input
                    type="text"
                    class="form-control"
                    placeholder="Prénom"
                  />
                </div>
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon1"
                    >Numéro de télephone :</span
                  >
                  <input
                    type="text"
                    class="form-control"
                    placeholder="téléphone"
                  />
                </div>
                <div class="input-group">
                  <span class="input-group-text" id="basic-addon1"
                    >Adresse email:</span
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

        <div class="col-12 input-group mb-5">
          <span class="input-group-text" id="basic-addon1">Adresse :</span>
          <input type="text" class="form-control" placeholder="Adresse" />
        </div>
      </div>

      <div class="d-flex justify-content-center mt-5 mb-5 mx-2">
        <div class="justify-content-center">
          <button class="btn btn-confirmer mb-2 ms-md-3 me-3" type="submit">
            Confirmer
          </button>
          <button class="btn btn-quitter mb-2 ms-md-3 px-4" @click="back">
            Quitter
          </button>
        </div>
      </div>
    </form>
  </section>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");
h5 {
  font-family: "Pacifico", cursive;
  color: rgb(166, 161, 161);
}
h1 {
  font-family: "Pacifico", cursive;
  color: rgba(180, 95, 146, 0.674);
}
.btn-quitter {
  background-color: rgba(180, 95, 146, 0.674);
  font-family: "Pacifico", cursive;
  color: white;
}
.btn-confirmer {
  background-color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  color: white;
}
</style>
