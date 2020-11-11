package au.edu.swin.sdmd.mygraphingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anychart.anychart.*
import com.anychart.anychart.Set

class AnyChartActivity : AppCompatActivity() {

    lateinit var anyChartView: AnyChartView
    lateinit var cartesian: Cartesian

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_any_chart)

        anyChartView = findViewById(R.id.any_chart_view)

        cartesian = AnyChart.line()

        cartesian.setAnimation(true)

        renderData()

        makeInteractive()
    }

    fun renderData() {
        cartesian.setPadding(20.0, 20.0, 20.0, 20.0)

        cartesian.setTitle("Mood Score vs Date")

        cartesian.xAxis.setTitle("Date")
        cartesian.xAxis.labels.setPadding(5.0, 5.0, 5.0, 5.0)
        cartesian.xAxis.setMinorTicks(true)

        cartesian.yAxis.setTitle("Mood Score")
        cartesian.yAxis.labels.setPadding(5.0, 5.0, 5.0, 5.0)

        cartesian.setXScale(DateTime())

        setData()
    }

    fun setData() {
        var seriesData: ArrayList<ValueDataEntry> = ArrayList()

        seriesData.add(ValueDataEntry("2020-10-01", 3))
        seriesData.add(ValueDataEntry("2020-10-02", 4))
        seriesData.add(ValueDataEntry("2020-10-03", 6))
        seriesData.add(ValueDataEntry("2020-10-04", 5))
        seriesData.add(ValueDataEntry("2020-10-07", 9))
        seriesData.add(ValueDataEntry("2020-10-08", 8))
        seriesData.add(ValueDataEntry("2020-10-09", 2))
        seriesData.add(ValueDataEntry("2020-10-10", 3))
        seriesData.add(ValueDataEntry("2020-10-10", 4))
        seriesData.add(ValueDataEntry("2020-10-10", 5))
        seriesData.add(ValueDataEntry("2020-10-11", 2))
        seriesData.add(ValueDataEntry("2020-10-12", 4))
        seriesData.add(ValueDataEntry("2020-10-13", 3))
        seriesData.add(ValueDataEntry("2020-10-14", 5))
        seriesData.add(ValueDataEntry("2020-10-15", 4))
        seriesData.add(ValueDataEntry("2020-10-16", 5))

        cartesian.setData(seriesData as List<DataEntry>?)

        anyChartView.setChart(cartesian)
    }

    fun makeInteractive() {
        cartesian.setXScroller(true)

        cartesian.crosshair.setEnabled(true)
        cartesian.crosshair.setYLabel(true)
        cartesian.tooltip.setPositionMode(TooltipPositionMode.POINT)
    }
}