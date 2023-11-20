<script>
import { RouterLink } from "vue-router";
import { useVuelidate } from "@vuelidate/core";
import { required, email } from "@vuelidate/validators";

export default {
  //  activate Vuelidate inside setup by calling useVuelidate
  setup() {
    return {
      validator: useVuelidate({}),
    };
  },
  data() {
    return {
      email: "",
      password: "",
      message: "",
    };
  },

  // data and validations have a matching structure
  validations() {
    return {
      email: { required, email },
      password: { required },
    };
  },

  methods: {
    // Fonction qui va formater le message d'erreur ou de succès lors de la validation du formulaire
    formatMessage(error) {
      if (error.$validator == "required") {
        this.message = `Le champs ${error.$property} est obligatoire`;
      } else if (error.$validator == "email") {
        this.message = `L'email est invalid`;
      }
    },

    async submit() {
      // Déclenchement de la validation
      const validate = await this.validator.$validate();
      if (!validate) {
        // notify user form is invalid
        this.formatMessage(this.validator.$errors[0]);
        return;
      }
      alert("Submited baby !");
    },
  },
};
</script>

<template>
  <div class="main">
    <section class="h-100">
      <div class="container h-100">
        <div class="row justify-content-sm-center align-items-center h-100">
          <div class="col-xxl-6 col-xl-6 col-lg-9 col-md-11 col-sm-12">
            <div class="text-center mb-2">
              <img src="../assets/massnote64.png" alt="logo" width="100" />
            </div>
            <div class="card">
              <div class="card-body p-5">
                <h1 class="fs-1 fw-semibold text-center mb-5">
                  Sign in to your account
                </h1>
                <form @submit.prevent="submit" novalidate>
                  <div v-if="validator.$error" class="redToaster mb-4">
                    {{ this.message }}
                  </div>
                  <div class="mb-5 fs-5">
                    <label class="mb-2" for="email">Email</label>
                    <input
                      id="email"
                      type="email"
                      class="form-control focus-ring focus-ring-light py-3"
                      name="email"
                      required
                      autofocus
                      v-model="email"
                    />
                  </div>

                  <div class="mb-3">
                    <div class="mb-2 w-100">
                      <label class="fs-5" for="password">Password</label>
                      <RouterLink to="/reset" class="link float-end mb-2"
                        >Forgot Password?</RouterLink
                      >
                    </div>
                    <input
                      id="password"
                      type="password"
                      class="form-control focus-ring focus-ring-light py-3"
                      name="password"
                      required
                      v-model="password"
                    />
                  </div>

                  <div class="d-flex align-items-center">
                    <button
                      type="submit"
                      class="w-100 btn btn-primary py-3 mt-3"
                    >
                      Sign in
                    </button>
                  </div>
                </form>
              </div>
              <div class="fs-5 py-1 border-0">
                <div class="text-center">
                  Don't have an account?

                  <RouterLink
                    :to="{ name: 'SignUp', params: { id: 3 } }"
                    class="link"
                    >Create One</RouterLink
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped>
.main {
  position: relative;
  width: 100%;
  height: 100vh;
  background-color: var(--background-color);
}

.card {
  color: #eeeeee;
  background-color: var(--background-color);
  border: none;
}

input {
  background-color: #222222;
  border: none;
  font-size: large;
  color: #eeeeee;
}

input:focus {
  background-color: #222222;
  outline: 0;
  color: #eeeeee;
  text-justify: text-center;
}

.link {
  color: #a78bfa;
  text-decoration: none;
  font-size: 18px;
  font-weight: bold;
}

.link:hover {
  color: #b195ff;
}

button {
  background-color: #7c3aed;
  border: none;
  font-size: 20px;
  font-weight: bold;
}

button:hover {
  background-color: #8b5cf6;
}

.redToaster {
  color: rgb(248 113 113);
  border: 1px solid rgb(127 29 29);
  background-color: rgb(69 10 10);
  padding: 0.8rem;
  border-radius: 0.25rem;
  text-align: center;
  font-size: 20px;
  line-height: 28px;
}

.greenToaster {
  color: rgb(74 222 128);
  border: 1px solid rgb(20 83 45);
  background-color: rgb(5 46 22);
  padding: 0.6rem;
  border-radius: 0.25rem;
  text-align: center;
  font-size: 20px;
  line-height: 28px;
}
</style>
