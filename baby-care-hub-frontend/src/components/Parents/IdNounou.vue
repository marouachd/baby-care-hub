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
      inputs: {
        childminderCode: "",
        active: false,
      },
    };
  },
  created() {
    this.$http = axios;
  },
  methods: {
    async getChildProfile() {
      const response = await this.$http.get(
        `${import.meta.env.VITE_API_BASE_URL}/child/${this.id}/detail`
      );
      this.data = response.data;
      console.log("child-id-nounou", this.data);
    },
    async Submit() {
      if (
        !this.data.active ||
        this.data.childminderCode.personId.pseudoName !=
          this.inputs.childminderCode
      ) {
        const resp = await this.$http.patch(
          `${import.meta.env.VITE_API_BASE_URL}/child/active/${this.id}`,
          this.inputs
        );
        console.log(resp, "patch-id-nounou");

        if (resp.status === 204) {
          this.$toast.success(
            "toast-global",
            "La nounou de votre enfant est modifié avec succées"
          );
          this.$router.push({
            name: "mes-enfants",
            params: { id: this.userId },
          });
        } else {
          this.$toast.error("toast-global", "Un problème est survenu.");
        }
      } else {
        localStorage.setItem("childminderCode", this.inputs.childminderCode);
        this.$router.push({
          name: "create-profile-enfant",
          params: { id: this.userId },
        });
      }
    },
  },
  mounted() {
    this.getChildProfile();
  },
  beforeUpdate() {
    this.userId = localStorage.getItem("userId");
  },
};
</script>
<template>
  <section class="container-xl text-center mb-5">
    <h1 class="mt-5 mb-5">{{ $t("idNounou.title") }}</h1>
    <div class="row justify-content-center mb-5">
      <div class="col-md-6 col-12 mb-5">
        <form class="my-4 mb-5" @submit.prevent="Submit()">
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1"
              >{{ $t("idNounou.id") }} :</span
            >
            <input
              type="text"
              class="form-control"
              placeholder="MAS241278"
              id="childminderCode"
              name="childminderCode"
              v-model="inputs.childminderCode"
            />
          </div>
          <div class="d-flex justify-content-center mt-5 mb-5">
            <button
              @click="Submit"
              type="submit"
              class="btn col-md-3 col-12 mx-2 mt-5 mb-4 bouton"
            >
              {{ $t("idNounou.buttons") }}
            </button>
          </div>
          <div class="input-group mb-5"></div>
        </form>
      </div>
    </div>
  </section>
</template>

<style>
h1 {
  font-family: "Pacifico", cursive;
  color: rgba(180, 95, 146, 0.674);
}
.bouton {
  background-color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  color: white;
}
</style>
