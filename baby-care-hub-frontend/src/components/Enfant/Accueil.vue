<template>
  <div class="countainer mx-3 my-3">
    <h1 class="mt-4 mb-4 text-center">Mes bouts de choux</h1>

    <RouterLink :to="{ name: 'create-profile-enfant' }" class="btn" id="button"
      >Nouvel Arrivant</RouterLink
    >

    <div class="row row-cols-2 row-cols-md-3 g-4 mt-4">
      <div class="col-12 col-md-2">
        <div class="card h-100 bg-light">
          <img src="../../assets/Noah.jpg" class="card-img-top" alt="..." />
          <div class="card-body d-flex flex-column justify-content-end">
            <h5 class="card-title">Noah David</h5>
            <p class="card-text">Age : 3 ans</p>
            <RouterLink :to="{ name: 'ma-journée' }" class="btn"
              >Ma journée</RouterLink
            >
          </div>
        </div>
      </div>
      <div class="col-12 col-md-2">
        <div class="card h-100 bg-light">
          <img src="../../assets/Jade.jpg" class="card-img-top" alt="..." />
          <div class="card-body d-flex flex-column justify-content-end">
            <h5 class="card-title">Jade Brosse</h5>
            <p class="card-text">Age : 5 ans</p>
            <a href="#" class="btn">Ma journée</a>
          </div>
        </div>
      </div>
      <div class="col-12 col-md-2">
        <div class="card h-100 bg-light">
          <img src="../../assets/Judy.jpg" class="card-img-top" alt="..." />
          <div class="card-body d-flex flex-column justify-content-end">
            <h5 class="card-title">Judy Karmann</h5>
            <p class="card-text">Age : 9 mois</p>
            <a href="#" class="btn">Ma journée</a>
          </div>
        </div>
      </div>
      <div class="col-12 col-md-2">
        <div class="card h-100 bg-light">
          <img src="../../assets/Sarah.jpg" class="card-img-top" alt="..." />
          <div class="card-body d-flex flex-column justify-content-end">
            <h5 class="card-title">Sarah Marion</h5>
            <p class="card-text">Age : 2 ans</p>
            <a href="#" class="btn">Ma journée</a>
          </div>
        </div>
      </div>
      <div class="col-12 col-md-2">
        <div class="card h-100 bg-light">
          <img src="../../assets/Arthur.png" class="card-img-top" alt="..." />
          <div class="card-body d-flex flex-column justify-content-end">
            <h5 class="card-title">Arthur Leman</h5>
            <p class="card-text">Age : 5 ans</p>
            <a href="#" class="btn">Ma journée</a>
          </div>
        </div>
      </div>
      <div class="col-12 col-md-2">
        <div class="card h-100 bg-light">
          <img src="../../assets/Aliyah.jpg" class="card-img-top" alt="..." />
          <div class="card-body d-flex flex-column justify-content-end">
            <h5 class="card-title">Aliyah Lutton</h5>
            <p class="card-text">Age : 8 mois</p>
            <a href="#" class="btn">Ma journée</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");

h1 {
  font-family: "Pacifico", cursive;
  color: rgb(160, 197, 237);
}
#button {
  background-color: rgba(180, 95, 146, 0.674);
  font-family: "Pacifico", cursive;
  color: white;
}
a {
  background-color: rgb(160, 197, 237);
  font-family: "Pacifico", cursive;
  color: white;
}

h5 {
  font-family: "Pacifico", cursive;
}
p {
  font-family: "Satisfy", cursive;
}
</style>
