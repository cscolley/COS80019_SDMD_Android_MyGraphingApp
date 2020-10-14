package au.edu.swin.sdmd.mygraphingapp

import android.graphics.Color
import android.graphics.DashPathEffect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter;
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

var dates: ArrayList<String> = ArrayList()


class MainActivity : AppCompatActivity() {

    lateinit var mChart: LineChart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mChart = findViewById(R.id.chart)
        mChart.setTouchEnabled(true)
        mChart.setPinchZoom(true)

        populateDateStringArrayList()

        renderData()

    }

    fun populateDateStringArrayList() {
        dates.add("01/10/2020")
        dates.add("02/10/2020")
        dates.add("03/10/2020")
        dates.add("04/10/2020")
        dates.add("05/10/2020")
        dates.add("06/10/2020")
        dates.add("07/10/2020")
        dates.add("08/10/2020")
        dates.add("09/10/2020")
        dates.add("10/10/2020")
        dates.add("11/10/2020")
        dates.add("12/10/2020")
        dates.add("13/10/2020")
        dates.add("14/10/2020")
        dates.add("15/10/2020")
    }

    fun renderData() {
        var xAxis: XAxis = mChart.getXAxis()
        xAxis.enableGridDashedLine(10f, 10f, 10f)
        xAxis.valueFormatter = MyXAxisValueFormatter()

        var yAxis: YAxis = mChart.axisLeft
        yAxis.axisMaximum = 10f
        yAxis.axisMinimum = 0f
        yAxis.enableGridDashedLine(10f, 10f, 0f)
        var yAxisRight: YAxis = mChart.axisRight
        yAxisRight.isEnabled = false

        setData()

    }

    fun setData() {
        var scores: ArrayList<Entry> = ArrayList()
        scores.add(Entry(0f, 6f))
        scores.add(Entry(1f, 5f))
        scores.add(Entry(2f, 4f))
        scores.add(Entry(3f, 3f))
        scores.add(Entry(4f, 4f))
        scores.add(Entry(5f, 5f))
        scores.add(Entry(6f, 8f))
        scores.add(Entry(7f, 9f))
        scores.add(Entry(8f, 2f))
        scores.add(Entry(9f, 1f))
        scores.add(Entry(10f, 2f))
        scores.add(Entry(11f, 4f))
        scores.add(Entry(12f, 5f))
        scores.add(Entry(13f, 3f))
        scores.add(Entry(14f, 4f))



        var set1: LineDataSet

        if(mChart.data != null && mChart.data.dataSetCount > 0) {
            set1 = mChart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = scores
            mChart.data.notifyDataChanged()
            mChart.notifyDataSetChanged()
        } else {
            set1 = LineDataSet(scores, "Mood Score Data")
            set1.setDrawIcons(false)
            set1.enableDashedLine(10f, 5f, 0f)
            set1.enableDashedHighlightLine(10f, 5f, 0f)
            set1.color = Color.DKGRAY
            set1.setCircleColor(Color.DKGRAY)
            set1.lineWidth = 1f
            set1.circleRadius = 3f
            set1.setDrawCircleHole(false)
            set1.setDrawFilled(true)
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.setFormSize(15f)

            var dataSets: ArrayList<ILineDataSet> = ArrayList()
            dataSets.add(set1)
            var data: LineData = LineData(dataSets)
            mChart.data = data


        }
    }
}

class MyXAxisValueFormatter : ValueFormatter() {

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        var position: Int = value.roundToInt()
        val sdf = SimpleDateFormat("dd MMM")

        println("dates.size = ${dates.size}")
        println("position = $position")

        return sdf.format(Date(getDateInMilliseconds(dates.get(position), "dd/MM/yyyy")))
    }

    fun getDateInMilliseconds(givenDateString: String, format: String) : Long {
        val sdf: SimpleDateFormat = SimpleDateFormat(format, Locale.UK)
        var timeInMilliseconds: Long = 1
        try {
            var date: Date = sdf.parse(givenDateString);
            timeInMilliseconds = date.time
        } catch(pe: ParseException) {
            pe.printStackTrace()
        }
        return timeInMilliseconds
    }
}
