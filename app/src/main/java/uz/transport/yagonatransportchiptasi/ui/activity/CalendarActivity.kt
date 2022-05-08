package uz.transport.yagonatransportchiptasi.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import ru.cleverpumpkin.calendar.CalendarDate
import ru.cleverpumpkin.calendar.CalendarView
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.ActivityCalendarBinding
import java.util.*

/**
 * in this activity, the user selects the time of departure and arrival
 */
class CalendarActivity : AppCompatActivity() {
    lateinit var binding: ActivityCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = ContextCompat.getColor(this,R.color.white)

        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        val locationStart = intent.getStringExtra("locationStart")
        val locationEnd = intent.getStringExtra("locationEnd")
        val type = intent.getStringExtra("type")

        binding.ivClose.setOnClickListener { finish() }

        binding.tvAddress.text = "$locationStart - $locationEnd"

        calendar()



        binding.calendarView.onDateClickListener = { date ->

            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("day", "${date.dayOfMonth}")
            intent.putExtra("month", "${date.month}")
            intent.putExtra("year", "${date.year}")
            intent.putExtra("type", "${type}")
            setResult(RESULT_OK,intent)
            finish()
        }
    }

    /**
     * this fun calendar control
     */
    private fun calendar() {
        val calendar = Calendar.getInstance()

        val month = calendar.get(Calendar.MONTH)
        val year: Int = calendar.get(Calendar.YEAR)
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)

        calendar.set(year, month, day)
        val initialDate = CalendarDate(calendar.time)

        calendar.set(year, month, day)
        val minDate = CalendarDate(calendar.time)

        calendar.set(year, month + 3, day)
        val maxDate = CalendarDate(calendar.time)


        val firstDayOfWeek = Calendar.MONDAY

        binding.calendarView.setupCalendar(
            initialDate = initialDate,
            minDate = minDate,
            maxDate = maxDate,
            selectionMode = CalendarView.SelectionMode.NONE,
            firstDayOfWeek = firstDayOfWeek,
            showYearSelectionView = true
        )
    }
}