import { createRouter, createWebHistory } from "vue-router";
import Sidebar from "../components/Commons/Sidebar.vue";
import navigationGuard from "./NavigationGuard";

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
      meta: { requiresAuth: true, role: 1 },
    },
    {
      path: "/error-page",
      name: "error",
      component: () => import("../components/Commons/ErrorPage.vue"),
    },
    {
      path: "/home/:id",
      name: "home",
      component: () => import("../components/Enfant/Home.vue"),
      meta: { requiresAuth: true, role: 1 },
    },

    {
      path: "/historique/:id",
      name: "historique",
      component: () => import("../components/Nourisse/Historique.vue"),
      meta: { requiresAuth: true, role: 1 },
    },

    {
      path: "/edit-profile-enfant/:id",
      name: "edit-profile-enfant",
      component: () => import("../components/Enfant/EditProfileEnfant.vue"),
      meta: { requiresAuth: true, role: 2 },
    },
    {
      path: "/edit-user-account/:id",
      name: "edit-user-account",
      component: () => import("../components/Users/UpdateUserAccount.vue"),
      meta: { requiresAuth: true },
    },
    {
      path: "/mes-enfants/:id",
      name: "mes-enfants",
      component: () => import("../components/Parents/MesEnfants.vue"),
      meta: { requiresAuth: true, role: 2 },
    },
    {
      path: "/fiche-enfant/:id",
      name: "fiche-enfant",
      component: () => import("../components/Enfant/FicheEnfant.vue"),
      meta: { requiresAuth: true },
    },
    {
      path: "/actualitees/:id",
      name: "actualitees",
      component: () => import("../components/Parents/Actualités.vue"),
      meta: { requiresAuth: true, role: 2 },
    },
    {
      path: "/id-nounou/:id",
      name: "id-nounou",
      component: () => import("../components/Parents/IdNounou.vue"),
      meta: { requiresAuth: true, role: 2 },
    },
    {
      path: "/coordonees/:id",
      name: "coordonees",
      component: () => import("../components/Users/Coordonees.vue"),
      meta: { requiresAuth: true },
    },
    {
      path: "/create-profile-enfant/:id",
      name: "create-profile-enfant",
      component: () => import("../components/Enfant/CreateProfileEnfant.vue"),
      meta: { requiresAuth: true, role: 2 },
    },
    {
      path: "/représentant-légal/:id",
      name: "représentant-légal",
      component: () => import("../components/Enfant/RepresentantLegal.vue"),
      meta: { requiresAuth: true },
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
      meta: { requiresAuth: true },
    },
    {
      path: "/ma-journee/horaire/:id",
      name: "horaire",
      component: () => import("../components/Journée/Horaire.vue"),
      meta: { requiresAuth: true, role: 1 },
    },
    {
      path: "/ma-journee/activites/:id",
      name: "activités",
      component: () => import("../components/Journée/Activités.vue"),
      meta: { requiresAuth: true, role: 1 },
    },
    {
      path: "/ma-journee/biberons/:id",
      name: "biberons",
      component: () => import("../components/Journée/Biberons.vue"),
      meta: { requiresAuth: true, role: 1 },
    },
    {
      path: "/ma-journee/change",
      name: "change",
      component: () => import("../components/Journée/Change.vue"),
      meta: { requiresAuth: true, role: 1 },
    },
    {
      path: "/ma-journee/gouter/:id",
      name: "gouter",
      component: () => import("../components/Journée/Gouter.vue"),
      meta: { requiresAuth: true, role: 1 },
    },
    {
      path: "/ma-journee/sieste/:id",
      name: "sieste",
      component: () => import("../components/Journée/Sieste.vue"),
      meta: { requiresAuth: true, role: 1 },
    },
    {
      path: "/ma-journee/repas/:id",
      name: "repas",
      component: () => import("../components/Journée/Repas.vue"),
      meta: { requiresAuth: true, role: 1 },
    },
    {
      path: "/ma-journee/temperature",
      name: "température",
      component: () => import("../components/Journée/Température.vue"),
      meta: { requiresAuth: true, role: 1 },
    },
    {
      path: "/ma-journee/sortie-loisir/:id",
      name: "sortie-loisir",
      component: () => import("../components/Journée/SortieLoisir.vue"),
      meta: { requiresAuth: true, role: 1 },
    },
    {
      path: "/ma-journee/medicaments",
      name: "médicaments",
      component: () => import("../components/Journée/Médicaments.vue"),
      meta: { requiresAuth: true, role: 1 },
    },
    {
      path: "/new-password/:token",
      name: "password",
      component: () => import("../components/Users/NewPassword.vue"),
      meta: { requiresAuth: true },
    },
  ],
});
// Application du navigationGuard qui sera appelé avant chaque changement de route dans l'application
router.beforeEach(navigationGuard);
export default router;
