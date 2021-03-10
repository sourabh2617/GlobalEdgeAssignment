package com.sourabh.globaledgetest.util

import com.github.mikephil.charting.components.AxisBase

import com.github.mikephil.charting.formatter.ValueFormatter


class ClaimsYAxisValueFormatter :
    ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase): String {
        return value.toString()
    }
}