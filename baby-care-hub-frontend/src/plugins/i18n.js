import enMessages from "../../labels/en.js";
import frMessages from "../../labels/fr.js";
import { createI18n } from "vue-i18n";

const shortDatetimeFormat = {
  year: "numeric",
  month: "numeric",
  day: "2-digit",
};

const longDatetimeFormat = {
  year: "numeric",
  month: "long",
  day: "numeric",
};

const datetimeFormats = {
  en: {
    short: shortDatetimeFormat,
    long: longDatetimeFormat,
  },
  fr: {
    short: shortDatetimeFormat,
    long: longDatetimeFormat,
  },
};

const numberFormats = {
  en: {
    currency: {
      style: "currency",
      currency: "USD",
      notation: "standard",
    },
  },
  fr: {
    currency: {
      style: "currency",
      currency: "EUR",
      notation: "standard",
    },
  },
};

const i18n = createI18n({
  locale:
    "en" /* la langue par defaut c'est anglais ou francais par rapport au choix 'en ou 'fr */,
  fallbackLocale: "en",
  messages: {
    en: enMessages,
    fr: frMessages,
  },
  datetimeFormats,
  numberFormats,
});
export default i18n;
