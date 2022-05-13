package uz.transport.yagonatransportchiptasi.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.FragmentSearchBinding
import uz.transport.yagonatransportchiptasi.extensions.Extensions.convertEnglishDateToUzbek
import uz.transport.yagonatransportchiptasi.extensions.Extensions.isNotEmpty
import uz.transport.yagonatransportchiptasi.extensions.Extensions.loadData
import uz.transport.yagonatransportchiptasi.extensions.Extensions.loadData2
import uz.transport.yagonatransportchiptasi.extensions.Extensions.setBackgroundChangeIconTint
import uz.transport.yagonatransportchiptasi.extensions.Extensions.setBackgroundChangeIconTintClick
import uz.transport.yagonatransportchiptasi.ui.activity.CalendarActivity
import uz.transport.yagonatransportchiptasi.ui.activity.FromActivity
import uz.transport.yagonatransportchiptasi.ui.activity.ToActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    var departureDateTime: String = ""
    var CURRENT_CLICKED_POSITION = 0
    var LAST_CLICKED_POSITION = -1
    var clicks = arrayOf(true, false, false, false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        CURRENT_CLICKED_POSITION = 0
        binding.apply {
            tvDeparture.text = giveDate().convertEnglishDateToUzbek()
            departureDateTime = giveDate().convertEnglishDateToUzbek()
            /**
             * this is Select the Departure date from the calendar
             */
            llDeparture.setOnClickListener {
                openCalendarActivity(1)

            }
            /**
             * this is Select the Arrival date from the calendar
             */
            llArrival.setOnClickListener {
                openCalendarActivity(2)
            }

            btnSearch.setOnClickListener {
                openPassengerSetupFragment()
            }

            ivClose.setOnClickListener {
                tvArrival.text = "Orqaga"
                ivClose.visibility = View.GONE
            }
            tvFrom.setOnClickListener {
                openFromFragment()
            }
            tvTo.setOnClickListener {
                openToFragment()
            }
        }
        controlTypeClick()
    }

    private fun controlTypeClick() {
        binding.apply {
            tvAll.setOnClickListener {
                CURRENT_CLICKED_POSITION = 0
                manageClicksList(CURRENT_CLICKED_POSITION)
                if (checkIfClicked(CURRENT_CLICKED_POSITION)) {
                    tvAll.setBackgroundChangeIconTintClick()
                    clearEarlierClick(LAST_CLICKED_POSITION)
                    manageClicksList(CURRENT_CLICKED_POSITION)
                    LAST_CLICKED_POSITION = 0
                } else {
                    tvAll.setBackgroundChangeIconTint()
                }
            }

            ivPlane.setOnClickListener {
                CURRENT_CLICKED_POSITION = 1
                manageClicksList(CURRENT_CLICKED_POSITION)
                if (checkIfClicked(CURRENT_CLICKED_POSITION)) {
                    ivPlane.setBackgroundChangeIconTintClick()
                    clearEarlierClick(LAST_CLICKED_POSITION)
                    manageClicksList(CURRENT_CLICKED_POSITION)
                    LAST_CLICKED_POSITION = 1
                } else {
                    ivPlane.setBackgroundChangeIconTint()
                }
            }

            ivTrain.setOnClickListener {
                CURRENT_CLICKED_POSITION = 2
                manageClicksList(CURRENT_CLICKED_POSITION)
                if (checkIfClicked(CURRENT_CLICKED_POSITION)) {
                    ivTrain.setBackgroundChangeIconTintClick()
                    clearEarlierClick(LAST_CLICKED_POSITION)
                    manageClicksList(CURRENT_CLICKED_POSITION)
                    LAST_CLICKED_POSITION = 2
                } else {
                    ivTrain.setBackgroundChangeIconTint()
                }
            }

            ivBus.setOnClickListener {
                CURRENT_CLICKED_POSITION = 3
                manageClicksList(CURRENT_CLICKED_POSITION)
                if (checkIfClicked(CURRENT_CLICKED_POSITION)) {
                    ivBus.setBackgroundChangeIconTintClick()
                    clearEarlierClick(LAST_CLICKED_POSITION)
                    manageClicksList(CURRENT_CLICKED_POSITION)
                    LAST_CLICKED_POSITION = 3
                } else {
                    ivBus.setBackgroundChangeIconTint()
                }
            }
        }
    }

    private fun clearEarlierClick(lastClickedPosition: Int) {
        when (lastClickedPosition) {
            0 -> {
                binding.tvAll.setBackgroundChangeIconTint()
            }
            1 -> {
                binding.ivPlane.setBackgroundChangeIconTint()
            }
            2 -> {
                binding.ivTrain.setBackgroundChangeIconTint()
            }
            3 -> {
                binding.ivBus.setBackgroundChangeIconTint()
            }
        }
    }

    private fun checkIfClicked(position: Int): Boolean = clicks[position]

    private fun manageClicksList(lastClickedPosition: Int) {
        clicks[lastClickedPosition] = !clicks[lastClickedPosition]
    }

    override fun onResume() {
        super.onResume()
        binding.tvFrom.text = loadData(requireContext())
        binding.tvTo.text = loadData2(requireContext())
    }

    private fun openPassengerSetupFragment() {
        if (binding.tvDeparture.isNotEmpty()) {
            when (CURRENT_CLICKED_POSITION) {
                0 -> {
                    openAllOptionsTransport()
                }
                1 -> {
                    openPlaneFragment()
                }
                2 -> {
                    openTrainFragment()
                }
                3 -> {
                    openBusFragment()
                }
                else -> {
                    Toast.makeText(requireContext(), "Transport turini tanlang", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } else {
            Toast.makeText(requireContext(), "Vaqt belgilanmadi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openBusFragment() {
        findNavController().navigate(
            R.id.action_searchFragment_to_busFragment,
            bundleOf("date" to departureDateTime)
        )
    }

    private fun openTrainFragment() {
        findNavController().navigate(
            R.id.action_searchFragment_to_trainDetailsFragment,
            bundleOf("date" to departureDateTime)
        )
    }

    private fun openPlaneFragment() {
        findNavController().navigate(
            R.id.action_searchFragment_to_planeFragment,
            bundleOf("date" to departureDateTime)
        )
    }

    private fun openAllOptionsTransport() {
        if (binding.tvFrom.text.toString().lowercase() == "moskva") {
            findNavController().navigate(
                R.id.action_searchFragment_to_allTransportFragment,
                bundleOf("date" to departureDateTime, "fromMoscow" to "true")
            )
        } else {
            findNavController().navigate(
                R.id.action_searchFragment_to_allTransportFragment,
                bundleOf("date" to departureDateTime, "fromMoscow" to "false")
            )
        }
    }

    private fun openFromFragment() {
        val intent = Intent(requireContext(), FromActivity::class.java)
        startActivity(intent)
    }

    private fun openToFragment() {
        val intent = Intent(requireContext(), ToActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("SimpleDateFormat")
    fun giveDate(): String {
        val cal: Calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("EEE,MMM d")
        return sdf.format(cal.time)
    }

    private fun openCalendarActivity(type: Int) {
        val intent = Intent(requireContext(), CalendarActivity::class.java)
        intent.putExtra("locationStart", loadData(requireContext()))
        intent.putExtra("locationEnd", loadData2(requireContext()))
        intent.putExtra("type", "$type")
        postActivity.launch(intent)
    }

    @SuppressLint("SimpleDateFormat")
    var postActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val day = data!!.getStringExtra("day")!!.toInt()
            val month = data.getStringExtra("month")!!
            val year = data.getStringExtra("year")!!
            val type = data.getStringExtra("type")!!

            /**
             * this is change the time format
             */
            val originalFormat = SimpleDateFormat("dd MM yyyy")
            val targetFormat = SimpleDateFormat("EEE,MMM d")
            val date: Date
            try {
                date = originalFormat.parse("$day $month $year")
                if (type.toInt() == 1) {
                    binding.tvDeparture.text =
                        targetFormat.format(date).toString().convertEnglishDateToUzbek()
                    departureDateTime = binding.tvDeparture.text.toString()
                } else {
                    binding.tvArrival.text =
                        targetFormat.format(date).toString().convertEnglishDateToUzbek()
                    binding.ivClose.visibility = View.VISIBLE
                }
            } catch (ex: ParseException) {

            }

        }
    }
}