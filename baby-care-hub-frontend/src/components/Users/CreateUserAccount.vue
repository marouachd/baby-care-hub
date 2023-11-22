<script>
import axios from "axios";
import { useVuelidate } from "@vuelidate/core";
import {
  required,
  email,
  maxLength,
  minLength,
  minValue,
  helpers,
  numeric,
} from "@vuelidate/validators";
export default {
  setup() {
    return { v$: useVuelidate() };
  },
  data() {
    return {
      file: "",
      nofile: false,
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
  validations() {
    return {
      inputs: {
        mailAdress: { required, email },
        phoneNumber: {
          required,
          numeric,
          minLength: minLength(10),
          maxLength: maxLength(10),
        },
        password: {
          required,
          minLength: minLength(8),
          maxLength: maxLength(16),
        },
        firstName: {
          required: helpers.withMessage(
            "Veuillez renseigner ce champ.",
            required
          ),
          maxLength: maxLength(100),
        },
        lastName: { required, maxLength: maxLength(100) },
        pseudoName: {
          required,
          minLength: minLength(4),
          maxLength: maxLength(30),
        },
        personalPicture: {
          required,
        },
        roleId: { required },
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
      const result = await this.v$.$validate();
      if (result) {
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
      }
      if (!this.file) {
        this.$refs.profileImage.classList.add("noFile");
        this.nofile = true;
      }
    },
    async initRoles() {
      const response = await this.$http.get(
        `${import.meta.env.VITE_API_BASE_URL}/role`
      );
      this.roles = response.data;
    },
    updateImage(event) {
      this.file = event.target.files[0];
      if (this.file) {
        const imageUrl = URL.createObjectURL(this.file);
        this.imageUrl = imageUrl;
        this.inputs.personalPicture = event.target.files[0];
        this.nofile = false;
        this.$refs.profileImage.classList.remove("noFile");
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
                      class="img-fluid h-50 w-50"
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
                    <div class="form-text text-danger" v-if="this.nofile">
                      Image de taille moins de 500Ko est requis!
                    </div>
                    <div id="image-helptext" class="fw-light" v-else>
                      Votre photo de profile
                    </div>
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
                        :class="{ 'is-invalid': v$.inputs.lastName.$error }"
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
                        :class="{ 'is-invalid': v$.inputs.firstName.$error }"
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
                  type="tel"
                  class="form-control"
                  placeholder="téléphone"
                  id="phoneNumber"
                  name="phoneNumber"
                  v-model="inputs.phoneNumber"
                  :class="{ 'is-invalid': v$.inputs.phoneNumber.$error }"
                  pattern="[0-9]*"
                  inputmode="numeric"
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
                  :class="{ 'is-invalid': v$.inputs.mailAdress.$error }"
                />
              </div>

              <div class="input-group">
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
                  :class="{ 'is-invalid': v$.inputs.password.$error }"
                />
              </div>
              <div id="image-helptext" class="fw-light ms-4">
                Longeur minimum de mot de passe est 8 caracéres!
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
                :class="{ 'is-invalid': v$.inputs.roleId.$error }"
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

                <div class="input-group">
                  <span class="input-group-text" id="basic-addon1">ID :</span>
                  <input
                    type="text"
                    class="form-control"
                    placeholder="MAS241278"
                    v-model="inputs.pseudoName"
                    :class="{ 'is-invalid': v$.inputs.pseudoName.$error }"
                    id="pseudoName"
                    name="pseudoName"
                  />
                </div>
                <div id="image-helptext" class="fw-light">
                  Veuillez renseigner votre identifiant!
                </div>
              </div>
            </div>
            <div v-if="inputs.roleId === 2">
              <div class="input-group mb-3">
                <div class="input-group">
                  <span class="input-group-text" id="basic-addon1"
                    >Pseudo:</span
                  >
                  <input
                    type="text"
                    class="form-control"
                    placeholder="Best mom"
                    v-model="inputs.pseudoName"
                    :class="{ 'is-invalid': v$.inputs.pseudoName.$error }"
                    id="pseudoName"
                    name="pseudoName"
                  />
                </div>
                <div id="image-helptext" class="fw-light mb-2 ms-5">
                  Longeur minimum de pseudo est 8 caracéres!
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
                    type="tel"
                    class="form-control"
                    placeholder="téléphone"
                    pattern="[0-9]*"
                    inputmode="numeric"
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

        <div class="col-12 input-group mb-2">
          <span class="input-group-text" id="basic-addon1">Adresse :</span>
          <input type="text" class="form-control" placeholder="Adresse" />
        </div>
        <div class="form-text text-danger" v-if="v$.inputs.$error">
          Ces champs sont requis !
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
#image-helptext {
  color: rgba(180, 95, 146, 0.674);
}
.noFile {
  border: solid 3px red;
}
</style>
