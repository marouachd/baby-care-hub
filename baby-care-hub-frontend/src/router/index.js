import { createRouter, createWebHistory } from "vue-router";
import Sidebar from "../components/Commons/Sidebar.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      redirect: "/signin",
    },
    {
      path: "/ma-journee/general/:id",
      name: "general",
      component: () => import("../components/Commons/General.vue"),
    },
    {
      path: "/home/:id",
      name: "home",
      component: () => import("../components/Enfant/Home.vue"),
    },

    {
      path: "/historique/:id",
      name: "historique",
      component: () => import("../components/Nourisse/Historique.vue"),
    },

    {
      path: "/edit-profile-enfant/:id",
      name: "edit-profile-enfant",
      component: () => import("../components/Enfant/EditProfileEnfant.vue"),
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
      component: () => import("../components/Enfant/FicheEnfant.vue"),
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
      component: () => import("../components/Users/Coordonees.vue"),
    },
    {
      path: "/create-profile-enfant/:id",
      name: "create-profile-enfant",
      component: () => import("../components/Enfant/CreateProfileEnfant.vue"),
    },
    {
      path: "/représentant-légal/:id",
      name: "représentant-légal",
      component: () => import("../components/Enfant/RepresentantLegal.vue"),
    },

    {
      path: "/create-user-account",
      name: "create-user-account",
      component: () => import("../components/Users/CreateUserAccount.vue"),
    },
    {
      path: "/signin",
      name: "signin",
      component: () => import("../components/Users/Signin.vue"),
    },
    {
      path: "/forgot-password",
      name: "forgot-password",
      component: () => import("../components/Users/ForgotPassword.vue"),
    },
    {
      path: "/ma-journee/horaire/:id",
      name: "horaire",
      component: () => import("../components/Journée/Horaire.vue"),
    },
    {
      path: "/ma-journee/activites/:id",
      name: "activités",
      component: () => import("../components/Journée/Activités.vue"),
    },
    {
      path: "/ma-journee/biberons/:id",
      name: "biberons",
      component: () => import("../components/Journée/Biberons.vue"),
    },
    {
      path: "/ma-journee/change",
      name: "change",
      component: () => import("../components/Journée/Change.vue"),
    },
    {
      path: "/ma-journee/gouter/:id",
      name: "gouter",
      component: () => import("../components/Journée/Gouter.vue"),
    },
    {
      path: "/ma-journee/sieste/:id",
      name: "sieste",
      component: () => import("../components/Journée/Sieste.vue"),
    },
    {
      path: "/ma-journee/repas/:id",
      name: "repas",
      component: () => import("../components/Journée/Repas.vue"),
    },
    {
      path: "/ma-journee/temperature",
      name: "température",
      component: () => import("../components/Journée/Température.vue"),
    },
    {
      path: "/ma-journee/sortie-loisir/:id",
      name: "sortie-loisir",
      component: () => import("../components/Journée/SortieLoisir.vue"),
    },
    {
      path: "/ma-journee/medicaments",
      name: "médicaments",
      component: () => import("../components/Journée/Médicaments.vue"),
    },
    {
      path: "/new-password/:token",
      name: "password",
      component: () => import("../components/Users/NewPassword.vue"),
    },
  ],
});

export default router;
