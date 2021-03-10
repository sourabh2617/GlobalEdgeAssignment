import com.google.gson.annotations.SerializedName

data class GraphData (
	@SerializedName("graphDataValue") val graphDataValue : List<GraphDataValue>
)