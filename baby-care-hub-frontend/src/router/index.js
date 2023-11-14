import { createRouter, createWebHistory } from "vue-router";
import Sidebar from "../Components/Commons/Sidebar.vue";
import Accueil from "../Components/Enfant/Accueil.vue";
import CreateProfileEnfant from "../Components/Enfant/CreateProfileEnfant.vue";
import RepresentantLegal from "../Components/Enfant/RepresentantLegal.vue";
import FicheEnfant from "../Components/Enfant/FicheEnfant.vue";
import EditProfileEnfant from "../Components/Enfant/EditProfileEnfant.vue";
import EditCoordonnéesParents from "../Components/Parents/EditCoordonnéesParents.vue";
import CreateUserAccount from "../Components/Users/CreateUserAccount.vue";
import Signin from "../Components/Users/Signin.vue";
import ForgotPassword from "../Components/Users/ForgotPassword.vue";
import Horaire from "../Components/Journée/Horaire.vue";
import Activités from "../Components/Journée/Activités.vue";
import Biberons from "../Components/Journée/Biberons.vue";
import Change from "../Components/Journée/Change.vue";
import Gouter from "../Components/Journée/Gouter.vue";
import Médicaments from "../Components/Journée/Médicaments.vue";
import Température from "../Components/Journée/Température.vue";
import SortieLoisir from "../Components/Journée/SortieLoisir.vue";
import Sieste from "../Components/Journée/Sieste.vue";
import Repas from "../Components/Journée/Repas.vue";
import MesEnfants from "../components/Parents/MesEnfants.vue";
import Actualités from "../components/Parents/Actualités.vue";
import IdNounou from "../components/Parents/IdNounou.vue";
import General from "../components/Commons/General.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "ma-journée",
      component: Sidebar,
    },
    {
      path: "/ma-journee/general",
      name: "general",
      component: General,
    },
    {
      path: "/accueil",
      name: "accueil",
      component: Accueil,
    },
    {
      path: "/create-profile-enfant",
      name: "create-profile-enfant",
      component: CreateProfileEnfant,
    },
    {
      path: "/représentant-légal",
      name: "représentant-légal",
      component: RepresentantLegal,
    },
    {
      path: "/fiche-enfant",
      name: "fiche-enfant",
      component: FicheEnfant,
    },
    {
      path: "/edit-profile-enfant",
      name: "edit-profile-enfant",
      component: EditProfileEnfant,
    },
    {
      path: "/edit-coordonnees-parents",
      name: "edit-coordonnees-parents",
      component: EditCoordonnéesParents,
    },
    {
      path: "/create-user-account",
      name: "create-user-account",
      component: CreateUserAccount,
    },
    {
      path: "/signin",
      name: "signin",
      component: Signin,
    },
    {
      path: "/forgot-password",
      name: "forgot-password",
      component: ForgotPassword,
    },
    {
      path: "/ma-journee/horaire",
      name: "horaire",
      component: Horaire,
    },
    {
      path: "/ma-journee/activités",
      name: "activités",
      component: Activités,
    },
    {
      path: "/ma-journee/biberons",
      name: "biberons",
      component: Biberons,
    },
    {
      path: "/ma-journee/change",
      name: "change",
      component: Change,
    },
    {
      path: "/ma-journee/gouter",
      name: "gouter",
      component: Gouter,
    },
    {
      path: "/ma-journee/sieste",
      name: "sieste",
      component: Sieste,
    },
    {
      path: "/ma-journee/repas",
      name: "repas",
      component: Repas,
    },
    {
      path: "/ma-journee/température",
      name: "température",
      component: Température,
    },
    {
      path: "/ma-journee/sortie-loisir",
      name: "sortie-loisir",
      component: SortieLoisir,
    },
    {
      path: "/ma-journee/médicaments",
      name: "médicaments",
      component: Médicaments,
    },
    {
      path: "/mes-enfants",
      name: "mes-enfants",
      component: MesEnfants,
    },
    {
      path: "/actualitees",
      name: "actualitees",
      component: Actualités,
    },
    {
      path: "/id-nounou",
      name: "id-nounou",
      component: IdNounou,
    },
  ],
});

export default router;
