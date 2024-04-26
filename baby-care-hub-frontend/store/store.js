import { defineStore } from "pinia";
import { ref } from "vue";

export default defineStore("babycarehubstore", () => {
  const actualities = ref({});
  const childId = ref("");

  return {
    actualities,
    childId,
  };
});
