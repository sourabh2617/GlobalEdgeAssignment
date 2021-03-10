package com.sourabh.globaledgetest.util

import android.annotation.SuppressLint
import android.content.Context
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utils{
    companion object{
        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

        @SuppressLint("SimpleDateFormat")
        fun convertTime(time: String):Float{
            val mTime = time.split(" ")
            println(mTime)
            var index =2;
            if(mTime.size==index){
                index-=1
            }
            var timeFloat = "0";
            try {
                val sdf = SimpleDateFormat("hh:mm:ss")
                val dateObj: Date = sdf.parse(mTime.get(index))
                timeFloat = SimpleDateFormat("K.mm").format(dateObj)
                System.out.println(timeFloat + " Float value: "+timeFloat.toFloat())
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return timeFloat.toFloat()
        }

    }
}
