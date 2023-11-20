<script>
export default {
  props: {
    id: {
      type: String,
      default: null,
    },
  },
};
</script>
<template>
  <div class="mb-5">
    <div
      :id="id"
      class="toast-container position-fixed bottom-0 end-0 p-3 mb-5"
    >
      <div class="toast shadow-sm">
        <div class="toast-body"></div>
      </div>
    </div>
  </div>
</template>
