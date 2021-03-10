package com.sourabh.globaledgetest.view

import ClaimsXAxisValueFormatter
import DataFromJason
import GraphDataValue
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sourabh.globaledgetest.util.ClaimsYAxisValueFormatter
import com.sourabh.globaledgetest.R
import com.sourabh.globaledgetest.util.Utils
import com.sourabh.globaledgetest.viewModel.GraphViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class GraphDisplayActivity : AppCompatActivity() {
    private val viewModel: GraphViewModel by viewModels()
    private val jsonFile = "graphData.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getJsonDataFromAsset(applicationContext, jsonFile)
        viewModel.graphDataValueList.observe(this, androidx.lifecycle.Observer {
            val data = getData(it)
            setupChart(chart, data)
        })

    }

    private fun setupChart(chart: LineChart, data: LineData?) {
        // no description text
        chart.description.isEnabled = true
        chart.setTouchEnabled(true)
        // enable scaling and dragging
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false)
        chart.setBackgroundColor(Color.WHITE)

        // set custom chart offsets (automatic offset calculation is hereby disabled)
        chart.setViewPortOffsets(50f, 0f, 10f, 70f)

        // add data
        chart.data = data
        val position = XAxisPosition.BOTTOM
        // get the legend (only possible after setting data)
        val l = chart.legend
        val xAxis = chart.xAxis

        l.isEnabled = false
        chart.axisLeft.isEnabled = true
        chart.axisLeft.spaceTop = 40f
        chart.axisLeft.spaceBottom = 40f
        chart.axisRight.isEnabled = false
        xAxis.isEnabled = true
        xAxis.position = position
        chart.axisLeft.valueFormatter =
            ClaimsYAxisValueFormatter()
        xAxis.valueFormatter = ClaimsXAxisValueFormatter()

        xAxis.enableGridDashedLine(2f, 7f, 0f);
        xAxis.setLabelCount(4, true);
        xAxis.isGranularityEnabled = true;
        xAxis.granularity = 7f;
        xAxis.labelRotationAngle = 315f;
        // animate calls invalidate()...
        chart.animateX(2500)
    }

    private fun getData(
        graphDataValueList: List<GraphDataValue> ): LineData? {
        val values: ArrayList<Entry> = ArrayList()
        var index = 0;
        //Showing less data to display clear graph, Need to look into json file values
        for (item in graphDataValueList){
            if(index<22){
                values.add(Entry(Utils.convertTime(item.name),item.value.toFloat()))
                index +=1
            }
        }
        val set1 = LineDataSet(values, "DataSet 1")

        set1.lineWidth = 4f
        set1.color = Color.RED
        set1.setDrawCircles(false)
        set1.setDrawValues(false)
        set1.mode = LineDataSet.Mode.CUBIC_BEZIER;

        // create a data object with the data sets
        return LineData(set1)
    }


    override fun onResume() {
        super.onResume()
         setupGradient(chart)
    }

    private fun setupGradient(mChart: LineChart) {
        val paint: Paint = mChart.renderer.paintRender
        val linGrad = LinearGradient(
            0f, 1024f, 0f, 0f,
            Color.YELLOW, Color.RED,
            Shader.TileMode.REPEAT
        )
        paint.shader = linGrad
    }
}