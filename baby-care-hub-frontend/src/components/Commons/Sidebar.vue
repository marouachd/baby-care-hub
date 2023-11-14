<template>
  <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block sidebar">
    <ul class="nav flex-column list-unstyled position-sticky">
      <li class="nav-item mb-3 mt-3">
        <RouterLink :to="{ name: 'horaire' }" class="dropdown-item">
          <img
            src="../../assets/horloge.jpg"
            alt="Image 1"
            class="img-fluid"
            width="50"
            height="50"
          />

          <span class="d-none d-md-inline">Horaire</span>
        </RouterLink>
      </li>
      <li class="nav-item mb-3">
        <RouterLink :to="{ name: 'biberons' }" class="dropdown-item">
          <img
            src="../../assets/biberon.jpg"
            alt="Image 3"
            class="img-fluid"
            width="50"
            height="50"
          />
          <span class="d-none d-md-inline">Biberons</span>
        </RouterLink>
      </li>
      <li class="nav-item mb-3">
        <RouterLink :to="{ name: 'activités' }" class="dropdown-item">
          <img
            src="../../assets/activité.jpg"
            alt="Image 2"
            class="img-fluid"
            width="50"
            height="50"
          />
          <span class="d-none d-md-inline">Activités</span>
        </RouterLink>
      </li>
      <li class="nav-item mb-3">
        <RouterLink :to="{ name: 'repas' }" class="dropdown-item">
          <img
            src="../../assets/repas.png"
            alt="Image 3"
            class="img-fluid"
            width="50"
            height="50"
          />
          <span class="d-none d-md-inline">Repas</span>
        </RouterLink>
      </li>
      <li class="nav-item mb-3">
        <RouterLink :to="{ name: 'sieste' }" class="dropdown-item">
          <img
            src="../../assets/sieste.jpg"
            alt="Image 3"
            class="img-fluid"
            width="50"
            height="50"
          />
          <span class="d-none d-md-inline">Sieste</span>
        </RouterLink>
      </li>
      <li class="nav-item mb-3">
        <RouterLink :to="{ name: 'change' }" class="dropdown-item">
          <img
            src="../../assets/change.jpg"
            alt="Image 3"
            class="img-fluid"
            width="50"
            height="50"
          />
          <span class="d-none d-md-inline">Change</span>
        </RouterLink>
      </li>
      <li class="nav-item mb-3">
        <RouterLink :to="{ name: 'température' }" class="dropdown-item">
          <div class="d-flex align-items-center">
            <img
              src="../../assets/Thermometre.jpg"
              alt="Image 3"
              class="img-fluid"
              width="50"
              height="50"
            />
            <span class="d-none d-md-inline">Température</span>
          </div>
        </RouterLink>
      </li>
      <li class="nav-item mb-3">
        <RouterLink :to="{ name: 'médicaments' }" class="dropdown-item">
          <div class="d-flex align-items-center">
            <img
              src="../../assets/medicament.png"
              alt="Image 3"
              class="img-fluid"
              width="50"
              height="50"
            />
            <span class="d-none d-md-inline">Médicaments</span>
          </div>
        </RouterLink>
      </li>
      <li class="nav-item mb-3">
        <RouterLink :to="{ name: 'gouter' }" class="dropdown-item">
          <img
            src="../../assets/gouter.jpg"
            alt="Image 3"
            class="img-fluid"
            width="50"
            height="50"
          />
          <span class="d-none d-md-inline">Gouter</span>
        </RouterLink>
      </li>
      <li class="nav-item mb-0 d-flex flex-fill">
        <RouterLink :to="{ name: 'sortie-loisir' }" class="dropdown-item">
          <img
            src="../../assets/parc.jpg"
            alt="Image 3"
            class="img-fluid"
            width="50"
            height="50"
          />
          <span class="d-flex align-items-center d-none d-md-inline"
            >Sortie & Loisir</span
          >
        </RouterLink>
      </li>
    </ul>
  </nav>
</template>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");
#sidebar ul.nav.flex-column {
  list-style: none;
  margin-left: 10px;
  padding-left: 5;
  flex-grow: 1;
}

#sidebar ul.nav.flex-column li {
  list-style: none;
  border-bottom: 1px solid rgba(0, 0, 0, 0.125);
  padding-bottom: 5px;
  padding-right: 10px;
}
span {
  margin-left: 20px;
  color: rgb(166, 161, 161);
  font-family: "Pacifico", cursive;
}
</style>
