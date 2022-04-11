<template>
  <div>
    <div id="main" :style="{width: '100%', height: '1000px'}"></div>
  </div>
</template>

<script>
export default {
  name: "DiskUsage",
  data(){
    return{
    }
  },
  mounted() {
    this.laodData();
  },
  methods:{
    laodData(){
      var app = {};

      var chartDom = document.getElementById('main');
      var myChart = echarts.init(chartDom);
      var option;
      function colorMappingChange(value) {
        var levelOption = getLevelOption(value);
        chart.setOption({
          series: [{
            levels: levelOption
          }]
        });
      }

      var formatUtil = echarts.format;

      function getLevelOption() {
        return [
          {
            itemStyle: {
              borderWidth: 0,
              gapWidth: 5
            }
          },
          {
            itemStyle: {
              gapWidth: 1
            }
          },
          {
            colorSaturation: [0.35, 0.5],
            itemStyle: {
              gapWidth: 1,
              borderColorSaturation: 0.6
            }
          }
        ];
      }
      var diskData = require('../../assets/data/disk.tree.json');
      myChart.setOption(option = {

        title: {
          text: 'Disk Usage',
          left: 'center'
        },

        tooltip: {
          formatter: function (info) {
            var value = info.value;
            var treePathInfo = info.treePathInfo;
            var treePath = [];

            for (var i = 1; i < treePathInfo.length; i++) {
              treePath.push(treePathInfo[i].name);
            }

            return [
              '<div class="tooltip-title">' + formatUtil.encodeHTML(treePath.join('/')) + '</div>',
              'Disk Usage: ' + formatUtil.addCommas(value) + ' KB',
            ].join('');
          }
        },

        series: [
          {
            name: 'Disk Usage',
            type: 'treemap',
            visibleMin: 300,
            label: {
              show: true,
              formatter: '{b}'
            },
            itemStyle: {
              borderColor: '#fff'
            },
            levels: getLevelOption(),
            data: diskData
          }
        ]
      })
      option && myChart.setOption(option);
    }

  }

}
</script>

<style scoped>

</style>