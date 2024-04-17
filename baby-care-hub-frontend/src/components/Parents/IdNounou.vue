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
      childminderList: [],
      search: "",
      child: "",
    };
  },

  methods: {
    selectChildminder(childminder) {
      if (childminder && childminder.id) {
        this.inputs.childminderCode = childminder.personId.pseudoName;
      }
      this.submit();
    },
    getImage(childminder) {
      if (childminder.personId.identityPhotoName) {
        return "/personal-pictures/" + childminder.personId.identityPhotoName;
      }
    },
    async getChildProfile() {
      const response = await this.$axios.get(`/child/${this.id}/detail`);
      this.child = response.body;
    },
    async getChildminderList() {
      const response = await this.$axios.get(`/user/childminder/1`);
      this.childminderList = response.body;
    },
    async submit() {
      if (this.child) {
        if (
          !this.child.active ||
          this.child.childminderCode.personId.pseudoName !=
            this.inputs.childminderCode
        ) {
          const resp = await this.$axios.patch(
            `/child/active/${this.id}`,
            this.inputs
          );

          if (resp.status === 204) {
            this.$toast.success(
              "toast-global",
              "Vous avez bien selectionné la nounou de votre enfant"
            );
            this.$router.push({
              name: "mes-enfants",
              params: { id: this.userId },
            });
          } else {
            this.$toast.error("toast-global", "Un problème est survenu.");
          }
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
  computed: {
    // Filtrer les nounous en fonction du terme de recherche
    filteredChildminders() {
      return this.childminderList
        .filter((childminder) => {
          // Vérifier si le terme de recherche correspond à l'adresse e-mail ou au numéro de téléphone
          return (
            childminder.mailAdress.includes(this.search) ||
            childminder.phoneNumber.includes(this.search)
          );
        })
        .sort((a, b) => a.mailAdress.localeCompare(b.mailAdress));
    },
  },

  async mounted() {
    await this.getChildProfile();
    await this.getChildminderList();
  },
  beforeUpdate() {
    this.userId = localStorage.getItem("userId");
  },
};
</script>
<template>
  <section class="container-xl text-center mb-5">
    <h1 class="mt-5 mb-5">{{ $t("idNounou.title") }}</h1>
    <!--<div class="row justify-content-center mb-5">
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
    </div>-->

    <section class="container-xl text-center mb-5">
      <form class="d-flex justify-content-center">
        <input
          class="form-control mr-sm-2"
          type="search"
          placeholder="Search"
          aria-label="Search"
          v-model="search"
        />
        <button class="btn btn-outline search my-2 my-sm-0" type="submit">
          Search
        </button>
      </form>
    </section>
    <section class="container-xl text-center mb-5">
      <div class="row row-cols-2 row-cols-md-3 g-4 mt-4 ms-5">
        <div
          class="card me-5"
          style="width: 18rem"
          v-for="childminder in filteredChildminders"
          :key="childminder.id"
          @click="selectChildminder(childminder)"
        >
          <img
            class="card-img-top"
            :src="getImage(childminder)"
            alt="Card image cap"
          />
          <div class="card-body">
            <p class="card-text">
              <span>{{ childminder.personId.firstName }}</span> &nbsp;
              <span>{{ childminder.personId.lastName }}</span>
            </p>
            <p class="card-text">
              <span>{{ childminder.mailAdress }}</span>
            </p>
            <p>
              <span>{{ childminder.phoneNumber }}</span>
            </p>
          </div>
        </div>
      </div>
    </section>
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
.search {
  background-color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  color: white;
}
</style>
