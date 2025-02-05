import { Configuration } from '@nuxt/types'
const purgecss = require('@fullhuman/postcss-purgecss')
const autoprefixer = require('autoprefixer')

const config: Configuration = {
  server: {
    port: 3000, // default: 3000
    // host: '0.0.0.0' // default: localhost
  },
  mode: 'universal',
  /*
   ** Headers of the page
   */
  head: {
    htmlAttrs: {
      prefix: 'og: http://ogp.me/ns#'
    },
    titleTemplate: '%s | Covid-19 疫情分析与管理系统 | 数据库 2020 期末项目',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      {
        hid: 'description',
        name: 'description',
        content:
          '当サイトは新型コロナウイルス感染症（COVID-19）に関する最新情報を提供するために、東京都が開設したものです。'
      },
      {
        hid: 'og:site_name',
        property: 'og:site_name',
        content: 'Covid-19 疫情分析与管理系统 | 数据库 2020 期末项目'
      },
      { hid: 'og:type', property: 'og:type', content: 'website' },
      {
        hid: 'og:url',
        property: 'og:url',
        content: 'https://stopcovid19.metro.tokyo.lg.jp'
      },
      {
        hid: 'og:title',
        property: 'og:title',
        content: 'Covid-19 疫情分析与管理系统 | 数据库 2020 期末项目'
      },
      {
        hid: 'og:description',
        property: 'og:description',
        content:
          '数据库分析系统'
      },
      {
        hid: 'og:image',
        property: 'og:image',
        content: 'https://stopcovid19.metro.tokyo.lg.jp/ogp.png'
      },
      {
        hid: 'twitter:card',
        name: 'twitter:card',
        content: 'summary_large_image'
      },
      {
        hid: 'twitter:site',
        name: 'twitter:site',
        content: '@tokyo_bousai'
      },
      {
        hid: 'twitter:creator',
        name: 'twitter:creator',
        content: '@tokyo_bousai'
      },
      {
        hid: 'twitter:image',
        name: 'twitter:image',
        content: 'https://stopcovid19.metro.tokyo.lg.jp/ogp.png'
      }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' },
      { rel: 'apple-touch-icon', href: '/apple-touch-icon-precomposed.png' }
    ]
  },
  /*
   ** Customize the progress-bar color
   */
  loading: { color: '#fff' },
  /*
   ** Global CSS
   */
  css: ['~assets/global.scss'],
  /*
   ** Plugins to load before mounting the App
   */
  plugins: [
    {
      src: '@/plugins/vue-chart.ts',
      ssr: true
    },
    {
      src: '@/plugins/vuetify.ts',
      ssr: true
    }
  ],
  /*
   ** Nuxt.js dev-modules
   */
  buildModules: [
    '@nuxt/typescript-build',
    '@nuxtjs/google-analytics',
    '@nuxtjs/vuetify',
  ],
  /*
   ** Nuxt.js modules
   */
  modules: [
    // Doc: https://axios.nuxtjs.org/usage
    '@nuxtjs/axios',
    '@nuxtjs/pwa',
    // Doc: https://github.com/nuxt-community/dotenv-module
    '@nuxtjs/dotenv',
    [
      'nuxt-i18n',
      {
        strategy: 'prefix_except_default',

        locales: [
          {
            code: 'ja',
            name: '日本語',
            iso: 'ja-JP'
          },
          {
            code: 'en',
            name: 'English',
            iso: 'en-US'
          },
          {
            code: 'zh-cn',
            name: '簡体字',
            iso: 'zh-CN'
          },
          {
            code: 'zh-tw',
            name: '繁體字',
            iso: 'zh-TW'
          },
          {
            code: 'ko',
            name: '한국어',
            iso: 'ko-KR'
          }

        ],
        defaultLocale: 'zh-cn',
        vueI18n: {
          fallbackLocale: 'zh-cn',
          formatFallbackMessages: false
        },
        vueI18nLoader: true
      }
    ],
    'nuxt-svg-loader',
    'nuxt-purgecss',
    ['vue-scrollto/nuxt', { duration: 1000, offset: -72 }]
  ],
  /*
   ** Axios module configuration
   ** See https://axios.nuxtjs.org/options
   */
  axios: {},
  /*
   ** vuetify module configuration
   ** https://github.com/nuxt-community/vuetify-module
   */
  vuetify: {
    treeShake: true,
    customVariables: ['~/assets/variables.scss'],
    defaultAssets: {
      font: {
        family: 'Roboto'
      },
      icons: 'mdi',
      options: {customProperties: true},
      // themes: {
      //   light: {
      //     primary: '#C70000'
      //   },
      //   dark: {
      //     primary: '#C70000'
      //   }
      // }
    }
  },
  googleAnalytics: {
    id: 'UA-159417676-1'
  },
  build: {
    // postcss: {
    //   plugins: [
    //     autoprefixer({ grid: 'autoplace' }),
    //     purgecss({
    //       content: [
    //         './pages/**/*.vue',
    //         './layouts/**/*.vue',
    //         './components/**/*.vue',
    //       ],
    //       whitelist: ['html', 'body', 'nuxt-progress', 'DataCard'],
    //       whitelistPatterns: [/(col|row)/]
    //     })
    //   ]
    // },
    // https://ja.nuxtjs.org/api/configuration-build/#hardsource
    hardSource: process.env.NODE_ENV === 'development'
  },
  manifest: {
    name: '新馆疫情分析系统',
    theme_color: '#00a040',
    background_color: '#ffffff',
    display: 'standalone',
    Scope: '/',
    start_url: '/',
    splash_pages: null
  },
  generate: {
    fallback: true
  },
  // /*
  // ** hot read configuration for docker
  // */
  watchers: {
    webpack: {
      poll: true
    }
  }
}

export default config
