package mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.profile.components

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis

public  fun customChart(myChart: BarChart, myColors: ArrayList<Int>){

    myChart.description.isEnabled = false

    // Xaxis
    myChart.getXAxis().setDrawGridLines(false)
    myChart.getXAxis().position = XAxis.XAxisPosition.BOTTOM
    myChart.getXAxis().setDrawAxisLine(true)
    myChart.getXAxis().setDrawLabels(false)
    myChart.getXAxis().axisLineWidth = 1f

    //YaxisLeft
    myChart.getAxisLeft().setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
    myChart.getAxisLeft().setDrawGridLines(false)
    myChart.getAxisLeft().setDrawAxisLine(false)
    myChart.getAxisLeft().isEnabled = false

    myChart.getAxisRight().setEnabled(false)

    //YaxisRight
    myChart.getAxisRight().setEnabled(false)

    // set your custom renderer
    myChart.setRenderer(
        BarChartCustomRenderer(
            myChart,
            myChart.getAnimator(),
            myChart.getViewPortHandler(),
            myColors
        )
    )
    myChart.setDrawValueAboveBar(true)
}