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
      isUpdate: this.route.params.update,
      inputs: {
        childminderCode: "",
        active: false,
      },
      childminderList: [],
      childmindersGuardedChilds: [],
      childminderChildrenCounts: {},
      children: [],
      search: "",
      child: "",
    };
  },

  methods: {
    selectChildminder(childminder) {
      if (childminder && childminder.id) {
        this.inputs.childminderCode = childminder.personId.pseudoName;
        if (this.inputs.childminderCode) {
          this.$toast.success(
            "toast-global",
            `Vous avez bien sélectionné la nounou "${childminder.personId.firstName} ${childminder.personId.lastName}" pour votre enfant`
          );
        }
      }
      localStorage.setItem("childminderCode", this.inputs.childminderCode);
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
      console.log(this.child, "here child");
    },
    async getChildminderList() {
      const response = await this.$axios.get(`/user/childminder/1`);
      this.childminderList = response.body;
    },
    async getChildmindersGuardedChilds() {
      const ids = this.childminderList.map((item) => item.id).join(",");
      const response = await this.$axios.get(`/child/childminders/${ids}`);
      this.childmindersGuardedChilds = response.body;

      for (const childminder of this.childminderList) {
        this.children = this.childmindersGuardedChilds[childminder.id];
        for (const child of this.children) {
          if (child.active && child.accepted) {
            console.log("guarded child");
          } else {
            console.log("waiting list");
          }
        }
      }
    },
    waitingListCount(childminderId) {
      if (!this.childmindersGuardedChilds[childminderId]) return 0;
      return this.childmindersGuardedChilds[childminderId].filter(
        (child) => !child.active || !child.accepted
      ).length;
    },
    async submit() {
      console.log(this.isUpdate, "update in submit");
      console.log("subùmit called");

      // if (this.child) {
      //  if (
      //    this.child.childminderCode &&
      //   this.child.childminderCode.personId.pseudoName ==
      //     this.inputs.childminderCode
      //  ) {
      //    if (this.child.accepted) {
      //      this.$toast.error(
      //       "toast-global",
      //       "Votre enfant est deja gardé par cette nounou !"
      //     );
      //  } else {
      //    this.$toast.error(
      //      "toast-global",
      //     "Une demande est en attente d'acceptation pour cette nounou !"
      //    );
      // }
      //} else {
      //  const resp = await this.$axios.patch(
      //    `/child/active/${this.id}`,
      //    this.inputs
      //  );
      // console.log("rep", resp);
      // console.log(resp.status);

      //  if (resp) {
      //    this.$router.push({
      //     name: "mes-enfants",
      //     params: { id: this.userId },
      //   });
      //   console.log("mes enfants-id-nounou", this.userId);
      // }
      // }
      //} else {
      //localStorage.setItem("childminderCode", this.inputs.childminderCode);
      if (this.isUpdate === "false") {
        console.log(this.userId, "userId yess");
        this.$router.push({
          name: "create-profile-enfant",
          params: { id: this.userId },
        });
      } else {
        console.log("il est entré là ");
        if (this.child && this.child.childminderCode) {
          if (
            this.child.childminderCode.personId.pseudoName !=
            this.inputs.childminderCode
          ) {
            const resp = await this.$axios.patch(
              `/child/active/${this.id}`,
              this.inputs
            );

            if (resp) {
              this.$router.push({
                name: "mes-enfants",
                params: { id: this.userId },
              });
            }
          }
        }
      }
      console.log(this.userId, "userId before redirecting");

      //}
      //localStorage.removeItem("childminderCode");
      //},
    },
  },
  computed: {
    // Filtrer les nounous en fonction du terme de recherche
    filteredChildminders() {
      return this.childminderList
        .filter((childminder) => {
          // Vérifier si le terme de recherche correspond à l'adresse e-mail ou au numéro de téléphone
          return (
            childminder.mailAdress?.includes(this.search) ||
            "" ||
            childminder.phoneNumber?.includes(this.search) ||
            ""
          );
        })
        .sort((a, b) => (a.mailAdress || "").localeCompare(b.mailAdress || ""));
    },
    childsCount() {
      return (id) => {
        const childsWithSameChildminderId = this.childmindersGuardedChilds[id];

        if (childsWithSameChildminderId) {
          return childsWithSameChildminderId.filter(
            (child) => child.active && child.accepted
          ).length;
        } else {
          return 0;
        }
      };
    },
  },

  async mounted() {
    console.log(this.route.params.update, "update");
    if (this.isUpdate) {
      console.log("isUpdate mounted", this.isUpdate);
      await this.getChildProfile();
    }

    await this.getChildminderList();
    await this.getChildmindersGuardedChilds();
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
    <section class="container-xl mb-5">
      <div
        class="row row-cols-2 row-cols-md-3 g-4 mt-4 ms-5 d-flex justify-content-center"
      >
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
            style="max-height: 20rem"
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
            <p>
              Nombre des enfants gardé:
              <span>{{ childsCount(childminder.id) }}</span>
            </p>
            <p>
              Demande en attente :
              <span>{{ waitingListCount(childminder.id) }}</span>
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
