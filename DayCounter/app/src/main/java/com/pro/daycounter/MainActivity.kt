package com.pro.daycounter

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pro.daycounter.ui.theme.DayCounterTheme
import org.w3c.dom.Text
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.GregorianCalendar

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.startBtn)
        val endButton = findViewById<Button>(R.id.endBtn)
        val textArea = findViewById<TextView>(R.id.finalDate)

        var startDate: LocalDate? = null
        var endDate: LocalDate? = null
        var todayDate = ""

        val today = GregorianCalendar()
        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DATE)

        val todayDateTxt = findViewById<TextView>(R.id.todayDate)
        todayDate="Today is ${year}.${month+1}.${day}"
        todayDateTxt.text = todayDate

        textArea.text = ChronoUnit.DAYS.between(LocalDate.of(2024, 8, 23), LocalDate.now()).toString() +"일 째"

        startButton.setOnClickListener {


            val dlg= DatePickerDialog(this, object:DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    startDate = LocalDate.of(year, month + 1, dayOfMonth)

                }
            }, year, month, day)
            dlg.show()
        }

        endButton.setOnClickListener {

            val dlg=DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//                    endDate = year.toString() + month + 1.toString() + dayOfMonth.toString()
//                    textArea.text = (endDate.toInt() - startDate.toInt() + 1).toString()
                    endDate = LocalDate.of(year, month + 1, dayOfMonth)

                    textArea.text=ChronoUnit.DAYS.between(startDate,endDate).toString() + "일 째"
                }
            } , year, month, day)

            dlg.show()
        }

    }
}