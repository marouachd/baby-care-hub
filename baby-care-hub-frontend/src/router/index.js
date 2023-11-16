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
      path: "/acceuil/:id",
      name: "acceuil",
      component: () => import("../Components/Enfant/Accueil.vue"),
    },
    {
      path: "/create-profile-enfant",
      name: "create-profile-enfant",
      component: () => import("../Components/Enfant/CreateProfileEnfant.vue"),
    },
    {
      path: "/représentant-légal",
      name: "représentant-légal",
      component: () => import("../Components/Enfant/RepresentantLegal.vue"),
    },

    {
      path: "/fiche-enfant/:id",
      name: "fiche-enfant",
      component: () => import("../Components/Enfant/FicheEnfant.vue"),
    },
    {
      path: "/edit-profile-enfant/:id",
      name: "edit-profile-enfant",
      component: () => import("../Components/Enfant/EditProfileEnfant.vue"),
    },
    {
      path: "/edit-user-account",
      name: "edit-user-account",
      component: () => import("../components/Users/UpdateUserAccount.vue"),
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
      path: "/ma-journee/horaire",
      name: "horaire",
      component: () => import("../Components/Journée/Horaire.vue"),
    },
    {
      path: "/ma-journee/activites",
      name: "activités",
      component: () => import("../Components/Journée/Activités.vue"),
    },
    {
      path: "/ma-journee/biberons",
      name: "biberons",
      component: () => import("../Components/Journée/Biberons.vue"),
    },
    {
      path: "/ma-journee/change",
      name: "change",
      component: () => import("../Components/Journée/Change.vue"),
    },
    {
      path: "/ma-journee/gouter",
      name: "gouter",
      component: () => import("../Components/Journée/Gouter.vue"),
    },
    {
      path: "/ma-journee/sieste",
      name: "sieste",
      component: () => import("../Components/Journée/Sieste.vue"),
    },
    {
      path: "/ma-journee/repas",
      name: "repas",
      component: () => import("../Components/Journée/Repas.vue"),
    },
    {
      path: "/ma-journee/temperature",
      name: "température",
      component: () => import("../Components/Journée/Température.vue"),
    },
    {
      path: "/ma-journee/sortie-loisir",
      name: "sortie-loisir",
      component: () => import("../Components/Journée/SortieLoisir.vue"),
    },
    {
      path: "/ma-journee/medicaments",
      name: "médicaments",
      component: () => import("../Components/Journée/Médicaments.vue"),
    },
    {
      path: "/mes-enfants/:id",
      name: "mes-enfants",
      component: () => import("../components/Parents/MesEnfants.vue"),
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
  ],
});

export default router;
