import com.google.gson.annotations.SerializedName

data class GraphDataValue (
	@SerializedName("name") val name : String,
	@SerializedName("value") val value : Int
)