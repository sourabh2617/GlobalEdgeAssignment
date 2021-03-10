package com.sourabh.globaledgetest.viewModel

import DataFromJason
import GraphDataValue
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sourabh.globaledgetest.repository.GraphRepository
import java.io.IOException

class GraphViewModel : ViewModel() {
    var graphDataValueList = MutableLiveData<List<GraphDataValue>>()

    fun getJsonDataFromAsset(context: Context, fileName: String){
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            val gson = Gson()
            val dataJasonType = object : TypeToken<DataFromJason>() {}.type
            val mData: DataFromJason = gson.fromJson(jsonString, dataJasonType)
            graphDataValueList.value= mData.graphData.get(0).graphDataValue
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

}