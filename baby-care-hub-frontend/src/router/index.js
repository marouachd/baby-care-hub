import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "ma-journée",
      component: () => import("../Components/Commons/Sidebar.vue"),
    },
    {
      path: "/ma-journee/general/:id",
      name: "general",
      component: () => import("../components/Commons/General.vue"),
    },
    {
      path: "/home/:id",
      name: "home",
      component: () => import("../Components/Enfant/Home.vue"),
    },

    {
      path: "/edit-profile-enfant/:id",
      name: "edit-profile-enfant",
      component: () => import("../Components/Enfant/EditProfileEnfant.vue"),
    },
    {
      path: "/edit-user-account/:id",
      name: "edit-user-account",
      component: () => import("../components/Users/UpdateUserAccount.vue"),
    },
    {
      path: "/mes-enfants/:id", //ici le probléme
      name: "mes-enfants",
      component: () => import("../components/Parents/MesEnfants.vue"),
    },
    {
      path: "/fiche-enfant/:id",
      name: "fiche-enfant",
      component: () => import("../Components/Enfant/FicheEnfant.vue"),
    },
    {
      path: "/actualitees/:id",
      name: "actualitees",
      component: () => import("../components/Parents/Actualités.vue"),
    },
    {
      path: "/id-nounou/:id",
      name: "id-nounou",
      component: () => import("../components/Parents/IdNounou.vue"),
    },
    {
      path: "/coordonees/:id",
      name: "coordonees",
      component: () => import("../Components/Users/Coordonees.vue"),
    },
    {
      path: "/create-profile-enfant/:id",
      name: "create-profile-enfant",
      component: () => import("../Components/Enfant/CreateProfileEnfant.vue"),
    },
    {
      path: "/représentant-légal/:id",
      name: "représentant-légal",
      component: () => import("../Components/Enfant/RepresentantLegal.vue"),
    },

    {
      path: "/create-user-account",
      name: "create-user-account",
      component: () => import("../Components/Users/CreateUserAccount.vue"),
    },
    {
      path: "/signin",
      name: "signin",
      component: () => import("../Components/Users/Signin.vue"),
    },
    {
      path: "/forgot-password",
      name: "forgot-password",
      component: () => import("../Components/Users/ForgotPassword.vue"),
    },
    {
      path: "/ma-journee/horaire/:id",
      name: "horaire",
      component: () => import("../Components/Journée/Horaire.vue"),
    },
    {
      path: "/ma-journee/activites/:id",
      name: "activités",
      component: () => import("../Components/Journée/Activités.vue"),
    },
    {
      path: "/ma-journee/biberons/:id",
      name: "biberons",
      component: () => import("../Components/Journée/Biberons.vue"),
    },
    {
      path: "/ma-journee/change",
      name: "change",
      component: () => import("../Components/Journée/Change.vue"),
    },
    {
      path: "/ma-journee/gouter/:id",
      name: "gouter",
      component: () => import("../Components/Journée/Gouter.vue"),
    },
    {
      path: "/ma-journee/sieste/:id",
      name: "sieste",
      component: () => import("../Components/Journée/Sieste.vue"),
    },
    {
      path: "/ma-journee/repas/:id",
      name: "repas",
      component: () => import("../Components/Journée/Repas.vue"),
    },
    {
      path: "/ma-journee/temperature",
      name: "température",
      component: () => import("../Components/Journée/Température.vue"),
    },
    {
      path: "/ma-journee/sortie-loisir/:id",
      name: "sortie-loisir",
      component: () => import("../Components/Journée/SortieLoisir.vue"),
    },
    {
      path: "/ma-journee/medicaments",
      name: "médicaments",
      component: () => import("../Components/Journée/Médicaments.vue"),
    },
    {
      path: "/new-password/:token",
      name: "password",
      component: () => import("../Components/Users/NewPassword.vue"),
    },
  ],
});

export default router;
