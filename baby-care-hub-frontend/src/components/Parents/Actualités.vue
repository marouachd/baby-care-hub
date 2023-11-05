<template>
  <div class="container mt-4 mb-4">
    <h1 class="mt-4 mb-4 text-center">
      Fiche Récapitulative de la Journée de l'Enfant
    </h1>
    <div class="card bg-light rounded">
      <div class="card mx-2 mt-2">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Horaire</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/horloge.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>Horaire d'arrivée : 08:00 | Horaire de sortie : 16:30</div>
            </div>
            <div>
              <p>Le temps cumulé est : 8 heures et 30 minutes</p>
            </div>
          </div>
        </div>
      </div>

      <div class="comment-section mt-2">
        <span class="comment" @click="toggleSmiley('etoile')">⭐</span>
        <span class="comment" @click="toggleSmiley('coeur')">❤️</span>
        <span class="comment" @click="toggleSmiley('bravo')">👏</span>
        <span class="comment" @click="toggleSmiley('ohnon')">😞</span>
      </div>

      <div class="card mx-2">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Biberon</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/biberon.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>Biberon de 150 ml</div>
            </div>
            <div>
              <p>à 10:00</p>
            </div>
          </div>
        </div>
      </div>

      <div class="comment-section mt-2">
        <span class="comment" @click="toggleSmiley('etoile')">⭐</span>
        <span class="comment" @click="toggleSmiley('coeur')">❤️</span>
        <span class="comment" @click="toggleSmiley('bravo')">👏</span>
        <span class="comment" @click="toggleSmiley('ohnon')">😞</span>
      </div>

      <div class="card mx-2">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Activités</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/activité.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>Lego</div>
            </div>
            <div>
              <p>Noah a fait un tres jolie construction avec les lego</p>
            </div>
          </div>
        </div>
      </div>

      <div class="comment-section mt-2">
        <span class="comment" @click="toggleSmiley('etoile')">⭐</span>
        <span class="comment" @click="toggleSmiley('coeur')">❤️</span>
        <span class="comment" @click="toggleSmiley('bravo')">👏</span>
        <span class="comment" @click="toggleSmiley('ohnon')">😞</span>
      </div>

      <div class="card mx-2">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Repas</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/repas.png"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>Noah il a bien mangé</div>
            </div>
            <div>
              <p>
                il a fini sont repas, aujourd'hui il a mangé du poisson et des
                légumes
              </p>
            </div>
          </div>
        </div>
      </div>

      <div class="comment-section mt-2">
        <span class="comment" @click="toggleSmiley('etoile')">⭐</span>
        <span class="comment" @click="toggleSmiley('coeur')">❤️</span>
        <span class="comment" @click="toggleSmiley('bravo')">👏</span>
        <span class="comment" @click="toggleSmiley('ohnon')">😞</span>
      </div>

      <div class="card mx-2">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Sieset</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/sieste.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>Horaire de sommeil : 14:00 | Horaire de réveil : 15:30</div>
            </div>
            <div>
              <p>Bien dormi</p>
            </div>
          </div>
        </div>
      </div>

      <div class="comment-section mt-2">
        <span class="comment" @click="toggleSmiley('etoile')">⭐</span>
        <span class="comment" @click="toggleSmiley('coeur')">❤️</span>
        <span class="comment" @click="toggleSmiley('bravo')">👏</span>
        <span class="comment" @click="toggleSmiley('ohnon')">😞</span>
      </div>

      <div class="card mx-2">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Change</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/change.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>change couche selles normale</div>
            </div>
            <div>change pipi seulement</div>
          </div>
        </div>
      </div>

      <div class="comment-section mt-2">
        <span class="comment" @click="toggleSmiley('etoile')">⭐</span>
        <span class="comment" @click="toggleSmiley('coeur')">❤️</span>
        <span class="comment" @click="toggleSmiley('bravo')">👏</span>
        <span class="comment" @click="toggleSmiley('ohnon')">😞</span>
      </div>

      <div class="card mx-2">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Température</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/Thermometre.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>Rien à signaler</div>
            </div>
          </div>
        </div>
      </div>

      <div class="comment-section mt-2">
        <span class="comment" @click="toggleSmiley('etoile')">⭐</span>
        <span class="comment" @click="toggleSmiley('coeur')">❤️</span>
        <span class="comment" @click="toggleSmiley('bravo')">👏</span>
        <span class="comment" @click="toggleSmiley('ohnon')">😞</span>
      </div>

      <div class="card mx-2">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Médicaments</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/medicament.png"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>Pas de prise de médicament</div>
            </div>
          </div>
        </div>
      </div>

      <div class="comment-section mt-2">
        <span class="comment" @click="toggleSmiley('etoile')">⭐</span>
        <span class="comment" @click="toggleSmiley('coeur')">❤️</span>
        <span class="comment" @click="toggleSmiley('bravo')">👏</span>
        <span class="comment" @click="toggleSmiley('ohnon')">😞</span>
      </div>

      <div class="card mx-2">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Gouter</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/gouter.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>Le gouter pour aujourd'hui est un fruit</div>
            </div>
            <div>
              <p>il a bien mangé</p>
            </div>
          </div>
        </div>
      </div>

      <div class="comment-section mt-2">
        <span class="comment" @click="toggleSmiley('etoile')">⭐</span>
        <span class="comment" @click="toggleSmiley('coeur')">❤️</span>
        <span class="comment" @click="toggleSmiley('bravo')">👏</span>
        <span class="comment" @click="toggleSmiley('ohnon')">😞</span>
      </div>
      <div class="card mx-2">
        <div class="card-body">
          <div class="col-md-6">
            <h4>Sortie&Loisir</h4>
            <div class="d-flex align-items-center">
              <div class="img-container me-5">
                <img
                  src="../../assets/parc.jpg"
                  class="img-fluid rounded-circle"
                  alt="Icône Horaire"
                />
              </div>
              <div>
                Il ne fait pas beau aujourd'hui on decidé de rester à la maison
              </div>
            </div>
            <div>
              <p>On a danser</p>
            </div>
          </div>
        </div>
      </div>

      <div class="comment-section mt-2">
        <span class="comment" @click="toggleSmiley('etoile')">⭐</span>
        <span class="comment" @click="toggleSmiley('coeur')">❤️</span>
        <span class="comment" @click="toggleSmiley('bravo')">👏</span>
        <span class="comment" @click="toggleSmiley('ohnon')">😞</span>
      </div>
    </div>
  </div>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");
h4 {
  color: rgb(166, 161, 161);
  font-family: "Pacifico", cursive;
}
.img-container {
  width: 80px;
  height: 80px;
  overflow: hidden;
}

.img-container img {
  max-width: 100%;
  max-height: 100%;
  object-fit: cover;
}
</style>
