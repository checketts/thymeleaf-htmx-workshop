const colors = require('tailwindcss/colors')
const plugin = require('tailwindcss/plugin')
const defaultTheme = require('tailwindcss/defaultTheme')

/** @type {import('tailwindcss').Config} */
module.exports = plugin.withOptions(function (options = {}) {
  return function({ addBase, addComponents, addUtilities }) {
    addComponents({
      '.btn-base': {
        '@apply bg-blue-30 text-white rounded-sm px-4 py-0 font-normal hover:bg-blue-25  active:bg-blue-40 selection:bg-blue-40 disabled:bg-gray-90 disabled:bg-opacity-10 disabled:text-text-primary disabled:text-opacity-40': {}
      },
      '.btn-primary': {
        '@apply btn-base text-base h-10': {}
      },
      '.btn-primary-condensed': {
        '@apply btn-base text-sm h-8': {}
      },
      '.btn-base-secondary': {
        '@apply bg-white text-blue-30 rounded-sm px-4 font-normal border border-blue-30 box-border hover:text-blue-25 hover:ring-1 hover:ring-offset-0 hover:ring-blue-30 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-25 active:bg-blue-40 active:text-white selection:bg-blue-40 selection:text-white disabled:bg-transparent disabled:text-text-primary disabled:text-opacity-40 disabled:hover:ring-0 disabled:border-gray-90 disabled:border-opacity-40': {}
      },
      '.btn-secondary': {
        '@apply btn-base-secondary text-base h-10': {}
      },
      '.btn-secondary-condensed': {
        '@apply btn-base-secondary text-sm h-8': {}
      },
      '.btn-base-ghost': {
        '@apply bg-transparent text-blue-30 rounded-sm px-4 font-normal hover:bg-gray-3 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-25 active:bg-gray-5 selection:bg-gray-5 selection:text-white disabled:bg-transparent disabled:text-text-primary disabled:text-opacity-40  disabled:border-gray-90 disabled:border-opacity-40': {}
      },
      '.btn-ghost': {
        '@apply btn-base-ghost text-base h-10': {}
      },
      '.btn-ghost-condensed': {
        '@apply btn-base-ghost text-sm h-8': {}
      },
      '.btn-base-danger': {
        '@apply bg-danger-60 text-white rounded-sm px-4 py-0 font-normal hover:bg-danger-40 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-danger-60 active:bg-danger-60 selection:bg-blue-40 disabled:bg-gray-90 disabled:bg-opacity-10 disabled:text-text-primary disabled:text-opacity-40': {}
      },
      '.btn-danger': {
        '@apply btn-base-danger text-base h-10': {}
      },
      '.btn-danger-condensed': {
        '@apply btn-base-danger text-sm h-8': {}
      }

    });

    addUtilities({

    })



    
    const useZoramFont = options.useZoramFont ?? true

    if(useZoramFont) {
      addBase({
        '@font-face': {
          fontFamily: 'Zoram', fontStyle: 'normal', fontWeight: '300',
          src: "url('//cdn.churchofjesuschrist.org/cdn2/common/fonts/zoram/20150327/Zoram-GWeb-Light.woff') format('woff')"
        }})
        addBase({
        '@font-face': {
          fontFamily: 'Zoram', fontStyle: 'italic', fontWeight: '300',
          src: "url('//cdn.churchofjesuschrist.org/cdn2/common/fonts/zoram/20150327/Zoram-GWeb-Light-Italic.woff') format('woff')"
        }});
        addBase({
        '@font-face': {
          fontFamily: 'Zoram', fontStyle: 'normal', fontWeight: '400',
          src: "url('//cdn.churchofjesuschrist.org/cdn2/common/fonts/zoram/20150327/Zoram-GWeb-Regular.woff') format('woff')"
        }});
        addBase({
        '@font-face': {
          fontFamily: 'Zoram', fontStyle: 'italic', fontWeight: '400',
          src: "url('//cdn.churchofjesuschrist.org/cdn2/common/fonts/zoram/20150327/Zoram-GWeb-Italic.woff') format('woff')"
        }});
        addBase({
        '@font-face': {
          fontFamily: 'Zoram', fontStyle: 'normal', fontWeight: '700',
          src: "url('//cdn.churchofjesuschrist.org/cdn2/common/fonts/zoram/20150327/Zoram-GWeb-Bold.woff') format('woff')"
        }});
        addBase({
        '@font-face': {
          fontFamily: 'Zoram', fontStyle: 'italic', fontWeight: '700',
          src: "url('//cdn.churchofjesuschrist.org/cdn2/common/fonts/zoram/20150327/Zoram-GWeb-Bold-Italic.woff') format('woff')"
        }});
        addBase({
        '@font-face': {
          fontFamily: 'Zoram', fontStyle: 'normal', fontWeight: '800',
          src: "url('//cdn.churchofjesuschrist.org/cdn2/common/fonts/zoram/20150327/Zoram-GWeb-ExtraBold.woff') format('woff')"
        }});
        addBase({
        '@font-face': {
          fontFamily: 'Zoram', fontStyle: 'italic', fontWeight: '800',
          src: "url('//cdn.churchofjesuschrist.org/cdn2/common/fonts/zoram/20150327/Zoram-GWeb-ExtraBoldItalic.woff') format('woff')"
        },    
      })
    }
    
  }
}, function (options = {}) {
  const useZoramFont = options.useZoramFont ?? true

  const config = {
    theme: {
      colors: {
        transparent: 'transparent',
        current: 'currentColor',
        black: colors.black,
        white: colors.white,
        blue: {
          5: "#b0effc",
          10: "#7de3f4",
          15: "#49ccec",
          20: "#01b6d1",
          25: "#007da5",
          30: "#006184",
          35: "#005175",
          40: "#003057",
        },
        green: {
          5: "#d3e952",
          10: "#bed21e",
          15: "#93c742",
          20: "#6db344",
          25: "#50a83e",
          30: "#318d43",
          35: "#206b3f",
          40: "#235c35",
        },
        yellow: {
          5: "#ffd61a",
          10: "#ddb81c",
          15: "#faa61a",
          20: "#f68d2e",
          25: "#e66a1f",
          30: "#d45311",
          35: "#974a07",
          40: "#674730",
        },
        red: {
          5: "#fda1b2",
          10: "#fc4e6d",
          15: "#e10f5a",
          20: "#bd0057",
          25: "#a6004e",
          30: "#bf124a",
          35: "#7d003f",
          40: "#6e0d33",
        },
        info: {
          10: "#20abd7",
          40: "#157493",
          60: "#105970",
        },
        confirmation: {
          10: "#a3d287",
          40: "#74bb49",
          60: "#375a22",
        },
        warning: {
          40: "#ffd072",
          60: "#ffae0c",
          80: "#8f4200",
        },
        danger: {
          10: "#fc7473",
          40: "#DC3838",
          60: "#B00504",
          80: "#960101",
        },
        neutral: {
          5: "#EFEFE7",
          10: "#E9E6E0",
          15: "#E9E3D9",
          20: "#D5CFBE",
          25: "#C8C0AB",
          30: "#C6BFAD",
          35: "#BEB097",
          40: "#B3A18D",
        },
        gray: {
          2: "#f7f8f8",
          3: "#eff0f0",
          5: "#e0e2e2",
          10: "#d0d0d3",
          15: "#bdc0c0",
          20: "#a9adad",
          25: "#9da1a1",
          30: "#878a8c",
          35: "#767b6e",
          40: "#53575b",
          60: "#3a3d40",
          90: "#0d0f10",
        },
        text: {
          120: "#212225",
          primary: "#212225",
          secondary: "#53575B",
          hyperlink: "#157493",
        }
      },
      extend: {
        
      }
    },
    plugins: [],
  }
  if(useZoramFont) {
    config.theme.extend.fontFamily = { 'sans': ['Zoram', ...defaultTheme.fontFamily.sans]}
  }


  return config
});

