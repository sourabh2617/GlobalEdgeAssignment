import com.google.gson.annotations.SerializedName


data class DataFromJason (

	@SerializedName("graphData") val graphData : List<GraphData>
)