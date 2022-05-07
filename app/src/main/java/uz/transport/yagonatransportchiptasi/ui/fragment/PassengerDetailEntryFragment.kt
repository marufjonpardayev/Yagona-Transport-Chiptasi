package uz.transport.yagonatransportchiptasi.ui.fragment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.FragmentPassengerDetailEntryBinding
import uz.transport.yagonatransportchiptasi.ui.activity.MainActivity
import java.util.*


class PassengerDetailEntryFragment : Fragment() {

    lateinit var binding: FragmentPassengerDetailEntryBinding
    private var mDateSetListener: OnDateSetListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger_detail_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPassengerDetailEntryBinding.bind(view)

        binding.apply {
            edtBirthDate.setOnClickListener {
                showCalendarDialog()
            }
        }
    }

    private fun showCalendarDialog() {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            mDateSetListener,
            year,
            month,
            day
        )

        datePickerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        datePickerDialog.show()

        getSelectedDate()
    }

    private fun getSelectedDate() {
        mDateSetListener = OnDateSetListener { _, year, month, day ->
            val date = "$day/${month + 1}/$year"
            binding.edtBirthDate.setText(date)
        }
    }
}