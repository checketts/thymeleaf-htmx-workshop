
/** @type {import('tailwindcss').Config} */
module.exports = {
  // prefix: "tw-",
  content: [
      "./src/main/resources/templates/**/*.{html,js}",

  ],
  corePlugins: {
   // preflight: false,
  },
  theme: {
    extend: {
      colors: {
      },
    },
  },
  blocklist: [
  ],
  plugins: [
    require("./unity-defaults")
  ],
}
