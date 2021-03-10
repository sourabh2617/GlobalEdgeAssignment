
import com.github.mikephil.charting.components.AxisBase

import com.github.mikephil.charting.formatter.ValueFormatter


class ClaimsXAxisValueFormatter :
    ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase): String {
        System.out.println(""+ value.toString().replace(".",":") )
        return "%.2f".format(value).replace(".",":") + "am"
    }
}