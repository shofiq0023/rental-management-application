/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [ "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",],
  theme: {
    extend: {
      colors: {
        primary: "#1a202c",
      },
    },
    screens: {
      xl: { max: "1400px" },
      lg: { max: "1150px" },
      md: { max: "767px" },
      sm: { max: "639px" },
    },
  },
  plugins: [],
}

