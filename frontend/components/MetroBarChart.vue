<template>
  <data-view :title="title" :title-id="titleId" :date="date">
    <template v-slot:button>
      <p class="MetroGraph-Desc">
        *{{ $t('{range}都営地下鉄4路線の自動改札出場数',{range: $t(chartData.base_period)}) }}
      </p>
    </template>
    <bar
      :chart-id="chartId"
      :chart-data="displayData"
      :options="chartOption"
      :height="240"
    />
  </data-view>
</template>

<i18n>
{
  "ja": {
    "{range}の利用者数*の平均値を基準としたときの相対値": "{range}の利用者数*の平均値を基準としたときの相対値",
    "都営地下鉄4路線の自動改札出場数": "都営地下鉄4路線の自動改札出場数",
    "1月20日~1月24日": "1月20日~1月24日"
  },
  "en": {
    "{range}の利用者数*の平均値を基準としたときの相対値": "Relative value based on the average number of users {range}",
    "都営地下鉄4路線の自動改札出場数": "Total number of passengers using four Toei subway lines",
    "1月20日~1月24日": "from January 20 to 24"
  },
  "zh-cn": {
    "{range}の利用者数*の平均値を基準としたときの相対値": " ",
    "{range}都営地下鉄4路線の自動改札出場数": " 基于{range}间WHO数据",
    "1月20日~1月24日": "3月~7月"
  },
  "zh-tw": {
    "{range}の利用者数*の平均値を基準としたときの相対値": "基於{range}的平均搭乘人數*之相對數值",
    "都営地下鉄4路線の自動改札出場数": "都營地下鐵 4 條路線的出站人數",
    "1月20日~1月24日": "1月20日~1月24日"
  },
  "ko": {
    "{range}の利用者数*の平均値を基準としたときの相対値": "{range}의 이용자수*의 평균치를 기준으로 했을때의 상대치",
    "都営地下鉄4路線の自動改札出場数": "도에이 전철 4개 노선을 이용한 승객 수",
    "1月20日~1月24日": "1월 20일~1월24일"
  },
  "pt-BR": {
    "{range}の利用者数*の平均値を基準としたときの相対値": "",
    "都営地下鉄4路線の自動改札出場数": "Total de usuarios das 4 linhas de metro Toei",
    "1月20日~1月24日": ""
  },
  "ja-basic": {
    "{range}の利用者数*の平均値を基準としたときの相対値": "ひとの かずを くらべると",
    "都営地下鉄4路線の自動改札出場数": "ちかてつから でてきた ひとの かず",
    "1月20日~1月24日": "1がつ20にち から 1がつ24にち"
  }
}
</i18n>

<style lang="scss">
.MetroGraph {
  &-Desc {
    margin-top: 10px;
    margin-bottom: 0 !important;
    font-size: 12px;
    color: $gray-3;
  }
}
</style>

<script>
import DataView from '@/components/DataView.vue'

export default {
  components: { DataView },
  props: {
    title: {
      type: String,
      required: false,
      default: ''
    },
    titleId: {
      type: String,
      required: false,
      default: ''
    },
    chartId: {
      type: String,
      required: false,
      default: 'metro-bar-chart'
    },
    chartData: {
      type: Object,
      required: false,
      default: () => {}
    },
    chartOption: {
      type: Object,
      required: false,
      default: () => {}
    },
    date: {
      type: String,
      required: true,
      default: ''
    }
  },
  computed: {
    displayData() {
      // const colors = ['#a6e29f', '#63c765', '#008b41']
      const colors = ['#c70000', '#C75555', '#d59c9c']
      return {
        labels: this.chartData.datasets.map(d => d.label),
        datasets: this.chartData.labels.map((label, i) => {
          return {
            label,
            data: this.chartData.datasets.map(d => d.data[i]),
            backgroundColor: colors[i],
            borderWidth: 0
          }
        })
      }
    }
  }
}
</script>
